package com.hthyaq.malladmin.controller.app;

import com.alibaba.fastjson.JSON;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.common.exception.MyExceptionNotCatch;
import com.hthyaq.malladmin.model.bean.Cart;
import com.hthyaq.malladmin.model.entity.SysUser;
import com.hthyaq.malladmin.service.CartService;
import com.hthyaq.malladmin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/cart")
@ResponseResult
public class AppCartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    private static final String KEY_PREFIX = "cart:uid:";

    @GetMapping("/add")
    public boolean add(Integer userId, Cart cart) {
        //取出登录用户
        SysUser user = sysUserService.getById(userId);
        cartService.skuAddCart(user, cart);
        return true;
    }

    @GetMapping("/list")
    public List<Cart> list(Integer userId) {
        //取出登录用户
        SysUser user = sysUserService.getById(userId);
        String key = KEY_PREFIX + user.getId();
        if (!redisTemplate.hasKey(key)) {
            throw new RuntimeException("SKU商品不存在！");
        }

        // 获取登录用户的所有购物车
        BoundHashOperations<String, Object, Object> operation = redisTemplate.boundHashOps(key);

        List<Cart> carts = operation.values().stream()
                .map(o -> JSON.parseObject(o.toString(), Cart.class))
                .collect(Collectors.toList());
        return carts;
    }

    @GetMapping("/updateCartNum")
    public boolean updateCartNum(Integer userId, Long skuId, Integer num) {
        //取出登录用户
        SysUser user = sysUserService.getById(userId);
        String key = KEY_PREFIX + user.getId();

        // 获取登录用户的所有购物车
        BoundHashOperations<String, Object, Object> operation = redisTemplate.boundHashOps(key);

        // 查询
        String json = operation.get(skuId.toString()).toString();
        Cart cart = JSON.parseObject(json, Cart.class);
        cart.setNum(num);

        // 写回redis
        operation.put(skuId.toString(), JSON.toJSONString(cart));
        return true;
    }

    @GetMapping("/delete")
    public boolean delete(Integer userId, Long skuId) {
        //取出登录用户
        SysUser user = sysUserService.getById(userId);
        String key = KEY_PREFIX + user.getId();
        // 删除
        redisTemplate.opsForHash().delete(key, skuId.toString());
        return true;
    }
}
