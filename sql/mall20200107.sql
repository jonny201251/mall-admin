/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50716
Source Host           : 127.0.0.1:3306
Source Database       : mall

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2020-01-07 15:40:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '品牌名称',
  `image` varchar(128) DEFAULT '' COMMENT '品牌图片地址',
  `letter` char(1) DEFAULT '' COMMENT '品牌的首字母',
  `sort` double(10,2) DEFAULT '0.00' COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `comment` varchar(500) DEFAULT '' COMMENT '备注',
  `category_ids` varchar(500) DEFAULT '' COMMENT '多个商品类目',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品的品牌表';

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES ('3', 'OPPO', '/image/brand/oppo-93f60fa1-5683-4233-947f-6ba0811a21f6.jpg', 'O', '2.00', '2019-12-25 15:37:17', 'oppo啊啊啊啊', '4');
INSERT INTO `brand` VALUES ('11', 'huawei', '/image/brand/huawei-64842bfb-5b3f-4ad6-8cce-c52015331494.jpg', 'H', '11.00', '2019-12-31 14:43:49', '', '10');
INSERT INTO `brand` VALUES ('12', '南极人', '/image/brand/nanjiren-353c8732-8657-4e77-baac-968bf6df6b4a.jpg', 'N', '12.00', '2019-12-31 15:33:57', '', '8');
INSERT INTO `brand` VALUES ('13', '小米', '/image/brand/xiaomiLogo-89ef3e25-4d8c-4747-9148-f957f3519c4c.jpg', 'X', '1.00', '2019-12-25 15:31:51', '', '4');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT '' COMMENT '类目名称',
  `status` tinyint(1) DEFAULT '1' COMMENT '使用状态：0.禁用,1.正常',
  `pid` int(11) DEFAULT '0' COMMENT '父类目id,顶级类目填0',
  `sort` double(10,2) DEFAULT '0.00' COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `comment` varchar(500) DEFAULT '' COMMENT '备注',
  `template` tinyint(1) DEFAULT '0' COMMENT '对应的规格模板：0简单规格，1复杂规格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品的类目表';

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '图书、音像、电子书刊', '1', '0', '1.00', '2019-12-25 15:16:01', '', '0');
INSERT INTO `category` VALUES ('2', '手机', '1', '0', '2.00', '2019-12-25 15:16:16', '', '0');
INSERT INTO `category` VALUES ('3', '手机通讯', '1', '2', '3.00', '2019-12-25 15:16:46', '', '0');
INSERT INTO `category` VALUES ('4', '手机', '1', '3', '4.00', '2019-12-25 15:16:56', '', '1');
INSERT INTO `category` VALUES ('5', '对讲机', '1', '3', '5.00', '2019-12-25 15:17:03', '', '0');
INSERT INTO `category` VALUES ('6', '家用电器', '1', '0', '6.00', '2019-12-25 15:17:09', '', '0');
INSERT INTO `category` VALUES ('7', '男装', '1', '0', '7.00', '2019-12-26 20:35:47', '', '0');
INSERT INTO `category` VALUES ('8', '牛仔裤', '1', '7', '8.00', '2019-12-26 20:35:59', '', '0');
INSERT INTO `category` VALUES ('10', '手机2', '1', '3', '10.00', '2019-12-31 14:42:26', '', '1');
INSERT INTO `category` VALUES ('11', '夹克', '1', '7', '11.00', '2019-12-31 15:33:35', '', '0');

-- ----------------------------
-- Table structure for category_brand
-- ----------------------------
DROP TABLE IF EXISTS `category_brand`;
CREATE TABLE `category_brand` (
  `category_id` int(11) NOT NULL DEFAULT '-1' COMMENT 'category表的id',
  `brand_id` int(11) NOT NULL DEFAULT '-1' COMMENT 'brand表的id',
  PRIMARY KEY (`category_id`,`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='category和brand的中间表';

-- ----------------------------
-- Records of category_brand
-- ----------------------------
INSERT INTO `category_brand` VALUES ('4', '3');
INSERT INTO `category_brand` VALUES ('4', '13');
INSERT INTO `category_brand` VALUES ('8', '12');
INSERT INTO `category_brand` VALUES ('10', '11');

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT '' COMMENT '公司名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司表，包括公司、商家、供应商';

-- ----------------------------
-- Records of company
-- ----------------------------

-- ----------------------------
-- Table structure for entry
-- ----------------------------
DROP TABLE IF EXISTS `entry`;
CREATE TABLE `entry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_status` tinyint(1) DEFAULT '0' COMMENT '审核状态：0.未审核，1.审核中，2.审核通过，3.审核驳回',
  `company_name` varchar(60) DEFAULT '' COMMENT '公司名称',
  `shop_name` varchar(60) DEFAULT '' COMMENT '店铺名称',
  `agree_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '审核通过时间',
  `refuse_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '审核驳回时间',
  `refuse_reason` varchar(100) DEFAULT '' COMMENT '审核驳回原因',
  `email` varchar(60) DEFAULT '' COMMENT '电子邮箱',
  `mobile` varchar(60) DEFAULT '' COMMENT '手机号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家/供应商的入驻申请表';

-- ----------------------------
-- Records of entry
-- ----------------------------

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT '' COMMENT '店铺名称',
  `description` varchar(255) DEFAULT '' COMMENT '店铺描述',
  `logo` varchar(255) DEFAULT '' COMMENT '店铺logo',
  `address` varchar(100) DEFAULT '' COMMENT '店铺地址',
  `open_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '开店时间',
  `company_id` int(11) DEFAULT '-1' COMMENT '公司表的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺表';

-- ----------------------------
-- Records of shop
-- ----------------------------

-- ----------------------------
-- Table structure for sku
-- ----------------------------
DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'sku id',
  `spu_id` bigint(20) NOT NULL COMMENT 'spu id',
  `title` varchar(256) NOT NULL COMMENT '商品标题',
  `images` varchar(1024) DEFAULT '' COMMENT '商品的图片，多个图片以‘,’分割',
  `price` double(15,2) NOT NULL DEFAULT '0.00' COMMENT '销售价格，单位为分',
  `stock` int(255) DEFAULT '-1' COMMENT ' 库存',
  `indexes` varchar(32) DEFAULT '' COMMENT '特有规格属性在spu属性模板中的对应下标组合',
  `sku_spec` varchar(1024) DEFAULT '' COMMENT 'sku的特有规格参数键值对，json格式，反序列化时请使用linkedHashMap，保证有序',
  `saleable` tinyint(1) DEFAULT '1' COMMENT '是否上架，0下架，1上架',
  `valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效，0无效，1有效',
  `sku_code` varchar(255) DEFAULT '' COMMENT '商品货号，指卖家管理商品的编号，买家不可见',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `key_spu_id` (`spu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='商品的sku表,该表表示具体的商品实体';

-- ----------------------------
-- Records of sku
-- ----------------------------
INSERT INTO `sku` VALUES ('43', '1', '标题1 红色1 4G1', '', '1001.00', '2001', '0_0', '{\"4\":\"红色1\",\"6\":\"4G1\"}', '1', '1', '', '2020-01-07 12:08:39', '2020-01-07 12:08:39');
INSERT INTO `sku` VALUES ('44', '1', '标题1 红色1 8G1', '', '1001.00', '2001', '0_1', '{\"4\":\"红色1\",\"6\":\"8G1\"}', '1', '1', '', '2020-01-07 12:08:39', '2020-01-07 12:08:39');
INSERT INTO `sku` VALUES ('45', '1', '标题1 蓝色1 4G1', '', '1001.00', '2001', '1_0', '{\"4\":\"蓝色1\",\"6\":\"4G1\"}', '1', '1', '', '2020-01-07 12:08:39', '2020-01-07 12:08:39');
INSERT INTO `sku` VALUES ('46', '1', '标题1 蓝色1 8G1', '', '1001.00', '2001', '1_1', '{\"4\":\"蓝色1\",\"6\":\"8G1\"}', '1', '1', '', '2020-01-07 12:08:39', '2020-01-07 12:08:39');
INSERT INTO `sku` VALUES ('47', '2', '标题22', '', '300.00', '400', '', '', '1', '1', '22', '2020-01-07 15:26:34', '2020-01-07 15:26:34');
INSERT INTO `sku` VALUES ('48', '3', '标题33', '', '500.00', '600', '', '', '1', '1', '', '2020-01-07 15:29:11', '2020-01-07 15:29:11');

-- ----------------------------
-- Table structure for specification_group
-- ----------------------------
DROP TABLE IF EXISTS `specification_group`;
CREATE TABLE `specification_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT '-1' COMMENT 'category表的id',
  `name` varchar(100) DEFAULT '' COMMENT '规格组的名称',
  `sort` double(10,2) DEFAULT '0.00' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='规格组---复杂规格';

-- ----------------------------
-- Records of specification_group
-- ----------------------------
INSERT INTO `specification_group` VALUES ('1', '4', '主体', '1.00');
INSERT INTO `specification_group` VALUES ('2', '4', '基本信息', '2.00');
INSERT INTO `specification_group` VALUES ('3', '10', '主体', '3.00');

-- ----------------------------
-- Table structure for specification_param
-- ----------------------------
DROP TABLE IF EXISTS `specification_param`;
CREATE TABLE `specification_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT '-1' COMMENT 'category表的id',
  `group_id` int(11) DEFAULT '-1' COMMENT 'specification_group表的id',
  `name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `digit` tinyint(1) DEFAULT '0' COMMENT '是否是数字类型参数：0.不是，1.是',
  `unit` varchar(100) DEFAULT '' COMMENT '数字类型参数的单位，非数字类型可以为空',
  `generic` tinyint(1) DEFAULT '1' COMMENT '是否为通用属性：0.不是，1.是',
  `searching` tinyint(1) DEFAULT '0' COMMENT '是否用于搜索过滤：0.否，1.是',
  `segments` varchar(100) DEFAULT '' COMMENT '数值类型参数，如果需要搜索，则添加分段间隔值，如CPU频率间隔：0.5-1.0',
  `sort` double(10,2) DEFAULT '0.00' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='规格组下的规格参数---复杂规格';

-- ----------------------------
-- Records of specification_param
-- ----------------------------
INSERT INTO `specification_param` VALUES ('1', '4', '1', '品牌', '0', '', '1', '0', '', '1.00');
INSERT INTO `specification_param` VALUES ('2', '4', '1', '型号', '0', '', '1', '0', '', '2.00');
INSERT INTO `specification_param` VALUES ('3', '4', '1', '上市年份', '1', '年', '1', '0', '', '3.00');
INSERT INTO `specification_param` VALUES ('4', '4', '2', '机身颜色', '0', '', '0', '0', '', '4.00');
INSERT INTO `specification_param` VALUES ('5', '4', '2', '机身重量（g）', '1', 'g', '1', '0', '', '5.00');
INSERT INTO `specification_param` VALUES ('6', '4', '2', '内存', '0', '', '0', '0', '', '6.00');
INSERT INTO `specification_param` VALUES ('7', '10', '3', '品牌', '0', '', '1', '0', '', '7.00');
INSERT INTO `specification_param` VALUES ('8', '10', '3', '型号', '0', '', '1', '0', '', '8.00');

-- ----------------------------
-- Table structure for specification_param2
-- ----------------------------
DROP TABLE IF EXISTS `specification_param2`;
CREATE TABLE `specification_param2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` int(11) DEFAULT '-1' COMMENT 'category表的id',
  `name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `digit` tinyint(1) DEFAULT '0' COMMENT '是否是数字类型参数：0.不是，1.是',
  `unit` varchar(100) DEFAULT '' COMMENT '数字类型参数的单位，非数字类型可以为空',
  `searching` tinyint(1) DEFAULT '0' COMMENT '是否用于搜索过滤：0.否，1.是',
  `segments` varchar(100) DEFAULT '' COMMENT '数值类型参数，如果需要搜索，则添加分段间隔值，如CPU频率间隔：0.5-1.0',
  `sort` double(10,2) DEFAULT '0.00' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='规格组下的规格参数---简单规格';

-- ----------------------------
-- Records of specification_param2
-- ----------------------------
INSERT INTO `specification_param2` VALUES ('1', '8', '面料', '0', '', '0', '', '1.00');
INSERT INTO `specification_param2` VALUES ('2', '8', '材质', '0', '', '0', '', '2.00');
INSERT INTO `specification_param2` VALUES ('3', '8', '弹力', '0', '', '0', '', '3.00');

-- ----------------------------
-- Table structure for spec_seller_define
-- ----------------------------
DROP TABLE IF EXISTS `spec_seller_define`;
CREATE TABLE `spec_seller_define` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT '' COMMENT '自定义属性的名称',
  `value` varchar(100) DEFAULT '' COMMENT '自定义属性的值',
  `spu_id` bigint(20) DEFAULT '-1' COMMENT 'spu表的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商家自定义的规格参数';

-- ----------------------------
-- Records of spec_seller_define
-- ----------------------------
INSERT INTO `spec_seller_define` VALUES ('13', 'K11', 'V11', '1');
INSERT INTO `spec_seller_define` VALUES ('14', 'k22', 'v22', '2');
INSERT INTO `spec_seller_define` VALUES ('15', 'k33', 'k33', '3');

-- ----------------------------
-- Table structure for spu
-- ----------------------------
DROP TABLE IF EXISTS `spu`;
CREATE TABLE `spu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'spu id',
  `title` varchar(128) NOT NULL DEFAULT '' COMMENT '标题',
  `sub_title` varchar(256) DEFAULT '' COMMENT '子标题',
  `images` varchar(1024) DEFAULT '' COMMENT '商品的图片，多个图片以‘,’分割，先把sku中的所有图片用这个代替',
  `tmp_price` double(15,2) DEFAULT '0.00' COMMENT '商品的临时价格',
  `tmp_stock` bigint(20) DEFAULT '0' COMMENT '商品的临时库存',
  `category_id` int(11) NOT NULL DEFAULT '-1' COMMENT '商品所属类目的id',
  `brand_id` int(11) NOT NULL DEFAULT '-1' COMMENT '商品所属品牌id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `last_update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商品的spu表，该表描述的是一个抽象性的商品';

-- ----------------------------
-- Records of spu
-- ----------------------------
INSERT INTO `spu` VALUES ('1', '标题1', '副标题1', '/image/brand/choosed-50aff110-00c0-46fc-9722-6e753432ec45.png,/image/brand/huawei-c190f4f6-2abb-4255-9b0b-6b05c22af847.jpg', '1001.00', '2001', '4', '3', '2020-01-06 14:08:09', '2020-01-06 14:08:09');
INSERT INTO `spu` VALUES ('2', '标题22', '副标题22', '/image/brand/choosed-bf2c49e9-395c-4d82-b6da-37cfabfbf83b.png,/image/brand/Logo-0c1d015e-a560-4da7-a4a1-fc944c07ea3b.png', '3002.00', '4002', '10', '11', '2020-01-06 14:25:32', '2020-01-06 14:25:32');
INSERT INTO `spu` VALUES ('3', '标题33', '副标题33', '/image/brand/oppo-ccfa9bef-2e77-409d-aae3-bc4111727600.jpg', '5003.00', '6003', '8', '12', '2020-01-06 14:29:12', '2020-01-06 14:29:12');

-- ----------------------------
-- Table structure for spu_detail
-- ----------------------------
DROP TABLE IF EXISTS `spu_detail`;
CREATE TABLE `spu_detail` (
  `spu_id` bigint(20) NOT NULL,
  `description` varchar(10240) DEFAULT '' COMMENT '商品描述信息',
  `generic_spec` varchar(2048) NOT NULL DEFAULT '' COMMENT '通用规格参数数据',
  `special_spec` varchar(1024) NOT NULL DEFAULT '' COMMENT '特有规格参数及可选值信息，json格式',
  `packing_list` varchar(1024) DEFAULT '' COMMENT '包装清单',
  `after_service` varchar(1024) DEFAULT '' COMMENT '售后服务',
  PRIMARY KEY (`spu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品的spu的详细信息，和spu是一对一的关系';

-- ----------------------------
-- Records of spu_detail
-- ----------------------------
INSERT INTO `spu_detail` VALUES ('1', '<p>商品描述11</p><p></p><div class=\"media-wrap image-wrap\"><img class=\"media-wrap image-wrap\" src=\"http://localhost:8082/mall/image/brand/favicon-c32cfe58-3f0f-4143-b1c0-ffeef3eca6e4.ico\"/></div><p></p>', '{\"1\":\"品牌1\",\"2\":\"型号1\",\"3\":\"上市年份1\",\"5\":\"机身重量1\"}', '{\"4\":[\"红色1\",\"蓝色1\"],\"6\":[\"4G1\",\"8G1\"]}', '包装清单1', '售后服务1');
INSERT INTO `spu_detail` VALUES ('2', '<p>商品描述22</p><div class=\"media-wrap image-wrap\"><img class=\"media-wrap image-wrap\" src=\"http://localhost:8082/mall/image/brand/favicon-22acedf9-27ff-4216-be15-24b5c72dfbc8.ico\"/></div><p></p>', '{\"7\":\"品牌2\",\"8\":\"型号2\"}', '', '包装清单22', '售后服务22');
INSERT INTO `spu_detail` VALUES ('3', '<p>商品描述33</p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/brand/favicon-01d61234-eebc-481f-8868-a52d0c7db18e.ico\"/></div><p></p><p></p><div class=\"media-wrap image-wrap\"><img class=\"media-wrap image-wrap\" src=\"http://localhost:8082/mall/image/brand/choosed-ed9241da-be1b-4b8f-a3f7-29ee23e6426f.png\"/></div><p></p>', '{\"1\":\"面料3\",\"2\":\"材质3\",\"3\":\"弹力3\"}', '', '包装清单33', '售后服务33');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(11) DEFAULT '-1' COMMENT '上级节点的id',
  `name` varchar(60) DEFAULT '' COMMENT '权限名称',
  `url` varchar(100) DEFAULT '' COMMENT '权限的url',
  `icon` varchar(60) DEFAULT '' COMMENT '菜单的图标',
  `level` int(255) DEFAULT '0' COMMENT '等级，方便通过等级字段查询出权限',
  `sort` double(10,2) DEFAULT '0.00' COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT '' COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL DEFAULT '-1' COMMENT '对应sys_role表的id',
  `menu_id` int(11) NOT NULL DEFAULT '-1' COMMENT '对应sys_menu表的id',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='给角色分配菜单';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `user_id` int(11) NOT NULL DEFAULT '-1' COMMENT '对应sys_user表的id',
  `role_id` int(11) NOT NULL DEFAULT '-1' COMMENT '对应sys_role表的id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='给角色绑定用户';

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(100) DEFAULT '' COMMENT '登录名',
  `login_password` varchar(60) DEFAULT '' COMMENT '登录密码',
  `nick_name` varchar(60) DEFAULT '' COMMENT '昵称',
  `real_name` varchar(60) DEFAULT '' COMMENT '真实姓名',
  `gender` tinyint(1) DEFAULT '0' COMMENT '性别：0.男，1.女',
  `user_avatar` varchar(255) DEFAULT '' COMMENT '用户头像',
  `email` varchar(60) DEFAULT '' COMMENT '电子邮箱',
  `mobile` varchar(60) DEFAULT '' COMMENT '手机号码',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态：0.冻结，1.正常',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `comment` varchar(255) DEFAULT '' COMMENT '备注',
  `company_id` int(11) DEFAULT '-1' COMMENT '商家、公司的id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_account` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表、商城会员表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
