import request from '@/utils/request'

// 查询盘点操作列表
export function listCheck(query) {
  return request({
    url: '/storagecheck/check/list',
    method: 'get',
    params: query
  })
}

// 查询盘点操作详细
export function getCheck(id) {
  return request({
    url: '/storagecheck/check/' + id,
    method: 'get'
  })
}

// 新增盘点操作
export function addCheck(data) {
  return request({
    url: '/storagecheck/check',
    method: 'post',
    data: data
  })
}

// 修改盘点操作
export function updateCheck(data) {
  return request({
    url: '/storagecheck/check',
    method: 'put',
    data: data
  })
}

// 删除盘点操作
export function delCheck(id) {
  return request({
    url: '/storagecheck/check/' + id,
    method: 'delete'
  })
}
