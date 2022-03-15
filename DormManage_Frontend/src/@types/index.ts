import { AxiosResponse } from 'axios'

/**
 * HTTP 响应码解释枚举类
 */
export enum HttpStatusCode {
  'Continue' = 100,
  'SwitchingProtocols' = 101,
  'OK' = 200,
  'Created' = 201,
  'Accepted' = 202,
  'NonAuthoritativeInformation' = 203,
  'NoContent' = 204,
  'ResetContent' = 205,
  'PartialContent' = 206,
  'MultipleChoices' = 300,
  'Ambiguous' = 300,
  'MovedPermanently' = 301,
  'Moved' = 301,
  'Found' = 302,
  'Redirect' = 302,
  'SeeOther' = 303,
  'RedirectMethod' = 303,
  'NotModified' = 304,
  'UseProxy' = 305,
  'Unused' = 306,
  'TemporaryRedirect' = 307,
  'RedirectKeepVerb' = 307,
  'BadRequest' = 400,
  'Unauthorized' = 401,
  'PaymentRequired' = 402,
  'Forbidden' = 403,
  'NotFound' = 404,
  'MethodNotAllowed' = 405,
  'NotAcceptable' = 406,
  'ProxyAuthenticationRequired' = 407,
  'RequestTimeout' = 408,
  'Conflict' = 409,
  'Gone' = 410,
  'LengthRequired' = 411,
  'PreconditionFailed' = 412,
  'RequestEntityTooLarge' = 413,
  'RequestUriTooLong' = 414,
  'UnsupportedMediaType' = 415,
  'RequestedRangeNotSatisfiable' = 416,
  'ExpectationFailed' = 417,
  'UpgradeRequired' = 426,
  'InternalServerError' = 500,
  'NotImplemented' = 501,
  'BadGateway' = 502,
  'ServiceUnavailable' = 503,
  'GatewayTimeout' = 504,
  'HttpVersionNotSupported' = 505
}

/**
 * 前后端接口数据的响应码
 */
export enum RESTCode {
}

/**
 * 前后端接口数据格式
 */
export interface DataType<T> {
  code: number;
  message: string;
  data: T;
  timestamp: string;
}

/**
 * Http Json 响应数据格式
 */
export interface HttpResponse<T> extends AxiosResponse {
  status: number;
  statusText: string;
  data: DataType<T>;
}

/**
 * 判题语言枚举
 */
export enum Language {
  'C' = 1,
  'C++' = 2,
  'Java' = 3,
  'Python' = 4
}

/**
 * 判题结果枚举
 */
export enum RESULT_STR {
  'Waiting' = -1,
  'Accepted' = 0,
  'Presentation Error' = 1,
  'Time Limit Exceeded' = 2,
  'Memory Limit Exceeded' = 3,
  'Wrong Answer' = 4,
  'Runtime Error' = 5,
  'Output Limit Exceeded' = 6,
  'Compile Error' = 7,
  'System Error' = 8,
  'Server Error' = 9
}

// 对于判题结果显示的色彩
export const ResultTagColors = ['#87d068', '#e2aa0a', '#f50', '#f50', '#f50', '#f50', '#f50', '#f50', '#f50', 'rgb(179 68 255)']
