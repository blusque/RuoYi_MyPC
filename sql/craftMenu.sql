-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工艺', '3', '1', 'craft', 'system/craft/index', 1, 0, 'C', '0', '0', 'system:craft:list', '#', 'admin', sysdate(), '', null, '工艺菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工艺查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:craft:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工艺新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:craft:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工艺修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:craft:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工艺删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:craft:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('工艺导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:craft:export',       '#', 'admin', sysdate(), '', null, '');