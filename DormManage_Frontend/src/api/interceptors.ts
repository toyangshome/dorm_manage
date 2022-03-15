/**
 * @description 拦截器模块
 */
import { AxiosRequestConfig, AxiosResponse } from 'axios'
import TokenUtils from '@/utils/token'
import ProjectConfig from '@/config'
import { HttpResponse, HttpStatusCode } from '@/@types'
import router  from '@/router'
// config
const isTest = false
// 根据全局配置文件抽离出来的条件
const testToken = ProjectConfig.token.testToken
const tokenName = ProjectConfig.token.tokenName
/**
 * @description 请求拦截器，处理 token 挂载等功能
 * request 拦截器
 * tips- 1:  如果要访问 response 的 headers，那么需要后端对 headers 的访问权限进行暴露,
 * 解决方法为: 后端在 response 的 header 中添加 Access-Control-Expose-Headers: [你需要暴露的 headers,用','分割开来]
 */
export const requestInterceptor = {
  onFulfilled: (request: AxiosRequestConfig): any => {
    if (isTest) {
      request.headers[tokenName] = testToken
      return request
    }
    const token = TokenUtils.getTokenFromLocal()
    const isExists = TokenUtils.isExist()
    // 如果 token 存在，那么 request 就设置认证头
    if (isExists) {
      request.headers[tokenName] = token
    }
    return request
  },
  onRejected: (e: any): any => {
    return Promise.reject(e)
  }
}
/**
 * @description 响应拦截器， 处理 token 持久化，以及响应错误处理逻辑
 */
export const responseInterceptor = {
  onFulfilled: (response: AxiosResponse<HttpResponse<any>>): any => {
    // 测试环境下会设置最高权限，永久token
    if (isTest) {
      // 设置 test token 到本地
      TokenUtils.setTokenToLocal(testToken)
    } else {
      // 从响应头中获取 token，login 以及 token 更新等后端操作都会返回 token
      // 因为我们定义 token 的响应头为 Authorization 但 Http 会将 headers 中的 key 变为小写，也就是 authorization
      const token = response.headers[tokenName.toLocaleLowerCase()]
      if (token !== undefined && token !== null) {
        TokenUtils.setTokenToLocal(token)
      }
      // 如果返回 Http 状态码为200就将 data 解包, 因为后端定义的返回体在 data 中
      // 所以直接返回 response.data
      if (response.status === HttpStatusCode.OK) {
        return response
      }
    }
    return response
  },
  onRejected: (e: any): any => {
    if (e.response) {
      console.warn('Error Code')
      errorCodeHandler(e.response.status)
    } else if (e.request) {
      errorNotResponseHandler(e)
    } else {
      errorRequestHandler(e)
    }
    return Promise.reject(e)
  }
}

/**
 * @description 对 2xx 以外的状态码的处理函数
 * @param statusCode
 */
const errorCodeHandler = (statusCode: number): void => {
  if (statusCode === HttpStatusCode.Unauthorized) {
    // todo 当未认证时，强制跳转到 login
    router.push({
      path: '/login'
    })
  } else if (statusCode === HttpStatusCode.NotFound) {
    router.push({
      path: '/404'
    })
  } else if (statusCode === HttpStatusCode.InternalServerError) {
    router.push({
      path: '/500'
    })
  }
  console.warn(HttpStatusCode[statusCode])
}
/**
 * @description 请求已经成功发起，但没有收到响应
 * @description `error.request` 在浏览器中是 XMLHttpRequest 的实例，
 * @param e 错误信息
 */
const errorNotResponseHandler = (e: any): void => {
  console.error(e.message)
}
/**
 *
 * @description 发送请求时出了点问题
 * @param e 错误信息
 */
const errorRequestHandler = (e: any): void => {
  console.error(e.message)
}
