import { HttpResponse } from '@/@types'
import { webService } from '@/api/axios'

const passwordAPIUrl = {
  change: 'user/change_password'
}

export interface ChangePasswordParams {
  id: number
  newPassword: string
  oldPassword: string
}

const PasswordAPI = {
  change(params: ChangePasswordParams): Promise<HttpResponse<void>> {
    return webService.post(passwordAPIUrl.change, params)
  }
}

export default PasswordAPI
