import request from '@/utils/request'

// 查询采购列表
export function listPurchase(query) {
  return request({
    url: '/purchase/purchase/list',
    method: 'get',
    params: query
  })
}

// 查询采购详细
export function getPurchase(mrpPurchaseId) {
  return request({
    url: '/purchase/purchase/' + mrpPurchaseId,
    method: 'get'
  })
}

// 新增采购
export function addPurchase(data) {
  return request({
    url: '/purchase/purchase',
    method: 'post',
    data: data
  })
}

// 修改采购
export function updatePurchase(data) {
  return request({
    url: '/purchase/purchase',
    method: 'put',
    data: data
  })
}

// 删除采购
export function delPurchase(mrpPurchaseId) {
  return request({
    url: '/purchase/purchase/' + mrpPurchaseId,
    method: 'delete'
  })
}
