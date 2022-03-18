import { defineComponent, onBeforeMount, reactive, ref } from 'vue'
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

const data = reactive<StudentModel[]>([])
const loading = ref(false)

const useState = () => {
  async function loadStudents() {
    loading.value = true
    const { data: res } = await StudentAPI.list({
      page: {
        current: pagination.current - 1,
        pageSize: pagination.pageSize
      },
      dormBuildName: null,
      studentName: null
    }).finally(() => loading.value = false)
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
    loadStudents
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
      key: 'dormBuildId',
      dataIndex: 'dormBuildId',
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
        console.log(data.at(index))

        // todo
      }
      const addClick = () => {

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
    const { pagination, loadStudents } = useState()
    const { columns } = useColumns()
    const role = store.$state.currentRole
    const addModalVisible = ref<boolean>(false)
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
          <RecordAdd />
        </Modal>
        <div class={classes.operate_bar}>
          {role === RoleEnum.ADMIN ?
            <Button
              class={classes.add_btn}
              type={'primary'}
              onClick={() => addModalVisible.value = true}>
              添加
            </Button>
            :
            <div />
          }
          <div class={classes.search_bar}>
            <Select style={{ width: '150px' }}>
              <Select.Option value={1}>学号</Select.Option>
              <Select.Option value={2}>宿舍楼栋</Select.Option>
              <Select.Option value={3}>寝室号</Select.Option>
              <Select.Option value={4}>性别</Select.Option>
            </Select>
            <Input />
            <Button type={'primary'}>查询</Button>
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
