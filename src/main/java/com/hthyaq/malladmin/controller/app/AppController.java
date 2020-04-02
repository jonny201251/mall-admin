package com.hthyaq.malladmin.controller.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.hthyaq.malladmin.common.annotation.ResponseResult;
import com.hthyaq.malladmin.common.constants.GlobalConstants;
import com.hthyaq.malladmin.common.utils.StringLastUtil;
import com.hthyaq.malladmin.common.utils.UserCodecUtils;
import com.hthyaq.malladmin.model.entity.*;
import com.hthyaq.malladmin.model.vo.AppSpuView;
import com.hthyaq.malladmin.service.*;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app")
@ResponseResult
//手机app的访问地址
public class AppController {
    @Autowired
    SpuService spuService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ReceiveAddressService receiveAddressService;
    @Autowired
    MoneyLimitService moneyLimitService;

    @GetMapping("/lunbo")
    public List<AppSpuView> lunbo() {
        List<AppSpuView> list = Lists.newArrayList();
        List<Spu> spuList = spuService.list(new QueryWrapper<Spu>().in("id", 3, 16, 34));
        for (Spu spu : spuList) {
            AppSpuView spuView = new AppSpuView();
            spuView.setSpuId(spu.getId());
            spuView.setImage(GlobalConstants.HOST_PATH + StringLastUtil.get(spu.getImages()));
            list.add(spuView);
        }
        return list;
    }

    @GetMapping("/totalPage")
    public Integer totalPage(@RequestParam(defaultValue = "") String keyword, Integer currentPage, Integer pageSize) {
        int totalPage = 0;
        QueryWrapper<Spu> queryWrapper = new QueryWrapper<>();
        if (Strings.isNullOrEmpty(keyword)) {
            queryWrapper.orderByDesc("id");
        } else {
            queryWrapper.like("title", keyword);
        }
        IPage<Spu> page = spuService.page(new Page<>(currentPage, pageSize), queryWrapper);
        int total = (int) page.getTotal();
        //计算总页数
        totalPage = total / pageSize + ((total % pageSize == 0) ? 0 : 1);
        return totalPage;
    }

    @GetMapping("/spu")
    public List<AppSpuView> spu(@RequestParam(defaultValue = "") String keyword, Integer currentPage, Integer pageSize) {
        List<AppSpuView> list = Lists.newArrayList();
        QueryWrapper<Spu> queryWrapper = new QueryWrapper<>();
        if (Strings.isNullOrEmpty(keyword)) {
            queryWrapper.orderByDesc("id");
        } else {
            queryWrapper.like("title", keyword);
        }
        IPage<Spu> page = spuService.page(new Page<>(currentPage, pageSize), queryWrapper);
        List<Spu> spuList = page.getRecords();
        for (Spu spu : spuList) {
            AppSpuView spuView = new AppSpuView();
            spuView.setSpuId(spu.getId());
            spuView.setImage(GlobalConstants.HOST_PATH + StringLastUtil.get(spu.getImages()));
            spuView.setTitle(spu.getTitle());
            spuView.setTmpPrice(spu.getTmpPrice());
            list.add(spuView);
        }
        return list;
    }

    @GetMapping("/item")
    public Map<String, Object> getItemById(Long id) {
        // 查询数据模型
        Map<String, Object> attributes = spuService.getItemData(id);
        //根据detail->description取出所有的图片
        List<String> descriptionImages = Lists.newArrayList();
        SpuDetail spuDetail = (SpuDetail) attributes.get("detail");
        Document doc = Jsoup.parse(spuDetail.getDescription());
        Elements elements = doc.select("img");
        for (Element element : elements) {
            descriptionImages.add(element.attr("src"));
        }
        attributes.put("descriptionImages", descriptionImages);
        return attributes;
    }

    @GetMapping("/login")
    public SysUser login(@RequestParam("username") String username, @RequestParam("password") String password) {
        //根据用户名查询用户
        List<SysUser> list = sysUserService.list(new QueryWrapper<SysUser>().eq("login_name", username));
        //校验用户名
        if (list.size() != 1) {
            throw new RuntimeException("用户名错误!");
        }
        SysUser user = list.get(0);
        // 校验密码
        if (!StringUtils.equals(user.getLoginPassword(), UserCodecUtils.md5Hex(password, user.getSalt()))) {
            throw new RuntimeException("密码错误!");
        }
        //设置用户的公司
        Company company = companyService.getById(user.getCompanyId());
        user.setCompany(company);

        return user;
    }

    //根据用户，获取收货地址
    @GetMapping("/receiveAddress")
    public ReceiveAddress receiveAddress(Integer userId) {
        return receiveAddressService.getOne(new QueryWrapper<ReceiveAddress>().eq("user_id", userId).eq("isDefault", 1));
    }

    //提交订单时，订单金额限制
    //根据userId查询出一季度的金额
    @GetMapping("/moneyLimit/getMoney")
    public Double getMoney(Integer userId) {
        SysUser user = sysUserService.getById(userId);
        Integer companyId = user.getCompanyId();

        QueryWrapper<MoneyLimit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_id", companyId);
        //获取当前的月份
        int month = LocalDateTime.now().getMonth().getValue();
        if (month <= 3) {
            queryWrapper.eq("quarter", 0);
        } else if (month <= 6) {
            queryWrapper.eq("quarter", 1);
        } else if (month <= 9) {
            queryWrapper.eq("quarter", 2);
        } else {
            queryWrapper.eq("quarter", 3);
        }
        MoneyLimit moneyLimit = moneyLimitService.getOne(queryWrapper);
        return moneyLimit == null ? 0.0 : moneyLimit.getMoney();
    }

}
