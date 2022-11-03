import request from '@/utils/request'

// 查询采购订单退货明细列表
export function listDetail(query) {
  return request({
    url: '/purchase/cancel/list',
    method: 'get',
    params: query
  })
}

// 查询采购订单退货明细详细
export function getDetail(orderId) {
  return request({
    url: '/purchase/cancel/' + orderId,
    method: 'get'
  })
}

// 新增采购订单退货明细
export function addDetail(data) {
  return request({
    url: '/purchase/cancel',
    method: 'post',
    data: data
  })
}

// 修改采购订单退货明细
export function updateDetail(data) {
  return request({
    url: '/purchase/cancel',
    method: 'put',
    data: data
  })
}

// 删除采购订单退货明细
export function delDetail(orderId) {
  return request({
    url: '/purchase/cancel/' + orderId,
    method: 'delete'
  })
}
