package com.hthyaq.malladmin.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.model.entity.ReceiveAddress;
import com.hthyaq.malladmin.model.entity.SysUser;
import com.hthyaq.malladmin.service.ReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 收货地址 前端控制器
 * </p>
 *
 * @author zhangqiang
 * @since 2020-03-04
 */
@RestController
@RequestMapping("/receiveAddress")
@ResponseResult
public class ReceiveAddressController {
    @Autowired
    ReceiveAddressService receiveAddressService;

    @GetMapping("/list")
    public IPage<ReceiveAddress> list(HttpSession httpSession, String json) {
        //字符串解析成java对象
        JSONObject jsonObject = JSON.parseObject(json);
        //从对象中获取值
        Integer currentPage = jsonObject.getInteger("currentPage");
        Integer pageSize = jsonObject.getInteger("pageSize");
        QueryWrapper<ReceiveAddress> queryWrapper = new QueryWrapper<>();
        //从session中取出用户
        SysUser user = (SysUser) httpSession.getAttribute("user");
        queryWrapper.eq("user_id", user.getId());
        return receiveAddressService.page(new Page<>(currentPage, pageSize), queryWrapper);
    }

    @PostMapping("/add")
    public boolean add(HttpSession httpSession, @RequestBody ReceiveAddress receiveAddress) {
        //从session中取出用户
        SysUser user = (SysUser) httpSession.getAttribute("user");
        receiveAddress.setUserId(user.getId());
        return receiveAddressService.save(receiveAddress);
    }

    @GetMapping("/getById")
    public ReceiveAddress getById(Integer id) {
        return receiveAddressService.getById(id);
    }

    @PostMapping("/edit")
    public boolean edit(@RequestBody ReceiveAddress receiveAddress) {
        return receiveAddressService.updateById(receiveAddress);
    }

    @GetMapping("/all")
    public List<ReceiveAddress> getAll(HttpSession httpSession) {
        SysUser user = (SysUser) httpSession.getAttribute("user");
        return receiveAddressService.list(new QueryWrapper<ReceiveAddress>().eq("user_id", user.getId()));
    }
}
