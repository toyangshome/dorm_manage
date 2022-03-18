<template>
  <a-card>
    <StudentTableView/>
  </a-card>
</template>

<script lang='ts'>
import { defineComponent } from 'vue'
import StudentTableView from '@/views/student/StudentTableView'

export default defineComponent({
  name: 'Student',
  components: { StudentTableView }
})
</script>

<style lang='scss' scoped>

</style>
