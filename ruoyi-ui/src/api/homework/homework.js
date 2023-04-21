import request from '@/utils/request'

// 查询发布作业列表
export function listHomework(query) {
  return request({
    url: '/homework/homework/list',
    method: 'get',
    params: query
  })
}

// 查询发布作业详细
export function getHomework(homeworkId) {
  return request({
    url: '/homework/homework/' + homeworkId,
    method: 'get'
  })
}

// 新增发布作业
export function addHomework(data) {
  return request({
    url: '/homework/homework',
    method: 'post',
    data: data
  })
}

// 修改发布作业
export function updateHomework(data) {
  return request({
    url: '/homework/homework',
    method: 'put',
    data: data
  })
}

// 删除发布作业
export function delHomework(homeworkId) {
  return request({
    url: '/homework/homework/' + homeworkId,
    method: 'delete'
  })
}
