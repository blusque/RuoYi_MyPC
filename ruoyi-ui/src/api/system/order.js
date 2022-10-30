import request from '@/utils/request'

// 查询销售订单列表
export function listOrder(data) {
  return request({
    url: '/system/order/list',
    method: 'put',
    data: data
  })
}

// 查询销售订单列表
export function listOrderDetail(query) {
  return request({
    url: '/system/order/list/detail',
    method: 'get',
    params: query
  })
}

// 查询销售订单列表
export function listCancelDetail(query) {
  return request({
    url: '/system/order/list/cancel',
    method: 'get',
    params: query
  })
}

// 查询销售订单详细
export function getOrder(id) {
  return request({
    url: '/system/order/' + id,
    method: 'get'
  })
}

// 查询销售订单详细
export function getOrderList(ids) {
  return request({
    url: '/system/order/' + ids,
    method: 'get'
  })
}

// 新增销售订单
export function addOrder(data) {
  return request({
    url: '/system/order',
    method: 'post',
    data: data
  })
}

// 新增销售订单明细
export function addOrderDetail(data) {
  return request({
    url: '/system/order/detail',
    method: 'post',
    data: data
  })
}

// 新增销售退货明细
export function addCancelDetail(data) {
  return request({
    url: '/system/order/cancel',
    method: 'post',
    data: data
  })
}

// 修改销售订单
export function updateOrder(data) {
  return request({
    url: '/system/order',
    method: 'put',
    data: data
  })
}

// 修改销售订单
export function updateOrderDetail(data) {
  return request({
    url: '/system/order/detail',
    method: 'put',
    data: data
  })
}

// 修改销售订单
export function updateCancelDetail(data) {
  return request({
    url: '/system/order/cancel',
    method: 'put',
    data: data
  })
}

// 删除销售订单
export function delOrder(ids) {
  return request({
    url: '/system/order/' + ids,
    method: 'delete'
  })
}

export function delOrderDetail(ids) {
  return request({
    url: '/system/order/detail/' + ids,
    method: 'delete'
  })
}

export function delCancelDetail(ids) {
  return request({
    url: '/system/order/cancel/' + ids,
    method: 'delete'
  })
}
