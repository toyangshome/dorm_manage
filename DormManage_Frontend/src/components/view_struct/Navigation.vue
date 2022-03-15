<template>
  <div class='wbu-side-nav'>
    <a-layout-sider>
      <div class='logo'>
        <img style='height: 36px' src='../../assets/adminLogo.png' alt='' />
      </div>
      <a-menu
        v-model:selectedKeys='selectedKeys'
        theme='light'
        mode='inline'
        @click='menuClick'
      >
        <a-menu-item key='/welcome'>
          <pie-chart-outlined />
          <span>首页</span>
        </a-menu-item>
        <a-menu-item key='/dorm_manager'>
          <file-text-outlined />
          <span>宿舍管理员管理</span>
        </a-menu-item>
        <a-menu-item key='/student'>
          <ReadOutlined />
          <span>学生管理</span>
        </a-menu-item>
        <a-menu-item key='/dorm_build'>
          <layout-outlined />
          <span>宿舍楼管理</span>
        </a-menu-item>
        <a-menu-item key='/record'>
          <setting-outlined />
          <span>缺勤记录</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
  </div>
</template>

<script lang='ts'>
import {
  PieChartOutlined,
  ReadOutlined,
  FileTextOutlined,
  LayoutOutlined,
  SettingOutlined
} from '@ant-design/icons-vue'
import { defineComponent, ref } from 'vue'
import { useRouter } from 'vue-router'
import { MenuInfo } from 'ant-design-vue/es/menu/src/interface'

export default defineComponent({
  name: 'WbuSideNav',
  components: {
    PieChartOutlined,
    ReadOutlined,
    FileTextOutlined,
    LayoutOutlined,
    SettingOutlined
  },
  setup() {
    const router = useRouter()
    const selectedKeys = ref([])
    const menuClick = (e: MenuInfo) => {
      router.push({
        path: e.key as string
      })
    }
    return {
      menuClick,
      selectedKeys
    }
  }
})
</script>

<style lang='scss' scoped>
.wbu-side-nav {
  height: 100vh;
  background-color: #ffffff;
  outline: none;
  transition: background 0.3s, width 0.3s cubic-bezier(0.2, 0, 0, 1) 0s;

  .ant-layout-sider {
    background: #ffffff;
  }
}

.trigger:hover {
  color: #1890ff;
}

.logo {
  height: 32px;
  margin: 16px;
  text-align: center;
  line-height: 32px;
}

.site-layout .site-layout-background {
  background: #fff;
}
</style>
