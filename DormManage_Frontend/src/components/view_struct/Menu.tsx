import { defineComponent, ref } from 'vue'
import { Menu } from 'ant-design-vue'
import { useRouter } from 'vue-router'
import { getMenuRoutes } from '@/router'
import { MenuInfo } from 'ant-design-vue/es/menu/src/interface'
import { Icon } from '@/components/view_struct/Icon'

export default defineComponent({
  name: 'Menu',
  setup() {
    const router = useRouter()
    const menus = getMenuRoutes()
    const selectedKeys = ref([])
    const menuClick = (e: MenuInfo) => {
      router.push({
        path: e.key as string
      })
    }
    return () => (
      <>
        <Menu
          v-model:selectedKeys={selectedKeys.value}
          theme={'light'}
          mode={'inline'}
          onClick={menuClick}
        >
          {menus.map((item) => {
            return (
              <Menu.Item key={item.path}>
                <Icon icon={item.meta.icon} />
                <span>{item.meta.title}</span>
              </Menu.Item>)
          })}
        </Menu>
      </>
    )
  }
})
