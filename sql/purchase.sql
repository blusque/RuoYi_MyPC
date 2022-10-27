DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS purchase_order;
DROP TABLE IF EXISTS supplier_detail;
DROP TABLE IF EXISTS purchase_order_detail;
DROP TABLE IF EXISTS purchase_order_supplier;
DROP TABLE IF EXISTS purchase_cancel_detail;

CREATE TABLE
    IF NOT EXISTS supplier (
        `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '供应商编号',
        `name` VARCHAR(30) NOT NULL COMMENT '供应商名称',
        `tel` VARCHAR(15) COMMENT '供应商电话',
        `email` VARCHAR(30) COMMENT '供应商邮件',
        `level` FLOAT NOT NULL COMMENT '供应商评级',
        `discount` FLOAT COMMENT '供应商折扣',
        `address` VARCHAR(255) NOT NULL COMMENT '供应商地址',
        PRIMARY KEY (`id`)
    ) COMMENT = '供应商表';

CREATE TABLE
    IF NOT EXISTS supplier_detail (
        `supplier_id` BIGINT(20) NOT NULL COMMENT '供应商编号',
        `material_id` BIGINT(20) NOT NULL COMMENT '物料编号',
        PRIMARY KEY (`supplier_id`, `material_id`)
    ) COMMENT = '供应商明细';

CREATE TABLE
    IF NOT EXISTS purchase_order (
        `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '采购订单',
        `start` DATE NOT NULL COMMENT '申请日期',
        `end` DATE NOT NULL COMMENT '预定到达日期',
        `purchase_order_status` VARCHAR(10) NOT NULL COMMENT '订单状态',
        PRIMARY KEY (`id`)
    ) COMMENT = '采购订单表';

CREATE TABLE
    IF NOT EXISTS purchase_order_detail (
        `order_id` BIGINT(20) NOT NUll COMMENT '订单编号',
        `material_id` BIGINT(20) NOT NULL COMMENT '物料编号',
        `number` INT NOT NULL COMMENT '',
        PRIMARY KEY (`order_id`, `material_id`)
    ) COMMENT = '采购订单明细';

CREATE TABLE
    IF NOT EXISTS purchase_order_supplier (
        `order_id` BIGINT(20) NOT NULL COMMENT '订单编号',
        `supplier_id` BIGINT(20) NOT NULL COMMENT '供应商编号',
        PRIMARY KEY (`order_id`, `supplier_id`)
    ) COMMENT = '采购订单供应商关系表';

CREATE TABLE
    IF NOT EXISTS purchase_cancel_detail (
        `order_id` BIGINT(20) NOT NULL COMMENT '订单编号',
        `material_id` BIGINT(20) NOT NULL COMMENT '物料编号',
        `number` INT NOT NULL COMMENT '物料数量',
        PRIMARY KEY (`order_id`, `material_id`)
   ) COMMENT = '采购订单退货明细表';