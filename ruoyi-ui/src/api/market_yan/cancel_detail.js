import request from '@/utils/request'

// 查询退货明细列表
export function listCancel_detail(query) {
  return request({
    url: '/market_yan/cancel_detail/list',
    method: 'get',
    params: query
  })
}

// 查询退货明细详细
export function getCancel_detail(id) {
  return request({
    url: '/market_yan/cancel_detail/' + id,
    method: 'get'
  })
}

// 新增退货明细
export function addCancel_detail(data) {
  return request({
    url: '/market_yan/cancel_detail',
    method: 'post',
    data: data
  })
}

// 修改退货明细
export function updateCancel_detail(data) {
  return request({
    url: '/market_yan/cancel_detail',
    method: 'put',
    data: data
  })
}

// 删除退货明细
export function delCancel_detail(id) {
  return request({
    url: '/market_yan/cancel_detail/' + id,
    method: 'delete'
  })
}
