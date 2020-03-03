package com.hthyaq.malladmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.hthyaq.malladmin.common.utils.treeSelect.TreeSelectUtil;
import com.hthyaq.malladmin.common.utils.treeSelect.TreeSelectView;
import com.hthyaq.malladmin.model.entity.Category;
import com.hthyaq.malladmin.model.entity.Spu;
import com.hthyaq.malladmin.model.vo.IndexView;
import com.hthyaq.malladmin.model.vo.SpuView;
import com.hthyaq.malladmin.service.CategoryService;
import com.hthyaq.malladmin.service.SpuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class FrontController {
    @Autowired
    SpuService spuService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/cart.html")
    public String cart() {
        return "front/cart";
    }

    @GetMapping("/foot.html")
    public String foot() {
        return "front/foot";
    }

    @GetMapping("/copyright.html")
    public String copyright() {
        return "front/copyright";
    }

    @GetMapping("/shortcut.html")
    public String shortcut() {
        return "front/shortcut";
    }

    @GetMapping("/item.html")
    public String item(Long id, Model model) {
        // 查询数据模型
        Map<String, Object> attributes = spuService.getItemData(id);
        // 准备数据模型
        model.addAllAttributes(attributes);
        // 返回视图
        return "front/item-" + attributes.get("specType");
    }

    @GetMapping("/getOrderInfo.html")
    public String getOrderInfo() {
        return "front/getOrderInfo";
    }

    @GetMapping("/login.html")
    public String login() {
        return "front/login";
    }

    @GetMapping("/login-manage.html")
    public String loginManage() {
        return "front/login-manage";
    }

    @GetMapping("/order.html")
    public String order() {
        return "front/order";
    }

    @GetMapping("/register.html")
    public String register() {
        return "front/register";
    }

    //第一次的搜索
    @GetMapping("/search.html")
    public String search() {
        return "front/search";
    }

    @GetMapping("/success.html")
    public String success() {
        return "front/success";
    }

    @GetMapping("/side.html")
    public String side() {
        return "front/side";
    }

    @GetMapping("/index.html")
    public String index(Model model) {
        //获取商城首页的数据
        IndexView indexView = new IndexView();
        //
        List<Category> list = categoryService.list(new QueryWrapper<Category>().eq("status", 1).orderByAsc("sort").orderByDesc("id"));
        List<TreeSelectView> categoryList = TreeSelectUtil.get(list);
        indexView.setCategoryList(categoryList);
        //
        List<Spu> scrollTmp = spuService.list(new QueryWrapper<Spu>().in("id", 3, 34, 16));
        for (int i = 0; i < scrollTmp.size(); i++) {
            Spu spu=scrollTmp.get(i);
            SpuView scroll = new SpuView();
            scroll.setSpuId(spu.getId());
            scroll.setImage(StringUtils.substringAfter(spu.getImages(), ","));
            if(i==0){
                indexView.setScroll1(scroll);
            }else if(i==1){
                indexView.setScroll2(scroll);
            }else{
                indexView.setScroll3(scroll);
            }
        }
        //先做3个楼层，每个楼层显示5个商品
        List<Spu> floorList = Lists.newArrayList();
        List<TreeSelectView> children = categoryList.get(0).getChildren();
        int count = 0;
        for (int i = 0; i < children.size(); i++) {
            if (count == 3) break;
            TreeSelectView tmp = children.get(i);
            //二级分类的id
            Integer cid2 = tmp.getKey();
            List<Category> categoryTmp = categoryService.list(new QueryWrapper<Category>().eq("pid", cid2));
            List<Integer> categoryIdList = categoryTmp.stream().map(Category::getId).collect(Collectors.toList());
            IPage<Spu> page = spuService.page(new Page<>(1, 5), new QueryWrapper<Spu>().in("category_id", categoryIdList));
            int size = page.getRecords().size();
            if (size >= 5) {
                List<Spu> recordList=page.getRecords();
                List<SpuView> floorData= Lists.newArrayList();
                for (Spu spu : recordList) {
                    SpuView floorSpu = new SpuView();
                    floorSpu.setSpuId(spu.getId());
                    floorSpu.setImage(StringUtils.substringBefore(spu.getImages(), ","));
                    floorData.add(floorSpu);
                }
                if (count == 0) {
                    indexView.setFloor1(tmp);
                    indexView.setFloor1Data(floorData);
                } else if (count == 1) {
                    indexView.setFloor2(tmp);
                    indexView.setFloor2Data(floorData);
                } else if (count == 2) {
                    indexView.setFloor3(tmp);
                    indexView.setFloor3Data(floorData);
                }
                count++;
            }
        }

        model.addAttribute(indexView);
        return "front/index";
    }
}
