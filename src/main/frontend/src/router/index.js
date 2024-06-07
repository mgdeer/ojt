import { createRouter, createWebHistory } from 'vue-router'
import OjtMain from "../components/OjtMain.vue"
import MyInfo from "../components/myInfo/MyInfo.vue"
import MemberManagement from "../components/management/MemberManagement.vue"
import OjtProduct from "../components/product/OjtProduct.vue"
import ProductManagement from "../components/management/ProductManagement.vue"

const routes = [
  //네비바
  {
    path: '/',
    name: 'ojtMain',
    component: OjtMain
  },
  {
    path: '/:name',
    name: 'ojtMainLogined',
    component: OjtMain
  },
  {
    path: '/product',
    name: 'ojtProduct',
    component: OjtProduct
  },
  {
    path: '/product/:name',
    name: 'ojtProductLogined',
    component: OjtProduct
  },
  //로그인한 유저의 직책에 따라 나뉨
  {
    path: '/myinfo/:name',
    name: 'myInfo',
    component: MyInfo
  },
  {
    path: '/management/member/:name',
    name: 'memberManagement',
    component: MemberManagement
  },
  //회원 관리
  {
    path: '/management/product/:name',
    name: 'productManagement',
    component: ProductManagement
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
