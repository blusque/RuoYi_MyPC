import request from '@/utils/request'

// 查询车间列表
export function listFactory(query) {
  return request({
    url: '/system/factory/list',
    method: 'get',
    params: query
  })
}

// 查询车间详细
export function getFactory(id) {
  return request({
    url: '/system/factory/' + id,
    method: 'get'
  })
}

// 新增车间
export function addFactory(data) {
  return request({
    url: '/system/factory',
    method: 'post',
    data: data
  })
}

// 修改车间
export function updateFactory(data) {
  return request({
    url: '/system/factory',
    method: 'put',
    data: data
  })
}

// 删除车间
export function delFactory(id) {
  return request({
    url: '/system/factory/' + id,
    method: 'delete'
  })
}
