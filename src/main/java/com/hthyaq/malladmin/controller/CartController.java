package com.hthyaq.malladmin.controller;

import com.alibaba.fastjson.JSON;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.common.exception.MyExceptionNotCatch;
import com.hthyaq.malladmin.model.bean.Cart;
import com.hthyaq.malladmin.model.entity.SysUser;
import com.hthyaq.malladmin.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
@ResponseResult
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    private static final String KEY_PREFIX = "cart:uid:";

    @PostMapping("/batch")
    public boolean batch(HttpSession httpSession, @RequestBody List<Cart> carts) {
        //取出登录用户
        SysUser user = (SysUser) httpSession.getAttribute("user");
        cartService.batchAddCart(user, carts);
        return true;
    }

    @PostMapping("/add")
    public boolean add(HttpSession httpSession, @RequestBody Cart cart) {
        //取出登录用户
        SysUser user = (SysUser) httpSession.getAttribute("user");
        cartService.skuAddCart(user, cart);
        return true;
    }

    @GetMapping("/list")
    public List<Cart> list(HttpSession httpSession) {
        //取出登录用户
        SysUser user = (SysUser) httpSession.getAttribute("user");
        String key = KEY_PREFIX + user.getId();
        if (!redisTemplate.hasKey(key)) {
            throw new MyExceptionNotCatch("SKU商品不存在！");
        }

        // 获取登录用户的所有购物车
        BoundHashOperations<String, Object, Object> operation = redisTemplate.boundHashOps(key);

        List<Cart> carts = operation.values().stream()
                .map(o -> JSON.parseObject(o.toString(), Cart.class))
                .collect(Collectors.toList());
        return carts;
    }

    @GetMapping("/updateCartNum")
    public boolean updateCartNum(HttpSession httpSession, Long skuId, Integer num) {
        //取出登录用户
        SysUser user = (SysUser) httpSession.getAttribute("user");
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
    public boolean delete(HttpSession httpSession, Long skuId) {
        //取出登录用户
        SysUser user = (SysUser) httpSession.getAttribute("user");
        String key = KEY_PREFIX + user.getId();
        // 删除
        redisTemplate.opsForHash().delete(key, skuId.toString());
        return true;
    }
}
