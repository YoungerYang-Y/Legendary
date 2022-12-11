interface ImportMetaEnv {
  readonly VITE_APP_TITLE: string;
  readonly VITE_REQUEST_BASE_URL: string;
  // 更多环境变量...
}

interface ImportMeta {
  readonly env: ImportMetaEnv;
}

// 定义 .vue文件 的类型
declare module "*.vue" {
  import type { DefineComponent } from "vue";
  const vueComponent: DefineComponent<unknown, unknown, unknown>;
  export default vueComponent;
}
