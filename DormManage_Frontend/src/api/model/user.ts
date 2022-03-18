interface AdminModel {
  adminId?: number
  userName?: string
  name?: string
  sex?: string
  tel?: string
}

interface DormManagerAddParams {
  userName?: string
  dormBuildName?: string
  password?: string
  name?: string
  sex?: string
  tel?: string
}

interface DormManagerModel {
  dormManId?: number
  userName?: string
  dormBuildName?: string
  name?: string
  sex?: string
  tel?: string
}

interface StudentModel {
  studentId?: number
  stuNum?: string
  name?: string
  dormBuildId?: string
  dormName?: string
  sex?: string
  tel?: string
}

interface LoginResponse<T> {
  userInfo: T
  role: 0 | 1 | 2
}

export {
  AdminModel,
  DormManagerModel,
  StudentModel,
  LoginResponse,
  DormManagerAddParams
}
