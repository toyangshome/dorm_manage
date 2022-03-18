import { acceptHMRUpdate, defineStore } from 'pinia'
import { LoginAPI } from '@/api/login'
import { CommonUser } from '@/api/model/user'
import { cloneDeep } from 'lodash'

interface StateType {
  currentRole: number,
  auth: boolean,
  userInfo: CommonUser
}

const defaultState: StateType = {
  currentRole: -1,
  auth: false,
  userInfo: {
    name: '',
    sex: '',
    tel: '',
    stuNum: '',
    dormBuildName: '',
    dormName: '',
    dormManId: 0,
    dormBuildId: 0,
    studentId: 0,
    userName: '',
    adminId: 0
  }
}
const useUserStore = defineStore({
  id: 'user',
  state: () => {
    return cloneDeep(defaultState)
  },
  actions: {
    async loadUser() {
      console.log('loading info...')
      const { data: res } = await LoginAPI.getInfo()
      if (res.code !== 200) {
        this.$state = defaultState
        return
      }
      this.auth = true
      this.currentRole = res.data.role
      Object.assign(this.userInfo, res.data.userInfo)
    },
    reset() {
      this.$state = cloneDeep(defaultState)
    }
  },
  persist: {
    enabled: true
  }
})
if (import.meta.hot) {
  import.meta.hot.accept(acceptHMRUpdate(useUserStore, import.meta.hot))
}
export default useUserStore
