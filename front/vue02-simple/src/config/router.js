import App from '../app'
import store from '../store'
import Vue from 'vue'
import VueRouter from 'vue-router'
/**
 * auth true登录才能访问，false不需要登录，默认true
 */
const routes = [
    {
        path: '/',
        component: App,
        children: [
            {
                name: 'login',
                path: 'login', //登录
                meta: { auth: false },
                component: resolve => require(['../pages/login.vue'], resolve)//懒加载
            },
            {
                name: 'signout',
                path: 'signout', //退出
                component: resolve => require(['../pages/signout.vue'], resolve)
            },
            {
                name: 'home',
                path: 'home', //个人主页
                component: resolve => require(['../pages/home.vue'], resolve),
            },
            {
                name: 'register',
                path: 'register', //注册页面
                component: resolve => require(['../pages/register.vue'], resolve)
            },
            {
                name: 'index',
                path: '', //首页
                /* meta: { auth: false }, */
                component: resolve => require(['../pages/index.vue'], resolve)
            },
            {
                name: 'blogHome',
                path: 'articles',
                component: resolve => require(['../pages/blog/blogHome.vue'], resolve),
                children: [
                    {
                        path: '',
                        component: resolve => require(['../pages/blog/articles.vue'], resolve),
                    },
                    {
                        name: 'articleDetail',
                        path: 'details/:id',
                        component: resolve => require(['../pages/blog/articleDetail.vue'], resolve)
                    }
                ]
            },
            {
                name: "newArticle",
                path: 'mdeditor',
                component: resolve => require(['../pages/blog/mdEditor.vue'], resolve)
            },
            {
                name: "editArticle",
                path: 'mdeditor/:id',
                component: resolve => require(['../pages/blog/mdEditor.vue'], resolve)
            },
            {
                path: '*', //其他页面，强制跳转到登录页面
                redirect: '/login'
            }
        ]
    }
]

Vue.use(VueRouter)

const router = new VueRouter({
  routes
})

router.beforeEach(({meta, path}, from, next) => {
    console.info('```````beforeEach````````````')
    console.info(store.state)
    let {auth = true} = meta;
    let isLogin = Boolean(store.state.user.userid);
    console.info(`islogin:${isLogin}`)
    if(auth && !isLogin && path !== '/login'){
      return next({path: '/login'});
    }
    next();
  })

export default router;