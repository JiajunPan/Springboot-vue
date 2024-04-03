/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:3306
 Source Schema         : projectdb

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 08/09/2023 20:15:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `adminid` int NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `adminname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理员用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`adminid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'hanszeyu', 'admin');
INSERT INTO `admin` VALUES (2, 'tao2', 'hTtcPpPGAw');
INSERT INTO `admin` VALUES (3, 'haoi76', 'aC0Vqm1vUf');
INSERT INTO `admin` VALUES (4, 'smts', 'VgNgD7u1cR');
INSERT INTO `admin` VALUES (6, 'admin', '111111');

-- ----------------------------
-- Table structure for balance
-- ----------------------------
DROP TABLE IF EXISTS `balance`;
CREATE TABLE `balance`  (
  `userid` bigint NOT NULL COMMENT '用户id',
  `balance` decimal(12, 2) NULL DEFAULT NULL COMMENT '余额',
  INDEX `userid1`(`userid` ASC) USING BTREE,
  CONSTRAINT `userid1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of balance
-- ----------------------------
INSERT INTO `balance` VALUES (1, 329113.60);
INSERT INTO `balance` VALUES (2, 155659.94);
INSERT INTO `balance` VALUES (3, 77377.00);
INSERT INTO `balance` VALUES (4, 86506.00);
INSERT INTO `balance` VALUES (5, 25578.00);
INSERT INTO `balance` VALUES (6, 290000.00);
INSERT INTO `balance` VALUES (7, 78299.00);
INSERT INTO `balance` VALUES (8, 47281.00);
INSERT INTO `balance` VALUES (9, 21285.00);
INSERT INTO `balance` VALUES (10, 11033.00);
INSERT INTO `balance` VALUES (12, 22333.00);

-- ----------------------------
-- Table structure for buy
-- ----------------------------
DROP TABLE IF EXISTS `buy`;
CREATE TABLE `buy`  (
  `userid` bigint NOT NULL COMMENT '用户id',
  `productid` int NOT NULL COMMENT '产品id',
  `buydate` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '购买时间',
  `purchaseamount` decimal(10, 2) NOT NULL COMMENT '购买金额',
  `yield` decimal(10, 5) NOT NULL COMMENT '收益率',
  INDEX `useridbuy`(`userid` ASC) USING BTREE,
  INDEX `yield`(`yield` ASC) USING BTREE,
  INDEX `productidbuy`(`productid` ASC) USING BTREE,
  CONSTRAINT `useridbuy` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buy
-- ----------------------------
INSERT INTO `buy` VALUES (8, 1, '2023-08-18 15:39:35', 72006.33, 0.05945);
INSERT INTO `buy` VALUES (4, 5, '2023-08-18 15:40:23', 48982.90, 0.03782);
INSERT INTO `buy` VALUES (9, 10, '2023-08-18 15:41:37', 8359.77, 0.04819);
INSERT INTO `buy` VALUES (8, 10, '2023-08-18 15:41:37', 33975.12, 0.04819);
INSERT INTO `buy` VALUES (5, 9, '2023-08-18 15:41:25', 78227.64, 0.02103);
INSERT INTO `buy` VALUES (2, 2, '2023-08-18 15:39:47', 50088.85, 0.03611);
INSERT INTO `buy` VALUES (7, 7, '2023-08-18 15:41:04', 22547.12, 0.05482);
INSERT INTO `buy` VALUES (9, 3, '2023-08-18 15:40:04', 92797.50, 0.04497);
INSERT INTO `buy` VALUES (8, 9, '2023-08-18 15:41:25', 7061.23, 0.02103);
INSERT INTO `buy` VALUES (3, 10, '2023-08-18 15:41:37', 95845.83, 0.04819);
INSERT INTO `buy` VALUES (7, 5, '2023-08-18 15:40:23', 82616.75, 0.03782);
INSERT INTO `buy` VALUES (4, 2, '2023-08-18 15:39:47', 26294.70, 0.03611);
INSERT INTO `buy` VALUES (10, 10, '2023-08-18 15:41:37', 43061.74, 0.04819);
INSERT INTO `buy` VALUES (9, 8, '2023-08-18 15:41:15', 84936.13, 0.04166);
INSERT INTO `buy` VALUES (2, 4, '2023-08-18 15:40:13', 77412.68, 0.05526);
INSERT INTO `buy` VALUES (8, 5, '2023-08-18 15:40:23', 69596.57, 0.03782);
INSERT INTO `buy` VALUES (4, 5, '2023-08-18 15:40:23', 7090.44, 0.03782);
INSERT INTO `buy` VALUES (7, 3, '2023-08-18 15:40:04', 5849.34, 0.04497);
INSERT INTO `buy` VALUES (1, 7, '2023-08-18 15:41:04', 71364.57, 0.05482);
INSERT INTO `buy` VALUES (2, 7, '2023-08-18 15:41:04', 51092.50, 0.05482);
INSERT INTO `buy` VALUES (5, 5, '2023-08-18 15:40:23', 30787.33, 0.03782);
INSERT INTO `buy` VALUES (2, 6, '2023-08-18 15:40:42', 38723.22, 0.02491);
INSERT INTO `buy` VALUES (7, 8, '2023-08-18 15:41:15', 12510.08, 0.04166);
INSERT INTO `buy` VALUES (3, 6, '2023-08-18 15:40:42', 2132.61, 0.02491);
INSERT INTO `buy` VALUES (6, 1, '2023-09-04 18:07:47', 300000.00, 0.05945);
INSERT INTO `buy` VALUES (6, 4, '2023-09-04 18:07:55', 300000.00, 0.05526);
INSERT INTO `buy` VALUES (1, 1, '2023-09-08 09:38:07', 250000.00, 0.05945);

-- ----------------------------
-- Table structure for information
-- ----------------------------
DROP TABLE IF EXISTS `information`;
CREATE TABLE `information`  (
  `userid` bigint NOT NULL COMMENT '用户id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `birth` date NULL DEFAULT NULL COMMENT '生日',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  INDEX `userid`(`userid` ASC) USING BTREE,
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of information
-- ----------------------------
INSERT INTO `information` VALUES (1, '钱杰宏', '男', 24, '1999-08-14', 'qianjieh@outlook.com', '129 Flatbush Ave', '20321375355');
INSERT INTO `information` VALUES (2, 'Marjorie Foster', '女', 36, '1987-03-28', 'marjoriefoster@yahoo.com', '704 Bergen St', '13413710327');
INSERT INTO `information` VALUES (3, 'Joel Burns', '男', 29, '1994-02-24', 'joel2003@outlook.com', '坑美十五巷276号', '18147718233');
INSERT INTO `information` VALUES (4, 'Steven Holmes', '男', 26, '1997-06-24', 'holmess@gmail.com', '949 Flatbush Ave', '76078986826');
INSERT INTO `information` VALUES (5, '袁云熙', '男', 42, '1981-02-20', 'yuanyunxi@icloud.com', '乐丰六路195号', '14221917649');
INSERT INTO `information` VALUES (6, 'Christopher Mendez', '男', 26, '1997-02-11', 'chmendez6@hotmail.com', '491 1st Ave', '17792799269');
INSERT INTO `information` VALUES (7, '姜子韬', '男', 43, '1980-10-14', 'zjia@gmail.com', '919 Wicklow Road', '2859125607');
INSERT INTO `information` VALUES (8, 'Laura Robertson', '女', 24, '1999-07-17', 'robertson58@mail.com', '680 Columbia St', '2150381086');
INSERT INTO `information` VALUES (9, '龙嘉伦', '男', 37, '1986-02-28', 'jilong413@gmail.com', '527 Columbia St', '76966484058');
INSERT INTO `information` VALUES (10, 'Gloria Ward', '女', 24, '1999-08-14', 'gward725@gmail.com', '11 Fifth Avenue', '2168262591');
INSERT INTO `information` VALUES (12, '王浩', '男', 22, '2001-03-01', '未填写', '未填写', '未填写');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `productid` int NOT NULL AUTO_INCREMENT COMMENT '产品id',
  `productname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品名',
  `introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品介绍',
  `yield` decimal(10, 5) NULL DEFAULT NULL COMMENT '收益率',
  PRIMARY KEY (`productid`) USING BTREE,
  INDEX `yield`(`yield` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '月月利37号A现金管理型理财产品', '中高风险理财产品', 0.05950);
INSERT INTO `product` VALUES (2, '月月利13号C现金管理型理财产品', '中风险理财产品', 0.03611);
INSERT INTO `product` VALUES (3, '月月利24号D现金管理型理财产品', '中风险理财产品', 0.04497);
INSERT INTO `product` VALUES (4, '月月利2号H现金管理型理财产品', '中高风险理财产品', 0.05526);
INSERT INTO `product` VALUES (5, '月月利12号E现金管理型理财产品', '中风险理财产品', 0.03782);
INSERT INTO `product` VALUES (6, '月月利25号H现金管理型理财产品', '低风险理财产品', 0.02491);
INSERT INTO `product` VALUES (7, '月月利17号G现金管理型理财产品', '中高风险理财产品', 0.05482);
INSERT INTO `product` VALUES (8, '月月利10号H现金管理型理财产品', '中风险理财产品', 0.04166);
INSERT INTO `product` VALUES (9, '月月利8号B现金管理型理财产品', '低风险理财产品', 0.02103);
INSERT INTO `product` VALUES (10, '月月利6号F现金管理型理财产品', '中风险理财产品', 0.04819);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userid` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `protectquestion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密保问题',
  `protectanswer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '答案',
  `status` int NOT NULL COMMENT '用户状态',
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'ziyliu', '000000', '常用支付方式', 'Apple Pay', 0);
INSERT INTO `user` VALUES (2, 'weblouise', 'PBhEQ5Xtms', '常用支付方式', 'PayPal', 0);
INSERT INTO `user` VALUES (3, 'huzhiyuan306', 'ef9JfCn4XE', '常用支付方式', 'Apple Pay', 0);
INSERT INTO `user` VALUES (4, 'rm999', 'cnFujj7BRb', '常用支付方式', 'Apple Pay', 0);
INSERT INTO `user` VALUES (5, 'miosa', '4nbkriBPpk', '常用支付方式', 'PayPal', 2);
INSERT INTO `user` VALUES (6, 'chowwl', 'aaaaaa', 'aaaa', 'aaaa', 1);
INSERT INTO `user` VALUES (7, 'ihikari5', 'J40s0UcKXC', '常用支付方式', 'Apple Pay', 2);
INSERT INTO `user` VALUES (8, 'zhiyuanchen', 'nSvsi44OPf', '常用支付方式', 'Credit Card', 2);
INSERT INTO `user` VALUES (9, 'hasegawah', 'ECHTqIHap9', '常用支付方式', 'Credit Card', 0);
INSERT INTO `user` VALUES (10, 'swwo', '9r0JgluIHS', '常用支付方式', 'Apple Pay', 2);
INSERT INTO `user` VALUES (12, 'llaowang', '111111', '12345', '54321', 0);

SET FOREIGN_KEY_CHECKS = 1;
