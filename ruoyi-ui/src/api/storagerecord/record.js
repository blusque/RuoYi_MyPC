import request from '@/utils/request'

// 查询库存信息列表
export function listRecord(query) {
  return request({
    url: '/storagerecord/record/list',
    method: 'get',
    params: query
  })
}

// 查询库存信息详细
export function getRecord(id) {
  return request({
    url: '/storagerecord/record/' + id,
    method: 'get'
  })
}

// 新增库存信息
export function addRecord(data) {
  return request({
    url: '/storagerecord/record',
    method: 'post',
    data: data
  })
}

// 修改库存信息
export function updateRecord(data) {
  return request({
    url: '/storagerecord/record',
    method: 'put',
    data: data
  })
}

// 删除库存信息
export function delRecord(id) {
  return request({
    url: '/storagerecord/record/' + id,
    method: 'delete'
  })
}
