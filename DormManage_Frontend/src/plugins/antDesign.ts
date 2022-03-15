import {
  Button,
  Card,
  Row,
  Col,
  Tag,
  Form,
  Input,
  ConfigProvider,
  Select,
  DatePicker,
  Dropdown,
  Menu,
  Divider,
  Badge,
  BackTop,
  Carousel,
  Affix,
  Layout,
  Typography,
  Empty,
  Table,
  Modal,
  Spin,
  Collapse,
  Alert,
  Skeleton,
  Pagination,
  List,
  Drawer,
  Avatar,
  Popover,
  Space,
  Switch,
  Progress,
  Result
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
  app.use(Tag)
  app.use(Form)
  app.use(Input)
  app.use(Dropdown)
  app.use(Menu)
  app.use(Divider)
  app.use(ConfigProvider)
  app.use(Select)
  app.use(DatePicker)
  app.use(BackTop)
  app.use(Badge)
  app.use(Carousel)
  app.use(Typography)
  app.use(Layout)
  app.use(Affix)
  app.use(Empty)
  app.use(Table)
  app.use(Modal)
  app.use(Spin)
  app.use(Collapse)
  app.use(Alert)
  app.use(Skeleton)
  app.use(Pagination)
  app.use(List)
  app.use(Drawer)
  app.use(Avatar)
  app.use(Popover)
  app.use(Space)
  app.use(Switch)
  app.use(Progress)
  app.use(Result)
}
export default loadComponent
