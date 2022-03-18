import { defineComponent, onBeforeMount, reactive, ref } from 'vue'
import { ColumnsType, ColumnType } from 'ant-design-vue/es/table'
import { Button, Input, message, Modal, Select, Table } from 'ant-design-vue'
import classes from './style/index.module.less'
import { TablePaginationConfig } from 'ant-design-vue/es'
import { RecordModel } from '@/api/model/record'
import RecordAPI from '@/api/record'
import useUserStore from '@/store/userStore'
import { RoleEnum } from '@/@types'
import table_style from '../style/table.module.less'
import RecordAdd from '@/views/record/RecordAdd'

const records = ref<RecordModel[]>([])
const loading = ref(false)

const useState = () => {
  async function loadRecords() {
    loading.value = true
    const { data: res } = await RecordAPI.list({
      page: {
        current: pagination.current - 1,
        pageSize: pagination.pageSize
      },
      dormBuildName: null
    }).finally(() => loading.value = false)
    if (res.code !== 200) {
      return message.error('加载失败')
    }
    records.value = res.data.content
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
      console.log(records)
      await loadRecords()
    }
  })
  return {
    pagination,
    loadRecords
  }
}
const useColumns = () => {
  const columns: ColumnsType<RecordModel> = [
    {
      title: 'Id',
      key: 'recordId',
      dataIndex: 'recordId',
      align: 'center'
    },
    {
      title: '学号',
      key: 'studentNumber',
      dataIndex: 'studentNumber',
      align: 'center'
    },
    {
      title: '日期',
      key: 'date',
      dataIndex: 'date',
      align: 'center'
    },

    {
      title: '宿舍号',
      key: 'dormName',
      dataIndex: 'dormName',
      align: 'center'
    },
    {
      title: '宿舍',
      key: 'dormBuildName',
      dataIndex: 'dormBuildName',
      align: 'center'
    },
    {
      title: '学生姓名',
      key: 'studentName',
      dataIndex: 'studentName',
      align: 'center'
    }, {
      title: '学号',
      key: 'studentNumber',
      dataIndex: 'studentNumber',
      align: 'center'
    },
    {
      title: '备注',
      key: 'detail',
      dataIndex: 'detail',
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
        console.log(records.value)
        // todo
      }
      const deleteClick = () => {
        console.log(records.value.at(index))

        // todo
      }
      return (
        <>
          {
            role === 1 ?
              <Button
                size={'small'}
                style={{ 'margin': '10px' }}
                type={'primary'}
                onClick={changeClick}>
                修改
              </Button> : null
          }
          {
            role >= 1 ?
              <Button
                size={'small'}
                type={'primary'}
                danger
                onClick={deleteClick}>
                删除
              </Button> : null
          }
        </>
      )
    }
  }
}
export default defineComponent({
  name: 'RecordTableView',
  setup() {
    const store = useUserStore()
    const role = store.currentRole
    const { pagination, loadRecords } = useState()
    const { columns } = useColumns()
    const addModalVisible = ref<boolean>(false)
    if (role >= 1) {
      columns.push(operate(role))
    }
    onBeforeMount(async () => {
      await loadRecords()
    })
    const customRow = () => {
      return {
        class: table_style.record_table
      }
    }
    return () => (
      <>
        <Modal
          width={400}
          centered
          closable={true}
          footer={null}
          v-model:visible={addModalVisible.value}>
          <RecordAdd />
        </Modal>
        <div class={classes.operate_bar}>
          {
            role === RoleEnum.DORM_MANAGER ?
              <Button class={classes.add_btn} type={'primary'}
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
          dataSource={records.value}
          columns={columns}
          pagination={pagination}>
        </Table>
      </>
    )
  }
})
