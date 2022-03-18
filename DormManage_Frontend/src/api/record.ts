import { HttpResponse, PageResponse } from '@/@types'
import { webService } from '@/api/axios'
import { RecordListParams, RecordModel, RecordUpdateParams } from '@/api/model/record'
import exp from 'constants'

const recordAPIUrl = {
  list: 'record/list',
  update: 'record/update',
  delete: 'record/delete',
  add: 'record/add'
}


const RecordAPI = {
  async list(params: RecordListParams): Promise<HttpResponse<PageResponse<RecordModel>>> {
    return webService.post(recordAPIUrl.list, params)
  },
  async update(params: RecordUpdateParams): Promise<HttpResponse<RecordModel>> {
    return webService.post(recordAPIUrl.update, params)
  },
  async delete(id: number): Promise<HttpResponse<void>> {
    return webService.post(recordAPIUrl.delete + '/' + id)

  },
  async add(params: RecordModel): Promise<HttpResponse<void>> {
    return webService.post(recordAPIUrl.add, params)
  }
}

export default RecordAPI
