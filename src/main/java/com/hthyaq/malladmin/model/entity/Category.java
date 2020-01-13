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
 * 商品的类目表
 * </p>
 *
 * @author zhangqiang
 * @since 2019-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类目名称
     */
    private String name;

    /**
     * 使用状态：0.禁用,1.正常
     */
    private Integer status;

    /**
     * 父类目id,顶级类目填0
     */
    private Integer pid;

    /**
     * 排序
     */
    private Double sort;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 备注
     */
    private String comment;

    /*
    对应的规格模板：0简单规格，1复杂规格
     */
    private Integer template;
}
