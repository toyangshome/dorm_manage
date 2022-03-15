<template>
<a-card>
  <TableView></TableView>
</a-card>
</template>

<script lang='ts'>
import TableView from '@/views/dormmanager/TableView'
import { defineComponent } from 'vue'
export default defineComponent({
  components: {
    TableView
  }
})
</script>

<style lang='scss' scoped>

</style>
