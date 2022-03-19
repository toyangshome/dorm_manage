import { defineComponent, reactive, ref } from 'vue'
import { Button, Form, FormInstance, Input, message } from 'ant-design-vue'
import useUserStore from '@/store/userStore'
import RecordAPI from '@/api/record'

const userStore = useUserStore()
const curDormBuild = userStore.$state.userInfo.dormBuildName
const curDormBuildId = userStore.$state.userInfo.dormBuildId

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
export default defineComponent({
  name: 'RecordAdd',
  emits: ['addSuccess'],
  // eslint-disable-next-line vue/no-setup-props-destructure
  setup(props,{emit}) {
    const formRef = ref<FormInstance>()
    const addModel = reactive({
      studentNumber: '',
      dormBuildId: curDormBuildId,
      detail: ''
    })
    const addSubmit = async () => {
      RecordAPI.add(addModel).then(() => {
        message.success('添加成功')
        emit('addSuccess')
      })
    }
    return () => (
      <>
        <Form
          ref={formRef}
          rules={rules}
          model={addModel}
          onSubmit={addSubmit}
          labelAlign={'right'}
          labelCol={{ 'span': 6 }}
          wrapperCol={{ 'span': 14 }}>
          <Form.Item hasFeedback label={'学号'} name={'studentNumber'}>
            <Input v-model:value={addModel.studentNumber} />
          </Form.Item>
          <Form.Item hasFeedback label={'宿舍楼'} name={'dormBuildName'}>
            <Input disabled v-model:value={curDormBuild} />
          </Form.Item>
          <Form.Item label={'备注'} name={'detail'}>
            <Input.TextArea v-model:value={addModel.detail} />
          </Form.Item>
          <Form.Item wrapperCol={{ span: 14, offset: 7 }}>
            <Button type={'primary'} onClick={addSubmit}>添加</Button>
            <Button style={{ 'margin-left': '10px' }} htmlType={'reset'}>重置</Button>
          </Form.Item>
        </Form>
      </>
    )
  }
})
