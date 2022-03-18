<template>
  <div class='wbu-side-nav'>
    <a-layout-sider>
      <div class='logo'>
        <img style='height: 36px' src='../../assets/adminLogo.png' alt='' />
      </div>
      <Menu />
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
import { getMenuRoutes } from '@/router'
import Menu from '@/components/view_struct/Menu'

export default defineComponent({
  name: 'WbuSideNav',
  components: {
    Menu,
    PieChartOutlined,
    ReadOutlined,
    FileTextOutlined,
    LayoutOutlined,
    SettingOutlined
  },
  setup() {
    const router = useRouter()
    const menus = getMenuRoutes()
    const selectedKeys = ref([])
    const menuClick = (e: MenuInfo) => {
      router.push({
        path: e.key as string
      })
    }
    return {
      menuClick,
      selectedKeys,
      menus
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
