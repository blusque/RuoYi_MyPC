import request from '@/utils/request'

// 查询出入明细列表
export function listOutindetail(query) {
  return request({
    url: '/storageoutindetail/outindetail/list',
    method: 'get',
    params: query
  })
}

// 查询出入明细详细
export function getOutindetail(id) {
  return request({
    url: '/storageoutindetail/outindetail/' + id,
    method: 'get'
  })
}

// 新增出入明细
export function addOutindetail(data) {
  return request({
    url: '/storageoutindetail/outindetail',
    method: 'post',
    data: data
  })
}

// 修改出入明细
export function updateOutindetail(data) {
  return request({
    url: '/storageoutindetail/outindetail',
    method: 'put',
    data: data
  })
}

// 删除出入明细
export function delOutindetail(id) {
  return request({
    url: '/storageoutindetail/outindetail/' + id,
    method: 'delete'
  })
}
