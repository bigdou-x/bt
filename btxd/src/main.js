// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from "vue";
import App from "./App";
import router from "./router";
import store from "./store";
import { initMenu } from "./utils/common";
import ElementUI from "element-ui";
import { Message } from "element-ui";
import { postRequest } from "./utils/common";
import { getRequest } from "./utils/common";
import { deleteRequest } from "./utils/common";
import { putRequest } from "./utils/common";
import { uploadFileRequest } from "./utils/common";

import "element-ui/lib/theme-chalk/index.css";
import axios from "axios";
import VueAxios from "vue-axios";
import upperFirst from "lodash/upperFirst";
import camelCase from "lodash/camelCase";

Vue.config.productionTip = false;
Vue.use(ElementUI);
//axios.defaults.baseURL="http://127.0.0.1:8080";
Vue.prototype.postRequest = postRequest;
Vue.prototype.getRequest = getRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.uploadFileRequest = uploadFileRequest;

Vue.prototype.$axios = axios;

Vue.use(VueAxios, axios);

const requireComponent = require.context(
  // 其组件目录的相对路径
  "./components",
  // 是否查询其子目录
  true,
  // 匹配基础组件文件名的正则表达式
  /[A-Z]\w+\.(vue|js)$/
);
requireComponent.keys().forEach(fileName => {
  // 获取组件配置
  const componentConfig = requireComponent(fileName);

  // 获取组件的 PascalCase 命名
  const componentName = upperFirst(
    camelCase(
      // 获取和目录深度无关的文件名
      fileName
        .split("/")
        .pop()
        .replace(/\.\w+$/, "")
    )
  );

  // 全局注册组件
  Vue.component(
    componentName,
    // 如果这个组件选项是通过 `export default` 导出的，
    // 那么就会优先使用 `.default`，
    // 否则回退到使用模块的根。
    componentConfig.default || componentConfig
  );
});
axios.interceptors.response.use(
  data => {
    console.info(data);
    if (data.status == 200 && data.data.status == "error") {
      Message.error({ message: data.data.msg });
      return;
    } else if (data.data.msg) {
      Message.success({ message: data.data.msg });
    }
    return data;
  },
  err => {
    console.info(err);
    if (err.response.status == 504 || err.response.status == 404) {
      Message.error({ message: "服务器被吃了⊙﹏⊙∥" });
    } else if (err.response.status == 403) {
      Message.error({ message: "权限不足,请联系管理员!" });
    } else {
      Message.error({ message: "未知错误!" });
    }
    return Promise.resolve(err);
  }
);
var store1 = store;
router.beforeEach((to, from, next) => {
  if (to.path == "/") {
    next();
    return;
  }
  var email = window.localStorage.getItem("email");
  debugger;
  if (email == "null") {
    next({ path: "/", query: { redirect: to.path } });
  } else {
    initMenu(router, store1);
    next();
  }
});

/* eslint-disable no-new */
new Vue({
  el: "#app",
  router,
  store,
  components: { App },
  template: "<App/>"
});
