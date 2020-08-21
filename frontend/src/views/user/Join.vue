<template>
  <div class="container">
      <h1>가입하기</h1>
      <router-link to="/user/login"><h6 class='mt-3'>GetBlog 계정으로 로그인하기 ></h6></router-link>
      <form class="col-6 mx-auto">
        <div class="form-group">
          <v-text-field label="이메일" hide-details="auto" v-model="email" type="email"></v-text-field>
          <!-- <label for="exampleInputEmail1">이메일</label>
          <input v-model="email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"> -->
          <!-- <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
        </div>
        <div class="form-group">
          <v-text-field label="닉네임" hide-details="auto" v-model="nickName" type="text"></v-text-field>
          <!-- <label for="nickname">닉네임</label>
          <input v-model="nickName" type="text" class="form-control" id="nickname"> -->
        </div>
        <div class="form-group">
          <v-text-field label="비밀번호" hide-details="auto" v-model="password" type="password"></v-text-field>
          <!-- <label for="exampleInputPassword1">비밀번호</label>
          <input v-model="password" type="password" class="form-control" id="exampleInputPassword1"> -->
        </div>
        <div class="form-group">
          <v-text-field label="비밀번호 확인" hide-details="auto" v-model="passwordConfirm" type="password"></v-text-field>
          <!-- <label for="exampleInputPassword2">비밀번호 확인</label>
          <input v-model="passwordConfirm" type="password" class="form-control" id="exampleInputPassword2"> -->
        </div>
        <div class="d-flex justify-content-between">
          <div class="d-flex align-items-center">
            <!-- <input v-model="isTerm" type="checkbox" class="form-check-input" id="exampleCheck1">
            <label class="form-check-label" for="exampleCheck1">약관동의</label> -->
            <b-form-checkbox
                id="checkbox-1"
                v-model="isTerm"
                name="checkbox-1"
                value="accepted"
              >
                약관동의
              </b-form-checkbox>
          </div>
            <v-btn @click="check" rounded outlined color="indigo">가입하기</v-btn>
        </div>
        <!-- <button @click="check" type="button" class="btn btn-primary">가입하기</button> -->
      </form>
      
  </div>
</template>

<script>
// import axios from 'axios'

export default {
  data: () => {
    return {
      email: '',
      nickName: '',
      password: '',
      passwordConfirm: '',
      isTerm: false,

    }
  },
  methods: {
    check() {
        let checkList = [ this.email == '', this.nickName == '', this.password == '',
                        this.password != this.passwordConfirm, !this.isTerm];
        let checkMessage = ['이메일을 입력', '닉네임을 입력', '비밀번호를 입력', '비밀번호를 확인', '약관에 동의'];

        for (let i = 0; i < 5; i++) {
            if (checkList[i]) {
                alert(checkMessage[i]+'해주세요.');
                return;
            }
        }

        this.signup();
    },
    signup() {
        this.$http.post(`${this.$store.state.HOST}/auth/signup`, {
            email: this.email,
            password: this.password,
            nickname: this.nickName
        })
        .then(() => {
            alert('회원가입 인증 메일이 발송되었습니다. 이메일을 확인해주세요.');
            this.$router.push('/');
        },
        (err) => {
            console.log(err.response.data.message)
        })
        .catch((err) => {
            console.log(err);
            alert('이메일 형식 지키기(abc@abc.com)\n비밀번호는 영문/숫자 포함 8자 이상');
        });
    },
  }
}
</script>

<style scoped>
/* h1 {
  font-size: 4rem;
} */
</style>
