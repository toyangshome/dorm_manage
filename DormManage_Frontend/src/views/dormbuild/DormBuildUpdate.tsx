import { computed, defineComponent, PropType, reactive, ref } from 'vue'
import { Button, Form, FormInstance, Input, message } from 'ant-design-vue'
import { DormBuildModel } from '@/api/model/dormBuild'
import DormBuildAPI from '@/api/dormBuild'

export default defineComponent({
  name: 'DormBuildUpdate',
  props: {
    dormBuild: {
      type: Object as PropType<DormBuildModel>,
      required: true
    }
  },
  emits: ['updateSuccess'],
  setup(props, { emit }) {
    const formRef = ref<FormInstance>()
    const updateDormBuild = computed(() => props.dormBuild).value as DormBuildModel
    const addModel = reactive<DormBuildModel>({
      id: updateDormBuild.id,
      dormBuildName: updateDormBuild.dormBuildName,
      detail: updateDormBuild.detail
    })
    const addSubmit = async () => {
      const { data: res } = await DormBuildAPI.update(addModel)
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
          model={addModel}
          labelAlign={'right'}
          labelCol={{ 'span': 6 }}
          wrapperCol={{ 'span': 14 }}>
          <Form.Item required hasFeedback label={'Id'} name={'id'}>
            <Input disabled value={addModel.id} />
          </Form.Item>
          <Form.Item required hasFeedback label={'楼栋名称'} name={'dormBuildName'}>
            <Input v-model:value={addModel.dormBuildName} />
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
