import request from '@/utils/request'

// 查询订单明细列表
export function listDetail(query) {
  return request({
    url: '/market_yan/detail/list',
    method: 'get',
    params: query
  })
}

// 查询订单明细详细
export function getDetail(id) {
  return request({
    url: '/market_yan/detail/' + id,
    method: 'get'
  })
}

// 新增订单明细
export function addDetail(data) {
  return request({
    url: '/market_yan/detail',
    method: 'post',
    data: data
  })
}

// 修改订单明细
export function updateDetail(data) {
  return request({
    url: '/market_yan/detail',
    method: 'put',
    data: data
  })
}

// 删除订单明细
export function delDetail(id) {
  return request({
    url: '/market_yan/detail/' + id,
    method: 'delete'
  })
}
