import { computed, defineComponent, reactive, ref } from 'vue'
import { Button, Form, FormInstance, Input, message, Radio, Select } from 'ant-design-vue'
import useBuildStore from '@/store/dormBuildStore'
import StudentAPI from '@/api/student'
import { StudentAddParams } from '@/api/model/student'

const rules = {
  name: [
    {
      required: true,
      trigger: 'blur',
      message: '不能为空'
    },
    {
      max: 15,
      min: 3,
      message: '长度必须在 3-15',
      trigger: 'blur'
    },
    {
      whitespace: true,
      message: '不能含有空格'
    }
  ],
  userName: [
    {
      required: true,
      trigger: 'blur',
      message: '不能为空'
    },
    {
      max: 15,
      min: 3,
      message: '长度必须在 3-15',
      trigger: 'blur'
    },
    {
      whitespace: true,
      message: '不能含有空格'
    }
  ],
  password: [
    {
      required: true,
      trigger: 'blur',
      message: '不能为空'
    },
    {
      max: 15,
      min: 3,
      message: '长度必须在 3-15',
      trigger: 'blur'
    },
    {
      whitespace: true,
      message: '不能含有空格'
    }
  ],
  tel: [
    {
      trigger: 'blur',
      len: 11,
      message: '电话号码必须符合正确格式'
    }
  ]
}
export default defineComponent({
  name: 'StudentAdd',
  emits: ['addSuccess'],
  setup(props, { emit }) {
    const formRef = ref<FormInstance>()
    const addModel = reactive<StudentAddParams>({
      name: '',
      dormBuildId: null,
      password: '',
      sex: '男',
      tel: '',
      stuNum: ''
    })
    const optionsLoading = ref<boolean>(false)
    const dormBuildStore = useBuildStore()
    const dormBuildList = computed(() => dormBuildStore.$state.dormBuilds)
    const addSubmit = async () => {
      const { data: res } = await StudentAPI.add(addModel)
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
          <Form.Item hasFeedback label={'用户名'} name={'name'}>
            <Input v-model:value={addModel.name} />
          </Form.Item>
          <Form.Item hasFeedback label={'学号'} name={'stuNum'}>
            <Input v-model:value={addModel.stuNum} />
          </Form.Item>
          <Form.Item hasFeedback label={'密码'} name={'password'}>
            <Input type={'password'} v-model:value={addModel.password} />
          </Form.Item>
          <Form.Item hasFeedback label={'宿舍楼'} name={'dormBuildName'}>
            <Select
              onMousedown={() => {
                optionsLoading.value = true
                dormBuildStore.loadDormBuilds().finally(() => optionsLoading.value = false)
              }}
              loading={optionsLoading.value}
              v-model:value={addModel.dormBuildId}
            >
              {dormBuildList.value.map(dormBuild => {
                return <Select.Option value={dormBuild.dormBuildId}>{dormBuild.dormBuildName}</Select.Option>
              })}
            </Select>
          </Form.Item>
          <Form.Item hasFeedback label={'宿舍号'} name={'dormName'}>
            <Input v-model:value={addModel.dormName} />
          </Form.Item>
          <Form.Item label={'性别'} name={'sex'}>
            <Radio.Group v-model:value={addModel.sex}>
              <Radio value={'男'}>
                男
              </Radio>
              <Radio value={'女'}>
                女
              </Radio>
            </Radio.Group>
          </Form.Item>
          <Form.Item label={'电话号码'} name={'tel'}>
            <Input type={'tel'} v-model:value={addModel.tel} />
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
