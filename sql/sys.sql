DROP TABLE IF EXISTS sys_factory;

DROP TABLE IF EXISTS sys_bom;

DROP TABLE IF EXISTS sys_craft;

DROP TABLE IF EXISTS sys_craft_factory;

DROP TABLE IF EXISTS sys_storage;

DROP TABLE IF EXISTS report_seller;

CREATE TABLE
    IF NOT EXISTS sys_factory (
        `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '车间编号',
        `center_number` INT NOT NULL COMMENT '加工位数量',
        `comment` VARCHAR(255) COMMENT '备注',
        PRIMARY KEY (`id`)
    ) COMMENT = '车间表';

CREATE TABLE
    IF NOT EXISTS sys_bom (
        `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '物料编号',
        `name` VARCHAR(255) NOT NULL COMMENT '物料名称',
        `property` VARCHAR(5) NOT NULL COMMENT '产品/物料',
        `from` VARCHAR(1) NOT NULL COMMENT '获取途径',
        `duration` INT COMMENT '预计用时',
        PRIMARY KEY (`id`)
    ) COMMENT = 'BOM表';

CREATE TABLE
    IF NOT EXISTS sys_craft (
        `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '工艺编号',
        `craft_name` VARCHAR(5) NOT NULL COMMENT '工艺名称',
        `material_id` BIGINT(20) NOT NULL COMMENT '子物料',
        `parent_id` BIGINT(20) NOT NULL COMMENT '父物料',
        `duration` INT NOT NULL COMMENT '预计耗时(单件)',
        `factory_id` INT(3) NOT NULL COMMENT '车间编号',
        PRIMARY KEY (`id`)
    ) COMMENT = '工艺表';

LOCK TABLES `sys_bom` WRITE;

/*!40000 ALTER TABLE `sys_bom` DISABLE KEYS */;

INSERT INTO `sys_bom` (`id`, `name`, `from`, `duration`)
VALUES (
        1,
        'macbook air m1 (8, 256) 深空灰',
        '0',
        NULL
    ), (
        2,
        'macbook air m1 (8, 256) 银',
        '0',
        NULL
    ), (
        3,
        'macbook air m1 (16, 512) 深空灰',
        '0',
        NULL
    ), (
        4,
        'macbook air m1 (16, 512) 银',
        '0',
        NULL
    ), (
        5,
        'macbook air m2(8, 8) (8, 256) 深空灰',
        '0',
        NULL
    ), (
        6,
        'macbook air m2(8, 8) (8, 256) 银',
        '0',
        NULL
    ), (
        7,
        'macbook air m2(8, 10) (16, 512) 深空灰',
        '0',
        NULL
    ), (
        8,
        'macbook air m2(8, 10) (16, 512) 银',
        '0',
        NULL
    ), (
        9,
        'macbook pro 14 m1pro (16, 512) 深空灰',
        '0',
        NULL
    ), (
        10,
        'macbook pro 14 m1pro (16, 512) 银',
        '0',
        NULL
    ), (
        11,
        'macbook pro 16 m1pro (16, 512) 深空灰',
        '0',
        NULL
    ), (
        12,
        'macbook pro 16 m1pro (16, 512) 银',
        '0',
        NULL
    ), (
        13,
        'macbook pro m2(8, 10) (16, 512) 深空灰',
        '0',
        NULL
    ), (
        14,
        'macbook pro m2(8, 10) (16, 512) 银',
        '0',
        NULL
    ), (
        15,
        'macbook air m2(8, 10) (16, 512) 其他',
        '0',
        NULL
    ), (
        16,
        'macbook pro 16 m1max (16, 512) 深空灰',
        '0',
        NULL
    ), (
        17,
        'macbook pro 16 m1max (16, 512) 银',
        '0',
        NULL
    ), (18, 'm1', '0', NULL), (19, 'm2(8, 8)', '0', NULL), (20, 'm2(8, 10)', '0', NULL), (21, 'm1pro', '0', NULL), (22, 'm1max', '0', NULL), (23, '内存8GB', '1', NULL), (24, '内存16GB', '1', NULL), (25, 'SSD256GB', '1', NULL), (26, 'SSD512GB', '1', NULL);

/*!40000 ALTER TABLE `sys_bom` ENABLE KEYS */;

UNLOCK TABLES;

CREATE TABLE
    IF NOT EXISTS sys_storage (
        `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '仓库编号',
        `capacity` INT NOT NULL COMMENT '仓库容量',
        PRIMARY KEY (`id`)
    ) COMMENT = '仓库表';