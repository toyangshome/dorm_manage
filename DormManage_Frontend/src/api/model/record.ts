import { Page } from '@/@types'

export interface RecordModel {
  recordId?: number
  date?: Date
  detail?: string
  dormBuildName?: string
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
  detail?: string
  dormBuildId?:number
  studentNumber?: string
}
