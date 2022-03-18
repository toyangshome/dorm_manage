import { Page } from '@/@types'

export interface RecordModel {
  recordId?: number
  date?: string
  detail?: string
  dormBuildName?: number
  dormName?: string
  studentName?: string
  studentNumber?: string
}

export interface RecordListParams {
  page: Page
  dormBuildName?: string
  studentName?: string
  dormName?: string
}

export interface RecordUpdateParams {

}


export interface RecordAddParams {

}
