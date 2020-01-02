package com.hthyaq.malladmin.common.utils.tree;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;


public class TreeUtil {
    //组装category和specification_group的结构树，用于商品管理->规格参数名称


    public static <T> List<TreeView> get(List<T> list) {
        String columns = "id,pid,name";
        return get(list, columns);
    }

    /**
     * @param list    原始数据
     * @param columns 和id、pid、name对应的字段名称
     */
    public static <T> List<TreeView> get(List<T> list, String columns) {
        if (CollectionUtil.isEmpty(list)) throw new RuntimeException("集合为空！");
        //顶级节点
        List<TreeDto> rootList = Lists.newArrayList();
        //子节点
        List<TreeDto> childList = Lists.newArrayList();
        //遍历
        for (T item : list) {
            String[] tmp = columns.split(",");
            //通过反射获取出item中的id、name、pid的属性值
            Integer id = (Integer) ReflectUtil.getFieldValue(item, tmp[0]);
            Integer pid = (Integer) ReflectUtil.getFieldValue(item, tmp[1]);
            String name = (String) ReflectUtil.getFieldValue(item, tmp[2]);
            if (pid == 0) {
                rootList.add(new TreeDto(id, pid, name));
            } else {
                childList.add(new TreeDto(id, pid, name));
            }
        }
        return getTreeSelect(rootList, childList);
    }

    private static List<TreeView> getTreeSelect(List<TreeDto> rootList, List<TreeDto> childList) {
        //声明一个map，用来过滤已操作过的数据
        Map<Integer, Integer> map = Maps.newHashMapWithExpectedSize(childList.size());

        List<TreeView> treeViewList = Lists.newArrayList();
        rootList.forEach(tmp -> {
            TreeView root = new TreeView();
            convert(tmp, root);
            treeViewList.add(root);
            getChildren(root, childList, map);
        });
        return treeViewList;
    }

    private static void getChildren(TreeView root, List<TreeDto> childList, Map<Integer, Integer> map) {
        List<TreeView> children = Lists.newArrayList();
        childList.stream().filter(tmp -> !map.containsKey(tmp.getId()))
                .filter(tmp -> root.getKey().equals(tmp.getPid()))
                .forEach(tmp -> {
                    //记录已遍历的节点
                    map.put(tmp.getId(), tmp.getPid());
                    //转换和存储
                    TreeView treeView = new TreeView();
                    convert(tmp, treeView);
                    children.add(treeView);
                    //递归
                    getChildren(treeView, childList, map);

                });
        root.setChildren(children);
    }

    //将TreeDto转换为TreeView
    private static void convert(TreeDto treeDto, TreeView treeView) {
        treeView.setTitle(treeDto.getName());
        treeView.setKey(treeDto.getId());
    }
}
