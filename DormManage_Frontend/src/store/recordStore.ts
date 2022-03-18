import { defineStore } from 'pinia'
import useUserStore from '@/store/userStore'
import { RoleEnum } from '@/@types'
import { RecordModel } from '@/api/model/record'
import RecordAPI from '@/api/record'

const defaultState: { records: RecordModel[] } = {
  records: []
}

const recordStore = defineStore('record', {
  state: () => (defaultState),
  actions: {
    loadRecord() {
      const userStore = useUserStore()
      const curRole = userStore.currentRole
      if (curRole === RoleEnum.ADMIN || curRole === RoleEnum.DORM_MANAGER) {
      } else if (curRole === RoleEnum.STUDENT) {
      } else {
        return
      }
    }
  }
})
