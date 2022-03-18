import { defineStore } from 'pinia'
import DormBuildAPI from '@/api/dormBuild'

const defaultState = {
  dormBuilds: [],
  currentDormBuild: {
    dormBuildName: '',
    dormBuildId: 0
  }
}
const useBuildStore = defineStore({
  id: 'dormBuild',
  state: () => (defaultState),
  actions: {
    async loadDormBuilds() {
      const { data: res } = await DormBuildAPI.listAll()
      if (res.code !== 200) return
      this.$state.dormBuilds = res.data
    }
  },
  getters: {
    getDormBuilds(state) {
      return state.dormBuilds
    }
  }
})
export default useBuildStore
