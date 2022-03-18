<template>
  <div class='wbu-side-nav'>
    <a-layout-sider>
      <div class='logo'>
        <img style='height: 36px' src='../../assets/adminLogo.png' alt='' />
      </div>
      <CurrentUserInfo/>
      <Menu />
    </a-layout-sider>
  </div>
</template>

<script lang='ts'>
import { defineComponent, ref } from 'vue'

import Menu from '@/components/view_struct/Menu'
import CurrentUserInfo from '@/components/view_struct/CurrentUserInfo'

export default defineComponent({
  name: 'WbuSideNav',
  components: {
    CurrentUserInfo,
    Menu
  },
  setup() {
    return {

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
