import Vue from 'vue'
import Vuex from 'vuex'

import VueCookies from 'vue-cookies'
import cookies from 'vue-cookies'

import createPersistedState from 'vuex-persistedstate';
import http from '../util/http-common';

import router from '@/router'

import VueScrollMonitor from 'vue-scrollmonitor'

import uid from './uid.js';

const modules = {
  uid,
}
const plugins = [
  createPersistedState({
    paths: [
      'uid'
    ]
  })
]

Vue.use(Vuex);
Vue.use(VueCookies)
Vue.use(VueScrollMonitor)

export default new Vuex.Store({
  modules,
  plugins,
  state: {
    HOST: 'http://localhost:8080',
    authToken: cookies.get('auth-token'),
    posts: [],
    post: {},
    replies: [],
    reply: {},
    pnoArr: [], // 중복을 걸러주기 위한 pno array
    newPostsArr: [],
    // search
    searchPosts: [],
    searchFlag: false,
    // rendering
    renderNum: '0', // 0: main 1: form1, 2: form2, 3: form3
    // userinfo
    email: '',
    nickname: '',
    imageUrl: '',
    blogname: '',
    myblog: [],
    myblogbid: [],
    page: 0,
    bid: ''
  },
  getters: {
    // user
    isLogIn: state => !!state.authToken,
    // post
    posts(state) {
      return state.posts;
    },
    post(state) {
      return state.post;
    },
    replies(state) {
      return state.replies;
    },
    reply(state) {
      return state.reply;
    },
    newPosts(state) { // state.posts 를 돌면서 새로운 데이터(pnoArr로 판단)를 newPostsArr에 넣어 반환
      for (let post of state.posts) {
        if (state.pnoArr.includes(post.pno)) {
          console.log('중복데이터')
        } else {
          console.log('새로운데이터')
          state.pnoArr.push(post.pno)
          state.newPostsArr.push(post)
        }
      }
      return state.newPostsArr
    },
    canCreateBlogNum(state) {
      return 5 - (state.myblog).length
    },
  },
  mutations: {
    // user
    setCookie(state, payload) {
      state.authToken = payload
      Vue.$cookies.set('auth-token', payload)
    },
    // post
    setPOSTs(state, payload) {
      if (state.searchFlag === true) {
        state.posts = [...payload]
      } else {
        state.posts = [...state.posts, ...payload];
      }
    },
    setPOST(state, payload) {
      state.post = payload;
    },
    setREPLIES(state, payload) {
      state.replies = payload;
    },
    setREPLY(state, payload) {
      state.reply = payload;
    },
    // render
    setRenderNum(state, payload) {
      state.renderNum = payload
    },
    getUserinfo(state) {
      http
        .get(`/account/userinfo`, {
          params: {
            accessToken: this.$store.state.authToken,
          },
        })
        .then((res) => {
          console.log(res.data);
          state.email = res.data.email
          state.nickname = res.data.nickname
          state.imageUrl = res.data.imageUrl
        })
        .catch((err) => {
          console.log(err)
        })
    },
    setMyBlog(state, payload) {
      for (let item of payload) {
        if (item.uid === state.uid.uid && !state.myblogbid.includes(item.bid)) {
          state.myblog.push(item)
          state.myblogbid.push(item.bid)
          console.log('하나 추가')
        }
        // console.log(item.uid)
      }
      // state.myblog = [...payload]
    },
    pagePlus(state) {
      state.page++
    }
  },
  actions: {
    // user
    logout({ commit }) {
      commit('setCookie', null)
      cookies.remove('auth-token')
      router.push({ name: 'Main' })
      router.go()
    },
    // post
    getPOSTs({ state }, bid) {
      // console.log(bid)
      http
        .get(`/api/post/list/${bid}`, {
          params: {
            bid: bid,
            accessToken: state.authToken,
            page: state.page++,
            size: 4,
          }
        })
        .then(({ data }) => {
          console.log(data)
          if (data.empty === false) {
            // commit('setPOSTs', data.content);
            if (state.searchFlag === true) {
              state.posts = [...data.content]
            } else {
              console.log("posts 데이터 다시")
              state.posts = [...state.posts, ...data.content];
            }
          }
        })
        .catch((err) => {
          alert('글을 가져오는 데 에러가 발생했습니다.');
          console.log(err)
        });
    },
    getPOST(context, payload) {
      http
      .get(payload).then(({ data }) => {
        context.commit('setPOST', data);
      });
    },
    getREPLIES(context) {
      http
        .get(`/api/reply/list`)
        .then(({ data }) => {
          context.commit('setREPLIES', data);
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },
    getREPLY(context, payload) {
      http.get(payload).then(({ data }) => {
        context.commit('setREPLY', data);
      });
    },
    search({ commit }, searchInput) {
      http.get(`api/post/search/${searchInput}`)
        .then((res) => {
          console.log(res.data)
          this.state.searchFlag = true
          this.state.newPostsArr = []
          this.state.posts = []
          this.state.pnoArr = []
          commit('setPOSTs', res.data)
            .catch((err) => {
              console.log(err)
            })
        })
    },
    moveToBlog({ commit }, payload) {
      this.state.blogname = payload.blogname
      commit('setRenderNum', payload.template_num)
      // router.push({ name: 'List' })
      if (payload.template_num == 1) {
        router.push({ name: 'List', params: { bid: payload.bid } })
      } else if (payload.template_num == 2) {
        router.push({ name: 'List2', params: { bid: payload.bid } })
      } else if (payload.template_num == 3) {
        router.push({ name: 'RecentList', params: { bid: payload.bid } })
      }
    },
    createBlog({ commit }, payload) {
      http.post(`api/blog/insert`, {
        bid: payload.bid,
        blogname: payload.blogname,
        template_num: payload.template_num+1,
        uid: payload.uid,
        visitors_num: payload.visitors_num,
        // payload
      })
        .then((res) => {
          commit('setRenderNum', payload.template_num+1)
          console.log(res.data)
          // router.push({ name: 'List', params: { uid: this.state.uid.uid, bid: res.data } })
          if (payload.template_num == 0) {
            router.push({ name: 'List', params: { bid: res.data } })
          } else if (payload.template_num == 1) {
            router.push({ name: 'List2', params: { bid: res.data } })
          } else if (payload.template_num == 2) {
            router.push({ name: 'RecentList', params: { bid: res.data } })
          } // blogcreate 시에 값이 0 1 2로 되어 있어 일단 이렇게
        })
        .catch((err) => {
          console.log(err)
        })
    },
    getMyBlog({ commit }) {
      http.get(`api/blog/list`)
        .then((res) => {
          console.log(res.data)
          commit('setMyBlog', res.data)
        })
        .catch((err) => {
          console.log(err)
        })
    },
    like({ state, dispatch }, payload) {
      const likeData = {
        accessToken: state.authToken,
        pno: payload
      }
      http.post(`heart/click`, likeData)
      .then((res) => {
        console.log(res.data.message)
        dispatch('getPOSTs', state.bid)
      })
      .catch((err) => {
        console.log(err)
      })
    },
  },
})
