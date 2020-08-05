import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
// import { index } from './store'

Vue.config.productionTip = false
Vue.prototype.$http = axios;
Vue.use(Vuetify);
new Vue({
  vuetify : new Vuetify(),
  router,
  // index,
  store,
  render: h => h(App)
}).$mount('#app')
