import Vue from 'vue'
import VueRouter from 'vue-router'

// import constants from '../lib/constants.js'
// home
import Main from '@/views/main/Main.vue'
import BlogCreate from '@/views/main/BlogCreate.vue'

// post
import List from '../views/template1/post/List.vue'
import Tag from '../views/template1/board/Tag.vue'
import Read from '../views/post/Read.vue'
import Create from '../views/post/Create.vue'
import Update from '../views/post/Update.vue'
import Delete from '../views/post/Delete.vue'
import Create3 from '../views/template3/post/Create3.vue'
// user
import Join from '@/views/user/Join.vue'
import Login from '@/views/user/Login.vue'
import MyPage from '@/views/user/MyPage.vue'
import VisitPage from '@/views/template1/board/VisitPage.vue'
import VisitCreate from '@/views/template1/board/VisitCreate.vue'
import UpdateTmp from '@/views/template1/board/UpdateTmp.vue'

//err
import NotFoundPage from '@/views/template1/err/NotFoundPage.vue'

import store from '@/store'

// hong place(template3)
// import Home from '@/views/template3/home/Home.vue'
import List3 from '@/views/template3/post/List3.vue'
import VisitPage3 from '@/views/template3/board/VisitPage3.vue'
import VisitCreate3 from '@/views/template3/board/VisitCreate3.vue'
import Read3 from '@/views/template3/post/Read3.vue'
import Delete3 from '@/views/template3/post/Delete3.vue'
import UpdateTmp3 from '@/views/template3/board/UpdateTmp3.vue'
// +재완
// lsit2
import List2 from '@/views/template2/post/List2.vue'
import Create2 from '../views/template2/post/Create2.vue'
import VisitCreate2 from '@/views/template2/board/VisitCreate2.vue'
import Read2 from '@/views/template2/post/Read2.vue'
import Delete2 from '@/views/template2/post/Delete2.vue'
import UpdateTmp2 from '@/views/template2/board/UpdateTmp2.vue'

Vue.use(VueRouter)


const routes = [
  // home
  {
    path:'/',
    name:'Main',
    component:Main
  },
  {
    path:'/blogcreate',
    name:'BlogCreate',
    component:BlogCreate
  },
  // user
  {
    path: '/user/join',
    name: 'Join',
    component: Join
  },
  {
    path: '/user/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/user/mypage',
    name: 'MyPage',
    component: MyPage
  },
  // temp1
  {
    path: '/1/:bid/visitpage',
    name: 'VisitPage',
    component: VisitPage
  },
  {
    path: '/1/:bid/visitcreate',
    name: 'VisitCreate',
    component: VisitCreate
  },
  // temp1/post
  {
    path: '/1/:bid',
    name: 'List',
    component: List
  },
  {
    path: '/1/:bid/tag',
    name: 'Tag',
    component: Tag
  },
  {
    path: '/1/:bid/create',
    name: 'Create',
    component: Create,
  },
  {
    path: '/read',
    name: 'Read',
    component: Read
  },
  {
    path: '/update',
    name: 'Update',
    component: Update,
  },
  {
    path: '/delete',
    name: 'Delete',
    component: Delete,
  },
  {
    path: '/1/:bid/updatetmp',
    name: 'UpdateTmp',
    component: UpdateTmp,
  },
  {
    path: '/oauth2/redirect',
    name: 'SNSLogin',
    redirect: (to) => {
      console.log(to.query.token);
      store.commit('setCookie', to.query.token);

      return {
        path: '/', query: null
      }
    }
  },
  // err
  {
    path: '*',
    name: 'NotFoundPage',
    component: NotFoundPage
  },
  // temp2
  {
    path:'/2/:bid',
    name:'List2',
    component:List2
  },
  {
    path: '/2/:bid/create',
    name: 'Create2',
    component: Create2,
  },
  {
    path: '/2/:bid/updatetmp',
    name: 'UpdateTmp2',
    component: UpdateTmp2,
  },
  {
    path: '/read2',
    name: 'Read2',
    component: Read2
  },
  {
    path: '/delete2',
    name: 'Delete2',
    component: Delete2,
  },
  {
    path: '/2/:bid/visitcreate',
    name: 'VisitCreate2',
    component: VisitCreate2
  },
  // temp3
  // {
  //   path:'/3/:bid/home',
  //   name:'Home',
  //   component:Home
  // },
  // temp3/post
  {
    path: '/3/:bid/create',
    name: 'Create3',
    component: Create3,
  },
  {
    path: '/read3',
    name: 'Read3',
    component: Read3
  },
  {
    path: '/delete3',
    name: 'Delete3',
    component: Delete3,
  },
  {
    path: '/3/:bid/',
    name: 'List3',
    component: List3,
  },
  {
    path: '/3/:bid/visitpage',
    name: 'VisitPage3',
    component: VisitPage3
  },
  {
    path: '/3/:bid/visitcreate',
    name: 'VisitCreate3',
    component: VisitCreate3
  },
  {
    path: '/3/:bid/updatetmp',
    name: 'UpdateTmp3',
    component: UpdateTmp3,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  const publicPages = ['Login', 'Join', 'List', 'Main']
  const authPages = ['Login', 'Join']

  const authRequired = !publicPages.includes(to.name)
  const unauthRequired = authPages.includes(to.name)
  const isLogin = !!Vue.$cookies.isKey('auth-token')

  if(unauthRequired && isLogin) {
    next('/') 
  }
  authRequired && !isLogin ? next({ name: 'Login' }) : next()
})

export default router
