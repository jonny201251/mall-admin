package com.hthyaq.malladmin.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.hthyaq.malladmin.common.utils.UserCodecUtils;
import com.hthyaq.malladmin.model.entity.SysUser;
import com.hthyaq.malladmin.model.vo.FactoryUserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryUserService {
    @Autowired
    SysUserService sysUserService;

    public boolean save(FactoryUserView factoryUserView) {
        List<SysUser> userDBList = Lists.newArrayList();
        List<SysUser> userViewList = factoryUserView.getUsers().getDataSource();
        if (ObjectUtil.length(userViewList) > 0) {
            for (SysUser user : userViewList) {
                if (!Strings.isNullOrEmpty(user.getLoginName()) && !Strings.isNullOrEmpty(user.getLoginPassword())) {
                    // 对密码进行加密
                    String salt = UserCodecUtils.generateSalt();
                    user.setSalt(salt);
                    user.setLoginPassword(UserCodecUtils.md5Hex(user.getLoginPassword(), salt));
                    user.setCompanyId(factoryUserView.getCompanyId());
                    userDBList.add(user);
                }
            }
            return sysUserService.saveBatch(userDBList);
        }
        return false;
    }

    public boolean edit(FactoryUserView factoryUserView) {
        boolean flag = false;
        //先删除，后插入
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_id", factoryUserView.getCompanyId());
        flag = sysUserService.remove(queryWrapper);
        if (flag) {
            this.save(factoryUserView);
        }
        return flag;
    }
}
