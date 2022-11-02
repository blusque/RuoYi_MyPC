import request from '@/utils/request'

// 查询盘点明细列表
export function listCheckdetail(query) {
  return request({
    url: '/storagecheckdetail/checkdetail/list',
    method: 'get',
    params: query
  })
}

// 查询盘点明细详细
export function getCheckdetail(id) {
  return request({
    url: '/storagecheckdetail/checkdetail/' + id,
    method: 'get'
  })
}

// 新增盘点明细
export function addCheckdetail(data) {
  return request({
    url: '/storagecheckdetail/checkdetail',
    method: 'post',
    data: data
  })
}

// 修改盘点明细
export function updateCheckdetail(data) {
  return request({
    url: '/storagecheckdetail/checkdetail',
    method: 'put',
    data: data
  })
}

// 删除盘点明细
export function delCheckdetail(id) {
  return request({
    url: '/storagecheckdetail/checkdetail/' + id,
    method: 'delete'
  })
}
