-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('仓库管理', '1', '1', 'storage', 'system/storage/index', 1, 0, 'C', '0', '0', 'system:storage:list', '#', 'admin', sysdate(), '', null, '仓库管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('仓库管理查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:storage:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('仓库管理新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:storage:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('仓库管理修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:storage:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('仓库管理删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:storage:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('仓库管理导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:storage:export',       '#', 'admin', sysdate(), '', null, '');