import { defineComponent, onBeforeMount, reactive, ref } from 'vue'
import { ColumnsType } from 'ant-design-vue/lib/table'
import { Button, Input, message, Select, Table } from 'ant-design-vue'
import { DormManagerModel } from '@/api/model/user'
import { DormManagerAPI } from '@/api/dormManager'
import { TablePaginationConfig } from 'ant-design-vue/es'
import classes from './style/index.module.less'
import table_style from '@/views/style/table.module.less'

const dormManagers = ref<DormManagerModel[]>([])


const useColumns = () => {
  const columns: ColumnsType<DormManagerModel> = [
    {
      title: '编号',
      key: 'id',
      width: 50,
      dataIndex: 'dormManId',
      align: 'center'
    },
    {
      title: '用户名',
      key: 'userName',
      width: 50,
      dataIndex: 'userName',
      align: 'center'
    },
    {
      title: '宿舍楼',
      key: 'dormBuildName',
      width: 50,
      dataIndex: 'dormBuildName',
      align: 'center'
    },
    {
      title: '姓名',
      key: 'name',
      width: 50,
      dataIndex: 'name',
      align: 'center'
    },
    {
      title: '性别',
      key: 'sex',
      width: 50,
      dataIndex: 'sex',
      align: 'center'
    },
    {
      title: '电话号码',
      key: 'tel',
      width: 50,
      dataIndex: 'tel',
      align: 'center'
    },
    {
      title: '操作',
      key: 'id',
      width: 100,
      align: 'center',
      customRender: ({ index }) => {
        const changeClick = () => {
          console.log(dormManagers.value[index])
        }
        const deleteClick = () => {
          console.log(dormManagers.value[index])
          // todo
        }
        return (
          <>
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
  id: 'TableView',
  setup() {
    const { columns } = useColumns()
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
        await getDormManagerList()
      }
    })
    const loading = ref(false)
    const getDormManagerList = async () => {
      loading.value = true
      const { data: res } = await DormManagerAPI.list({
        page: {
          current: pagination.current - 1,
          pageSize: pagination.pageSize
        },
        name: null
      }).finally(() => loading.value = false)
      if (res.code != 200) {
        return message.error('加载数据失败!')
      }
      dormManagers.value = res.data.content
      pagination.current = res.data.number + 1
      pagination.pageSize = res.data.size
      pagination.total = res.data.totalElements
    }
    onBeforeMount(async () => {
      await getDormManagerList()
    })
    const customRow = () => {
      return {
        class: table_style.record_table
      }
    }
    return () =>
      <div>
        <div class={classes.operate_bar}>
          <Button class={classes.add_btn} type={'primary'}>添加</Button>
          <div class={classes.search_bar}>
            <Select style={{ width: '150px' }}>
              <Select.Option value={1}>1栋</Select.Option>
              <Select.Option value={2}>2栋</Select.Option>
              <Select.Option value={3}>3栋</Select.Option>
            </Select>
            <Input />
            <Button type={'primary'}>查询</Button>
          </div>
        </div>
        <Table
          customRow={customRow}
          pagination={pagination}
          size={'small'}
          columns={columns}
          dataSource={dormManagers.value}
          bordered={true}
          loading={loading.value}
        >
        </Table>
      </div>
  }
})
