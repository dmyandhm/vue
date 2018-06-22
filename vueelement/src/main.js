// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from './store';


Vue.config.productionTip = false
Vue.prototype.$ajax = axios  //能直接在组件的 methods 中使用 $ajax 命令
Vue.use(ElementUI)

// eslint-disable no-new
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>',
  render: h => h(App)
})
