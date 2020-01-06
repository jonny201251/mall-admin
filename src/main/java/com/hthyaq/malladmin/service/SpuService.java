package com.hthyaq.malladmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hthyaq.malladmin.model.entity.Spu;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 商品的spu表，该表描述的是一个抽象性的商品 服务类
 * </p>
 *
 * @author zhangqiang
 * @since 2019-12-24
 */
public interface SpuService extends IService<Spu> {
    //获取商品的规格模板
    public String getSpecType(Integer categoryId);
    //添加商品
    public Boolean add(MultipartFile[] images, String description, String form, String genericSpec) throws IOException;
}
