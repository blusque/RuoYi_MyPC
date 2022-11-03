import request from '@/utils/request'

// 查询生产列表
export function listProduce(query) {
  return request({
    url: '/produce/produce/list',
    method: 'get',
    params: query
  })
}

// 查询生产详细
export function getProduce(mrpProduceId) {
  return request({
    url: '/produce/produce/' + mrpProduceId,
    method: 'get'
  })
}

// 新增生产
export function addProduce(data) {
  return request({
    url: '/produce/produce',
    method: 'post',
    data: data
  })
}

// 修改生产
export function updateProduce(data) {
  return request({
    url: '/produce/produce',
    method: 'put',
    data: data
  })
}

// 删除生产
export function delProduce(mrpProduceId) {
  return request({
    url: '/produce/produce/' + mrpProduceId,
    method: 'delete'
  })
}
