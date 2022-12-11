import axios, {
  type AxiosInstance,
  type AxiosResponse,
  type AxiosRequestConfig,
} from "axios";
import type RequestConfig from "@/util/axios/RequestConfig";
import type Response from "@/util/axios/Response";

// 自定义拦截器
interface InterceptorHooks {
  requestInterceptor?: (config: AxiosRequestConfig) => AxiosRequestConfig;
  requestInterceptorCatch?: (error: unknown) => unknown;
  responseInterceptor?: (response: AxiosResponse) => AxiosResponse;
  responseInterceptorCatch?: (error: unknown) => unknown;
}

// 定义请求
class Request {
  instance: AxiosInstance;
  config: RequestConfig = {
    baseURL: import.meta.env.VITE_REQUEST_BASE_URL,
    timeout: 60000,
  };
  interceptorHooks?: InterceptorHooks;
  loading?: boolean;

  constructor(options: RequestConfig) {
    options = Object.assign(this.config, options);
    this.instance = axios.create(options);
    this.setupInterceptor();
  }

  // 类型参数的作用，T决定AxiosResponse实例中data的类型
  request<T>(config: RequestConfig): Promise<Response<T>> {
    return this.instance.request(config);
  }

  get<T>(config: RequestConfig): Promise<Response<T>> {
    return this.request({ ...config, method: "GET" });
  }

  post<T>(config: RequestConfig): Promise<Response<T>> {
    return this.request({ ...config, method: "POST" });
  }

  delete<T>(config: RequestConfig): Promise<Response<T>> {
    return this.request({ ...config, method: "DELETE" });
  }

  patch<T>(config: RequestConfig): Promise<Response<T>> {
    return this.request({ ...config, method: "PATCH" });
  }

  // 设置拦截器
  setupInterceptor(): void {
    this.instance.interceptors.request.use(
      this.interceptorHooks?.requestInterceptor,
      this.interceptorHooks?.requestInterceptorCatch
    );
    this.instance.interceptors.response.use(
      this.interceptorHooks?.responseInterceptor,
      this.interceptorHooks?.responseInterceptorCatch
    );

    // 请求拦截
    this.instance.interceptors.request.use(
      (config) => {
        console.log("请求发送成功");
        // TODO：设置全局loading
        if (this.config.showLoading) {
          //
        }
        return config;
      },
      (err) => {
        console.log("请求发送失败");
        return err;
      }
    );

    // 响应拦截
    this.instance.interceptors.response.use(
      // 请求完毕，关闭loading
      (res) => {
        console.log("响应成功的拦截");
        // this.loading?.close();
        return res;
      },
      (err) => {
        // this.loading?.close();
        // 这里用来处理http常见错误，进行全局提示
        // let message = "";
        // switch (err.response.status) {
        //   case 400:
        //     message = "请求错误(400)";
        //     break;
        //   case 401:
        //     message = "未授权，请重新登录(401)";
        //     // 这里可以做清空storage并跳转到登录页的操作
        //     break;
        //   case 403:
        //     message = "拒绝访问(403)";
        //     break;
        //   case 404:
        //     message = "请求出错(404)";
        //     break;
        //   case 408:
        //     message = "请求超时(408)";
        //     break;
        //   case 500:
        //     message = "服务器错误(500)";
        //     break;
        //   case 501:
        //     message = "服务未实现(501)";
        //     break;
        //   case 502:
        //     message = "网络错误(502)";
        //     break;
        //   case 503:
        //     message = "服务不可用(503)";
        //     break;
        //   case 504:
        //     message = "网络超时(504)";
        //     break;
        //   case 505:
        //     message = "HTTP版本不受支持(505)";
        //     break;
        //   default:
        //     message = `连接出错(${err.response.status})!`;
        // }
        console.log("响应成功的拦截：" + err);
        return err;
      }
    );
  }
}

export default Request;
