package com.hthyaq.malladmin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 159分厂的订单金额限制
 * </p>
 *
 * @author zhangqiang
 * @since 2020-01-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MoneyLimit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 季度，0：一季度，1：二季度，2：三季度，3：四季度
     */
    private Boolean quarter;

    /**
     * 限制的下单金额
     */
    private Double money;

    /**
     * 159分厂的id
     */
    private Integer companyId;


}
