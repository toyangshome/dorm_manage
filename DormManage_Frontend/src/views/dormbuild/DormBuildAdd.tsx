import { defineComponent } from 'vue'
import { Form, Input } from 'ant-design-vue'
import style from './style/index.module.less'

export default defineComponent({
  name: 'DormBuildAdd',
  setup() {
    return () => (
      <>
        <Form labelAlign={'right'} labelCol={{ 'span': 6 }} wrapperCol={{ 'span': 14 }}>
          <Form.Item label={'用户名'}>
            <Input></Input>
          </Form.Item>
          <Form.Item label={'宿舍楼'}>
            <Input></Input>
          </Form.Item>
          <Form.Item label={'姓名'}>
            <Input></Input>
          </Form.Item>
          <Form.Item label={'性别'}>
            <Input></Input>
          </Form.Item>
          <Form.Item label={'电话号码'}>
            <Input></Input>
          </Form.Item>
        </Form>
      </>
    )
  }
})