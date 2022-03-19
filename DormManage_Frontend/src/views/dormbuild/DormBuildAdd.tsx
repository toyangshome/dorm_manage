import { defineComponent, reactive, ref } from 'vue'
import { Button, Form, FormInstance, Input, message } from 'ant-design-vue'

import { DormManagerAPI } from '@/api/dormManager'
import { DormBuildModel } from '@/api/model/dormBuild'
import DormBuildAPI from '@/api/dormBuild'

const rules = {
  dormBuildName: [
    {
      required: true,
      trigger: 'blur',
      message: '不能为空'
    },
    {
      max: 10,
      min: 2,
      message: '格式错误',
      trigger: 'blur'
    }
  ]
}
export default defineComponent({
  name: 'DormBuildAdd',
  emits: ['addSuccess'],
  setup(props, { emit }) {
    const formRef = ref<FormInstance>()
    const addModel = reactive<DormBuildModel>({
      dormBuildName: '',
      detail: ''
    })
    const addSubmit = async () => {
      const { data: res } = await DormBuildAPI.add(addModel)
      if (res.code !== 200) {
        return message.error(res.message)
      }
      emit('addSuccess', res.data)
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
          <Form.Item hasFeedback label={'楼栋'} name={'dormBuildName'}>
            <Input v-model:value={addModel.dormBuildName} />
          </Form.Item>
          <Form.Item label={'详细'} name={'detail'}>
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
