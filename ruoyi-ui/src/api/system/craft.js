import request from '@/utils/request'

// 查询工艺列表
export function listCraft(query) {
  return request({
    url: '/system/craft/list',
    method: 'get',
    params: query
  })
}

// 查询工艺详细
export function getCraft(id) {
  return request({
    url: '/system/craft/' + id,
    method: 'get'
  })
}

// 新增工艺
export function addCraft(data) {
  return request({
    url: '/system/craft',
    method: 'post',
    data: data
  })
}

// 修改工艺
export function updateCraft(data) {
  return request({
    url: '/system/craft',
    method: 'put',
    data: data
  })
}

// 删除工艺
export function delCraft(id) {
  return request({
    url: '/system/craft/' + id,
    method: 'delete'
  })
}
