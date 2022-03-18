<template>
  <a-card>
    <RecordTableView/>
  </a-card>
</template>

<script lang='ts'>
import { defineComponent } from 'vue'
import RecordTableView from '@/views/record/RecordTableView'

export default defineComponent({
  name: 'Record',
  components: { RecordTableView }
})
</script>

<style lang='scss' scoped>

</style>
