<template>
  <div class='container'>
      <h1>GetBlog 로그인</h1>
      <router-link to="/user/join"><h6 class="mt-2">새 계정 만들기 ></h6></router-link>
      <form class="mx-auto col-6">
      <div class='login-section'>
        <div class="form-group">
          <!-- <label for="email">이메일</label>
          <input
            v-model="loginData.email"
            type="email"
            class="form-control"
            id="email"
            aria-describedby="emailHelp"
          /> -->
          <v-text-field label="아이디(이메일)" :rules="rules" hide-details="auto" v-model="loginData.email" type='email'></v-text-field>
        </div>
        <div class="form-group">
          <!-- <label for="password">비밀번호</label>
          <input v-model="loginData.password" type="password" class="form-control" id="password" /> -->
          <v-text-field type='password' label="비밀번호" v-model="loginData.password"></v-text-field>
        </div>
        <div class='d-flex justify-content-between'>
          <div class='d-flex align-items-center'>
            <b-form-checkbox
              id="checkbox-1"
              name="checkbox-1"
              value="accepted"
              unchecked-value="not_accepted"
            >
              내 계정 기억하기
            </b-form-checkbox>
          </div>
        <v-btn @click="login" rounded outlined color="indigo">로그인</v-btn>
        </div>
      </div>

      </form>
        <br />
        <!-- <button @click="login" type="button" class="btn btn--login btn-success">로그인</button> -->
        <!-- <router-link :to="{ name: 'Join'}"><v-btn color="primary">회원가입</v-btn></router-link> -->
      <!-- <div class='divider'></div> -->
      <!-- <div style="margin-top: 5%"></div> -->
      <div>
        <a href='http://localhost:8080/oauth2/authorize/google?redirect_uri="http://localhost:3000/oauth2/redirect"'>
            <div class='socialLogin'>
                <img :src="google.logo" alt="Google" contain height="30%" width="30%"/>
                <!-- <button class='outline-white'>Google 로그인</button> -->
            </div>
        </a>
      </div>
  </div>
</template>

<script>
// import constants from '../../lib/constants'
// import LoginSuccess from '@/views/user/LoginSuccess.vue'
import { mapGetters } from "vuex";
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
      google: {
        logo: require('../../assets/image/google-login-button.png')
      }
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
          $('#exampleModal').modal('hide');
          // this.$router.go()
          this.$router.push('/')
        })
        .catch((err) => {
          alert("이메일 및 비밀번호를 확인해주세요.")
          console.log(err.response.data.message);
        });
    },
  },
  computed: {
    ...mapGetters(["isLogIn"]),
  }
};
</script>

<style scoped>
/* h1 {
  font-size:3rem
  
} */
.divider {
    width: 1px;
    opacity: .8;
    background-color: #858f96;
    margin: 0 71px;
    height: 257px;
}

</style>