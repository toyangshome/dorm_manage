<template>
  <a-result status="500" title="500" sub-title="对不起,服务器发生了错误.">
    <template #extra>
      <a-button type="primary" @click="backHome">Back Home</a-button>
    </template>
  </a-result>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: '500',
  setup () {
    const router = useRouter()
    const backHome = () => {
      router.push({
        path: '/home'
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
