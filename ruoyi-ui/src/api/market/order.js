import request from '@/utils/request'

// 查询销售订单列表
export function listOrder(data) {
  return request({
    url: '/market/order/list',
    method: 'put',
    data: data
  })
}

export function listOrderFromTo(query) {
  return request({
    url: '/market/order/list/from_to',
    method: 'get',
    params: query
  })
}

// 查询销售订单列表
export function listOrderDetail(query) {
  return request({
    url: '/market/order/list/detail',
    method: 'get',
    params: query
  })
}

// 查询销售订单列表
export function listCancelDetail(query) {
  return request({
    url: '/market/order/list/cancel',
    method: 'get',
    params: query
  })
}

// 查询销售订单详细
export function getOrder(id) {
  return request({
    url: '/market/order/' + id,
    method: 'get'
  })
}

// 查询销售订单详细
export function getOrderList(ids) {
  return request({
    url: '/market/order/' + ids,
    method: 'get'
  })
}

export function getReportByMaterialName(query) {
  return request({
    url: '/market/order/search/name',
    method: 'get',
    params: query,
  })
}

// 新增销售订单
export function addOrder(data) {
  return request({
    url: '/market/order',
    method: 'post',
    data: data
  })
}

// 新增销售订单明细
export function addOrderDetail(data) {
  return request({
    url: '/market/order/detail',
    method: 'post',
    data: data
  })
}

// 新增销售退货明细
export function addCancelDetail(data) {
  return request({
    url: '/market/order/cancel',
    method: 'post',
    data: data
  })
}

// 修改销售订单
export function updateOrder(data) {
  return request({
    url: '/market/order',
    method: 'put',
    data: data
  })
}

// 修改销售订单
export function updateOrderDetail(data) {
  return request({
    url: '/market/order/detail',
    method: 'put',
    data: data
  })
}

// 修改销售订单
export function updateCancelDetail(data) {
  return request({
    url: '/market/order/cancel',
    method: 'put',
    data: data
  })
}

// 删除销售订单
export function delOrder(ids) {
  return request({
    url: '/market/order/' + ids,
    method: 'delete'
  })
}

export function delOrderDetail(ids) {
  return request({
    url: '/market/order/detail/' + ids,
    method: 'delete'
  })
}

export function delCancelDetail(ids) {
  return request({
    url: '/market/order/cancel/' + ids,
    method: 'delete'
  })
}
