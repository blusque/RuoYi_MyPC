DROP TABLE IF EXISTS storage_outin;
DROP TABLE IF EXISTS storage_outin_detail;
DROP TABLE IF EXISTS storage_move;
DROP TABLE IF EXISTS storage_move_detail;
DROP TABLE IF EXISTS storage_check;
DROP TABLE IF EXISTS storage_check_detail;
DROP TABLE IF EXISTS storage_record;


CREATE TABLE
    IF NOT EXISTS storage_outin (
        `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '出入库单编号',
        `date` DATE NOT NULL COMMENT '出入库日期',
        `staff_id` VARCHAR(5) NOT NULL COMMENT '申请员工编号',
        `worker_id` VARCHAR(5) NOT NULL COMMENT '操作员工编号',
        `type` VARCHAR(1) NOT NULL COMMENT '出入库类型（出/入）',
        PRIMARY KEY (`id`)
    ) COMMENT = '出入库单表';

CREATE TABLE
    IF NOT EXISTS storage_outin_detail (
        `outin_id` BIGINT(20) NOT NULL COMMENT '出入库单编号',
        `material_id` BIGINT(20) NOT NULL COMMENT '物料编号',
        `number` INT NOT NULL COMMENT '出入库数量',
        PRIMARY KEY (`outin_id`, `material_id`, `number`)
    ) COMMENT = '出入库单明细表';

CREATE TABLE
    IF NOT EXISTS storage_move (
        `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '移库单编号',
        `date` DATE NOT NULL COMMENT '移库日期',
        `staff_id` BIGINT(20) NOT NULL COMMENT '操作员工编号',
        `from` INT(3) NOT NULL COMMENT '出库',
        `to` INT(3) NOT NULL COMMENT '入库', 
        PRIMARY KEY (`id`)
    ) COMMENT = '移库单';

CREATE TABLE
    IF NOT EXISTS storage_move_detail (
        `move_id` BIGINT(20) NOT NULL COMMENT '移库单编号',
        `material_id` BIGINT(20) NOT NULL COMMENT '物料编号',
        `number` INT NOT NULL COMMENT '移库数量',
        PRIMARY KEY (`move_id`, `material_id`)
    ) COMMENT = '移库单明细';

CREATE TABLE
    IF NOT EXISTS storage_check (
        `id` BIGINT(20) NOT NULL COMMENT '盘点单编号',
        `date` DATE NOT NULL COMMENT '盘点日期',
        `staff_id` BIGINT(20) NOT NULL COMMENT '盘点人员编号',
        PRIMARY KEY (`id`)
    ) COMMENT = '盘点单';

CREATE TABLE
    IF NOT EXISTS storage_check_detail (
        `check_id` BIGINT(20) NOT NULL COMMENT '盘点单编号',
        `material_id` BIGINT(20) NOT NULL COMMENT '物料编号',
        `check_type` TINYINT(1) NOT NULL COMMENT '盘盈盘亏',
        `number` INT NOT NULL COMMENT '盈亏数量',
        PRIMARY KEY (`check_id`, `material_id`)
    ) COMMENT = '盘点单明细';

CREATE TABLE
    IF NOT EXISTS storage_record (
        `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '库存记录编号',
        `material_id` BIGINT(20) NOT NULL COMMENT '物料编号',
        `storage_id` BIGINT(20) NOT NULL COMMENT '仓库编号',
        `number` INT NOT NULL COMMENT '存储数量',
        PRIMARY KEY (`id`)
    ) COMMENT = '库存记录';