import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
// import { index } from './store'

Vue.config.productionTip = false
Vue.prototype.$http = axios;

new Vue({
  router,
  // index,
  store,
  render: h => h(App)
}).$mount('#app')
