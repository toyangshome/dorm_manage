import { defineComponent } from 'vue'
import useUserStore from '@/store/userStore'
import { RoleEnum } from '@/@types'
import { AdminModel, DormManagerModel, StudentModel } from '@/api/model/user'
import { Card } from 'ant-design-vue'
import style from './index.module.less'

export default defineComponent({
  name: 'CurrentUserInfo',
  setup() {
    const userStore = useUserStore()
    return () => (
      <>
        <Card class={style.info_card}>
          {() => {
            if (userStore.currentRole === RoleEnum.ADMIN) {
              const userInfo = userStore.userInfo as AdminModel
              return <div>当前为系统管理员: {userInfo.name} </div>
            } else if (userStore.currentRole === RoleEnum.DORM_MANAGER) {
              const userInfo = userStore.userInfo as DormManagerModel
              return <div>
                <p> 当前为宿舍管理员: {userInfo.name}</p>
                <p> 管理楼栋: {userInfo.dormBuildName}</p>
              </div>
            } else {
              const userInfo = userStore.userInfo as StudentModel
              return <div>
                <p>当前为学生: {userInfo.name}</p>
                <p>宿舍: {userInfo.dormBuildName} - {userInfo.dormName}号</p>
              </div>
            }
          }
          }
        </Card>
      </>
    )
  }
})
