DROP TABLE IF EXISTS work_order;
DROP TABLE IF EXISTS worker_order_mission;
DROP TABLE IF EXISTS work_order_craft;
DROP TABLE IF EXISTS work_order_worker;
DROP TABLE IF EXISTS factory_worker;
DROP TABLE IF EXISTS worker_material;
DROP TABLE IF EXISTS work_mission;
DROP TABLE IF EXISTS work_mission_craft;
DROP TABLE IF EXISTS worker_order_material;

CREATE TABLE
    IF NOT EXISTS work_order (
        `id` BIGINT(20) NOT NULL COMMENT '工单编号',
        `date` DATE NOT NULL COMMENT '工单日期',
        `staff_id` BIGINT(20) NOT NULL COMMENT '员工编号',
        `mission_id` BIGINT(20) NOT NULL COMMENT '任务编号',
        `craft_id` BIGINT(20) NOT NULL COMMENT '工艺编号',
        PRIMARY KEY (`id`)
    ) COMMENT = '工单表';

CREATE TABLE
    IF NOT EXISTS factory_worker (
        `staff_id` BIGINT(20) NOT NULL COMMENT '员工编号',
        `factory_id` INT(3) NOT NULL COMMENT '车间编号',
        PRIMARY KEY (`staff_id`)
    ) COMMENT = '车间员工关系表';

CREATE TABLE
    IF NOT EXISTS worker_material (
        `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '领料单编号',
        `staff_id` BIGINT(20) NOT NULL COMMENT '员工编号',
        `date` DATE NOT NULL COMMENT '日期',
        `material_id` BIGINT(20) NOT NULL COMMENT '物料编号',
        `number` INT NOT NULL COMMENT '领料数量',
        PRIMARY KEY (`id`)
    ) COMMENT = '员工领料单';

CREATE TABLE
    IF NOT EXISTS worker_order_material (
        `worker_material_id` BIGINT(20) NOT NULL COMMENT '领料单编号',
        `order_id` BIGINT(20) NOT NULL COMMENT '工单编号',
        PRIMARY KEY (`worker_material_id`, `order_id`)
    ) COMMENT = '工单领料单关系表';

CREATE TABLE
    IF NOT EXISTS work_mission (
        `id` BIGINT(20) NOT NULL COMMENT '任务编号',
        `start` DATE NOT NULL COMMENT '起始日期',
        `end` DATE NOT NULL COMMENT '完成日期',
        `priority` TINYINT(1) NOT NULL COMMENT '任务优先级',
        `work_mission_status` VARCHAR(5) NOT NULL COMMENT '任务状态',
        `number` INT NOT NULL COMMENT '完工数量',
        PRIMARY KEY (`id`)
    ) COMMENT = '加工任务表';

CREATE TABLE
    IF NOT EXISTS work_mission_craft (
        `mission_id` BIGINT(20) NOT NULL COMMENT '任务编号',
        `craft_id` BIGINT(20) NOT NULL COMMENT '工艺编号',
        PRIMARY KEY (`mission_id`, `craft_id`)
    ) COMMENT = '任务工艺关系表';