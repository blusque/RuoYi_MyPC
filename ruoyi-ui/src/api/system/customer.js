import request from '@/utils/request'

// 查询客户管理列表
export function listCustomer(query) {
  return request({
    url: '/system/customer/list',
    method: 'get',
    params: query
  })
}

export function listCustomerAddress(query) {
  return request({
    url: '/system/customer/address/list',
    method: 'get',
    params: query
  })
}

// 查询客户管理详细
export function getCustomer(id) {
  return request({
    url: '/system/customer/list/' + id,
    method: 'get'
  })
}

// 查询客户地址
export function getCustomerAddress(id) {
  return request({
    url: '/system/customer/address/list/' + id,
    method: 'get'
  })
}

// 新增客户管理
export function addCustomer(data) {
  return request({
    url: '/system/customer',
    method: 'post',
    data: data
  })
}

// 新增客户地址
export function addCustomerAddress(data) {
  return request({
    url: '/system/customer/address',
    method: 'post',
    data: data
  })
}

// 编辑客户管理
export function updateCustomer(data) {
  return request({
    url: '/system/customer',
    method: 'put',
    data: data
  })
}

// 编辑客户地址
export function updateCustomerAddress(data) {
  return request({
    url: '/system/customer/address',
    method: 'put',
    data: data
  })
}

// 删除客户管理
export function delCustomer(id) {
  return request({
    url: '/system/customer/' + id,
    method: 'delete'
  })
}

// 批量删除客户管理
export function delCustomerList(ids) {
  return request({
    url: '/system/customer',
    method: 'delete',
    params: ids
  })
}

// 删除客户地址
export function delCustomerAddress(data) {
  return request({
    url: '/system/customer',
    method: 'delete',
    data: data
  })
}
