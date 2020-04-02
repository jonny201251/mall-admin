package com.hthyaq.malladmin.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.common.exception.MyExceptionNotCatch;
import com.hthyaq.malladmin.common.utils.UserCodecUtils;
import com.hthyaq.malladmin.model.entity.Company;
import com.hthyaq.malladmin.model.entity.SysUser;
import com.hthyaq.malladmin.model.vo.SysUserPassword;
import com.hthyaq.malladmin.service.CompanyService;
import com.hthyaq.malladmin.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    @Autowired
    private CompanyService companyService;

    @GetMapping("/sysUser/list")
    public IPage<SysUser> list(String json) {
        //字符串解析成java对象
        JSONObject jsonObject = JSON.parseObject(json);
        //从对象中获取值
        Integer currentPage = jsonObject.getInteger("currentPage");
        Integer pageSize = jsonObject.getInteger("pageSize");
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        return sysUserService.page(new Page<>(currentPage, pageSize), queryWrapper);
    }

    //验证用户是否已登录
    @GetMapping("/auth/verify")
    public SysUser verify(HttpSession httpSession) {
        //取出登录用户
        SysUser user = (SysUser) httpSession.getAttribute("user");
        if (user == null) {
            throw new RuntimeException("用户未登录");
        }
        return user;
    }

    //前台登录
    @PostMapping("/sysUser/login")
    public SysUser login(HttpSession httpSession, @RequestParam("username") String username, @RequestParam("password") String password) {
        //根据用户名查询用户
        List<SysUser> list = sysUserService.list(new QueryWrapper<SysUser>().eq("login_name", username));
        //校验用户名
        if (list.size() != 1) {
            throw new RuntimeException("用户名错误!");
        }
        SysUser user = list.get(0);
        // 校验密码
        if (!StringUtils.equals(user.getLoginPassword(), UserCodecUtils.md5Hex(password, user.getSalt()))) {
            throw new RuntimeException("密码错误!");
        }
        //设置用户的公司
        Company company = companyService.getById(user.getCompanyId());
        user.setCompany(company);
        //将用户放入session中
        httpSession.setAttribute("user", user);
        return user;
    }

    @GetMapping("/sysUser/logout")
    public boolean logout(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return true;
    }

    //后台登录
    @PostMapping("/admin/login")
    @ResponseResult
    public SysUser adminLogin(HttpSession httpSession, @RequestBody SysUser sysUser) {
        String loginName = sysUser.getLoginName();
        String loginPassword = sysUser.getLoginPassword();
        List<SysUser> list = sysUserService.list(new QueryWrapper<SysUser>().eq("login_name", loginName));
        if (list.size() != 1) {
            throw new RuntimeException("用户名或密码错误");
        }
        SysUser user = list.get(0);
        if (!StringUtils.equals(user.getLoginPassword(), UserCodecUtils.md5Hex(loginPassword, user.getSalt()))) {
            throw new RuntimeException("密码错误!");
        }
        //设置用户的公司
        Company company = companyService.getById(user.getCompanyId());
        user.setCompany(company);
        //将用户放入session中
        httpSession.setAttribute("user", user);
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

    @PostMapping("/sysUser/changePassword")
    public boolean changePassword(HttpSession httpSession, @RequestBody SysUserPassword sysUserPassword) {
        SysUser user = (SysUser) httpSession.getAttribute("user");
        if (StringUtils.equals(user.getLoginPassword(), UserCodecUtils.md5Hex(sysUserPassword.getLoginPassword(), user.getSalt()))) {
            user.setLoginPassword(UserCodecUtils.md5Hex(sysUserPassword.getNewPassword(), user.getSalt()));
            sysUserService.updateById(user);
            return true;
        } else {
            throw new RuntimeException("原密码错误!");
        }
    }
}
