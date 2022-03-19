import { computed, defineComponent, PropType, reactive, ref } from 'vue'
import { Button, DatePicker, Form, FormInstance, Input, message } from 'ant-design-vue'
import { RecordModel } from '@/api/model/record'
import dayjs from 'dayjs'
import RecordAPI from '@/api/record'

const rules = {
  studentNumber: [
    {
      required: true,
      len: 3,
      message: '长度必须为3',
      trigger: 'blur'
    },
    {
      whitespace: true,
      message: '不能含有空格'
    }
  ]
}
const updateProps = {
  record: {
    type: Object as PropType<RecordModel>,
    required: true
  }
}
export default defineComponent({
  name: 'RecordUpdate',
  props: updateProps,
  emits: ['updateSuccess'],
  setup(props, { emit }) {
    const formRef = ref<FormInstance>()
    const updateRecord = computed(() => props.record).value as RecordModel
    const addModel = reactive({
      recordId: updateRecord.recordId,
      studentName: updateRecord.studentName,
      studentNumber: updateRecord.studentNumber,
      dormBuildName: updateRecord.dormBuildName,
      date: dayjs(updateRecord.date, 'YYYY-MM-DD'),
      detail: updateRecord.detail,
      dormName: updateRecord.dormName
    })
    const addSubmit = async () => {
      const { data: res } = await RecordAPI.update(addModel)
      if (res.code !== 200) {
        return message.error(res.message)
      }
      emit('updateSuccess', res.data)
      return message.success('添加成功')
    }
    return () => (
      <>
        <Form
          ref={formRef}
          rules={rules}
          model={addModel}
          labelAlign={'right'}
          labelCol={{ 'span': 6 }}
          wrapperCol={{ 'span': 14 }}>
          <Form.Item hasFeedback label={'Id'} name={'name'}>
            <Input disabled value={addModel.recordId} />
          </Form.Item>
          <Form.Item hasFeedback label={'学号'} name={'studentNumber'}>
            <Input v-model:value={addModel.studentNumber} />
          </Form.Item>
          <Form.Item required hasFeedback label={'时间'} name={'date'}>
            <DatePicker format={'YYYY-MM-DD'} v-model:value={addModel.date} />
          </Form.Item>
          <Form.Item hasFeedback label={'详细'} name={'detail'}>
            <Input v-model:value={addModel.detail} />
          </Form.Item>
          <Form.Item wrapperCol={{ span: 14, offset: 9 }}>
            <Button type={'primary'} onClick={addSubmit}>添加</Button>
          </Form.Item>
        </Form>
      </>
    )
  }
})
