import { acceptHMRUpdate, defineStore } from 'pinia'
import { LoginAPI } from '@/api/login'

const defaultState = {
  currentRole: -1,
  auth: false,
  userInfo: {}
}
const useUserStore = defineStore({
  id: 'user',
  state: () => {
    return defaultState
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
      this.userInfo = res.data.userInfo
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
