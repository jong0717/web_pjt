<template>
  <div class="container">
    <div class="row">
      <div class="col">
        <h3>기본 정보</h3>
        <form class="wrap_set">
          <div class="form-group">
            <div v-if="imageUrl">
              <img :src="imageUrl" alt="" width="100px">
            </div>
            <div v-if="!imageUrl">
              <v-gravatar email="somebody@somewhere.com" />
            </div>
          </div>
          <div class="form-group">
            <label for="exampleFormControlInput1">이메일</label>
            <input type="email" class="form-control" id="exampleFormControlInput1" v-model="email" />
          </div>
          <div class="form-group">
            <label for="nickname">닉네임</label>
            <input
              type="text"
              class="form-control"
              id="exampleFormControlInput2"
              v-model="nickname"
            />
          </div>
          <div class="form-group">
            <label for="exampleFormControlTextarea1">소개</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
export default {
  data() {
    return {
      accessToken: this.$store.state.authToken,
      email: "",
      nickname: "",
      imageUrl: "",
    };
  },
  methods: {
    getUserinfo() {
      axios
        .get(`${this.$store.state.HOST}/account/userinfo`, {
          params: {
            accessToken: this.$store.state.authToken,
          },
        })
        .then((res) => {
          console.log(res.data);
          this.email = res.data.email;
          this.nickname = res.data.nickname;
          this.imageUrl = res.data.imageUrl
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  mounted() {
    this.getUserinfo();
  },
  created() {
    this.getUserinfo();
  },
  computed: {
    ...mapState(['imageUrl'])
  }
};
</script>

<style>
</style>