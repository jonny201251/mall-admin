package com.hthyaq.malladmin.service;

import com.alibaba.fastjson.JSON;
import com.hthyaq.malladmin.model.bean.Cart;
import com.hthyaq.malladmin.model.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX = "cart:uid:";

    public void skuAddCart(SysUser user, Cart cart) {
        // redis存储的结构是一个Map<String,Map<String,String>>,第一个key是用户的key，第二个key是商品的key，value是商品信息
        String key = KEY_PREFIX + user.getId();
        String hashKey = cart.getSkuId().toString();
        BoundHashOperations<String, Object, Object> operation = redisTemplate.boundHashOps(key);

        if (operation.hasKey(hashKey)) {
            // 如果存在  商品数量新增,新增之前先取出商品信息
            String json = operation.get(hashKey).toString();
            Cart cacheCart = JSON.parseObject(json, Cart.class);
            cacheCart.setNum(cacheCart.getNum() + cart.getNum());
            operation.put(hashKey, JSON.toJSONString(cacheCart));
        } else {
            // 如果不存在 新增
            operation.put(hashKey, JSON.toJSONString(cart));
        }
    }

    public void batchAddCart(SysUser user, List<Cart> carts) {
        for (Cart cart : carts) {
            this.skuAddCart(user, cart);
        }
    }
}
