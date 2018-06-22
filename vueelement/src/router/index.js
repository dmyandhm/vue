import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'

Vue.use(Router)

export const constantRouterMap = [
  {path: '/',component: () => import('@/components/HelloWorld'), hidden: true},
  { path: '/login', component: () => import('@/components/Login'), hidden: true },
]

export default new Router({
  routes: constantRouterMap,
})
