<template>
  <div id='app'>
    <router-view v-if='isRouterAlive' />
  </div>
</template>
<script lang='ts' setup>
import { nextTick, provide, ref } from 'vue'

const isRouterAlive = ref<boolean>(true)
const reload = () => {
  isRouterAlive.value = false
  nextTick(() => {
    location.reload()
    console.log('reload....')
  })
}
provide('reload', reload)
</script>

