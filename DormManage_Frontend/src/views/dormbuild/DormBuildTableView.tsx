import { defineComponent, onBeforeMount, reactive, ref } from 'vue'
import { DormBuildModel } from '@/api/model/dormBuild'
import { ColumnsType } from 'ant-design-vue/es/table'
import { Button, Input, message, Select, Table } from 'ant-design-vue'
import classes from './style/index.module.less'
import { TablePaginationConfig } from 'ant-design-vue/es'
import DormBuildAPI from '@/api/dormBuild'
import table_style from '@/views/style/table.module.less'

const dormBuilds = ref<DormBuildModel[]>([])
const loading = ref(false)

const useState = () => {
  async function loadDormBuilds() {
    loading.value = true
    const { data: res } = await DormBuildAPI.list({
      page: {
        current: pagination.current - 1,
        pageSize: pagination.pageSize
      },
      dormBuildName: null
    }).finally(() => loading.value = false)
    if (res.code !== 200) {
      return message.error('加载失败')
    }
    dormBuilds.value = res.data.content
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
      console.log(dormBuilds)
      await loadDormBuilds()
    }
  })
  return {
    pagination,
    loadDormBuilds
  }
}
const useColumns = () => {
  const columns: ColumnsType<DormBuildModel> = [
    {
      title: 'Id',
      key: 'id',
      dataIndex: 'id',
      align: 'center'
    },
    {
      title: '宿舍名称',
      key: 'dormBuildName',
      dataIndex: 'dormBuildName',
      align: 'center'
    },
    {
      title: '详细',
      key: 'detail',
      dataIndex: 'detail',
      align: 'center'
    },
    {
      title: '操作',
      key: 'id',
      width: 200,
      align: 'center',
      customRender: ({ index }) => {
        const changeClick = () => {
          console.log(dormBuilds.value)
          // todo
        }
        const deleteClick = () => {
          console.log(dormBuilds.value.at(index))

          // todo
        }
        const checkManager = () => {

        }
        return (
          <>
            <Button size={'small'} type={'primary'} onClick={checkManager}>管理员</Button>
            <Button size={'small'} style={{ 'margin': '10px' }} type={'primary'} onClick={changeClick}>修改</Button>
            <Button size={'small'} type={'primary'} danger onClick={deleteClick}>删除</Button>
          </>
        )
      }
    }
  ]
  return {
    columns
  }
}

export default defineComponent({
  name: 'DormBuildTableView',
  setup() {
    const { pagination, loadDormBuilds } = useState()
    const { columns } = useColumns()
    onBeforeMount(async () => {
      await loadDormBuilds()
    })
    const customRow = () => {
      return {
        class: table_style.record_table
      }
    }
    return () => (
      <>
        <div class={classes.operate_bar}>
          <Button class={classes.add_btn} type={'primary'}>添加</Button>
          <div class={classes.search_bar}>
            <Input placeholder={'宿舍名称'} />
            <Button type={'primary'}>查询</Button>
          </div>
        </div>
        <Table
          customRow={customRow}
          size={'small'}
          loading={loading.value}
          bordered={true}
          dataSource={dormBuilds.value}
          columns={columns}
          pagination={pagination}>
        </Table>
      </>
    )
  }
})
