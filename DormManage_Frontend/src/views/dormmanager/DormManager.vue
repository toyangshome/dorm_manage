<template>
<a-card>
  <DormManagerTableView/>
</a-card>
</template>

<script lang='ts'>
import DormManagerTableView from '@/views/dormmanager/DormManagerTableView'
import { defineComponent } from 'vue'
export default defineComponent({
  components: {
    DormManagerTableView
  }
})
</script>

<style lang='scss' scoped>

</style>
