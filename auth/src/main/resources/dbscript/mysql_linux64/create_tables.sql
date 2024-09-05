use MyWork;

-- ----------------------------
-- Table structure for `chain_user`
-- ----------------------------
DROP TABLE IF EXISTS `chain_user`;
CREATE TABLE `chain_user`
(
    `id`              mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
    `username`        varchar(60)           NOT NULL DEFAULT '',
    `password`        varchar(32)                    DEFAULT '',
    `gender`          tinyint(1)                     DEFAULT NULL,
    `birthday`        datetime                       DEFAULT NULL,
    `register_time`   datetime                       DEFAULT NULL,
    `last_login_time` datetime                       DEFAULT NULL,
    `last_login_ip`   varchar(15)                    DEFAULT '',
    `user_level_id`   tinyint(3)                     DEFAULT NULL,
    `nickname`        varchar(60)                    DEFAULT '',
    `mobile`          varchar(20)                    DEFAULT '',
    `register_ip`     varchar(45)                    DEFAULT '',
    `avatar`          varchar(255)                   DEFAULT '',
    `weixin_openid`   varchar(50)                    DEFAULT '',
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_name` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 24
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for `chain_token`
-- ----------------------------
DROP TABLE IF EXISTS `chain_token`;
CREATE TABLE `chain_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户Token';

-- ----------------------------
-- Table structure for `chain_user_level`
-- ----------------------------
DROP TABLE IF EXISTS `chain_user_level`;
CREATE TABLE `chain_user_level`
(
    `id`          tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
    `name`        varchar(30)         NOT NULL DEFAULT '',
    `description` varchar(255)        NOT NULL DEFAULT '',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of chain_user_level
-- ----------------------------
INSERT INTO `chain_user_level`
VALUES ('1', '普通用户', '0');
INSERT INTO `chain_user_level`
VALUES ('2', 'vip', '10000');
INSERT INTO `chain_user_level`
VALUES ('4', '高级VIP', '100000');
