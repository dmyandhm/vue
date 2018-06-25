import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)

export const constantRouterMap = [
  {path: '/',  component: () => import('@/components/Home'), hidden: true},
  { path: '/login', component: () => import('@/components/Login'), hidden: true },
]

let router=new Router({
  routes: constantRouterMap,
})

/*登录拦截器*/
router.beforeEach((to, from, next) => {
  if (to.path.startsWith('/login')) {
    window.localStorage.removeItem('userInfo')
    next()
  } else {
    let user = JSON.parse(window.localStorage.getItem('userInfo'))
    if (!user) {
      next({path: '/login'})
    } else {
      next()
    }
  }
})

export default router
