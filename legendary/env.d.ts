interface ImportMetaEnv {
  readonly VITE_APP_TITLE: string;
  readonly VITE_REQUEST_BASE_URL: string;
  // 更多环境变量...
}

interface ImportMeta {
  readonly env: ImportMetaEnv;
}
