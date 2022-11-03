import request from '@/utils/request'

// 查询生产工票列表
export function listTickets(query) {
  return request({
    url: '/tickets/tickets/list',
    method: 'get',
    params: query
  })
}

// 查询生产工票详细
export function getTickets(mcnum) {
  return request({
    url: '/tickets/tickets/' + mcnum,
    method: 'get'
  })
}

// 新增生产工票
export function addTickets(data) {
  return request({
    url: '/tickets/tickets',
    method: 'post',
    data: data
  })
}

// 修改生产工票
export function updateTickets(data) {
  return request({
    url: '/tickets/tickets',
    method: 'put',
    data: data
  })
}

// 删除生产工票
export function delTickets(mcnum) {
  return request({
    url: '/tickets/tickets/' + mcnum,
    method: 'delete'
  })
}
