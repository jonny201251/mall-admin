package com.hthyaq.malladmin.service.impl;

import com.hthyaq.malladmin.model.entity.Stock;
import com.hthyaq.malladmin.mapper.StockMapper;
import com.hthyaq.malladmin.service.StockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品的库存表，代表库存，秒杀库存等信息 服务实现类
 * </p>
 *
 * @author zhangqiang
 * @since 2019-12-18
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

}
