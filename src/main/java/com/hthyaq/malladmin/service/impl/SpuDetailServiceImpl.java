package com.hthyaq.malladmin.service.impl;

import com.hthyaq.malladmin.model.entity.SpuDetail;
import com.hthyaq.malladmin.mapper.SpuDetailMapper;
import com.hthyaq.malladmin.service.SpuDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品的spu的详细信息，和spu是一对一的关系 服务实现类
 * </p>
 *
 * @author zhangqiang
 * @since 2019-12-24
 */
@Service
public class SpuDetailServiceImpl extends ServiceImpl<SpuDetailMapper, SpuDetail> implements SpuDetailService {

}
