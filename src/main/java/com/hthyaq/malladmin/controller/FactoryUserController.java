package com.hthyaq.malladmin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.common.utils.UserCodecUtils;
import com.hthyaq.malladmin.model.entity.Company;
import com.hthyaq.malladmin.model.entity.ReceiveAddress;
import com.hthyaq.malladmin.model.entity.SysUser;
import com.hthyaq.malladmin.service.CompanyService;
import com.hthyaq.malladmin.service.ReceiveAddressService;
import com.hthyaq.malladmin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/factoryUser")
@ResponseResult
public class FactoryUserController {
    @Autowired
    SysUserService sysUserService;
    @Autowired
    CompanyService companyService;
    @Autowired
    ReceiveAddressService receiveAddressService;

    @GetMapping("/list")
    public IPage<SysUser> list(String json) {
        //字符串解析成java对象
        JSONObject jsonObject = JSON.parseObject(json);
        //从对象中获取值
        Integer currentPage = jsonObject.getInteger("currentPage");
        Integer pageSize = jsonObject.getInteger("pageSize");
        //取出159分厂
        List<Company> list = companyService.list(new QueryWrapper<Company>().eq("type", 1));
        Map<Integer, Company> map = Maps.newHashMap();
        for (Company company : list) {
            map.put(company.getId(), company);
        }
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("company_id", list.stream().map(Company::getId).collect(Collectors.toList()));
        IPage<SysUser> userIPage = sysUserService.page(new Page<>(currentPage, pageSize), queryWrapper);
        //设置公司
        List<SysUser> userList = userIPage.getRecords();
        for (SysUser user : userList) {
            user.setCompany(map.get(user.getCompanyId()));
        }
        return userIPage;
    }

    @PostMapping("/add")
    public boolean add(@RequestBody SysUser user) {
        boolean flag = false;
        String salt = UserCodecUtils.generateSalt();
        user.setSalt(salt);
        user.setLoginPassword(UserCodecUtils.md5Hex(user.getLoginPassword(), salt));
        flag = sysUserService.save(user);
        if (flag) {
            //往收货人地址中添加一条信息
            ReceiveAddress receiveAddress = new ReceiveAddress();
            receiveAddress.setRealName(user.getLoginName());
            receiveAddress.setMobile(user.getMobile());
            receiveAddress.setAddress("北京市丰台区东王佐北路9号");
            receiveAddress.setZip("100074");
            receiveAddress.setIsDefault(1);
            receiveAddress.setUserId(user.getId());
            flag = flag && receiveAddressService.save(receiveAddress);
        }

        return flag;
    }

    @GetMapping("/getById")
    public SysUser getById(Integer id) {
        return sysUserService.getById(id);
    }

    @PostMapping("/edit")
    public boolean edit(@RequestBody SysUser user) {
        String loginPassword = user.getLoginPassword();
        try {
            Integer.parseInt(loginPassword);
            //修改了密码
            String salt = UserCodecUtils.generateSalt();
            user.setSalt(salt);
            user.setLoginPassword(UserCodecUtils.md5Hex(user.getLoginPassword(), salt));
        } catch (Exception e) {
        }
        return sysUserService.updateById(user);
    }

}
