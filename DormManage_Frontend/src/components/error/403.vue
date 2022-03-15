<template>
  <a-result status="403" title="403" sub-title="对不起,您没有权限访问该页面.">
    <template #extra>
      <a-button type="primary" @click="backHome">Back Home</a-button>
    </template>
  </a-result>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: '403',
  setup () {
    const router = useRouter()
    const backHome = () => {
      router.push({
        path: '/welcome'
      })
    }
    return {
      backHome
    }
  }
})
</script>

<style lang="scss" scoped>

</style>
