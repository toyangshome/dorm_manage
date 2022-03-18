import { HttpResponse, Page, PageResponse } from '@/@types'
import { DormManagerAddParams, DormManagerModel } from '@/api/model/user'
import { webService } from '@/api/axios'

const dormManagerApiUrl = {
  list: 'dorm_manager/list',
  update: 'dorm_manager/update',
  delete: 'dorm_manager/delete',
  add: 'dorm_manager/add'
}

export interface DormManagerListParams {
  page: Page
  name?: string
}

export const DormManagerAPI = {
  async list(params: DormManagerListParams): Promise<HttpResponse<PageResponse<DormManagerModel>>> {
    return webService.post(dormManagerApiUrl.list, params)
  },
  async add(params: DormManagerAddParams): Promise<HttpResponse<void>> {
    return webService.post(dormManagerApiUrl.add, params)
  },
  async delete(dormManagerId: number): Promise<HttpResponse<void>> {
    return webService.get(dormManagerApiUrl.delete + '/' + dormManagerId)
  }
}
