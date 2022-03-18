<template>
  <a-card>
    <DormBuildTableView/>
  </a-card>
</template>

<script lang='ts'>
import { defineComponent } from 'vue'
import DormBuildTableView from '@/views/dormbuild/DormBuildTableView'

export default defineComponent({
  name: 'DormBuild',
  components: { DormBuildTableView }
})
</script>

<style lang='scss' scoped>

</style>
