import Vue from 'vue'
import App from './App.vue'

import router from './config/router'
import store from './store/'
import components from './components/'

//自定义组件
Object.keys(components).forEach(key => {
  var name = key.replace(/(\w)/, (v) => v.toUpperCase()) //首字母大写
  Vue.component(`v${name}`, components[key]);
});



new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App)
})
