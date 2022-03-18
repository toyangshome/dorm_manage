<template>
<a-card>
  <div class='welcome'>欢迎您，{{ roleMap[role] }}</div>
</a-card>
</template>

<script lang='ts' setup>
import { roleMap } from '@/api/login'
import useUserStore from '@/store/userStore'
const store = useUserStore()
const role = store.currentRole
</script>

<style lang='scss' scoped>
.welcome {
  padding: 60px 0;
  text-align: center;
  font-size: 80px;
  font-weight: bold;
}
</style>
