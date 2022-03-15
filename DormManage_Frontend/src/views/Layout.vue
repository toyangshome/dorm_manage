<template>
  <a-layout>
    <a-affix :offset-top="0">
      <WbuSideNav />
    </a-affix>
    <a-layout class="mainPage">
      <a-affix :offset-top="0">
        <WbuHeader />
      </a-affix>
      <div class="content-footer">
        <a-layout-content>
          <breadcrumb />
          <router-view class="content" />
        </a-layout-content>
        <WbuFooter />
      </div>
    </a-layout>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent, provide, ref} from 'vue';
import WbuHeader from '@/components/view_struct/Header.vue'
import WbuFooter from '@/components/view_struct/Footer.vue'
import WbuSideNav from '@/components/view_struct/Navigation.vue'

import Breadcrumb from '@/components/Breadcrumb/Breadcrumb.vue';

export default defineComponent({
  name: 'Layout',
  components: {
    Breadcrumb,
    WbuHeader,
    WbuFooter,
    WbuSideNav,
  },
  setup() {
    const collapsed = ref<boolean>(false);
    provide('collapsed', collapsed);
    return {
      collapsed,
    };
  },
});
</script>

<style lang="scss" scoped>
.content {
  margin: 15px 15px;
}
.content-footer {
  overflow: auto;
  height: 92vh;
}

.override-ant-card {
  :global(.ant-card) {
    border-style: none;
    border-radius: .5em;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08), 0 1px 2px rgba(0, 0, 0, 0.1);
  }
}
</style>
