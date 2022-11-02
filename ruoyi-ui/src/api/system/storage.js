import request from '@/utils/request'

// 查询仓库管理列表
export function listStorage(query) {
  return request({
    url: '/system/storage/list',
    method: 'get',
    params: query
  })
}

// 查询仓库管理详细
export function getStorage(id) {
  return request({
    url: '/system/storage/' + id,
    method: 'get'
  })
}

// 新增仓库管理
export function addStorage(data) {
  return request({
    url: '/system/storage',
    method: 'post',
    data: data
  })
}

// 修改仓库管理
export function updateStorage(data) {
  return request({
    url: '/system/storage',
    method: 'put',
    data: data
  })
}

// 删除仓库管理
export function delStorage(id) {
  return request({
    url: '/system/storage/' + id,
    method: 'delete'
  })
}
