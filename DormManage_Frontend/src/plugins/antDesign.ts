import {
  Button,
  Card,
  Row,
  Col,
  Form,
  Input,
  Select,
  Dropdown,
  Menu,
  Divider,
  Badge,
  Carousel,
  Affix,
  Layout,
  Empty,
  Table,
  Modal,
  Spin,
  Alert,
  Pagination,
  Drawer,
  Avatar,
  Popover,
  Space,
  Switch,
  Progress,
  Result, Radio, Breadcrumb
} from 'ant-design-vue'
import { createApp } from 'vue'

/**
 * @description 手动注册 antd-vue 组件,达到按需加载目的
 * @description Automatically register components under Button, such as Button.Group
 * @param {ReturnType<typeof createApp>} app 整个应用的实例
 * @returns void
 */
const loadComponent = (app: ReturnType<typeof createApp>): void => {
  app.use(Button)
  app.use(Card)
  app.use(Row)
  app.use(Col)
  app.use(Form)
  app.use(Input)
  app.use(Dropdown)
  app.use(Menu)
  app.use(Divider)
  app.use(Select)
  app.use(Badge)
  app.use(Carousel)
  app.use(Layout)
  app.use(Affix)
  app.use(Empty)
  app.use(Table)
  app.use(Modal)
  app.use(Spin)
  app.use(Alert)
  app.use(Pagination)
  app.use(Drawer)
  app.use(Avatar)
  app.use(Popover)
  app.use(Space)
  app.use(Switch)
  app.use(Progress)
  app.use(Result)
  app.use(Radio)
  app.use(Breadcrumb)
}
export default loadComponent
