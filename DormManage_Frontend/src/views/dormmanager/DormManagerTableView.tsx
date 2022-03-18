import { computed, defineComponent, onBeforeMount, reactive, ref } from 'vue'
import { ColumnsType } from 'ant-design-vue/lib/table'
import { Button, Input, message, Modal, Select, Table } from 'ant-design-vue'
import { DormManagerModel } from '@/api/model/user'
import { DormManagerAPI } from '@/api/dormManager'
import { TablePaginationConfig } from 'ant-design-vue/es'
import classes from './style/index.module.less'
import table_style from '@/views/style/table.module.less'
import DormBuildAdd from '@/views/dormbuild/DormBuildAdd'
import DormManagerAdd from '@/views/dormmanager/DormManagerAdd'
import useBuildStore from '@/store/dormBuildStore'

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
          DormManagerAPI
            .delete(dormManagers.value[index].dormManId)
            .then(({ data: res }) => {
              if (res.code === 200) {
                dormManagers.value.splice(index, 1)
                return message.success('删除成功')
              }
              return message.error('删除失败')
            })
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
    const reqParams = reactive({
      page: {
        current: computed(() => pagination.current - 1),
        pageSize: computed(() => pagination.pageSize)
      },
      name: null,
      dormBuildName: null
    })
    const buildStore = useBuildStore()
    const addModalVisible = ref<boolean>(false)
    const selectionLoading = ref<boolean>(false)
    const loading = ref(false)
    const getDormManagerList = async () => {
      loading.value = true
      const { data: res } = await DormManagerAPI
        .list(reqParams)
        .finally(() => loading.value = false)
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
        <Modal
          width={400}
          centered
          closable={true}
          footer={null}
          destroyOnClose={true}
          v-model:visible={addModalVisible.value}>
          <DormManagerAdd />
        </Modal>
        <div class={classes.operate_bar}>
          <Button
            class={classes.add_btn}
            type={'primary'}
            onClick={() => addModalVisible.value = true}>
            添加
          </Button>
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
            <Input placeholder={'姓名'} v-model:value={reqParams.name} />
            <Button type={'primary'} onClick={() => getDormManagerList()}>查询</Button>
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
