interface AdminModel {
  adminId?: number
  userName?: string
  name?: string
  sex?: string
  tel?: string
}

interface DormManagerAddParams {
  userName?: string
  dormBuildId?: number
  password?: string
  name?: string
  sex?: string
  tel?: string
}

interface DormManagerModel {
  dormManId?: number
  userName?: string
  dormBuildId?: number
  dormBuildName?: string
  name?: string
  sex?: string
  tel?: string
}

interface StudentModel {
  studentId?: number
  stuNum?: string
  name?: string
  dormBuildId?: number
  dormBuildName?: string
  dormName?: string
  sex?: string
  tel?: string
}

interface CommonUser extends StudentModel, AdminModel, DormManagerModel {
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
  DormManagerAddParams,
  CommonUser
}
