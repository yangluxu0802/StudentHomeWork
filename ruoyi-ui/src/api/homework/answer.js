import request from '@/utils/request'

// 查询学生作业作答列表
export function listAnswer(query) {
  return request({
    url: '/homework/answer/list',
    method: 'get',
    params: query
  })
}

// 查询学生作业作答详细
export function getAnswer(homeworkAnswerId) {
  return request({
    url: '/homework/answer/' + homeworkAnswerId,
    method: 'get'
  })
}

// 新增学生作业作答
export function addAnswer(data) {
  return request({
    url: '/homework/answer',
    method: 'post',
    data: data
  })
}

// 修改学生作业作答
export function updateAnswer(data) {
  return request({
    url: '/homework/answer',
    method: 'put',
    data: data
  })
}

// 删除学生作业作答
export function delAnswer(homeworkAnswerId) {
  return request({
    url: '/homework/answer/' + homeworkAnswerId,
    method: 'delete'
  })
}
