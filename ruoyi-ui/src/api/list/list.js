import request from '@/utils/request'

// 查询领料单物料列表
export function listList(query) {
  return request({
    url: '/list/list/list',
    method: 'get',
    params: query
  })
}

// 查询领料单物料详细
export function getList(mrnum) {
  return request({
    url: '/list/list/' + mrnum,
    method: 'get'
  })
}

// 新增领料单物料
export function addList(data) {
  return request({
    url: '/list/list',
    method: 'post',
    data: data
  })
}

// 修改领料单物料
export function updateList(data) {
  return request({
    url: '/list/list',
    method: 'put',
    data: data
  })
}

// 删除领料单物料
export function delList(mrnum) {
  return request({
    url: '/list/list/' + mrnum,
    method: 'delete'
  })
}
