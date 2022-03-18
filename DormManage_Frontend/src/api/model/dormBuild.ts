import { Page } from '@/@types'

export interface DormBuildModel {
  id?: number
  detail?: string
  dormBuildName: string
}
export interface DormBuildListParams {
  page: Page
  dormBuildName?: string
}
