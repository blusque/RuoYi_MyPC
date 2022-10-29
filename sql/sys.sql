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
        `name` VARCHAR(30) NOT NULL COMMENT '物料名称',
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

CREATE TABLE
    IF NOT EXISTS sys_storage (
        `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '仓库编号',
        `capacity` INT NOT NULL COMMENT '仓库容量',
        PRIMARY KEY (`id`)
    ) COMMENT = '仓库表';