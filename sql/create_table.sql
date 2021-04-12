
-- ----------------------------
-- Table structure for answer_option
-- ----------------------------
DROP TABLE IF EXISTS `answer_option`;
CREATE TABLE `answer_option`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '问题答案配置ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建用户',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否被删除',
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新人员',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '答案代码',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '答案内容',
  `type` bigint(20) NULL DEFAULT NULL COMMENT '答案类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for business_answer_option
-- ----------------------------
DROP TABLE IF EXISTS `business_answer_option`;
CREATE TABLE `business_answer_option`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人员',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否被删除',
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新人员',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `answer_option_id` bigint(20) NULL DEFAULT NULL COMMENT '答案ID',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '答案代码',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '答案内容',
  `selected` tinyint(1) NULL DEFAULT NULL COMMENT '是否选择项',
  `type` bigint(20) NULL DEFAULT NULL COMMENT '答案类型',
  `business_node_id` bigint(20) NULL DEFAULT NULL COMMENT '答案所属节点',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKq5bkujcfuomimrlbrwodb7voe`(`business_node_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for business_node
-- ----------------------------
DROP TABLE IF EXISTS `business_node`;
CREATE TABLE `business_node`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人员',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否被删除',
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新人员',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '节点问题代码',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '节点问题内容',
  `end_node_indi` tinyint(1) NULL DEFAULT NULL COMMENT '是否结束节点',
  `node_id` bigint(20) NULL DEFAULT NULL COMMENT '问卷节点ID',
  `presentation_type` bigint(20) NULL DEFAULT NULL COMMENT '问题展示形式',
  `question_id` bigint(20) NULL DEFAULT NULL COMMENT '问题ID',
  `question_type` bigint(20) NULL DEFAULT NULL COMMENT '问题类型',
  `root_node_indi` tinyint(1) NULL DEFAULT NULL COMMENT '是否起始节点',
  `business_questionnaire_id` bigint(20) NULL DEFAULT NULL COMMENT '节点所属客户问卷',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK83c4jkiqd8y6ioqkuceit7y50`(`business_questionnaire_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for business_questionnaire
-- ----------------------------
DROP TABLE IF EXISTS `business_questionnaire`;
CREATE TABLE `business_questionnaire`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人员',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否被删除',
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新人员',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `business_id` bigint(20) NULL DEFAULT NULL COMMENT '业务ID',
  `business_type` bigint(20) NULL DEFAULT NULL COMMENT '业务类型',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '问卷代码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '问卷名字',
  `questionnaire_desc` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '问卷描述',
  `questionnaire_id` bigint(20) NULL DEFAULT NULL COMMENT '问卷ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for node
-- ----------------------------
DROP TABLE IF EXISTS `node`;
CREATE TABLE `node`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人员',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否被删除',
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新人员',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `end_node_indi` tinyint(1) NULL DEFAULT NULL COMMENT '是否结束节点',
  `root_node_indi` tinyint(1) NULL DEFAULT NULL COMMENT '是否起始节点',
  `question_id` bigint(20) NULL DEFAULT NULL COMMENT '节点问题ID',
  `questionnaire_id` bigint(20) NULL DEFAULT NULL COMMENT '问卷ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKhtoi2sur8ws1rl2r3d5lggp4m`(`question_id`) USING BTREE,
  INDEX `FKdh7deow7sedowwcig8pdjnxq7`(`questionnaire_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for path
-- ----------------------------
DROP TABLE IF EXISTS `path`;
CREATE TABLE `path`  (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人员',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否被删除',
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新人员',
  `update_time` datetime(6) NULL DEFAULT NULL COMMENT '更新时间',
  `child_node_id` bigint(20) NULL DEFAULT NULL COMMENT '子节点',
  `parent_node_id` bigint(20) NULL DEFAULT NULL COMMENT '父节点',
  `questionnaire_id` bigint(20) NULL DEFAULT NULL COMMENT '问卷ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKoi79knlwi9efqheylmy5olkh1`(`questionnaire_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for path_option
-- ----------------------------
DROP TABLE IF EXISTS `path_option`;
CREATE TABLE `path_option`  (
  `option_id` bigint(20) NOT NULL COMMENT '答案选项ID',
  `path_id` bigint(20) NOT NULL COMMENT '节点路径ID',
  INDEX `FKog4jf9vxnlxnjdj7e9v73tfj7`(`path_id`) USING BTREE,
  INDEX `FK4siplbddd9quo0t6nu023rm0g`(`option_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product_questionnaire
-- ----------------------------
DROP TABLE IF EXISTS `product_questionnaire`;
CREATE TABLE `product_questionnaire`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '产品问卷ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人员',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否被删除',
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新人员',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '产品ID',
  `questionnaire_id` bigint(20) NULL DEFAULT NULL COMMENT '问卷ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK9hrqyoeobjx9rlxuqf02r0v3`(`questionnaire_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '问题ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人员',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否被删除',
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新人员',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '问题代码',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '问题内容',
  `presentation_type` bigint(20) NULL DEFAULT NULL COMMENT '展示形式',
  `question_type` bigint(20) NULL DEFAULT NULL COMMENT '问题类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question_answer_option
-- ----------------------------
DROP TABLE IF EXISTS `question_answer_option`;
CREATE TABLE `question_answer_option`  (
  `option_id` bigint(20) NOT NULL COMMENT '选项答案ID',
  `question_id` bigint(20) NOT NULL COMMENT '问题ID',
  INDEX `FKmtpfej7ppf9244lbr910my075`(`question_id`) USING BTREE,
  INDEX `FKqp7e7pwlkvox4x3b7auxssgpc`(`option_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for questionnaire
-- ----------------------------
DROP TABLE IF EXISTS `questionnaire`;
CREATE TABLE `questionnaire`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建人员',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否被删除',
  `modifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '更新人员',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '问卷代码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '问卷名字',
  `questionnaire_desc` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '问卷描述',
  `status` bigint(20) NULL DEFAULT NULL COMMENT '问卷状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

