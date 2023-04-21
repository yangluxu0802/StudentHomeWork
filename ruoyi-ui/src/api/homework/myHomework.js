import request from '@/utils/request'

// 查询作业分配列表
export function listAssignment(query) {
  return request({
    url: '/homework/my/list',
    method: 'get',
    params: query
  })
}

// 查询作业分配详细
export function getAssignment(assignmentId) {
  return request({
    url: '/homework/my/' + assignmentId,
    method: 'get'
  })
}

// 新增作业分配
export function addAssignment(data) {
  return request({
    url: '/homework/assignment',
    method: 'post',
    data: data
  })
}

// 完成作业
export function complete(data) {
  return request({
    url: '/homework/my/complete',
    method: 'put',
    data: data
  })
}

// 删除作业分配
export function delAssignment(assignmentId) {
  return request({
    url: '/homework/assignment/' + assignmentId,
    method: 'delete'
  })
}
