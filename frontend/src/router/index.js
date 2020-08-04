import Vue from 'vue'
import VueRouter from 'vue-router'

// import constants from '../lib/constants.js'

// post
import List from '../views/post/List.vue'
import Read from '../views/post/Read.vue'
import Create from '../views/post/Create.vue'
import Update from '../views/post/Update.vue'
import Delete from '../views/post/Delete.vue'

// user
import Join from '@/views/user/Join.vue'
import Login from '@/views/user/Login.vue'
import MyPage from '@/views/user/MyPage.vue'
import VisitPage from '@/views/board/VisitPage.vue'
import VisitCreate from '@/views/board/VisitCreate.vue'

//err
import NotFoundPage from '@/views/err/NotFoundPage.vue'

import store from '@/store'

Vue.use(VueRouter)


const routes = [
  {
    path: '/',
    name: 'List',
    component: List
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
  {
    path: '/visitPage',
    name: 'VisitPage',
    component: VisitPage
  },
  {
    path: '/visitCreate',
    name: 'VisitCreate',
    component: VisitCreate
  },
  {
    path: '/create',
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

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
