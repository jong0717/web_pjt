<template>
  <div class="container">
    <form class="mx-auto col-6">
      <div class="form-group">
        <label for="email">이메일</label>
        <input
          v-model="loginData.email"
          type="email"
          class="form-control"
          id="email"
          aria-describedby="emailHelp"
        />
      </div>
      <div class="form-group">
        <label for="password">비밀번호</label>
        <input v-model="loginData.password" type="password" class="form-control" id="password" />
      </div>
      <br />
      <button @click="login" type="button" class="btn btn--login btn-success">Submit</button>
    </form>
  </div>
</template>

<script>
// import constants from '../../lib/constants'
import axios from "axios";
import $ from 'jquery'
import 'bootstrap'

export default {
  data() {
    return {
      loginData: {
        email: null,
        password: null,
      },
    };
  },
  methods: {
    modalHide() {
        $('#exampleModal').modal('hide')
    },
    login() {
      axios
        .post(`${this.$store.state.HOST}/auth/login`, this.loginData)
        .then((res) => {
          console.log(res.data);
          this.$store.commit('setCookie', res.data.accessToken);
          // this.$router.push('/')
          $('#exampleModal').modal('hide');
        })
        .catch((err) => {
          console.log(err);
          console.log("실패");
        });
    },
    loginGet() { // get 으로 로그인. 서버에 그렇게 돼있음.
      this.$http
        .get(`${this.$store.state.HOST}/account/login`, {
          params: {
            email: this.loginData.email,
            password: this.loginData.password,
          },
        })
        .then((res) => {
          console.log(res.data.token);
          this.$store.commit('setCookie', res.data.token)
        //   this.$router.push('/')
        //   $('#exampleModal').modal('hide')
          $('#exampleModal').modal('hide');
          
          // 새로고침 자동 적용 --> 바꾸셔도 돼요
          this.$router.go()
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style>
</style>