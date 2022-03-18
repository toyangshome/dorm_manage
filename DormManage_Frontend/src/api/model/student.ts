import { Page } from '@/@types'

export interface StudentListParams {
  page: Page
  dormBuildName?: string
  studentName?: string
}

export interface StudentUpdateParams {
  dormBuildId: string
  dormName: string
  name: string
  sex?: string
  studentId: number
  tel?: string
}


export interface StudentAddParams {
  dormBuild: string
  dormName: string
  name: string
  password: string
  sex?: string
  stuNum: string
  tel: string
}
