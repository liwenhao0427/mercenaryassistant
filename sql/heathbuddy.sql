/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : heathbuddy

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 05/01/2022 10:19:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for game_ability_power
-- ----------------------------
DROP TABLE IF EXISTS `game_ability_power`;
CREATE TABLE `game_ability_power`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '佣兵名称',
  `ability_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '技能id',
  `ability_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '技能名称',
  `power` double(14, 2) NULL DEFAULT NULL COMMENT '权重',
  `cnt` int(11) NULL DEFAULT NULL COMMENT '次数',
  `win_cnt` int(11) NULL DEFAULT NULL COMMENT '胜利次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for game_action_result
-- ----------------------------
DROP TABLE IF EXISTS `game_action_result`;
CREATE TABLE `game_action_result`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `record_id` int(11) NULL DEFAULT NULL COMMENT '对局记录id',
  `action_id` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作id',
  `point` double(16, 2) NULL DEFAULT 0.00 COMMENT '场面评分变化',
  `result` int(4) NULL DEFAULT 0 COMMENT '最终胜负 -1 败 0 未知 1 胜',
  `rank` int(11) NULL DEFAULT 0 COMMENT '天梯排名 -1 表示为 pve',
  `machine` int(11) NULL DEFAULT 0 COMMENT '0 机器 1 人类',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `result_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'json 串表示操作后结果信息',
  `before_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'json 串表示操作前结果信息',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `key_action_id`(`action_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作后的结果' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for game_playfield
-- ----------------------------
DROP TABLE IF EXISTS `game_playfield`;
CREATE TABLE `game_playfield`  (
  `id` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '信息相加 hash，确保不重复',
  `rounds` int(11) NULL DEFAULT NULL COMMENT '回合数',
  `own_minions_name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '我方佣兵名称，排序，逗号隔开',
  `enemy_minions_name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '敌方佣兵名称，排序，逗号隔开',
  `own_minions_hp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '我方佣兵生命值，根据佣兵顺序排序，逗号隔开',
  `enemy_minions_hp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '敌方佣兵生命值，根据佣兵顺序排序，逗号隔开',
  `own_minions_enchs` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '我方佣兵附魔，单佣兵逗号隔开，多佣兵分号隔开，技能附魔标识123',
  `enemy_minions_enchs` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '敌方佣兵附魔，单佣兵逗号隔开，多佣兵分号隔开',
  `own_minions_atk` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '我方佣兵攻击力，根据佣兵顺序排序，逗号隔开',
  `enemy_minions_atk` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '敌方佣兵攻击力，根据佣兵顺序排序，逗号隔开',
  `own_minions_ability` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '我方佣兵技能',
  `enemy_minions_ability` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '敌方佣兵技能',
  `minions_hash` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '仅根据佣兵 hash 结果',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '场面记录，做筛选用' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for game_playfield_action
-- ----------------------------
DROP TABLE IF EXISTS `game_playfield_action`;
CREATE TABLE `game_playfield_action`  (
  `id` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '根据 playfield_id 和 actions hash后的结果，确保记录唯一',
  `playfield_id` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '对局记录 id',
  `minions_hash` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '仅根据佣兵 hash 结果',
  `weight` double NULL DEFAULT NULL COMMENT '权重',
  `points` int(11) NULL DEFAULT NULL COMMENT '评分变动',
  `data_from` int(4) NULL DEFAULT NULL COMMENT '0 我方数据 1敌方数据',
  `actions` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '操作序列，json 文本',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `key_playfield_id`(`playfield_id`) USING BTREE COMMENT '外键，场面id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '根据场面进行的操作' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for game_record
-- ----------------------------
DROP TABLE IF EXISTS `game_record`;
CREATE TABLE `game_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '战网id，外键',
  `enemy_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '对手战网id',
  `own_team` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '我方阵容，战斗中出场的佣兵，逗号隔开',
  `enemy_team` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '敌方阵容，战斗中出场的佣兵，逗号隔开',
  `result` int(4) NULL DEFAULT NULL COMMENT '-1 失败 0 未知 1 胜利 2平局',
  `rank` int(11) NULL DEFAULT 0 COMMENT '天梯排名 -1 表示为 pve',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '对局记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '战网id',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'ip地址',
  `interface_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调用接口名称',
  `result` int(4) NULL DEFAULT 0 COMMENT '0 报错 1 成功返回',
  `msg` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '额外信息',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上次更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '登录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '主键，直接使用战网 id 加密后做主键',
  `salt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '盐',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '预留字段，id+password+salt md5 加密2次',
  `is_delete` int(11) NOT NULL DEFAULT 0 COMMENT '删除 0 正常 1 删除',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态 0 正常 1 封禁',
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `token` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '验证token',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上次登录 ip，请求数据时验证 ip 是否一致',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for view_action
-- ----------------------------
DROP VIEW IF EXISTS `view_action`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `view_action` AS select `a`.`id` AS `id`,`a`.`actions` AS `actions`,`a`.`minions_hash` AS `minions_hash`,`a`.`playfield_id` AS `playfield_id`,`a`.`weight` AS `weight`,`b`.`cnt` AS `cnt`,`b`.`point` AS `point`,`b`.`win_rate` AS `win_rate`,`b`.`rank` AS `rank` from (`game_playfield_action` `a` left join `view_action_statistics` `b` on((`a`.`id` = `b`.`action_id`)));

-- ----------------------------
-- View structure for view_action_statistics
-- ----------------------------
DROP VIEW IF EXISTS `view_action_statistics`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `view_action_statistics` AS select `game_action_result`.`action_id` AS `action_id`,count(0) AS `cnt`,avg(`game_action_result`.`point`) AS `point`,avg(`game_action_result`.`result`) AS `win_rate`,12000 AS `rank` from `game_action_result` where (`game_action_result`.`rank` >= 10000) group by `game_action_result`.`action_id` union select `game_action_result`.`action_id` AS `action_id`,count(0) AS `cnt`,avg(`game_action_result`.`point`) AS `point`,avg(`game_action_result`.`result`) AS `win_rate`,10000 AS `rank` from `game_action_result` where ((`game_action_result`.`rank` >= 10000) and (`game_action_result`.`rank` < 12000)) group by `game_action_result`.`action_id` union select `game_action_result`.`action_id` AS `action_id`,count(0) AS `cnt`,avg(`game_action_result`.`point`) AS `point`,avg(`game_action_result`.`result`) AS `win_rate`,8500 AS `rank` from `game_action_result` where ((`game_action_result`.`rank` >= 8500) and (`game_action_result`.`rank` < 10000)) group by `game_action_result`.`action_id` union select `game_action_result`.`action_id` AS `action_id`,count(0) AS `cnt`,avg(`game_action_result`.`point`) AS `point`,avg(`game_action_result`.`result`) AS `win_rate`,6000 AS `rank` from `game_action_result` where ((`game_action_result`.`rank` >= 6000) and (`game_action_result`.`rank` < 8500)) group by `game_action_result`.`action_id` union select `game_action_result`.`action_id` AS `action_id`,count(0) AS `cnt`,avg(`game_action_result`.`point`) AS `point`,avg(`game_action_result`.`result`) AS `win_rate`,0 AS `rank` from `game_action_result` where ((`game_action_result`.`rank` >= 0) and (`game_action_result`.`rank` < 6000)) group by `game_action_result`.`action_id` union select `game_action_result`.`action_id` AS `action_id`,count(0) AS `cnt`,avg(`game_action_result`.`point`) AS `point`,avg(`game_action_result`.`result`) AS `win_rate`,-(1) AS `rank` from `game_action_result` where (`game_action_result`.`rank` < 0) group by `game_action_result`.`action_id`;

SET FOREIGN_KEY_CHECKS = 1;
