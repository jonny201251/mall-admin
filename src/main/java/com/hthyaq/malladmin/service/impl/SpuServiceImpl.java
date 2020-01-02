package com.hthyaq.malladmin.service.impl;

import com.hthyaq.malladmin.model.entity.Spu;
import com.hthyaq.malladmin.mapper.SpuMapper;
import com.hthyaq.malladmin.service.SpuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品的spu表，该表描述的是一个抽象性的商品 服务实现类
 * </p>
 *
 * @author zhangqiang
 * @since 2019-12-24
 */
@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {

}
