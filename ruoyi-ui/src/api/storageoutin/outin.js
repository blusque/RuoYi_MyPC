import request from '@/utils/request'

// 查询出入操作列表
export function listOutin(query) {
  return request({
    url: '/storageoutin/outin/list',
    method: 'get',
    params: query
  })
}

// 查询出入操作详细
export function getOutin(id) {
  return request({
    url: '/storageoutin/outin/' + id,
    method: 'get'
  })
}

// 新增出入操作
export function addOutin(data) {
  return request({
    url: '/storageoutin/outin',
    method: 'post',
    data: data
  })
}

// 修改出入操作
export function updateOutin(data) {
  return request({
    url: '/storageoutin/outin',
    method: 'put',
    data: data
  })
}

// 删除出入操作
export function delOutin(id) {
  return request({
    url: '/storageoutin/outin/' + id,
    method: 'delete'
  })
}
