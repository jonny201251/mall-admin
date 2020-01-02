package com.hthyaq.malladmin.service;

import com.hthyaq.malladmin.model.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hthyaq.malladmin.model.vo.BrandView;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 商品的品牌表 服务类
 * </p>
 *
 * @author zhangqiang
 * @since 2019-09-25
 */
public interface BrandService extends IService<Brand> {

    boolean add(BrandView brandView, MultipartFile imageFile) throws IOException;

    boolean edit(BrandView brandView, MultipartFile imageFile) throws IOException;

    boolean delete(Integer id);
}
