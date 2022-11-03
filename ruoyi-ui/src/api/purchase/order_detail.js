import request from '@/utils/request'

// 查询采购订单明细列表
export function listDetail(query) {
  return request({
    url: '/purchase/order/detail/list',
    method: 'get',
    params: query
  })
}

// 查询采购订单明细详细
export function getDetail(orderId) {
  return request({
    url: '/purchase/order/detail/' + orderId,
    method: 'get'
  })
}

// 新增采购订单明细
export function addDetail(data) {
  return request({
    url: '/purchase/order/detail',
    method: 'post',
    data: data
  })
}

// 修改采购订单明细
export function updateDetail(data) {
  return request({
    url: '/purchase/order/detail',
    method: 'put',
    data: data
  })
}

// 删除采购订单明细
export function delDetail(orderId) {
  return request({
    url: '/purchase/order/detail/' + orderId,
    method: 'delete'
  })
}
