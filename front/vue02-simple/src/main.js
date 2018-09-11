import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'

import routes from './config/routes'
import store from './store/'
import components from './components/'

//自定义组件
Object.keys(components).forEach(key => {
  var name = key.replace(/(\w)/, (v) => v.toUpperCase()) //首字母大写
  Vue.component(`v${name}`, components[key]);
})

Vue.use(VueRouter)

const router = new VueRouter({
  routes
})

router.beforeEach(({meta, path}, from, next) => {
  let {auth = true} = meta;
  let isLogin = Boolean(store.state.user.id);
  if(auth && !isLogin && path !== '/login'){
    return next({path: '/login'});
  }
  next();
})

new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App)
})
