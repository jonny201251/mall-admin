package com.hthyaq.malladmin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品的spu表，该表描述的是一个抽象性的商品
 * </p>
 *
 * @author zhangqiang
 * @since 2019-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Spu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * spu id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 子标题
     */
    private String subTitle;

    /**
     * 商品的图片，多个图片以‘,’分割，先把sku中的所有图片用这个代替
     */
    private String images;

    /**
     * 商品的临时价格
     */
    private Double tmpPrice;

    /**
     * 商品的临时库存
     */
    private Long tmpStock;

    /**
     * 商品所属类目的id
     */
    private Integer categoryId;

    /**
     * 商品所属品牌id
     */
    private Integer brandId;

    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdateTime;


}
