package com.hthyaq.malladmin.common.constants;

public class GlobalConstants {
    //分页时，显示的数据条数
    public static final Integer PAGE_SIZE = 10;
    public static final Boolean TRUE = true;
    public static final Boolean FALSE = false;
    //状态码
    public static final Integer SUCCESS = 0;
    public static final String SUCCESS_MSG = "操作成功！";
    public static final Integer FAIL = 1;
    public static final String FAIL_MSG = "操作失败！";
    //换行符
    public static final String NEWLINE = System.getProperty("line.separator");
    //路径分隔符
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    //db的操作类型
    public static final String DB_OPERATE_INSERT = "insert";
    public static final String DB_OPERATE_UPDATE = "update";
    public static final String DB_OPERATE_DELETE = "delete";
    //图片路径
    public static final String IMAGE_PATH = "D:/mallFile/image";
    //图片的品牌路径
    public static final String IMAGE_BRAND_PATH = "/image/brand/";
    //发布商品时的表单类型
    //复杂-有特有规格
    public static final String complexSpecHave = "complexSpecHave";
    //复杂-无特有规格
    public static final String complexSpecNo = "complexSpecNo";
    //简单规格
    public static final String easySpec = "easySpec";
    //项目名称
    public static final String PROJECT_PATH = "http://localhost:8080/mall";
}
