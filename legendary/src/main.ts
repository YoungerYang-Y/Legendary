import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";

import "./assets/css/main.css";
import "./assets/css/base.css";

// 完整引入ant-design-vue
import Antd from "ant-design-vue";
import "ant-design-vue/dist/antd.less";

const app = createApp(App);

app.use(router).use(Antd);

app.mount("#app");
