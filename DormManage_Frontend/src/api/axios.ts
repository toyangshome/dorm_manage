import axios from 'axios'
import { requestInterceptor, responseInterceptor } from '@/api/interceptors'

const baseUrl = import.meta.env.VITE_API_URL

export const webService = axios.create({
  baseURL: baseUrl,
  timeout: 10000,
  timeoutErrorMessage: '请求超时',
  responseType: 'json'
})
/**
 * request 拦截器
 * tips- 1:  如果要访问 response 的 headers，那么需要后端对 headers 的访问权限进行暴露,
 * 解决方法为: 后端在 response 的 header 中添加 Access-Control-Expose-Headers: [你需要暴露的 headers,用','分割开来]
 */
webService.interceptors.request.use(
  requestInterceptor.onFulfilled,
  requestInterceptor.onRejected
)

/**
 * response 拦截器
 */
webService.interceptors.response.use(
  responseInterceptor.onFulfilled,
  responseInterceptor.onRejected
)
