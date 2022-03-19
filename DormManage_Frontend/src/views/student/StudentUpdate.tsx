import { computed, defineComponent, onBeforeMount, PropType, reactive, ref } from 'vue'
import { StudentModel } from '@/api/model/user'
import { Button, Form, FormInstance, Input, message, Radio, Select } from 'ant-design-vue'
import useBuildStore from '@/store/dormBuildStore'
import StudentAPI from '@/api/student'

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
  name: 'StudentUpdate',
  props: {
    student: {
      type: Object as PropType<StudentModel>,
      required: true
    }
  },
  emits: ['updateSuccess'],
  setup(props, { emit }) {
    const formRef = ref<FormInstance>()
    const updateStudent = computed(() => props.student).value as StudentModel
    const updateModel = reactive<StudentModel>({
      dormBuildId: updateStudent.dormBuildId,
      dormName: updateStudent.dormName,
      dormBuildName: updateStudent.dormBuildName,
      name: updateStudent.name,
      sex: updateStudent.sex,
      tel: updateStudent.tel,
      stuNum: updateStudent.stuNum,
      studentId: updateStudent.studentId
    })
    const optionsLoading = ref<boolean>(false)
    const dormBuildStore = useBuildStore()
    const dormBuildList = computed(() => dormBuildStore.$state.dormBuilds)
    const addSubmit = async () => {
      const { data: res } = await StudentAPI.update(updateModel)
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
          <Form.Item hasFeedback label={'Id'} name={'studentId'}>
            <Input disabled value={updateModel.studentId} />
          </Form.Item>
          <Form.Item hasFeedback label={'名字'} name={'name'}>
            <Input disabled value={updateModel.name} />
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
          <Form.Item hasFeedback label={'宿舍'} name={'dormName'}>
            <Input v-model:value={updateModel.dormName} />
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
