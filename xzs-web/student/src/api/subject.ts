import request from '../utils/request'

export function getSubjectList() {
  return request.get('/student/education/subject/list')
}
