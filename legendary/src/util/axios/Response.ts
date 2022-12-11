import type { AxiosResponse } from "axios";

// 自定义请求返回数据的类型
export default interface Response<T> extends AxiosResponse {
  code: number;
  message: string;
  data: T;
}
