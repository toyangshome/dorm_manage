<template>
  <div id="app">
    <a-button type="primary"> Hello </a-button>
  </div>
</template>
<script lang="ts">
import { defineComponent, onBeforeMount } from 'vue'

export default defineComponent({
  name: 'App',
  setup() {
    return {}
  }
})
</script>
<style lang="scss">
#nprogress .bar {
  background: #590eea;
}
</style>
