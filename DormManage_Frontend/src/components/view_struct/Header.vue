<template>
  <div>
    <a-layout-header
      class='wbu-header header-bar'
      style='background: #fff; padding: 0; height: 44px'
    >
      <a-dropdown placement='bottomLeft'>
        <div class='userinfo no-drag'>
          <span @click.prevent>
            <a-avatar class='avatar' :size='32'>
              <template #icon>
                <UserOutlined style='color: rgb(2,186,253)' />
              </template>
            </a-avatar>
          </span>
        </div>
        <template #overlay>
          <a-menu class='menu'>
            <a-menu-item key='settings' @click='changePassword'>
              <template #icon>
                <setting-outlined />
              </template>
              修改密码
            </a-menu-item>
            <a-menu-divider />
            <a-menu-item key='logout' @click='logout'>
              <template #icon>
                <LogoutOutlined />
              </template>
              登出
            </a-menu-item>
          </a-menu>
        </template>
      </a-dropdown>
    </a-layout-header>
  </div>
</template>

<script lang='ts'>
import { defineComponent, inject, nextTick } from 'vue'
import {
  HomeOutlined,
  LogoutOutlined,
  SettingOutlined,
  UserOutlined,
  CloseOutlined,
  MinusOutlined,
  FullscreenExitOutlined,
  FullscreenOutlined
} from '@ant-design/icons-vue'
import { useRoute, useRouter } from 'vue-router'
import { LoginAPI } from '@/api/login'
import useUserStore from '@/store/userStore'

export default defineComponent({
  name: 'WbuHeader',
  components: {
    UserOutlined,
    SettingOutlined,
    LogoutOutlined
  },
  setup() {
    const router = useRouter()
    const userStore = useUserStore()
    const reload = inject('reload') as () => any
    const logout = () => {
      LoginAPI.quit().then(() => {
        userStore.reset()
        router.push('/login').then(() => {
          reload()
        })
      })
    }
    const changePassword = () => {
      router.push('/change_pwd')
      console.log('changePassword')
    }
    return {
      logout,
      changePassword,
      navigator
    }
  }
})
</script>

<style lang='less' scoped>
.wbu-header {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  line-height: 44px;

  .userinfo {
    cursor: pointer;
    padding: 0 10px;
    margin: 0 10px;
    height: 100%;
  }

  .userinfo:hover {
    background-color: rgba(252, 245, 245, 0.73);
  }

  .menu {
    width: 120px;
  }
}

.trigger {
  justify-self: flex-start;
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}

.trigger:hover {
  color: #1890ff;
}
</style>
