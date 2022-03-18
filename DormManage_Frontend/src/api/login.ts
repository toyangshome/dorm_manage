import { AdminModel, CommonUser, DormManagerModel, LoginResponse, StudentModel } from '@/api/model/user'
import { HttpResponse } from '@/@types'
import { webService } from '@/api/axios'

const LoginUrl = {
  LOGIN: 'user/login',
  GET: 'user/info',
  QUIT: 'user/quit'
}
export const roleMap = {
  0: '学生',
  1: '宿舍管理员',
  2: '系统管理员'
}

export interface LoginParams {
  username: string
  password: string
  role: number
}

export const LoginAPI = {
  async login(params: LoginParams): Promise<HttpResponse<{ userInfo?: CommonUser, role: number }>> {
    return webService.post(LoginUrl.LOGIN, params)
  },
  async getInfo(): Promise<HttpResponse<LoginResponse<any>>> {
    return webService.get(LoginUrl.GET)
  },
  async quit(): Promise<HttpResponse<void>> {
    return webService.get(LoginUrl.QUIT)
  }
}
