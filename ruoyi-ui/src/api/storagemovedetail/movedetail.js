import request from '@/utils/request'

// 查询移动明细列表
export function listMovedetail(query) {
  return request({
    url: '/storagemovedetail/movedetail/list',
    method: 'get',
    params: query
  })
}

// 查询移动明细详细
export function getMovedetail(id) {
  return request({
    url: '/storagemovedetail/movedetail/' + id,
    method: 'get'
  })
}

// 新增移动明细
export function addMovedetail(data) {
  return request({
    url: '/storagemovedetail/movedetail',
    method: 'post',
    data: data
  })
}

// 修改移动明细
export function updateMovedetail(data) {
  return request({
    url: '/storagemovedetail/movedetail',
    method: 'put',
    data: data
  })
}

// 删除移动明细
export function delMovedetail(id) {
  return request({
    url: '/storagemovedetail/movedetail/' + id,
    method: 'delete'
  })
}
