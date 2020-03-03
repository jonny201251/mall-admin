package com.hthyaq.malladmin.model.vo;

import com.hthyaq.malladmin.common.utils.treeSelect.TreeSelectView;
import lombok.Data;

import java.util.List;

@Data
public class IndexView {
    //左边的分类
    List<TreeSelectView> categoryList;
    //右边的轮播图
    SpuView scroll1;
    SpuView scroll2;
    SpuView scroll3;
    //楼层
    TreeSelectView floor1;
    TreeSelectView floor2;
    TreeSelectView floor3;
    List<SpuView> floor1Data;
    List<SpuView> floor2Data;
    List<SpuView> floor3Data;
}
