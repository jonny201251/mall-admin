package com.hthyaq.malladmin.service.impl;

import com.hthyaq.malladmin.model.entity.Company;
import com.hthyaq.malladmin.mapper.CompanyMapper;
import com.hthyaq.malladmin.service.CompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 公司表，包括公司、商家、供应商 服务实现类
 * </p>
 *
 * @author zhangqiang
 * @since 2020-01-17
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

}
