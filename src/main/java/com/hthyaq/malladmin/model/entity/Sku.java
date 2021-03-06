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
 * 商品的sku表,该表表示具体的商品实体
 * </p>
 *
 * @author zhangqiang
 * @since 2019-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Sku implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * sku id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * spu id
     */
    private Long spuId;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品的图片，多个图片以‘,’分割
     */
    private String images;

    /**
     * 销售价格，单位为分
     */
    private Double price;

    private Integer stock;

    /**
     * 特有规格属性在spu属性模板中的对应下标组合
     */
    private String indexes;

    /**
     * sku的特有规格参数键值对，json格式，反序列化时请使用linkedHashMap，保证有序
     */
    private String skuSpec;

    /**
     * 是否上架，0下架，1上架
     */
    private Integer saleable;

    /**
     * 是否有效，0无效，1有效
     */
    private Integer valid;

    /**
     * 商品货号，指卖家管理商品的编号，买家不可见
     */
    private String skuCode;

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
