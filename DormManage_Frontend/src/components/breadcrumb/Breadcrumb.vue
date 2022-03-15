<template>
  <div class="breadcrumb">
    <a-breadcrumb>
      <a-breadcrumb-item
        v-for="(item) in items"
        :key="item.name"
      >
        <router-link :to="item.path">
          {{ item.name }}
        </router-link>
      </a-breadcrumb-item>
    </a-breadcrumb>
  </div>
</template>

<script lang="ts">
import {defineComponent, ref} from 'vue';
import {useRoute, useRouter} from 'vue-router';

interface BreadcrumbItem {
  name: string,
  path: string
}

export default defineComponent({
  name: 'Breadcrumb',
  setup() {
    const route = useRoute();
    const router = useRouter();
    const getItems = () => {
      const items = [];
      route.matched.forEach((item) => {
        if (item.path == '/') return;
        items.push({
          name: item.meta.title as string,
          path: item.path,
        });
      });
      return items;
    };
    // 初始化面包屑项
    const items = ref<BreadcrumbItem[]>([...getItems()]);
    // 初始化路由跳转
    router.afterEach(() => {
      items.value = [];
      items.value.push(...getItems());
    });
    return {
      items,
    };
  },
});
</script>
<style scoped>
.breadcrumb {
  margin: 15px;
  background-color: white;
  padding: 8px 25px;
  border-radius: 0.5em;
}
</style>
