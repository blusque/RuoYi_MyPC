DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS customer_address;
DROP TABLE IF EXISTS market_order;
DROP TABLE IF EXISTS market_order_detail;
DROP TABLE IF EXISTS market_order_customer;
DROP TABLE IF EXISTS market_order_seller;
DROP TABLE IF EXISTS market_cancel_detail;

CREATE TABLE
    IF NOT EXISTS customer (
        `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '客户编号',
        `name` VARCHAR(30) NOT NULL COMMENT '客户姓名',
        `tel` VARCHAR(15) COMMENT '客户电话',
        `email` VARCHAR(30) COMMENT '客户邮件',
        `customer_level` FLOAT NOT NULL COMMENT '客户评级',
        `customer_tags` VARCHAR(15) COMMENT '客户标签',
        PRIMARY KEY (`id`)
    ) COMMENT = '客户表';

CREATE TABLE
    IF NOT EXISTS customer_address (
        `customer_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '客户编号',
        `customer_address` VARCHAR(255) NOT NULL COMMENT '客户地址',
        PRIMARY KEY (`customer_id`, `customer_address`)
    ) COMMENT = '客户地址表';

CREATE TABLE
    IF NOT EXISTS market_order (
        `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
        `date` DATE NOT NULL COMMENT '订单日期',
        `status` VARCHAR(10) NOT NULL COMMENT '订单状态',
        PRIMARY KEY (`id`)
    ) COMMENT = '销售订单表';

CREATE TABLE
    IF NOT EXISTS market_order_detail (
        `order_id` BIGINT(20) NOT NULL COMMENT '订单编号',
        `material_id` BIGINT(20) NOT NULL COMMENT '物料编号',
        `number` INT NOT NULL COMMENT '数量',
        PRIMARY KEY (`order_id`, `material_id`)
    ) COMMENT = '订单明细表';

CREATE TABLE
    IF NOT EXISTS market_order_customer (
        `order_id` BIGINT(20) NOT NULL COMMENT '订单编号',
        `customer_id` BIGINT(20) NOT NULL COMMENT '客户编号',
        PRIMARY KEY (`customer_id`, `order_id`)
    ) COMMENT = '订单客户关系表';

CREATE TABLE
    IF NOT EXISTS market_order_seller (
        `order_id` BIGINT(20) NOT NULL COMMENT '订单编号',
        `staff_id` BIGINT(20) NOT NULL COMMENT '销售员编号',
        PRIMARY KEY (`order_id`, `staff_id`)
    ) COMMENT = '订单销售员关系表';

CREATE TABLE
    IF NOT EXISTS market_cancel_detail (
        `order_id` BIGINT(20) NOT NULL COMMENT '订单编号',
        `date` DATE NOT NULL COMMENT '退货日期',
        `material_id` BIGINT(20) NOT NULL COMMENT '物料编号',
        `number` INT NOT NULL COMMENT '退货数量',
        PRIMARY KEY (`order_id`, `material_id`, `date`)
    ) COMMENT = '退货明细表';