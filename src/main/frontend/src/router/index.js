import { createRouter, createWebHistory } from 'vue-router'
import OjtMain from "../components/OjtMain.vue"
import MyInfo from "../components/myInfo/MyInfo.vue"
import MyInfoChange from "../components/myInfo/MyInfoChange.vue"
import PwChange from "../components/myInfo/PwChange.vue"
import MemberManagement from "../components/management/MemberManagement.vue"
import MemberChange from "../components/management/MemberChange.vue"
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
  //로그인한 유저의 직책에 따라 나뉨
  {
    path: '/myinfo/:name',
    name: 'myInfo',
    component: MyInfo
  },
  {
    path: '/management/member/:position',
    name: 'memberManagement',
    component: MemberManagement
  },
  //마이인포 사이드바
  {
    path: '/myinfo/change/:name',
    name: 'myInfoChange',
    component: MyInfoChange
  },
  {
    path: '/myinfo/pwchange/:name',
    name: 'pwChange',
    component: PwChange
  },
  //회원 관리
  {
    path: '/management/product/:position',
    name: 'productManagement',
    component: ProductManagement
  },
  {
    path: '/management/change/:position',
    name: 'memberChange',
    component: MemberChange
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
