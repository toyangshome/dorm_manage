import { computed, defineComponent, onBeforeMount, reactive, ref } from 'vue'
import { StudentModel } from '@/api/model/user'
import { ColumnsType, ColumnType } from 'ant-design-vue/es/table'
import { Button, Input, message, Modal, Select, Table } from 'ant-design-vue'
import { TablePaginationConfig } from 'ant-design-vue/es'
import classes from './style/index.module.less'
import StudentAPI from '@/api/student'
import table_style from '@/views/style/table.module.less'
import { RoleEnum } from '@/@types'
import userStore from '@/store/userStore'
import RecordAdd from '@/views/record/RecordAdd'
import useBuildStore from '@/store/dormBuildStore'
import StudentAdd from '@/views/student/StudentAdd'

const data = reactive<StudentModel[]>([])
const loading = ref(false)
const recordAddModalVisible = ref<boolean>(false)

const useState = () => {
  const reqParams = reactive({
    page: {
      current: computed(() => pagination.current - 1),
      pageSize: computed(() => pagination.pageSize)
    },
    dormBuildName: null,
    studentName: null,
    dormName: null
  })

  async function loadStudents() {
    loading.value = true
    const { data: res } = await StudentAPI.list(reqParams).finally(() => loading.value = false)
    if (res.code !== 200) {
      return message.error('加载失败')
    }
    data.splice(0, data.length, ...res.data.content)
    pagination.current = res.data.number + 1
    pagination.pageSize = res.data.size
    pagination.total = res.data.totalElements
  }

  const pagination = reactive<TablePaginationConfig>({
    current: 1,
    pageSize: 5,
    total: 0,
    showTotal: total => `共 ${total} 条数据`,
    showSizeChange: (current, pageSize) => {
      pagination.pageSize = pageSize
    },
    showSizeChanger: true,
    pageSizeOptions: ['5', '10', '15', '20'],
    onChange: async (current, pageSize) => {
      pagination.current = current
      pagination.pageSize = pageSize
      console.log(data)
      await loadStudents()
    }
  })
  return {
    pagination,
    loadStudents,
    reqParams
  }
}
const useColumns = () => {
  const columns: ColumnsType<StudentModel> = [
    {
      title: '学号',
      key: 'stuNum',
      dataIndex: 'stuNum',
      align: 'center'
    },
    {
      title: '姓名',
      key: 'name',
      dataIndex: 'name',
      align: 'center'
    },
    {
      title: '宿舍楼栋',
      key: 'dormBuildName',
      dataIndex: 'dormBuildName',
      align: 'center'
    },
    {
      title: '寝室号',
      key: 'dormName',
      dataIndex: 'dormName',
      align: 'center'
    },
    {
      title: '性别',
      key: 'sex',
      dataIndex: 'sex',
      align: 'center'
    },
    {
      title: '电话号码',
      key: 'tel',
      dataIndex: 'tel',
      align: 'center'
    }
  ]
  return {
    columns
  }
}
const operate = (role: number): ColumnType => {
  return {
    title: '操作',
    key: 'id',
    width: 200,
    align: 'center',
    customRender: ({ index }) => {
      const changeClick = () => {
        console.log(data)
        // todo
      }
      const deleteClick = () => {
        StudentAPI.delete(data[index].studentId)
          .then(({ data: res }) => {
            if (res.code === 200) {
              data.splice(index, 1)
              return message.success('删除成功')
            }
            return message.error('删除失败')
          })
      }
      const addClick = () => {
        recordAddModalVisible.value = true
      }
      let opt
      if (role === RoleEnum.ADMIN) {
        opt = [<Button
          size={'small'}
          style={{ 'margin': '10px' }}
          type={'primary'}
          onClick={changeClick}>
          修改
        </Button>,
          <Button
            size={'small'}
            type={'primary'}
            danger
            onClick={deleteClick}>
            删除
          </Button>]
      } else if (role === RoleEnum.DORM_MANAGER) {
        opt = <Button
          size={'small'}
          type={'primary'}
          onClick={addClick}>
          添加缺勤记录
        </Button>
      } else {
        opt = null
      }
      return (
        <>
          {opt}
        </>
      )
    }
  }
}
export default defineComponent({
  name: 'StudentTableView',
  setup() {
    const store = userStore()
    const { pagination, loadStudents, reqParams } = useState()
    const { columns } = useColumns()
    const role = store.$state.currentRole
    const addModalVisible = ref<boolean>(false)
    const selectionLoading = ref<boolean>(false)
    const buildStore = useBuildStore()
    onBeforeMount(async () => {
      await loadStudents()
    })
    if (role >= RoleEnum.DORM_MANAGER) {
      columns.push(operate(role))
    }
    const customRow = () => {
      return {
        class: table_style.record_table
      }
    }
    return () => (
      <>
        <Modal
          footer={null}
          width={400}
          centered
          closable={true}
          v-model:visible={addModalVisible.value}>
          <StudentAdd />
        </Modal>
        <Modal
          footer={null}
          width={400}
          centered
          closable={true}
          v-model:visible={recordAddModalVisible.value}>
          <RecordAdd />
        </Modal>
        <div class={classes.operate_bar}>
          {
            role === RoleEnum.ADMIN ?
              <Button type={'primary'} onClick={() => addModalVisible.value = true}>添加</Button>
              :
              <div />
          }
          <div class={classes.search_bar}>
            <Select
              placeholder={'宿舍'}
              style={{ 'width': '100px' }}
              onMousedown={() => {
                selectionLoading.value = true
                buildStore.loadDormBuilds().finally(() => selectionLoading.value = false)
              }}
              loading={selectionLoading.value}
              v-model:value={reqParams.dormBuildName}
            >
              {buildStore.getDormBuilds.map(dormBuild => {
                return <Select.Option value={dormBuild.dormBuildName}>{dormBuild.dormBuildName}</Select.Option>
              })}
            </Select>
            <Input type={'number'}
                   style={{ 'width': '120px' }}
                   placeholder={'宿舍号'}
                   v-model:value={reqParams.dormName} />
            <Input
              placeholder={'姓名'}
              style={{ 'width': '150px' }}
              v-model:value={reqParams.studentName} />
            <Button type={'primary'} onClick={() => loadStudents()}>查询</Button>
          </div>
        </div>
        <Table
          customRow={customRow}
          size={'small'}
          loading={loading.value}
          bordered={true}
          dataSource={data}
          columns={columns}
          pagination={pagination}>
        </Table>
      </>
    )
  }
})
