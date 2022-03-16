import { defineComponent, ref } from 'vue'
import { ColumnsType } from 'ant-design-vue/lib/table'
import { Button, Table } from 'ant-design-vue'
import { DormManagerModel } from '@/api/model/user'

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
      key: 'dormBuild',
      width: 50,
      dataIndex: 'dormBuild',
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
      customRender: ({ value, record }) => {
        const changeClick = () => {
          // todo
        }
        const deleteClick = () => {
          // todo
        }
        return (
          <>
            <Button style={{ 'margin': '10px' }} type={'primary'} onClick={changeClick}>修改</Button>
            <Button type={'primary'} danger onClick={deleteClick}>删除</Button>
          </>
        )
      }
    }
  ]

  return {
    columns
  }
}

const TableView = defineComponent({
  id: 'TableView',
  setup() {
    const { columns } = useColumns()
    const data = ref<DormManagerModel[]>([
      {
        dormManId: 1,
        dormBuild: '1栋',
        sex: '男',
        userName: 'newyang',
        name: '杨',
        tel: '124512452'
      }
    ])
    const loading = ref(false)
    return () => (
      <>
        <Button type={'primary'} style={{ marginBottom: '10px' }}>添加</Button>
        <Table
          size={'small'}
          columns={columns}
          dataSource={data.value}
          bordered={true}
          loading={loading.value}
        >
        </Table>
      </>
    )
  }
})
export default TableView
