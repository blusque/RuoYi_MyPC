import request from '@/utils/request'

// 查询主生产计划列表
export function listProduce(query) {
  return request({
    url: '/mrp/produce/list',
    method: 'get',
    params: query
  })
}

// 查询主生产计划详细
export function getProduce(mainProduceId) {
  return request({
    url: '/mrp/produce/' + mainProduceId,
    method: 'get'
  })
}

// 新增主生产计划
export function addProduce(data) {
  return request({
    url: '/mrp/produce',
    method: 'post',
    data: data
  })
}

// 修改主生产计划
export function updateProduce(data) {
  return request({
    url: '/mrp/produce',
    method: 'put',
    data: data
  })
}

// 删除主生产计划
export function delProduce(mainProduceId) {
  return request({
    url: '/mrp/produce/' + mainProduceId,
    method: 'delete'
  })
}
