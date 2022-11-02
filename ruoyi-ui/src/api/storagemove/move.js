import request from '@/utils/request'

// 查询移动操作列表
export function listMove(query) {
  return request({
    url: '/storagemove/move/list',
    method: 'get',
    params: query
  })
}

// 查询移动操作详细
export function getMove(id) {
  return request({
    url: '/storagemove/move/' + id,
    method: 'get'
  })
}

// 新增移动操作
export function addMove(data) {
  return request({
    url: '/storagemove/move',
    method: 'post',
    data: data
  })
}

// 修改移动操作
export function updateMove(data) {
  return request({
    url: '/storagemove/move',
    method: 'put',
    data: data
  })
}

// 删除移动操作
export function delMove(id) {
  return request({
    url: '/storagemove/move/' + id,
    method: 'delete'
  })
}
