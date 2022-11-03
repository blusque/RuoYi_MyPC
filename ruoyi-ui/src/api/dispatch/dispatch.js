import request from '@/utils/request'

// 查询派工单子列表
export function listDispatch(query) {
  return request({
    url: '/dispatch/dispatch/list',
    method: 'get',
    params: query
  })
}

// 查询派工单子详细
export function getDispatch(mcnum) {
  return request({
    url: '/dispatch/dispatch/' + mcnum,
    method: 'get'
  })
}

// 新增派工单子
export function addDispatch(data) {
  return request({
    url: '/dispatch/dispatch',
    method: 'post',
    data: data
  })
}

// 修改派工单子
export function updateDispatch(data) {
  return request({
    url: '/dispatch/dispatch',
    method: 'put',
    data: data
  })
}

// 删除派工单子
export function delDispatch(mcnum) {
  return request({
    url: '/dispatch/dispatch/' + mcnum,
    method: 'delete'
  })
}
