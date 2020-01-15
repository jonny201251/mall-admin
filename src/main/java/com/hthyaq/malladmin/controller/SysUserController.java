package com.hthyaq.malladmin.controller;


import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.common.exception.MyExceptionNotCatch;
import com.hthyaq.malladmin.common.utils.UserCodecUtils;
import com.hthyaq.malladmin.model.entity.SysUser;
import com.hthyaq.malladmin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户表、商城会员表 前端控制器
 * </p>
 *
 * @author zhangqiang
 * @since 2020-01-14
 */
@RestController
@ResponseResult
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    //验证用户是否已登录
    @GetMapping("/auth/verify")
    public SysUser verify(HttpSession httpSession) {
        //取出登录用户
        SysUser user = (SysUser) httpSession.getAttribute("user");
        if (user == null) {
            throw new MyExceptionNotCatch("用户未登录");
        }
        return user;
    }
    //测试用的
    @GetMapping("/sysUser/add")
    public boolean add(HttpSession httpSession, String loginName, String loginPassword) {
        boolean flag;
        SysUser user = new SysUser();
        user.setLoginName(loginName);
        // 对密码进行加密
        String salt = UserCodecUtils.generateSalt();
        user.setSalt(salt);
        user.setLoginPassword(UserCodecUtils.md5Hex(loginPassword, salt));
        flag = sysUserService.save(user);
        //将用户放入session中
        httpSession.setAttribute("user", user);
        return flag;
    }

    @PostMapping("/sysUser/register")
    public boolean register(@RequestBody SysUser user) {
        boolean flag;
        // 对密码进行加密
        String salt = UserCodecUtils.generateSalt();
        user.setSalt(salt);
        user.setLoginPassword(UserCodecUtils.md5Hex(user.getLoginPassword(), salt));
        flag = sysUserService.save(user);
        return flag;
    }
}
