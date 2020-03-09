/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50716
Source Host           : 127.0.0.1:3306
Source Database       : mall

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2020-03-09 15:46:18
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品的品牌表';

-- ----------------------------
-- Records of brand
-- ----------------------------

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
  `template` tinyint(1) DEFAULT '1' COMMENT '对应的规格模板：0简单规格，1复杂规格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品的类目表';

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '工业品', '1', '0', '1.00', '2020-02-27 11:15:47', '', '1');
INSERT INTO `category` VALUES ('2', '工具', '1', '1', '2.00', '2020-02-27 11:17:34', '', '1');
INSERT INTO `category` VALUES ('3', '手动工具', '1', '2', '3.00', '2020-02-27 11:17:48', '', '1');
INSERT INTO `category` VALUES ('4', '电动工具', '1', '2', '4.00', '2020-02-27 11:17:58', '', '1');
INSERT INTO `category` VALUES ('5', '测量工具', '1', '2', '5.00', '2020-02-27 11:18:10', '', '1');
INSERT INTO `category` VALUES ('6', '气动工具', '1', '2', '6.00', '2020-02-27 11:18:19', '', '1');
INSERT INTO `category` VALUES ('7', '工具套装', '1', '2', '7.00', '2020-02-27 11:18:29', '', '1');
INSERT INTO `category` VALUES ('8', '工具配件', '1', '2', '8.00', '2020-02-27 11:18:38', '', '1');
INSERT INTO `category` VALUES ('9', '劳动防护', '1', '1', '9.00', '2020-02-27 11:18:55', '', '1');
INSERT INTO `category` VALUES ('10', '手部防护', '1', '9', '10.00', '2020-02-27 11:19:36', '', '1');
INSERT INTO `category` VALUES ('11', '足部防护', '1', '9', '11.00', '2020-02-27 11:20:21', '', '1');
INSERT INTO `category` VALUES ('12', '身体防护', '1', '9', '12.00', '2020-02-27 11:20:37', '', '1');
INSERT INTO `category` VALUES ('13', '头部防护', '1', '9', '13.00', '2020-02-27 11:20:47', '', '1');
INSERT INTO `category` VALUES ('14', '眼脸部防护', '1', '9', '14.00', '2020-02-27 11:21:15', '', '1');
INSERT INTO `category` VALUES ('15', '呼吸防护', '1', '9', '15.00', '2020-02-27 11:21:25', '', '1');
INSERT INTO `category` VALUES ('16', '坠落防护', '1', '9', '16.00', '2020-02-27 11:21:37', '', '1');
INSERT INTO `category` VALUES ('17', '静电无尘', '1', '9', '17.00', '2020-02-27 11:21:54', '', '1');
INSERT INTO `category` VALUES ('18', '工控配电', '1', '1', '18.00', '2020-02-27 11:22:32', '', '1');
INSERT INTO `category` VALUES ('19', '电线电缆', '1', '18', '19.00', '2020-02-27 11:22:44', '', '1');
INSERT INTO `category` VALUES ('20', '开关插座', '1', '18', '20.00', '2020-02-27 11:22:59', '', '1');
INSERT INTO `category` VALUES ('21', '低压配电', '1', '18', '21.00', '2020-02-27 11:23:11', '', '1');
INSERT INTO `category` VALUES ('22', '控制保护', '1', '18', '22.00', '2020-02-27 11:23:26', '', '1');
INSERT INTO `category` VALUES ('23', '电力检测', '1', '18', '23.00', '2020-02-27 11:23:40', '', '1');
INSERT INTO `category` VALUES ('24', '工业控制', '1', '18', '24.00', '2020-02-27 11:23:50', '', '1');
INSERT INTO `category` VALUES ('25', '自动化', '1', '18', '25.00', '2020-02-27 11:23:59', '', '1');
INSERT INTO `category` VALUES ('26', '电料辅件', '1', '18', '26.00', '2020-02-27 11:24:21', '', '1');
INSERT INTO `category` VALUES ('27', '仪器仪表', '1', '1', '27.00', '2020-02-27 11:25:03', '', '1');
INSERT INTO `category` VALUES ('28', '温度仪表', '1', '27', '28.00', '2020-02-27 11:25:16', '', '1');
INSERT INTO `category` VALUES ('29', '电工仪表', '1', '27', '29.00', '2020-02-27 11:25:33', '', '1');
INSERT INTO `category` VALUES ('30', '气体检测', '1', '27', '30.00', '2020-02-27 11:27:50', '', '1');
INSERT INTO `category` VALUES ('31', '分析检测', '1', '27', '31.00', '2020-02-27 11:27:58', '', '1');
INSERT INTO `category` VALUES ('32', '压力仪表', '1', '27', '32.00', '2020-02-27 11:28:05', '', '1');
INSERT INTO `category` VALUES ('33', '流量仪表', '1', '27', '33.00', '2020-02-27 11:28:14', '', '1');
INSERT INTO `category` VALUES ('34', '物位仪表', '1', '27', '34.00', '2020-02-27 11:28:26', '', '1');
INSERT INTO `category` VALUES ('35', '阻容感及晶振', '1', '27', '35.00', '2020-02-27 11:28:40', '', '1');
INSERT INTO `category` VALUES ('36', '半导体', '1', '27', '36.00', '2020-02-27 11:28:48', '', '1');
INSERT INTO `category` VALUES ('37', '模块及开发板', '1', '27', '37.00', '2020-02-27 11:29:15', '', '1');
INSERT INTO `category` VALUES ('38', '清洁用品', '1', '1', '38.00', '2020-02-27 11:29:29', '', '1');
INSERT INTO `category` VALUES ('39', '清洁工具', '1', '38', '39.00', '2020-02-27 11:29:54', '', '1');
INSERT INTO `category` VALUES ('40', '清洁设备', '1', '38', '40.00', '2020-02-27 11:30:03', '', '1');
INSERT INTO `category` VALUES ('41', '清洗保养品', '1', '38', '41.00', '2020-02-27 11:30:13', '', '1');
INSERT INTO `category` VALUES ('42', '工业擦拭', '1', '38', '42.00', '2020-02-27 11:30:21', '', '1');
INSERT INTO `category` VALUES ('43', '地垫', '1', '38', '43.00', '2020-02-27 11:30:29', '', '1');
INSERT INTO `category` VALUES ('44', '垃圾处理', '1', '38', '44.00', '2020-02-27 11:30:36', '', '1');
INSERT INTO `category` VALUES ('45', '化学品', '1', '1', '45.00', '2020-02-27 11:30:47', '', '1');
INSERT INTO `category` VALUES ('46', '润滑剂', '1', '45', '46.00', '2020-02-27 11:31:32', '', '1');
INSERT INTO `category` VALUES ('47', '胶粘剂', '1', '45', '47.00', '2020-02-27 11:31:41', '', '1');
INSERT INTO `category` VALUES ('48', '化学试剂', '1', '45', '48.00', '2020-02-27 11:31:48', '', '1');
INSERT INTO `category` VALUES ('49', '工业涂层', '1', '45', '49.00', '2020-02-27 11:31:56', '', '1');
INSERT INTO `category` VALUES ('50', '清洗剂', '1', '45', '50.00', '2020-02-27 11:32:02', '', '1');
INSERT INTO `category` VALUES ('51', '防锈剂', '1', '45', '51.00', '2020-02-27 11:32:09', '', '1');
INSERT INTO `category` VALUES ('52', '脱模剂', '1', '45', '52.00', '2020-02-27 11:32:15', '', '1');
INSERT INTO `category` VALUES ('53', '安全消防', '1', '1', '53.00', '2020-02-27 11:32:25', '', '1');
INSERT INTO `category` VALUES ('54', '警示标识', '1', '53', '54.00', '2020-02-27 11:32:52', '', '1');
INSERT INTO `category` VALUES ('55', '应急处理', '1', '53', '55.00', '2020-02-27 11:32:59', '', '1');
INSERT INTO `category` VALUES ('56', '监控设备', '1', '53', '56.00', '2020-02-27 11:33:07', '', '1');
INSERT INTO `category` VALUES ('57', '安全锁具', '1', '53', '57.00', '2020-02-27 11:33:13', '', '1');
INSERT INTO `category` VALUES ('58', '化学品存储', '1', '53', '58.00', '2020-02-27 11:33:21', '', '1');
INSERT INTO `category` VALUES ('59', '消防器材', '1', '53', '59.00', '2020-02-27 11:33:27', '', '1');
INSERT INTO `category` VALUES ('60', '消防服装', '1', '53', '60.00', '2020-02-27 11:33:35', '', '1');
INSERT INTO `category` VALUES ('61', '仓储包装', '1', '1', '61.00', '2020-02-27 11:33:46', '', '1');
INSERT INTO `category` VALUES ('62', '包装工具', '1', '61', '62.00', '2020-02-27 11:34:09', '', '1');
INSERT INTO `category` VALUES ('63', '包装耗材', '1', '61', '63.00', '2020-02-27 11:34:17', '', '1');
INSERT INTO `category` VALUES ('64', '标签耗材', '1', '61', '64.00', '2020-02-27 11:34:24', '', '1');
INSERT INTO `category` VALUES ('65', '搬运设备', '1', '61', '65.00', '2020-02-27 11:34:31', '', '1');
INSERT INTO `category` VALUES ('66', '存储设备', '1', '61', '66.00', '2020-02-27 11:34:36', '', '1');
INSERT INTO `category` VALUES ('67', '焊接紧固', '1', '1', '67.00', '2020-02-27 11:34:57', '', '1');
INSERT INTO `category` VALUES ('68', '五金紧固件', '1', '67', '68.00', '2020-02-27 11:35:19', '', '1');
INSERT INTO `category` VALUES ('69', '密封件', '1', '67', '69.00', '2020-02-27 11:35:25', '', '1');
INSERT INTO `category` VALUES ('70', '焊接设备', '1', '67', '70.00', '2020-02-27 11:35:31', '', '1');
INSERT INTO `category` VALUES ('71', '焊接耗材', '1', '67', '71.00', '2020-02-27 11:35:40', '', '1');
INSERT INTO `category` VALUES ('72', '机械配件', '1', '1', '72.00', '2020-02-27 11:35:50', '', '1');
INSERT INTO `category` VALUES ('73', '轴承', '1', '72', '73.00', '2020-02-27 11:36:39', '', '1');
INSERT INTO `category` VALUES ('74', '皮带链条', '1', '72', '74.00', '2020-02-27 11:36:49', '', '1');
INSERT INTO `category` VALUES ('75', '机械零配件', '1', '72', '75.00', '2020-02-27 11:36:58', '', '1');
INSERT INTO `category` VALUES ('76', '机床及附件', '1', '72', '76.00', '2020-02-27 13:40:06', '', '1');
INSERT INTO `category` VALUES ('77', '刀具', '1', '72', '77.00', '2020-02-27 13:40:13', '', '1');
INSERT INTO `category` VALUES ('78', '气动元件', '1', '72', '78.00', '2020-02-27 13:40:22', '', '1');
INSERT INTO `category` VALUES ('79', '汞阀类', '1', '72', '79.00', '2020-02-27 13:40:43', '', '1');
INSERT INTO `category` VALUES ('80', '暖通照明', '1', '1', '80.00', '2020-02-27 13:40:55', '', '1');
INSERT INTO `category` VALUES ('81', '暖通', '1', '80', '81.00', '2020-02-27 13:41:55', '', '1');
INSERT INTO `category` VALUES ('82', '工业照明', '1', '80', '82.00', '2020-02-27 13:42:02', '', '1');
INSERT INTO `category` VALUES ('83', '管材管件', '1', '80', '83.00', '2020-02-27 13:42:10', '', '1');
INSERT INTO `category` VALUES ('84', '实验用品', '1', '1', '84.00', '2020-02-27 13:42:20', '', '1');
INSERT INTO `category` VALUES ('85', '实验室试剂', '1', '84', '85.00', '2020-02-27 13:42:46', '', '1');
INSERT INTO `category` VALUES ('86', '实验室耗材', '1', '84', '86.00', '2020-02-27 13:43:00', '', '1');
INSERT INTO `category` VALUES ('87', '实验室设备', '1', '84', '87.00', '2020-02-27 13:43:09', '', '1');
INSERT INTO `category` VALUES ('88', '润滑油', '1', '45', '88.00', '2020-02-27 14:14:28', '', '1');
INSERT INTO `category` VALUES ('89', '金属加工液', '1', '45', '89.00', '2020-02-27 14:14:40', '', '1');
INSERT INTO `category` VALUES ('90', '化工原料', '1', '45', '90.00', '2020-02-27 14:14:53', '', '1');
INSERT INTO `category` VALUES ('91', '合成材料', '1', '45', '91.00', '2020-02-27 14:15:05', '', '1');
INSERT INTO `category` VALUES ('92', '打磨工具', '1', '2', '92.00', '2020-02-27 14:16:28', '', '1');

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

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT '' COMMENT '公司名称',
  `type` tinyint(1) DEFAULT '-1' COMMENT '0：159厂，1：159分厂，2：供应商，3：管理员',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='公司表，包括公司、商家、供应商';

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('2', '管理员', '3', '2020-02-24 14:11:30');
INSERT INTO `company` VALUES ('4', '159厂', '0', '2020-02-24 14:12:02');
INSERT INTO `company` VALUES ('6', '北京翁达盛通贸易有限公司\r\n', '2', '2020-02-24 16:35:12');
INSERT INTO `company` VALUES ('7', '北京意隆达商贸有限公司\r\n', '2', '2020-02-24 16:35:26');
INSERT INTO `company` VALUES ('8', '北京航天创宇科贸有限公司\r\n', '2', '2020-02-24 16:36:06');
INSERT INTO `company` VALUES ('9', '北京利惠军顺商贸有限公司\r\n', '2', '2020-02-24 16:36:26');
INSERT INTO `company` VALUES ('10', '博润科（北京）科技有限公司\r\n', '2', '2020-02-24 16:36:30');
INSERT INTO `company` VALUES ('11', '北京德高航空检测材料有限责任公司\r\n', '2', '2020-02-24 16:37:07');
INSERT INTO `company` VALUES ('12', '北京天宇协力商贸有限公司\r\n', '2', '2020-02-24 16:37:14');
INSERT INTO `company` VALUES ('13', '森思泛亚（北京）电子有限公司\r\n', '2', '2020-02-24 16:37:22');
INSERT INTO `company` VALUES ('14', '北京华北工机电设备有限公司\r\n', '2', '2020-02-24 16:37:29');
INSERT INTO `company` VALUES ('15', '北京天丰兴业工贸有限公司\r\n', '2', '2020-02-24 16:37:39');
INSERT INTO `company` VALUES ('16', '北京信思福兴经工机电设备有限公司\r\n', '2', '2020-02-24 16:37:45');
INSERT INTO `company` VALUES ('17', '北京凯天诚信科技有限公司\r\n', '2', '2020-02-24 16:37:54');
INSERT INTO `company` VALUES ('18', '海鹰安全公司', '2', '2020-02-24 16:55:15');
INSERT INTO `company` VALUES ('21', '四分厂', '1', '2020-02-26 17:37:41');
INSERT INTO `company` VALUES ('22', '六分厂', '1', '2020-02-26 17:37:50');
INSERT INTO `company` VALUES ('23', '七分厂', '1', '2020-02-26 17:38:01');
INSERT INTO `company` VALUES ('24', '八分厂', '1', '2020-02-26 17:38:06');
INSERT INTO `company` VALUES ('25', '十八分厂', '1', '2020-02-26 17:38:16');
INSERT INTO `company` VALUES ('26', '十九分厂', '1', '2020-02-26 17:38:21');
INSERT INTO `company` VALUES ('27', '教培中心', '1', '2020-02-26 17:38:32');

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
-- Table structure for money_limit
-- ----------------------------
DROP TABLE IF EXISTS `money_limit`;
CREATE TABLE `money_limit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `quarter` tinyint(1) DEFAULT '-1' COMMENT '季度，0：一季度，1：二季度，2：三季度，3：四季度',
  `money` double(15,2) DEFAULT '0.00' COMMENT '限制的下单金额',
  `company_id` int(11) DEFAULT '-1' COMMENT '159分厂的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='159分厂的订单金额限制';

-- ----------------------------
-- Records of money_limit
-- ----------------------------

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单详情id ',
  `order_id` varchar(100) NOT NULL DEFAULT '' COMMENT '订单id',
  `sku_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT 'sku商品id',
  `num` int(11) NOT NULL DEFAULT '-1' COMMENT '购买数量',
  `title` varchar(256) NOT NULL DEFAULT '' COMMENT '商品标题',
  `sku_spec` varchar(1024) DEFAULT '' COMMENT '商品动态属性键值集',
  `price` double(15,2) NOT NULL DEFAULT '0.00' COMMENT '价格',
  `image` varchar(128) DEFAULT '' COMMENT '商品图片',
  PRIMARY KEY (`id`),
  KEY `key_order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情表';

-- ----------------------------
-- Records of order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `order_id` varchar(100) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '订单id',
  `total_pay` double(15,2) NOT NULL DEFAULT '0.00' COMMENT '总金额',
  `actual_pay` double(15,2) NOT NULL DEFAULT '0.00' COMMENT '实付金额。',
  `promotion_ids` varchar(256) COLLATE utf8_bin DEFAULT '' COMMENT '参与促销活动的id',
  `payment_type` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '支付类型，0、暂不付款 1、在线支付，2、货到付款',
  `post_fee` double(15,2) NOT NULL DEFAULT '0.00' COMMENT '邮费',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `shipping_name` varchar(20) COLLATE utf8_bin DEFAULT '' COMMENT '物流名称',
  `shipping_code` varchar(20) COLLATE utf8_bin DEFAULT '' COMMENT '物流单号',
  `user_id` int(11) NOT NULL DEFAULT '-1' COMMENT '用户id',
  `buyer_message` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '买家留言',
  `buyer_nick` varchar(32) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '买家昵称',
  `buyer_rate` tinyint(1) DEFAULT '0' COMMENT '买家是否已经评价,0未评价，1已评价',
  `receiver_state` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '收获地址（省）',
  `receiver_city` varchar(256) COLLATE utf8_bin DEFAULT '' COMMENT '收获地址（市）',
  `receiver_district` varchar(256) COLLATE utf8_bin DEFAULT '' COMMENT '收获地址（区/县）',
  `receiver_address` varchar(256) COLLATE utf8_bin DEFAULT '' COMMENT '收获地址（街道、住址等详细地址）',
  `receiver_mobile` varchar(11) COLLATE utf8_bin DEFAULT '' COMMENT '收货人手机',
  `receiver_zip` varchar(16) COLLATE utf8_bin DEFAULT '' COMMENT '收货人邮编',
  `receiver` varchar(32) COLLATE utf8_bin DEFAULT '' COMMENT '收货人',
  `invoice_type` int(1) DEFAULT '0' COMMENT '发票类型(0无发票1普通发票，2电子发票，3增值税发票)',
  `source_type` int(1) DEFAULT '2' COMMENT '订单来源：1:app端，2：pc端，3：M端，4：微信端，5：手机qq端',
  `company_id` int(11) DEFAULT '-1' COMMENT '该订单所对应的商家',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='订单表，包括用户信息、收货地址';

-- ----------------------------
-- Records of order_info
-- ----------------------------

-- ----------------------------
-- Table structure for order_status
-- ----------------------------
DROP TABLE IF EXISTS `order_status`;
CREATE TABLE `order_status` (
  `order_id` varchar(100) NOT NULL DEFAULT '' COMMENT '订单id',
  `status` int(1) DEFAULT '0' COMMENT '状态：0、等待商家发货 1、未付款 2、已付款,未发货 3、已发货,未确认 4、交易成功 5、交易关闭 6、已评价7、取消订单8、确认收货',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `payment_time` datetime DEFAULT NULL COMMENT '付款时间',
  `consign_time` datetime DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `comment_time` datetime DEFAULT NULL COMMENT '评价时间',
  PRIMARY KEY (`order_id`),
  KEY `status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单状态表';

-- ----------------------------
-- Records of order_status
-- ----------------------------

-- ----------------------------
-- Table structure for receive_address
-- ----------------------------
DROP TABLE IF EXISTS `receive_address`;
CREATE TABLE `receive_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `real_name` varchar(255) DEFAULT '' COMMENT '用户的真实姓名',
  `mobile` varchar(255) DEFAULT '' COMMENT '用户的手机号',
  `state` varchar(255) DEFAULT '' COMMENT '收获地址（省）',
  `city` varchar(255) DEFAULT '' COMMENT '收获地址（市）',
  `district` varchar(255) DEFAULT '' COMMENT '收获地址（区/县）',
  `address` varchar(255) DEFAULT '' COMMENT '收获地址（街道、住址等详细地址）',
  `zip` varchar(255) DEFAULT '' COMMENT 'zipcode,收货人邮编',
  `isDefault` tinyint(1) DEFAULT '0' COMMENT '默认地址，0：不是默认，1：默认',
  `user_id` int(11) DEFAULT '-1' COMMENT 'sys_user表的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='收货地址';

-- ----------------------------
-- Records of receive_address
-- ----------------------------
INSERT INTO `receive_address` VALUES ('1', '范海峰', '13581803971', '', '', '', '北京市丰台区东王佐北路9号', '100074', '1', '18');
INSERT INTO `receive_address` VALUES ('2', '杨宏宇', '13264087810', '', '', '', '北京市丰台区东王佐北路9号', '100074', '1', '19');
INSERT INTO `receive_address` VALUES ('3', '褚颖', '17310645575', '', '', '', '北京市丰台区东王佐北路9号', '100074', '1', '20');
INSERT INTO `receive_address` VALUES ('4', '李宏', '18910673662', '', '', '', '北京市丰台区东王佐北路9号', '100074', '1', '21');
INSERT INTO `receive_address` VALUES ('5', '徐丽', '13611334709', '', '', '', '北京市丰台区东王佐北路9号', '100074', '1', '22');
INSERT INTO `receive_address` VALUES ('6', '刘佳', '13501238201', '', '', '', '北京市丰台区东王佐北路9号', '100074', '1', '23');
INSERT INTO `receive_address` VALUES ('7', '史玮', '18210989525', '', '', '', '北京市丰台区东王佐北路9号', '100074', '1', '24');
INSERT INTO `receive_address` VALUES ('8', '王强', '13501000743', '', '', '', '北京市丰台区东王佐北路9号', '100074', '1', '25');

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
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='商品的sku表,该表表示具体的商品实体';

-- ----------------------------
-- Records of sku
-- ----------------------------
INSERT INTO `sku` VALUES ('3', '1', '新美科 cimtech 320', 'http://localhost:8082/mall/image/item/493dec99be3d4442a8a64554be1950ee.jpg', '9600.00', '50000', '', '', '1', '1', '', '2020-02-27 16:29:32', '2020-02-27 16:29:32');
INSERT INTO `sku` VALUES ('4', '2', '切削液', 'http://localhost:8082/mall/image/item/784f0a8ca3ea49c2b2044c4b6e74dd08.jpg', '5700.00', '50000', '', '', '1', '1', '', '2020-02-27 16:44:54', '2020-02-27 16:44:54');
INSERT INTO `sku` VALUES ('6', '4', '长城L-HM46抗磨液压油（高压）', 'http://localhost:8082/mall/image/item/c7f0609931ae474694b65c9284d31abf.jpg', '2630.00', '50000', '', '', '1', '1', '', '2020-02-27 16:51:04', '2020-02-27 16:51:04');
INSERT INTO `sku` VALUES ('7', '5', '切削液', 'http://localhost:8082/mall/image/item/c78870c47fb34f0aad3cdac3c6ecb608.jpg', '6500.00', '50000', '', '', '1', '1', '', '2020-02-27 16:54:12', '2020-02-27 16:54:12');
INSERT INTO `sku` VALUES ('8', '6', '新美科qualstar ap', 'http://localhost:8082/mall/image/item/dd6ebdb0ab104cdabbe5d6d3025dda95.jpg', '7800.00', '50000', '', '', '1', '1', '', '2020-02-27 16:55:52', '2020-02-27 16:55:52');
INSERT INTO `sku` VALUES ('9', '7', '美孚润滑油', 'http://localhost:8082/mall/image/item/3ca22728382e49868f2037718a8ea02c.jpg', '710.00', '50000', '', '', '1', '1', '', '2020-02-27 16:58:06', '2020-02-27 16:58:06');
INSERT INTO `sku` VALUES ('10', '8', '切削液', 'http://localhost:8082/mall/image/item/4326c3a05a864d2ea534d8994e343611.jpg', '610.00', '50000', '', '', '1', '1', '', '2020-02-27 16:59:12', '2020-02-27 16:59:12');
INSERT INTO `sku` VALUES ('11', '9', '航空胶带', 'http://localhost:8082/mall/image/item/33a45403f9cd47d09091b497e287f8d0.jpg', '247.00', '50000', '', '', '1', '1', '', '2020-02-27 17:02:03', '2020-02-27 17:02:03');
INSERT INTO `sku` VALUES ('12', '10', '下吸式喷枪', 'http://localhost:8082/mall/image/item/be743d32256f41f88f1ab25d2859b39e.0', '255.00', '50000', '', '', '1', '1', '', '2020-02-27 17:06:34', '2020-02-27 17:06:34');
INSERT INTO `sku` VALUES ('13', '11', '岩田喷枪', 'http://localhost:8082/mall/image/item/e4fc753564414b44bc5f858972892656.jpg', '980.00', '50000', '', '', '1', '1', '', '2020-02-27 17:07:38', '2020-02-27 17:07:38');
INSERT INTO `sku` VALUES ('14', '12', '美国固瑞克喷枪', 'http://localhost:8082/mall/image/item/914c3e7a9502489080e0e74844efe5e0.4mm）', '13424.00', '50000', '', '', '1', '1', '', '2020-02-27 17:08:28', '2020-02-27 17:08:28');
INSERT INTO `sku` VALUES ('15', '13', '屏蔽胶带', 'http://localhost:8082/mall/image/item/03647b40b27b4aac9969f52d55bee1c3.jpg', '300.00', '50000', '', '', '1', '1', '', '2020-02-27 17:10:50', '2020-02-27 17:10:50');
INSERT INTO `sku` VALUES ('16', '14', '塑铜线 慧远', 'http://localhost:8082/mall/image/item/b2c4a3c0b855473180199ebb7b2a5f8b.5 100,http://localhost:8082/mall/image/item/f8140711d6ac4ea4bb78a725a2a83a99.5 100图片2,http://localhost:8082/mall/image/item/f9cb936f31cb40439de0e854c82a2df8.5 100图片3', '202.00', '50000', '', '', '1', '1', '', '2020-02-27 17:13:44', '2020-02-27 17:13:44');
INSERT INTO `sku` VALUES ('17', '15', '公牛插线板', 'http://localhost:8082/mall/image/item/c970ad7857af440da8f81b7e52b26c47.5米', '162.50', '50000', '', '', '1', '1', '', '2020-02-27 17:15:39', '2020-02-27 17:15:39');
INSERT INTO `sku` VALUES ('19', '17', '施耐德空气开关', 'http://localhost:8082/mall/image/item/5603570acf014960a6e766c5978ba404.jpg,http://localhost:8082/mall/image/item/50ec2178949145b69a09123b814794f2.jpg,http://localhost:8082/mall/image/item/385c844d9d9e45fa8f06ea2d543cbe90.jpg', '194.00', '50000', '', '', '1', '1', '', '2020-02-27 17:18:27', '2020-02-27 17:18:27');
INSERT INTO `sku` VALUES ('20', '18', '海洋王', 'http://localhost:8082/mall/image/item/31f9bd78c51a41148aedeae25e703ee3.jpg', '181.00', '50000', '', '', '1', '1', '', '2020-02-27 17:20:12', '2020-02-27 17:20:12');
INSERT INTO `sku` VALUES ('21', '19', '塑料编织管', 'http://localhost:8082/mall/image/item/bce7c4df375643b2a69562cfe6f50a83.jpg', '273.00', '50000', '', '', '1', '1', '', '2020-02-27 17:27:06', '2020-02-27 17:27:06');
INSERT INTO `sku` VALUES ('22', '20', '塑料编织管', 'http://localhost:8082/mall/image/item/36a43b5a4d87409888391b4ff7ad42f3.jpg', '169.00', '50000', '', '', '1', '1', '', '2020-02-27 17:28:05', '2020-02-27 17:28:05');
INSERT INTO `sku` VALUES ('23', '21', '塑料编织管', 'http://localhost:8082/mall/image/item/8cbf57f5ae624a8bab614ced48896a04.jpg', '169.00', '50000', '', '', '1', '1', '', '2020-02-27 17:33:54', '2020-02-27 17:33:54');
INSERT INTO `sku` VALUES ('24', '22', '杜邦R22（22.7KG）制冷剂', 'http://localhost:8082/mall/image/item/6083d7d2f303499f91ca272afbc84917.7KG）制冷剂', '965.00', '50000', '', '', '1', '1', '', '2020-02-27 17:35:56', '2020-02-27 17:35:56');
INSERT INTO `sku` VALUES ('25', '23', '奇峰氧气管（三胶两线）', 'http://localhost:8082/mall/image/item/2ccd2057aec543668506cde749fb62da.jpg,http://localhost:8082/mall/image/item/b68b92c911d74f0e8d9f09ff7298a951.jpg', '162.00', '50000', '', '', '1', '1', '', '2020-02-27 17:37:51', '2020-02-27 17:37:51');
INSERT INTO `sku` VALUES ('26', '24', '佛山镇流器', 'http://localhost:8082/mall/image/item/50364bc197f94f5c859cb6cc38fdb22b.jpg,http://localhost:8082/mall/image/item/c61ad34ef21e466bae9b73accc40d6d5.jpg,http://localhost:8082/mall/image/item/e6b64385c6944d49b73330cb6e0b06dc.jpg', '18.30', '50000', '', '', '1', '1', '', '2020-02-27 17:40:12', '2020-02-27 17:40:12');
INSERT INTO `sku` VALUES ('27', '25', '正泰防水插座', 'http://localhost:8082/mall/image/item/6645b278bfb24333bbef79afab15cdab.jpg', '20.00', '50000', '', '', '1', '1', '', '2020-02-27 17:41:36', '2020-02-27 17:41:36');
INSERT INTO `sku` VALUES ('28', '26', '德力西旋转开关', 'http://localhost:8082/mall/image/item/ca9728b59e59404c83fe9cc7e14dc77c.jpg', '14.00', '50000', '', '', '1', '1', '', '2020-02-27 17:50:17', '2020-02-27 17:50:17');
INSERT INTO `sku` VALUES ('29', '27', '松下电池', 'http://localhost:8082/mall/image/item/404adfad7bd24ac09ddd122fdb7dcda7.png', '14.00', '50000', '', '', '1', '1', '', '2020-03-02 09:57:05', '2020-03-02 09:57:05');
INSERT INTO `sku` VALUES ('30', '28', '毛绒布', 'http://localhost:8082/mall/image/item/592ba156da454201bd452cfdc7883d7a.jpg', '48.00', '50000', '', '', '1', '1', '', '2020-03-02 09:58:31', '2020-03-02 09:58:31');
INSERT INTO `sku` VALUES ('31', '29', '除锈剂', 'http://localhost:8082/mall/image/item/3c365dc572f643f2a9a60e3dc7d51fe0.jpg,http://localhost:8082/mall/image/item/5dc36b30a01d4e3fa6367867af04e726.jpg', '42.00', '50000', '', '', '1', '1', '', '2020-03-02 10:01:12', '2020-03-02 10:01:12');
INSERT INTO `sku` VALUES ('32', '30', '五孔插座', 'http://localhost:8082/mall/image/item/df8227e1711a4f2eb5f472a1bab53a92.jpg', '24.00', '50000', '', '', '1', '1', '', '2020-03-02 10:02:29', '2020-03-02 10:02:29');
INSERT INTO `sku` VALUES ('33', '31', '吹尘枪', 'http://localhost:8082/mall/image/item/8163b68737c241deacf2e601ddaa16ca.jpg', '15.00', '50000', '', '', '1', '1', '', '2020-03-02 10:03:57', '2020-03-02 10:03:57');
INSERT INTO `sku` VALUES ('34', '32', '塑铜线 4平方慧远', 'http://localhost:8082/mall/image/item/5fa2023e5b0a434cbe9370cf7f390f44.jpg,http://localhost:8082/mall/image/item/85344abe6a56409e9c53dce1a11078a3.jpg', '361.00', '50000', '', '', '1', '1', '', '2020-03-02 10:05:27', '2020-03-02 10:05:27');
INSERT INTO `sku` VALUES ('35', '33', '施耐德', 'http://localhost:8082/mall/image/item/30137d2dc81d464fbea424140d4053cd.jpg', '211.00', '50000', '', '', '1', '1', '', '2020-03-02 10:06:17', '2020-03-02 10:06:17');
INSERT INTO `sku` VALUES ('37', '35', '导轨油', 'http://localhost:8082/mall/image/item/2d8fe2bd3ce842a3b5acdbc5b779787f.jpg', '540.00', '50000', '', '', '1', '1', '', '2020-03-02 10:09:32', '2020-03-02 10:09:32');
INSERT INTO `sku` VALUES ('38', '36', '导轨油', 'http://localhost:8082/mall/image/item/2730544eab984fcea684b8ac307c3e76.jpg', '800.00', '50000', '', '', '1', '1', '', '2020-03-02 10:10:53', '2020-03-02 10:10:53');
INSERT INTO `sku` VALUES ('39', '37', '公牛三联插线板', 'http://localhost:8082/mall/image/item/e24936df98cd436c83258bcce3ad85b8.jpg', '51.70', '50000', '', '', '1', '1', '', '2020-03-02 10:12:22', '2020-03-02 10:12:22');
INSERT INTO `sku` VALUES ('40', '38', '索尼5号电池', 'http://localhost:8082/mall/image/item/14b11ae2471e494480722dd1374aaa3a.jpg', '8.00', '50000', '', '', '1', '1', '', '2020-03-02 10:13:54', '2020-03-02 10:13:54');
INSERT INTO `sku` VALUES ('41', '39', '固力顺G48冷却液4L', 'http://localhost:8082/mall/image/item/463f1943edc4441d8d59fa013b941033.jpg', '165.00', '50000', '', '', '1', '1', '', '2020-03-02 10:16:11', '2020-03-02 10:16:11');
INSERT INTO `sku` VALUES ('42', '40', '德力西BK-50型控制变压器', 'http://localhost:8082/mall/image/item/a2229d68222c44259705383b3b60caa3.jpg,http://localhost:8082/mall/image/item/5e00c7ab75e14a17b229441b5e6d0df0.jpg', '65.00', '50000', '', '', '1', '1', '', '2020-03-02 10:18:30', '2020-03-02 10:18:30');
INSERT INTO `sku` VALUES ('43', '41', '超霸9V电池', 'http://localhost:8082/mall/image/item/25a6a62615034ba4b4fcbd73c2412eeb.jpg', '15.00', '50000', '', '', '1', '1', '', '2020-03-02 10:19:42', '2020-03-02 10:19:42');
INSERT INTO `sku` VALUES ('44', '42', '公牛1.8米插座', 'http://localhost:8082/mall/image/item/6c90f087ca0146f694a604a22bf5b769.8米插座', '30.60', '50000', '', '', '1', '1', '', '2020-03-02 10:21:24', '2020-03-02 10:21:24');
INSERT INTO `sku` VALUES ('45', '43', '世达-重型方钢台虎钳', 'http://localhost:8082/mall/image/item/676ba5bff69c424db08c53b4a6a06644.jpg', '1574.00', '50000', '', '', '1', '1', '', '2020-03-02 10:23:05', '2020-03-02 10:23:05');
INSERT INTO `sku` VALUES ('46', '44', '电池3.6V', 'http://localhost:8082/mall/image/item/9fd049bb9cb3400ebf1ce26176787aeb.6V', '21.50', '50000', '', '', '1', '1', '', '2020-03-02 10:24:07', '2020-03-02 10:24:07');
INSERT INTO `sku` VALUES ('47', '45', '工作灯', 'http://localhost:8082/mall/image/item/1e03709377764b9eb0533f3f7a258330.jpg', '97.50', '50000', '', '', '1', '1', '', '2020-03-02 10:24:57', '2020-03-02 10:24:57');
INSERT INTO `sku` VALUES ('48', '46', '数显卡尺150mm', 'http://localhost:8082/mall/image/item/1bcc9df19b80484bb7f35540de38799c.jpg', '199.00', '50000', '', '', '1', '1', '', '2020-03-02 10:25:38', '2020-03-02 10:25:38');
INSERT INTO `sku` VALUES ('49', '47', '塑料布', 'http://localhost:8082/mall/image/item/7b70afd2c45c462a98a7f2219c706f0a.jpg', '873.00', '50000', '', '', '1', '1', '', '2020-03-02 10:32:00', '2020-03-02 10:32:00');
INSERT INTO `sku` VALUES ('50', '48', '帆布', 'http://localhost:8082/mall/image/item/7fc5522adc264fdfbded79a9d24946d1.png', '479.00', '50000', '', '', '1', '1', '', '2020-03-02 10:33:06', '2020-03-02 10:33:06');
INSERT INTO `sku` VALUES ('51', '49', '氩弧焊嘴', 'http://localhost:8082/mall/image/item/73842008e0d54af486b29afcc189a42d.jpg', '2.00', '50000', '', '', '1', '1', '', '2020-03-02 10:34:00', '2020-03-02 10:34:00');
INSERT INTO `sku` VALUES ('52', '50', '天棚钨极', 'http://localhost:8082/mall/image/item/c9a2416627b5413cbb888fb2eb6a1952.jpg', '13.00', '50000', '', '', '1', '1', '', '2020-03-02 10:34:48', '2020-03-02 10:34:48');
INSERT INTO `sku` VALUES ('53', '51', '板刷', 'http://localhost:8082/mall/image/item/fa6c9ced6b95456faefa7538b6154c4b.jpg', '39.40', '50000', '', '', '1', '1', '', '2020-03-02 10:35:50', '2020-03-02 10:35:50');
INSERT INTO `sku` VALUES ('54', '52', '色带', 'http://localhost:8082/mall/image/item/8be4a7c97dbb4756895ab69f1837f10c.jpg', '10.00', '50000', '', '', '1', '1', '', '2020-03-02 10:37:08', '2020-03-02 10:37:08');
INSERT INTO `sku` VALUES ('55', '53', '4分水龙头', 'http://localhost:8082/mall/image/item/1d534cb63b954294ab6ddef6bd02ec68.jpg', '20.00', '50000', '', '', '1', '1', '', '2020-03-02 10:37:47', '2020-03-02 10:37:47');
INSERT INTO `sku` VALUES ('56', '54', '显像剂', 'http://localhost:8082/mall/image/item/50039d134568424680da7d3118fab508.jpg', '28.00', '50000', '', '', '1', '1', '', '2020-03-02 10:39:22', '2020-03-02 10:39:22');
INSERT INTO `sku` VALUES ('57', '16', '公牛', 'http://localhost:8082/mall/image/item/47c2f40c37344cf0af30581bc034303e.jpg,http://localhost:8082/mall/image/item/320a95000a4542b5a857fe9776a78c7d.jpg,http://localhost:8082/mall/image/item/e6893a8b316f4919bb769a92e66bfb50.jpg,http://localhost:8082/mall/image/item/0b1a570ad75d4f798ad12304f77ed01f.jpg', '282.00', '50000', '', '', '1', '1', '', '2020-03-03 20:11:10', '2020-03-03 20:11:10');
INSERT INTO `sku` VALUES ('58', '34', '飞利浦节能灯泡', 'http://localhost:8082/mall/image/item/6fcee6ec8687491392443d617d2b3c9e.jpg,http://localhost:8082/mall/image/item/bd935564dc0f4db0998a878a17f6e304.jpg', '18.00', '50000', '', '', '1', '1', '', '2020-03-03 20:11:38', '2020-03-03 20:11:38');
INSERT INTO `sku` VALUES ('59', '3', '美孚润滑油', 'http://localhost:8082/mall/image/item/70bfb601134444cfbb0e025cc62e57e4.jpg,http://localhost:8082/mall/image/item/9df7d5cb01f74be7bf4b5de2bb52ac1c.jpg', '565.00', '50000', '', '', '1', '1', '', '2020-03-03 20:12:33', '2020-03-03 20:12:33');

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
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COMMENT='规格组---复杂规格';

-- ----------------------------
-- Records of specification_group
-- ----------------------------
INSERT INTO `specification_group` VALUES ('1', '3', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('2', '4', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('3', '5', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('4', '6', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('5', '7', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('6', '8', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('7', '10', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('8', '11', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('9', '12', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('10', '13', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('11', '14', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('12', '15', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('13', '16', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('14', '17', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('15', '19', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('16', '20', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('17', '21', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('18', '22', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('19', '23', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('20', '24', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('21', '25', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('22', '26', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('23', '28', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('24', '29', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('25', '30', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('26', '31', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('27', '32', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('28', '33', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('29', '34', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('30', '35', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('31', '36', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('32', '37', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('33', '39', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('34', '40', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('35', '41', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('36', '42', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('37', '43', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('38', '44', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('39', '46', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('40', '47', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('41', '48', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('42', '49', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('43', '50', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('44', '51', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('45', '52', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('46', '54', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('47', '55', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('48', '56', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('49', '57', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('50', '58', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('51', '59', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('52', '60', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('53', '62', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('54', '63', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('55', '64', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('56', '65', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('57', '66', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('58', '68', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('59', '69', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('60', '70', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('61', '71', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('62', '73', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('63', '74', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('64', '75', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('65', '76', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('66', '77', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('67', '78', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('68', '79', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('69', '81', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('70', '82', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('71', '83', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('72', '85', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('73', '86', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('74', '87', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('75', '88', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('76', '89', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('77', '90', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('78', '91', '主体', '0.00');
INSERT INTO `specification_group` VALUES ('79', '92', '主体', '0.00');

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
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='规格组下的规格参数---复杂规格';

-- ----------------------------
-- Records of specification_param
-- ----------------------------
INSERT INTO `specification_param` VALUES ('1', '3', '1', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('2', '3', '1', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('3', '4', '2', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('4', '4', '2', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('5', '5', '3', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('6', '5', '3', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('7', '6', '4', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('8', '6', '4', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('9', '7', '5', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('10', '7', '5', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('11', '8', '6', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('12', '8', '6', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('13', '10', '7', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('14', '10', '7', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('15', '11', '8', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('16', '11', '8', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('17', '12', '9', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('18', '12', '9', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('19', '13', '10', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('20', '13', '10', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('21', '14', '11', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('22', '14', '11', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('23', '15', '12', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('24', '15', '12', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('25', '16', '13', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('26', '16', '13', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('27', '17', '14', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('28', '17', '14', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('29', '19', '15', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('30', '19', '15', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('31', '20', '16', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('32', '20', '16', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('33', '21', '17', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('34', '21', '17', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('35', '22', '18', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('36', '22', '18', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('37', '23', '19', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('38', '23', '19', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('39', '24', '20', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('40', '24', '20', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('41', '25', '21', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('42', '25', '21', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('43', '26', '22', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('44', '26', '22', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('45', '28', '23', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('46', '28', '23', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('47', '29', '24', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('48', '29', '24', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('49', '30', '25', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('50', '30', '25', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('51', '31', '26', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('52', '31', '26', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('53', '32', '27', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('54', '32', '27', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('55', '33', '28', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('56', '33', '28', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('57', '34', '29', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('58', '34', '29', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('59', '35', '30', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('60', '35', '30', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('61', '36', '31', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('62', '36', '31', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('63', '37', '32', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('64', '37', '32', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('65', '39', '33', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('66', '39', '33', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('67', '40', '34', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('68', '40', '34', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('69', '41', '35', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('70', '41', '35', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('71', '42', '36', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('72', '42', '36', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('73', '43', '37', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('74', '43', '37', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('75', '44', '38', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('76', '44', '38', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('77', '46', '39', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('78', '46', '39', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('79', '47', '40', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('80', '47', '40', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('81', '48', '41', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('82', '48', '41', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('83', '49', '42', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('84', '49', '42', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('85', '50', '43', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('86', '50', '43', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('87', '51', '44', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('88', '51', '44', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('89', '52', '45', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('90', '52', '45', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('91', '54', '46', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('92', '54', '46', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('93', '55', '47', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('94', '55', '47', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('95', '56', '48', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('96', '56', '48', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('97', '57', '49', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('98', '57', '49', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('99', '58', '50', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('100', '58', '50', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('101', '59', '51', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('102', '59', '51', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('103', '60', '52', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('104', '60', '52', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('105', '62', '53', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('106', '62', '53', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('107', '63', '54', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('108', '63', '54', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('109', '64', '55', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('110', '64', '55', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('111', '65', '56', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('112', '65', '56', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('113', '66', '57', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('114', '66', '57', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('115', '68', '58', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('116', '68', '58', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('117', '69', '59', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('118', '69', '59', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('119', '70', '60', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('120', '70', '60', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('121', '71', '61', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('122', '71', '61', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('123', '73', '62', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('124', '73', '62', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('125', '74', '63', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('126', '74', '63', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('127', '75', '64', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('128', '75', '64', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('129', '76', '65', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('130', '76', '65', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('131', '77', '66', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('132', '77', '66', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('133', '78', '67', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('134', '78', '67', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('135', '79', '68', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('136', '79', '68', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('137', '81', '69', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('138', '81', '69', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('139', '82', '70', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('140', '82', '70', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('141', '83', '71', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('142', '83', '71', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('143', '85', '72', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('144', '85', '72', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('145', '86', '73', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('146', '86', '73', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('147', '87', '74', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('148', '87', '74', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('149', '88', '75', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('150', '88', '75', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('151', '89', '76', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('152', '89', '76', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('153', '90', '77', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('154', '90', '77', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('155', '91', '78', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('156', '91', '78', '单位', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('157', '92', '79', '规格型号', '0', '', '1', '0', '', '0.00');
INSERT INTO `specification_param` VALUES ('158', '92', '79', '单位', '0', '', '1', '0', '', '0.00');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='规格组下的规格参数---简单规格';

-- ----------------------------
-- Records of specification_param2
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商家自定义的规格参数';

-- ----------------------------
-- Records of spec_seller_define
-- ----------------------------

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
  `company_id` int(11) DEFAULT '-1' COMMENT '该商品所属的供应商',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COMMENT='商品的spu表，该表描述的是一个抽象性的商品';

-- ----------------------------
-- Records of spu
-- ----------------------------
INSERT INTO `spu` VALUES ('1', '新美科 cimtech 320', '', '/image/item/493dec99be3d4442a8a64554be1950ee.jpg', '9600.00', '50000', '89', '-1', '2020-02-27 15:52:48', '2020-02-27 15:52:48', '12');
INSERT INTO `spu` VALUES ('2', '切削液', '', '/image/item/784f0a8ca3ea49c2b2044c4b6e74dd08.jpg', '5700.00', '50000', '89', '-1', '2020-02-27 16:44:54', '2020-02-27 16:44:54', '12');
INSERT INTO `spu` VALUES ('3', '美孚润滑油', '', '/image/item/70bfb601134444cfbb0e025cc62e57e4.jpg,/image/item/9df7d5cb01f74be7bf4b5de2bb52ac1c.jpg', '565.00', '50000', '46', '-1', '2020-02-27 16:47:48', '2020-02-27 16:47:48', '12');
INSERT INTO `spu` VALUES ('4', '长城L-HM46抗磨液压油（高压）', '', '/image/item/c7f0609931ae474694b65c9284d31abf.jpg', '2630.00', '50000', '46', '-1', '2020-02-27 16:51:04', '2020-02-27 16:51:04', '12');
INSERT INTO `spu` VALUES ('5', '切削液', '', '/image/item/c78870c47fb34f0aad3cdac3c6ecb608.jpg', '6500.00', '50000', '89', '-1', '2020-02-27 16:54:12', '2020-02-27 16:54:12', '12');
INSERT INTO `spu` VALUES ('6', '新美科qualstar ap', '', '/image/item/dd6ebdb0ab104cdabbe5d6d3025dda95.jpg', '7800.00', '50000', '89', '-1', '2020-02-27 16:55:52', '2020-02-27 16:55:52', '12');
INSERT INTO `spu` VALUES ('7', '美孚润滑油', '', '/image/item/3ca22728382e49868f2037718a8ea02c.jpg', '710.00', '50000', '46', '-1', '2020-02-27 16:58:06', '2020-02-27 16:58:06', '12');
INSERT INTO `spu` VALUES ('8', '切削液', '', '/image/item/4326c3a05a864d2ea534d8994e343611.jpg', '610.00', '50000', '89', '-1', '2020-02-27 16:59:12', '2020-02-27 16:59:12', '12');
INSERT INTO `spu` VALUES ('9', '航空胶带', '', '/image/item/33a45403f9cd47d09091b497e287f8d0.jpg', '247.00', '50000', '63', '-1', '2020-02-27 17:02:03', '2020-02-27 17:02:03', '13');
INSERT INTO `spu` VALUES ('10', '下吸式喷枪', '', '/image/item/be743d32256f41f88f1ab25d2859b39e.0', '255.00', '50000', '6', '-1', '2020-02-27 17:06:34', '2020-02-27 17:06:34', '14');
INSERT INTO `spu` VALUES ('11', '岩田喷枪', '', '/image/item/e4fc753564414b44bc5f858972892656.jpg', '980.00', '50000', '6', '-1', '2020-02-27 17:07:38', '2020-02-27 17:07:38', '14');
INSERT INTO `spu` VALUES ('12', '美国固瑞克喷枪', '', '/image/item/914c3e7a9502489080e0e74844efe5e0.4mm）', '13424.00', '50000', '6', '-1', '2020-02-27 17:08:28', '2020-02-27 17:08:28', '14');
INSERT INTO `spu` VALUES ('13', '屏蔽胶带', '', '/image/item/03647b40b27b4aac9969f52d55bee1c3.jpg', '300.00', '50000', '63', '-1', '2020-02-27 17:10:50', '2020-02-27 17:10:50', '15');
INSERT INTO `spu` VALUES ('14', '塑铜线 慧远', '', '/image/item/b2c4a3c0b855473180199ebb7b2a5f8b.5 100,/image/item/f8140711d6ac4ea4bb78a725a2a83a99.5 100图片2,/image/item/f9cb936f31cb40439de0e854c82a2df8.5 100图片3', '202.00', '50000', '19', '-1', '2020-02-27 17:13:44', '2020-02-27 17:13:44', '15');
INSERT INTO `spu` VALUES ('15', '公牛插线板', '', '/image/item/c970ad7857af440da8f81b7e52b26c47.5米', '162.50', '50000', '20', '-1', '2020-02-27 17:15:39', '2020-02-27 17:15:39', '15');
INSERT INTO `spu` VALUES ('16', '公牛', '', '/image/item/47c2f40c37344cf0af30581bc034303e.jpg,/image/item/320a95000a4542b5a857fe9776a78c7d.jpg,/image/item/e6893a8b316f4919bb769a92e66bfb50.jpg,/image/item/0b1a570ad75d4f798ad12304f77ed01f.jpg', '282.00', '50000', '20', '-1', '2020-02-27 17:16:59', '2020-02-27 17:16:59', '15');
INSERT INTO `spu` VALUES ('17', '施耐德空气开关', '', '/image/item/5603570acf014960a6e766c5978ba404.jpg,/image/item/50ec2178949145b69a09123b814794f2.jpg,/image/item/385c844d9d9e45fa8f06ea2d543cbe90.jpg', '194.00', '50000', '20', '-1', '2020-02-27 17:18:27', '2020-02-27 17:18:27', '15');
INSERT INTO `spu` VALUES ('18', '海洋王', '', '/image/item/31f9bd78c51a41148aedeae25e703ee3.jpg', '181.00', '50000', '82', '-1', '2020-02-27 17:20:12', '2020-02-27 17:20:12', '15');
INSERT INTO `spu` VALUES ('19', '塑料编织管', '', '/image/item/bce7c4df375643b2a69562cfe6f50a83.jpg', '273.00', '50000', '83', '-1', '2020-02-27 17:27:06', '2020-02-27 17:27:06', '15');
INSERT INTO `spu` VALUES ('20', '塑料编织管', '', '/image/item/36a43b5a4d87409888391b4ff7ad42f3.jpg', '169.00', '50000', '83', '-1', '2020-02-27 17:28:05', '2020-02-27 17:28:05', '15');
INSERT INTO `spu` VALUES ('21', '塑料编织管', '', '/image/item/8cbf57f5ae624a8bab614ced48896a04.jpg', '169.00', '50000', '83', '-1', '2020-02-27 17:33:54', '2020-02-27 17:33:54', '15');
INSERT INTO `spu` VALUES ('22', '杜邦R22（22.7KG）制冷剂', '', '/image/item/6083d7d2f303499f91ca272afbc84917.7KG）制冷剂', '965.00', '50000', '48', '-1', '2020-02-27 17:35:56', '2020-02-27 17:35:56', '15');
INSERT INTO `spu` VALUES ('23', '奇峰氧气管（三胶两线）', '', '/image/item/2ccd2057aec543668506cde749fb62da.jpg,/image/item/b68b92c911d74f0e8d9f09ff7298a951.jpg', '162.00', '50000', '83', '-1', '2020-02-27 17:37:51', '2020-02-27 17:37:51', '15');
INSERT INTO `spu` VALUES ('24', '佛山镇流器', '', '/image/item/50364bc197f94f5c859cb6cc38fdb22b.jpg,/image/item/c61ad34ef21e466bae9b73accc40d6d5.jpg,/image/item/e6b64385c6944d49b73330cb6e0b06dc.jpg', '18.30', '50000', '82', '-1', '2020-02-27 17:40:12', '2020-02-27 17:40:12', '15');
INSERT INTO `spu` VALUES ('25', '正泰防水插座', '', '/image/item/6645b278bfb24333bbef79afab15cdab.jpg', '20.00', '50000', '20', '-1', '2020-02-27 17:41:36', '2020-02-27 17:41:36', '15');
INSERT INTO `spu` VALUES ('26', '德力西旋转开关', '', '/image/item/ca9728b59e59404c83fe9cc7e14dc77c.jpg', '14.00', '50000', '20', '-1', '2020-02-27 17:50:17', '2020-02-27 17:50:17', '15');
INSERT INTO `spu` VALUES ('27', '松下电池', '', '/image/item/404adfad7bd24ac09ddd122fdb7dcda7.png', '14.00', '50000', '26', '-1', '2020-03-02 09:57:05', '2020-03-02 09:57:05', '15');
INSERT INTO `spu` VALUES ('28', '毛绒布', '', '/image/item/592ba156da454201bd452cfdc7883d7a.jpg', '48.00', '50000', '42', '-1', '2020-03-02 09:58:31', '2020-03-02 09:58:31', '15');
INSERT INTO `spu` VALUES ('29', '除锈剂', '', '/image/item/3c365dc572f643f2a9a60e3dc7d51fe0.jpg,/image/item/5dc36b30a01d4e3fa6367867af04e726.jpg', '42.00', '50000', '51', '-1', '2020-03-02 10:01:12', '2020-03-02 10:01:12', '15');
INSERT INTO `spu` VALUES ('30', '五孔插座', '', '/image/item/df8227e1711a4f2eb5f472a1bab53a92.jpg', '24.00', '50000', '20', '-1', '2020-03-02 10:02:29', '2020-03-02 10:02:29', '15');
INSERT INTO `spu` VALUES ('31', '吹尘枪', '', '/image/item/8163b68737c241deacf2e601ddaa16ca.jpg', '15.00', '50000', '6', '-1', '2020-03-02 10:03:57', '2020-03-02 10:03:57', '15');
INSERT INTO `spu` VALUES ('32', '塑铜线 4平方慧远', '', '/image/item/5fa2023e5b0a434cbe9370cf7f390f44.jpg,/image/item/85344abe6a56409e9c53dce1a11078a3.jpg', '361.00', '50000', '19', '-1', '2020-03-02 10:05:27', '2020-03-02 10:05:27', '15');
INSERT INTO `spu` VALUES ('33', '施耐德', '', '/image/item/30137d2dc81d464fbea424140d4053cd.jpg', '211.00', '50000', '20', '-1', '2020-03-02 10:06:17', '2020-03-02 10:06:17', '15');
INSERT INTO `spu` VALUES ('34', '飞利浦节能灯泡', '', '/image/item/6fcee6ec8687491392443d617d2b3c9e.jpg,/image/item/bd935564dc0f4db0998a878a17f6e304.jpg', '18.00', '50000', '82', '-1', '2020-03-02 10:07:26', '2020-03-02 10:07:26', '15');
INSERT INTO `spu` VALUES ('35', '导轨油', '', '/image/item/2d8fe2bd3ce842a3b5acdbc5b779787f.jpg', '540.00', '50000', '88', '-1', '2020-03-02 10:09:32', '2020-03-02 10:09:32', '15');
INSERT INTO `spu` VALUES ('36', '导轨油', '', '/image/item/2730544eab984fcea684b8ac307c3e76.jpg', '800.00', '50000', '88', '-1', '2020-03-02 10:10:53', '2020-03-02 10:10:53', '15');
INSERT INTO `spu` VALUES ('37', '公牛三联插线板', '', '/image/item/e24936df98cd436c83258bcce3ad85b8.jpg', '51.70', '50000', '20', '-1', '2020-03-02 10:12:22', '2020-03-02 10:12:22', '15');
INSERT INTO `spu` VALUES ('38', '索尼5号电池', '', '/image/item/14b11ae2471e494480722dd1374aaa3a.jpg', '8.00', '50000', '26', '-1', '2020-03-02 10:13:54', '2020-03-02 10:13:54', '15');
INSERT INTO `spu` VALUES ('39', '固力顺G48冷却液4L', '', '/image/item/463f1943edc4441d8d59fa013b941033.jpg', '165.00', '50000', '89', '-1', '2020-03-02 10:16:11', '2020-03-02 10:16:11', '15');
INSERT INTO `spu` VALUES ('40', '德力西BK-50型控制变压器', '', '/image/item/a2229d68222c44259705383b3b60caa3.jpg,/image/item/5e00c7ab75e14a17b229441b5e6d0df0.jpg', '65.00', '50000', '22', '-1', '2020-03-02 10:18:30', '2020-03-02 10:18:30', '15');
INSERT INTO `spu` VALUES ('41', '超霸9V电池', '', '/image/item/25a6a62615034ba4b4fcbd73c2412eeb.jpg', '15.00', '50000', '26', '-1', '2020-03-02 10:19:42', '2020-03-02 10:19:42', '15');
INSERT INTO `spu` VALUES ('42', '公牛1.8米插座', '', '/image/item/6c90f087ca0146f694a604a22bf5b769.8米插座', '30.60', '50000', '20', '-1', '2020-03-02 10:21:24', '2020-03-02 10:21:24', '15');
INSERT INTO `spu` VALUES ('43', '世达-重型方钢台虎钳', '', '/image/item/676ba5bff69c424db08c53b4a6a06644.jpg', '1574.00', '50000', '3', '-1', '2020-03-02 10:23:05', '2020-03-02 10:23:05', '15');
INSERT INTO `spu` VALUES ('44', '电池3.6V', '', '/image/item/9fd049bb9cb3400ebf1ce26176787aeb.6V', '21.50', '50000', '26', '-1', '2020-03-02 10:24:07', '2020-03-02 10:24:07', '15');
INSERT INTO `spu` VALUES ('45', '工作灯', '', '/image/item/1e03709377764b9eb0533f3f7a258330.jpg', '97.50', '50000', '82', '-1', '2020-03-02 10:24:57', '2020-03-02 10:24:57', '15');
INSERT INTO `spu` VALUES ('46', '数显卡尺150mm', '', '/image/item/1bcc9df19b80484bb7f35540de38799c.jpg', '199.00', '50000', '5', '-1', '2020-03-02 10:25:38', '2020-03-02 10:25:38', '15');
INSERT INTO `spu` VALUES ('47', '塑料布', '', '/image/item/7b70afd2c45c462a98a7f2219c706f0a.jpg', '873.00', '50000', '63', '-1', '2020-03-02 10:32:00', '2020-03-02 10:32:00', '15');
INSERT INTO `spu` VALUES ('48', '帆布', '', '/image/item/7fc5522adc264fdfbded79a9d24946d1.png', '479.00', '50000', '63', '-1', '2020-03-02 10:33:06', '2020-03-02 10:33:06', '15');
INSERT INTO `spu` VALUES ('49', '氩弧焊嘴', '', '/image/item/73842008e0d54af486b29afcc189a42d.jpg', '2.00', '50000', '8', '-1', '2020-03-02 10:34:00', '2020-03-02 10:34:00', '15');
INSERT INTO `spu` VALUES ('50', '天棚钨极', '', '/image/item/c9a2416627b5413cbb888fb2eb6a1952.jpg', '13.00', '50000', '8', '-1', '2020-03-02 10:34:48', '2020-03-02 10:34:48', '15');
INSERT INTO `spu` VALUES ('51', '板刷', '', '/image/item/fa6c9ced6b95456faefa7538b6154c4b.jpg', '39.40', '50000', '3', '-1', '2020-03-02 10:35:50', '2020-03-02 10:35:50', '15');
INSERT INTO `spu` VALUES ('52', '色带', '', '/image/item/8be4a7c97dbb4756895ab69f1837f10c.jpg', '10.00', '50000', '64', '-1', '2020-03-02 10:37:08', '2020-03-02 10:37:08', '15');
INSERT INTO `spu` VALUES ('53', '4分水龙头', '', '/image/item/1d534cb63b954294ab6ddef6bd02ec68.jpg', '20.00', '50000', '81', '-1', '2020-03-02 10:37:47', '2020-03-02 10:37:47', '15');
INSERT INTO `spu` VALUES ('54', '显像剂', '', '/image/item/50039d134568424680da7d3118fab508.jpg', '28.00', '50000', '85', '-1', '2020-03-02 10:39:22', '2020-03-02 10:39:22', '17');

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
INSERT INTO `spu_detail` VALUES ('1', '<p></p><p class=\"media-wrap image-wrap\"></p><p></p><p></p><p></p>', '{\"151\":\"200L\",\"152\":\"桶\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('2', '<p></p>', '{\"151\":\"HOCUT 5759 AL-S\",\"152\":\"桶\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('3', '<p></p><p>美孚威达数目系列是润滑机床导轨及滑槽的专门产品，能满足机床因高度及慢速而必须消除爬行及震颤等现象之要求。油膜强度极高，能符合重负荷导轨的要求，各大机床制造厂皆使用。</p><p></p>', '{\"77\":\"美孚威达2号\",\"78\":\"升\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('4', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/1ae153d00d8746619911ff6953ff5d15.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/011f2fa5c9ae418fb95236333491ad9f.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/6a600832a2804e8fbe637c4924f7b107.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/a1d95ba7ce6542b3807d6833d72d3993.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/0b46a0ba44f84bc68b1bd97f082e5324.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/a441a906b9784c6090ec2db7f3844d99.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/2398c204fc5348d0bc095790940ba137.jpg\"/></div><p></p>', '{\"77\":\"170KG\",\"78\":\"吨\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('5', '<p></p>', '{\"151\":\"PETROFER TS-1008\",\"152\":\"桶\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('6', '<p></p>', '{\"151\":\"200L\",\"152\":\"桶\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('7', '<p></p>', '{\"77\":\"美孚威达3号\",\"78\":\"升\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('8', '<p></p>', '{\"151\":\"HOCUT B 80 A\",\"152\":\"桶\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('9', '<p></p>', '{\"107\":\"W-417 SH（透明）\",\"108\":\"卷\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('10', '<p></p>', '{\"7\":\"W77-3.0\",\"8\":\"把\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('11', '<p></p>', '{\"7\":\"W-101-152S\",\"8\":\"台\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('12', '<p></p>', '{\"7\":\"288931（喷嘴1.4mm）\",\"8\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('13', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/deb9b746d9384122becf09f3dcc6be10.jpg\"/></div><p></p>', '{\"107\":\"50米\",\"108\":\"盘\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('14', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/eef42ef6cf394e6da277fb5c31f89801.5 100详情\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/d4e03a85177b47738d85fb2f864f15aa.5 100详情2\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/2b6587dc9e40406ca0a6b0984839bbb7.5 100详情3\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/8a6e3cfec88b42e3918c94541df0d474.5 100详情4\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/0a8c61868c4d4c5b99e7d49cd658070e.5 100详情5\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/95ab0a3d25dc46a08f944b2d79f064a1.5 100详情6\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/da0a9560f49e4d7b98ab3959276a9686.5 100详情7\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/a2bf8a945bbf44e88b482f5b3384644e.5 100详情8\"/></div><p></p>', '{\"29\":\"2.5*100\",\"30\":\"米\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('15', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/174559fb80674a16a5a0dc6ae3d33f32.5米详情\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/d06291672a7143a9a3584db506345790.5米详情2\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/7cbca7f35a9d4072950dd575cb639377.5米详情3\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/8a01dfa176e54583b5562c2d69e7def1.5米详情4\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/c9942064dd764ed99ee4a64750736343.5米详情5\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/64e4d58cc8b548c4bc44e4df9ddc4981.5米详情6\"/></div><p></p>', '{\"31\":\"7.5米\",\"32\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('16', '<p></p><div class=\"media-wrap image-wrap\"><img class=\"media-wrap image-wrap\" src=\"http://localhost:8082/mall/image/item/1065e6dd565c4c29a2f4864267c97424.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img class=\"media-wrap image-wrap\" src=\"http://localhost:8082/mall/image/item/eabb6fe999dc4266a80a481f68266c11.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img class=\"media-wrap image-wrap\" src=\"http://localhost:8082/mall/image/item/418c05a32a7144e2984529bab2595387.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img class=\"media-wrap image-wrap\" src=\"http://localhost:8082/mall/image/item/03b526dc1c7f411a9f841be1ffe9c6c2.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img class=\"media-wrap image-wrap\" src=\"http://localhost:8082/mall/image/item/60e66d1cd71d48febecec94ab4cf647c.jpg\"/></div><p></p>', '{\"31\":\"GN-803（30米）\",\"32\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('17', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/1aa32e933b394f5aac343066831ec798.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/25d25188560e4558a475ea333df531e1.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/6b3027bc6cdd4f3587e93f9b57f5f33f.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/05a1fb5441d64c61ad5b57422ac086ff.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/fb10193c84fb4e5b8ba120dfa5b1fd8b.jpg\"/></div><p></p>', '{\"31\":\"GV2ME16/9-14A\",\"32\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('18', '<p>海洋王IW5110 固态强光防爆头灯 BRW5130A</p><p></p><p>概况：</p><p>　　适用于各种易燃易爆场所作移动照明使用。</p><p>一 性能:</p><p>　／采用冷光源，发热量低，工作电流小，防爆性能优良，可在各种易燃易爆场所安全、可靠工作。</p><p>　／采用进口大功率超高亮度LED固态光源，平均使用寿命长达10万小时，可达到长期免维护的效果。</p><p>　／大容量聚合物锂离子电池， 环保、无污染，一次充满电后连续照明时间长达15小时，免除频繁充电的烦恼，满足长时间当班工作的需要。</p><p>　／一灯两用，即可作正常照明使用，又增加了信号功能。</p><p>　／精密的结构设计，优质的抗冲击材料和精细化表面处理，防水、防尘，耐腐蚀性好。</p><p>　／体积小，整灯重量不超过200克，可头戴、手持或挂靠在身上使用，轻便灵活。</p><p>二 使用:</p><p>1、使用前，将专用充电器的插头插入头灯背部的充电孔内进行充电。充电时指示灯为绿色，充满电后指示灯变为红色；</p><p>2、按下头灯顶部的开关约0.2秒，头灯点亮；再次按下开关，则灯熄灭；</p><p>3、该灯具有防误操作功能，若按下开关时间小于0.2秒，则不会改变灯的点亮或熄灭状态；</p><p>4、持续按住开关，灯光明暗交替闪烁，可作信号联络或指示使用;</p><p>5、该灯有多种佩戴方式，可手持、腰挂、吊挂和头戴。头戴时将头灯的挂钩卡入安全帽的卡座上即可。</p><p>三 BRW5130A注意:</p><p>1、头灯严禁撞击、抛甩；若不慎将头灯掉进水或水性溶液里时，需及时取出并擦拭干净。</p><p>2、维修头灯必须由专业人员在安全场所进行。</p><p>四 技术参数:</p><p>额定电压:DC3.7V</p><p>额定容量:4Ah（IW5110） 2Ah（IW5110B）</p><p>光源（LED）: 额定功率：1W 平均使用寿命：100000h</p><p>连续放电时间:15h（IW5110） 7h（IW5110B）</p><p>充电时间:8h（IW5110） 6h（IW5110B）</p><p>电池充电时间:6～8h</p><p>电池使用寿命:1000（循环）</p><p>外形尺寸:86×64×75mm</p><p>重量:0.2kg（IW5110）0.15kg（IW5110B）</p><p>外壳防护等级:IP65</p>', '{\"139\":\"IW5110\",\"140\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('19', '<p></p>', '{\"141\":\"直径25\",\"142\":\"盘\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('20', '<p></p>', '{\"141\":\"直径12\",\"142\":\"盘\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('21', '<p></p>', '{\"141\":\"直径8\",\"142\":\"盘\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('22', '<p>产品介绍</p><p></p><p>1.品名：杜邦制冷剂R22</p><p></p><p>2.型号：R22</p><p></p><p>3.化学成分：CHCLF2（二氟一氯甲烷）</p><p></p><p>4.单位：瓶</p><p></p><p>5.规格：22.7KG/瓶</p>', '{\"81\":null,\"82\":\"瓶\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('23', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/c2bc8ce74f4a4ff99eea2f2e459538c3.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/66403d4a74ae4761b892c0aae2920df1.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/937e777e7fb74f7d9ce434a8387f5108.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/8314390c0c604f738257fc640f417117.jpg\"/></div><p></p>', '{\"141\":null,\"142\":\"盘\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('24', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/2f2a71efd1784a72a8f40964ade844c8.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/16878ff8519742be8c8dca9c70f906b5.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/553ed1365d544368832712a3c848ecd9.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/80ff2d64e10e4db2a689edc999fe2d8f.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/8e9f2fc2f90746848a8d021ed80a1a09.jpg\"/></div><p></p>', '{\"139\":\"32W\",\"140\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('25', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/fe7b244ca3224e5e8b4f7009bba02c2f.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/341ac905966d4d379b017da6238f2ad8.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/557dda1214054e89a5e4f716a83bceaa.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/e2c83ef9b6ad43de83cedd54dab30808.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/acd46831f39a45c98f21a50b0528816c.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/509674e6a9ee4b768f788a228f399c81.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/786575389c0945bf9b8b2887c293a29a.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/0e11dbb93c3e410f84550a158599bdd4.jpg\"/></div><p></p>', '{\"31\":null,\"32\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('26', '<p></p>', '{\"31\":null,\"32\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('27', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/dc225a9609ce4e85bbd55758048978f7.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/88be9541ca0e4aeea3b3b2b8fc025df1.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/22972430a1fd48b1bcb3ed0f12ddcdf2.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/1ed0f062885c480b80ec83795c3179a8.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/b0053f7df4e24e199e98dc09984abd8c.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/8702f4e8bfbe4740b89dda549b8fbcd7.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/a60fdd49f0a64959afcc54f8e094d11f.jpg\"/></div><p></p>', '{\"43\":\"1号\",\"44\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('28', '<p></p>', '{\"71\":\"1米宽\",\"72\":\"米\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('29', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/2ae8b816f65148cf97a317f0531968fb.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/9c7201123df14f73ba664e6d7c19de48.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/4102822eb5d944ee9b3f8b8c91777f7d.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/b80249a92d4b4fafaa7b9a1226f9f034.jpg\"/></div><p></p>', '{\"87\":\"WD-40\",\"88\":\"瓶\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('30', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/5b5af03d21544971887b58b3e42aef2d.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/828db8779d9f4751a61134bab31faf9b.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/52a94f0df53d48e29e31b91a46e49c55.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/a4fa5af91b084b29b2edc2e320b258a2.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/164caf5c600b4fadba823e2d3948d685.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/18581e4ddf4448a89a4e6ceb4668716a.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/906ff903a97b4871bdcdc5114761ae97.jpg\"/></div><p></p>', '{\"31\":null,\"32\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('31', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/92fe24d6f46a493a817190c4a59f6c7c.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/e3ad5bdedc2c435fb86dbc4b0bf284b2.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/b27c654fb4a94556ac5d48d5396ab253.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/f087a7b001894af09fb0371c3f93182b.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/08bfd3d4701848218794b8f3d9e79c0f.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/aafe756aac14421cab7838a0a4a70138.jpg\"/></div><p></p>', '{\"7\":\"H-606\",\"8\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('32', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/535b60e00d00409e89cac8dc8fe54e73.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/4383b07f817647d98649fb6bb68f2076.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/228a4719b0b044cbb8f26c01966bb97a.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/b662e4f92c1d47c5a7a4ce70252093e1.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/8f7be4b227f846c1bd8da99eed7e52de.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/204d7c621b5a4fbd833a3fb6b1df38d4.jpg\"/></div><p></p>', '{\"29\":\"100米\",\"30\":\"盘\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('33', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/7ea8f3178cf443288bf99bab84ceb92e.jpg\"/></div><p></p>', '{\"31\":\"LCID09BL\",\"32\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('34', '<p></p><div class=\"media-wrap image-wrap\"><img class=\"media-wrap image-wrap\" src=\"http://localhost:8082/mall/image/item/ed206e76331741a792ddbff0168a5de4.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img class=\"media-wrap image-wrap\" src=\"http://localhost:8082/mall/image/item/6361383cc7674146839ea1edcdabfe96.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img class=\"media-wrap image-wrap\" src=\"http://localhost:8082/mall/image/item/6818754580964c7ba3ed97c6064352f9.jpg\"/></div><p></p>', '{\"139\":null,\"140\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('35', '<p>美孚威达数目系列是润滑机床导轨及滑槽的专门产品，能满足机床因高度及慢速而必须消除爬行及震颤等现象之要求。油膜强度极高，能符合重负荷导轨的要求，各大机床制造厂皆使用</p>', '{\"149\":\"2号 18L\",\"150\":\"桶\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('36', '<p>美孚威达数目系列是润滑机床导轨及滑槽的专门产品，能满足机床因高度及慢速而必须消除爬行及震颤等现象之要求。油膜强度极高，能符合重负荷导轨的要求，各大机床制造厂皆使用</p>', '{\"149\":\"3号 18L\",\"150\":\"桶\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('37', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/c4acab2e478943deb409d8cf1395151b.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/46ac8870f4064173a389727e0b2cad06.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/ac25299434c844fdafff0ea5435ada55.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/2ee376b67a424409a4fa9e394f7f0e40.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/ca87657808c34b1bb35e87de398d35e5.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/79e180e93be5451692e92e682773b92b.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/c4dd95476a504a619ec351985bf52ad9.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/ae607436835a43f3b439e25a7783ec44.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/05a3103071ea4b52a3a50931a387677f.jpg\"/></div><p></p>', '{\"31\":null,\"32\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('38', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/14bf0c89576c4f1083c12f2097d14f60.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/b87b7e913b07410cadca17708906632c.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/0b2decedb199428fa3fccacf100d73d4.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/b77e65973a4645ebaaab7ec03c7f6880.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/92fb0c9dff2747dcb6c5a5c87164db53.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/f4059b0a812048988882035dc9a620dc.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/034cc7c2670a4bd38c9c9fff1e39a585.jpg\"/></div><p></p>', '{\"43\":\"GR2032\",\"44\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('39', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/7e091471336f4195bb90db9f3883c8e0.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/7158783e826f4eec9d6681ea496626a7.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/8c9ca81c8496467fa61f706df2940e5c.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/fad4a2c8f2e843ba8ef346dcda45ced2.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/39658532816e4c7784c908b03977207c.jpg\"/></div><p></p>', '{\"151\":null,\"152\":\"桶\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('40', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/33d3b27dd9b24463a52ba6c29f4d54c4.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/044668bcc0bf4a32838c689b345eddba.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/3ef280e079d9488d8e0276a86ae10d04.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/d0c8c5abd7c34720899f7e48693d3ebf.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/7af962c2a04544ac9d2d84ebb7a308d1.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/047ab36204584614971af2ca168385ed.jpg\"/></div><p></p>', '{\"35\":null,\"36\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('41', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/044ff43fb81c4fb29e54d06b6c64d1f6.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/651eae779faa488b91b0634b85db8e9e.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/1b7fd692d9614b37abb6980713e280b8.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/cf809e14e4dc4ed4bea6af2c20980a84.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/7d1b635c1c0c4636b7649534b8b60ed1.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/b1d8d07253ee401ca41961cd760300dd.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/10d0933552aa4a7c9a81a665e4058979.jpg\"/></div><p></p>', '{\"43\":null,\"44\":\"节\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('42', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/837292f3384148129cc705296ae8ffc0.8米插座详情\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/52fc94fef6ca48ccb11420a3df8786c8.8米插座详情2\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/6dd733b00bf143b888cd50367edccbc2.8米插座详情3\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/5d941dac313641919adfa9cb298aad1f.8米插座详情4\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/88e06aebadba4e56bd933c152c6bf58a.8米插座详情5\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/198f4710e30c4c61a20978ad60f3f643.8米插座详情6\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/7a732f39541140418a1156555e5a6ede.8米插座详情7\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/0ee63c9d652349a69f3cb0bf7da252ec.8米插座详情8\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/541ecb5bfb1945bf95a7de9e33b8a100.8米插座详情9\"/></div><p></p>', '{\"31\":null,\"32\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('43', '<p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/79b1f7d642e04e5696b850a3700aef22.png\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/96a2af61eb724038a18a0ca51980b7d4.jpg\"/></div><p></p><div class=\"media-wrap image-wrap\"><img src=\"http://localhost:8082/mall/image/item/21864d7d5a5b4943b49e08130adcacd5.jpg\"/></div><p></p>', '{\"1\":null,\"2\":\"台\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('44', '<p></p>', '{\"43\":null,\"44\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('45', '<p></p>', '{\"139\":\"JC37-3\",\"140\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('46', '<p></p>', '{\"5\":null,\"6\":\"把\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('47', '<p></p>', '{\"107\":\"6m*420m\",\"108\":\"张\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('48', '<p></p>', '{\"107\":\"6m*8m\",\"108\":\"块\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('49', '<p></p>', '{\"11\":null,\"12\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('50', '<p></p>', '{\"11\":\"10根\",\"12\":\"盒\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('51', '<p></p>', '{\"1\":\"1-12\",\"2\":\"套\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('52', '<p>ERC09色带打印纸57*30MM</p>', '{\"109\":\"ERC-09\",\"110\":\"盒\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('53', '<p></p>', '{\"137\":null,\"138\":\"个\"}', '', '', '');
INSERT INTO `spu_detail` VALUES ('54', '<p></p>', '{\"143\":\"DPT-8\",\"144\":\"瓶\"}', '', '', '');

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
  `salt` varchar(50) DEFAULT '' COMMENT '登录密码的盐',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_account` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='用户表、商城会员表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '管理员', 'a571d622bcd6efc427f07a239463630c', '', '', '0', '', '', '', '1', '2020-02-26 17:30:15', '', '2', '21978503993d4802a243426698fc9cbb');
INSERT INTO `sys_user` VALUES ('2', '管理员2', '0f52bf4ba59edc68813925729773be66', '', '', '0', '', '', '', '1', '2020-02-26 17:30:55', '', '2', 'ff587dc7133a433b8419f818b744e323');
INSERT INTO `sys_user` VALUES ('3', '159厂', 'bf81085d4a3f65d4c8ece2f2fce98b14', '', '', '0', '', '', '', '1', '2020-02-26 17:31:12', '', '4', '07b1cb4a64f848c1a79505960551d85a');
INSERT INTO `sys_user` VALUES ('4', '159厂2', '8a92658f14ef6f0181500af72c0e5060', '', '', '0', '', '', '', '1', '2020-02-26 17:31:17', '', '4', '8de95bf5b7c44d349a77cfd5684f1c23');
INSERT INTO `sys_user` VALUES ('5', '翁达盛通', '76013df95371a040310d82d10bc408d9', '', '', '0', '', '', '', '1', '2020-02-26 17:32:35', '', '6', '5f76f5e8a4de4d318b48efa054db4a91');
INSERT INTO `sys_user` VALUES ('6', '意隆达', '9d4e3a1c4eae2ea29fa7c08a1f908bde', '', '', '0', '', '', '', '1', '2020-02-26 17:32:57', '', '7', '7b8c46c7a1c7415795010bc098a5aea4');
INSERT INTO `sys_user` VALUES ('7', '航天创宇', '8d1650f4854694d8c20b4498fa3eeefc', '', '', '0', '', '', '', '1', '2020-02-26 17:33:15', '', '8', '6ad65d9ffbf54220833a16bccfdfc5e3');
INSERT INTO `sys_user` VALUES ('8', '利惠军顺', '751d30d8215b097541057e5c3930e8a3', '', '', '0', '', '', '', '1', '2020-02-26 17:33:30', '', '9', '59ca7d16160d4eab9d8c98aaa9005a4a');
INSERT INTO `sys_user` VALUES ('9', '博润科', 'f37813e743b8ca073944f031e7086900', '', '', '0', '', '', '', '1', '2020-02-26 17:33:42', '', '10', '722af2df2c6f41a9aad096e64a2e2e1d');
INSERT INTO `sys_user` VALUES ('10', '德高航空', '797a9551562c6c9b1c05f0af9039afb5', '', '', '0', '', '', '', '1', '2020-02-26 17:33:54', '', '11', '8f700ca21b5745be9d48019eb8872263');
INSERT INTO `sys_user` VALUES ('11', '天宇协力', 'd7ca0f62fb2bf1351929857924a32dc9', '', '', '0', '', '', '', '1', '2020-02-26 17:34:08', '', '12', '2c587605befd4e0f91bf315070f2fa2b');
INSERT INTO `sys_user` VALUES ('12', '森思泛亚', 'dda3030d6e6e983073eaa84bb3b59538', '', '', '0', '', '', '', '1', '2020-02-26 17:34:21', '', '13', 'a2ead126a623456cbb9b506d086aac2d');
INSERT INTO `sys_user` VALUES ('13', '华北工', 'cbc2f681689151adc967dfe608d2346a', '', '', '0', '', '', '', '1', '2020-02-26 17:34:39', '', '14', '3a586d77c6674aa891bee005121ab0f5');
INSERT INTO `sys_user` VALUES ('14', '天丰兴业', 'a0921d5e3ed4c2e80bf11ce6b81783d4', '', '', '0', '', '', '', '1', '2020-02-26 17:34:54', '', '15', '6804dd1dce144446a0e93adc5b19303b');
INSERT INTO `sys_user` VALUES ('15', '信思福', '84f89f21da4699afc6b02b70c6e5c821', '', '', '0', '', '', '', '1', '2020-02-26 17:35:09', '', '16', 'c346f84626d44bbfb59b64c614b6c113');
INSERT INTO `sys_user` VALUES ('16', '凯天诚信', '7eea38e18b8bc953f10d1d16aab0c253', '', '', '0', '', '', '', '1', '2020-02-26 17:35:22', '', '17', '903c35d9319444fab7eab609ee3ff1a3');
INSERT INTO `sys_user` VALUES ('17', '海鹰安全', 'd1613c123ac89b00dddb8e6c1322530b', '', '', '0', '', '', '', '1', '2020-02-26 17:35:36', '', '18', '66139fb7a16f42bb85f6de358f142692');
INSERT INTO `sys_user` VALUES ('18', '范海峰', 'ffb106e440023bc876e3669ba67cf353', '', '', '0', '', '', '13581803971', '1', '2020-02-26 17:41:39', '', '21', 'cf19b35a193d437189ec06d013ea243e');
INSERT INTO `sys_user` VALUES ('19', '杨宏宇', '7e89a650c19f7ae63dbb8a33c4a66321', '', '', '0', '', '', '13264087810', '1', '2020-02-26 17:41:50', '', '22', '5b9d9669d4fb44e3b51ea0b06cfeba72');
INSERT INTO `sys_user` VALUES ('20', '褚颖', 'f6f7ba135aae9a4cf11aeb3b4f997a7c', '', '', '0', '', '', '17310645575', '1', '2020-02-26 17:42:02', '', '23', '524b8d0b240247ef86fb9a7f25890dec');
INSERT INTO `sys_user` VALUES ('21', '李宏', 'ae16797cfcfd0448794ba8d6ca7a38f2', '', '', '0', '', '', '18910673662', '1', '2020-02-26 17:42:25', '', '24', 'a12619260e884d6a9d5709335b958f5a');
INSERT INTO `sys_user` VALUES ('22', '徐丽', 'fd9c215a8c425f1fc1952b0485aef7a7', '', '', '0', '', '', '13611334709', '1', '2020-02-26 17:42:25', '', '24', '073bd572b9904d5a96cb7989126b1c0f');
INSERT INTO `sys_user` VALUES ('23', '刘佳', '1dcf2c3ca4afed10ae9be6425807c7f7', '', '', '0', '', '', '13501238201', '1', '2020-02-26 17:42:51', '', '25', '00996cb901254b758c7aec6810fb22ff');
INSERT INTO `sys_user` VALUES ('24', '史玮', '169dda1c597871b4d8e7bb286fc95b06', '', '', '0', '', '', '18210989525', '1', '2020-02-26 17:44:53', '', '26', '5ddc5d302f934f65886a0d4ead218c7d');
INSERT INTO `sys_user` VALUES ('25', '王强', 'e3d004d9ffbaea9a1e5111fb4d70795f', '', '', '0', '', '', '王强', '1', '2020-02-26 17:45:09', '', '27', '10084398da9a453bb50edb89bcf046b2');
