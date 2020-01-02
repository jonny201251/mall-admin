package com.hthyaq.malladmin.common.utils.tree;

import lombok.Data;

import java.util.List;

/**
 * antd需要的treedata数据结构
 * https://ant.design/components/tree-select-cn/
 */
@Data
public class TreeView {
    //名称
    private String title;
    //id
    private Integer key;
    private List<TreeView> children;
}