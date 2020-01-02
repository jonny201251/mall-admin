package com.hthyaq.malladmin.model.responseResult;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hthyaq.malladmin.model.bean.PageData;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;

//返回的结果
@Data
public class GlobalResponseResult implements Serializable {
    private static final long serialVersionUID = 5876920254332715285L;
    private Integer code;
    private String msg;
    private Object data;

    public static GlobalResponseResult success() {
        ResultCode code = ResultCode.SUCCESS;
        GlobalResponseResult globalResponseResult = new GlobalResponseResult();
        globalResponseResult.setCode(code.getCode());
        globalResponseResult.setMsg(code.getMsg());
        return globalResponseResult;
    }

    public static GlobalResponseResult success(Object data) {
        GlobalResponseResult globalResponseResult = success();
        if (data instanceof IPage) {
            IPage<T> page = (IPage<T>) data;
            int pageSize = (int) page.getSize();
            int total = (int) page.getTotal();
            //计算总页数
            int totalPage = total / pageSize + ((total % pageSize == 0) ? 0 : 1);
            PageData<T> pageData = new PageData<>((int) page.getCurrent(), pageSize, total, totalPage, page.getRecords());
            globalResponseResult.setData(pageData);
        } else if (data instanceof Boolean) {
            boolean b = (Boolean) data;
            if (!b) globalResponseResult = GlobalResponseResult.fail();
        } else {
            globalResponseResult.setData(data);
        }

        return globalResponseResult;
    }

    public static GlobalResponseResult fail() {
        ResultCode code = ResultCode.FAIL;
        GlobalResponseResult globalResponseResult = new GlobalResponseResult();
        globalResponseResult.setCode(code.getCode());
        globalResponseResult.setMsg(code.getMsg());
        return globalResponseResult;
    }

    public static GlobalResponseResult fail(String msg) {
        GlobalResponseResult globalResponseResult = new GlobalResponseResult();
        globalResponseResult.setCode(0);
        globalResponseResult.setMsg(msg);
        return globalResponseResult;
    }

    public static GlobalResponseResult fail(ResultCode code) {
        GlobalResponseResult globalResponseResult = new GlobalResponseResult();
        globalResponseResult.setCode(code.getCode());
        globalResponseResult.setMsg(code.getMsg());
        return globalResponseResult;
    }
}
