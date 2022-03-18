import { HttpResponse, PageResponse } from '@/@types'
import { webService } from '@/api/axios'
import { DormBuildListParams, DormBuildModel } from '@/api/model/dormBuild'

const dormBuildAPIUrl = {
  list: 'dorm_build/list',
  delete: 'dorm_build/delete',
  update: 'dorm_build/update',
  add: 'dorm_build/add'
}


const DormBuildAPI = {
  async list(params: DormBuildListParams): Promise<HttpResponse<PageResponse<DormBuildModel>>> {
    return webService.post(dormBuildAPIUrl.list, params)
  },
  async update(params: DormBuildModel): Promise<HttpResponse<DormBuildModel>> {
    return webService.post(dormBuildAPIUrl.update, params)
  },
  async delete(id: number): Promise<HttpResponse<void>> {
    return webService.post(dormBuildAPIUrl.delete + '/' + id)

  },
  async add(params: DormBuildModel): Promise<HttpResponse<void>> {
    return webService.post(dormBuildAPIUrl.add, params)
  }
}

export default DormBuildAPI
