import { HttpResponse, Page, PageResponse } from '@/@types'
import { StudentModel } from '@/api/model/user'
import { webService } from '@/api/axios'
import { StudentAddParams, StudentListParams, StudentUpdateParams } from '@/api/model/student'

const studentAPIUrl = {
  list: 'student/list',
  update: 'student/update',
  delete: 'student/delete',
  add: 'student/add'
}


const StudentAPI = {
  async list(params: StudentListParams): Promise<HttpResponse<PageResponse<StudentModel>>> {
    return webService.post(studentAPIUrl.list, params)
  },
  async update(params: StudentUpdateParams): Promise<HttpResponse<StudentModel>> {
    return webService.post(studentAPIUrl.update, params)
  },
  async delete(id: number): Promise<HttpResponse<void>> {
    return webService.post(studentAPIUrl.delete + '/' + id)

  },
  async add(params: StudentAddParams): Promise<HttpResponse<void>> {
    return webService.post(studentAPIUrl.add, params)
  }
}
export default StudentAPI
