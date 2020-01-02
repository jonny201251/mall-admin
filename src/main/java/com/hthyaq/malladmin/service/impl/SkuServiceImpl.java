package com.hthyaq.malladmin.service.impl;

import com.hthyaq.malladmin.model.entity.Sku;
import com.hthyaq.malladmin.mapper.SkuMapper;
import com.hthyaq.malladmin.service.SkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品的sku表,该表表示具体的商品实体 服务实现类
 * </p>
 *
 * @author zhangqiang
 * @since 2019-12-24
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

}
