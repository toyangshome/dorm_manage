import { computed, defineComponent, onBeforeMount, PropType, reactive, ref } from 'vue'
import { Button, Form, FormInstance, Input, message, Radio, Select } from 'ant-design-vue'
import { DormManagerModel } from '@/api/model/user'
import { DormManagerAPI } from '@/api/dormManager'
import useBuildStore from '@/store/dormBuildStore'
const rules = {
  name: [
    {
      required: true,
      trigger: 'blur',
      message: '不能为空'
    },
    {
      max: 10,
      min: 3,
      message: '长度必须在 2-10',
      trigger: 'blur'
    },
    {
      whitespace: true,
      message: '不能含有空格'
    }
  ],
  tel: [
    {
      required: false
    },
    {
      len: 11,
      trigger: 'blur',
      message: '格式错误'
    }
  ]
}

export default defineComponent({
  name: 'DormManagerUpdate',
  props: {
    dormManager: {
      type: Object as PropType<DormManagerModel>,
      required: true
    }
  },
  emits: ['updateSuccess'],
  setup(props, { emit }) {
    const formRef = ref<FormInstance>()
    const updateDormManager = computed(() => props.dormManager).value as DormManagerModel
    const updateModel = reactive<DormManagerModel>({
      dormBuildId: updateDormManager.dormBuildId,
      dormBuildName: updateDormManager.dormBuildName,
      dormManId: updateDormManager.dormManId,
      name: updateDormManager.name,
      sex: updateDormManager.sex,
      tel: updateDormManager.tel,
      userName: updateDormManager.userName
    })
    const optionsLoading = ref<boolean>(false)
    const dormBuildStore = useBuildStore()
    const dormBuildList = computed(() => dormBuildStore.$state.dormBuilds)
    const addSubmit = async () => {
      const { data: res } = await DormManagerAPI.update(updateModel)
      if (res.code !== 200) {
        return message.error(res.message)
      }
      emit('updateSuccess', res.data)
      return message.success('添加成功')
    }
    onBeforeMount(() => {
      dormBuildStore.loadDormBuilds()
    })
    return () => (
      <>
        <Form
          ref={formRef}
          rules={rules}
          model={updateModel}
          labelAlign={'right'}
          labelCol={{ 'span': 6 }}
          wrapperCol={{ 'span': 14 }}>
          <Form.Item hasFeedback label={'Id'} name={'dormManId'}>
            <Input disabled value={updateModel.dormManId} />
          </Form.Item>
          <Form.Item hasFeedback label={'用户名'} name={'userName'}>
            <Input disabled value={updateModel.userName} />
          </Form.Item>
          <Form.Item required hasFeedback label={'宿舍楼'} name={'dormBuildId'}>
            <Select
              loading={optionsLoading.value}
              v-model:value={updateModel.dormBuildId}
            >
              {dormBuildList.value.map(dormBuild => {
                return <Select.Option value={dormBuild.dormBuildId}>{dormBuild.dormBuildName}</Select.Option>
              })}
            </Select>
          </Form.Item>
          <Form.Item hasFeedback label={'姓名'} name={'name'}>
            <Input v-model:value={updateModel.name} />
          </Form.Item>
          <Form.Item label={'性别'} name={'sex'}>
            <Radio.Group v-model:value={updateModel.sex}>
              <Radio value={'男'}>
                男
              </Radio>
              <Radio value={'女'}>
                女
              </Radio>
            </Radio.Group>
          </Form.Item>
          <Form.Item label={'电话号码'} name={'tel'}>
            <Input v-model:value={updateModel.tel} />
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
