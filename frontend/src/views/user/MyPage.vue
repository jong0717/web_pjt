<template>
  <div class="container">
    <div class="row">
      <div class="col-6">
        <h3>기본 정보</h3>
        <form class="wrap_set">
          <div class="form-group">
            <div v-if="imageUrl">
              <img :src="imageUrl" alt width="100px" />
            </div>
            <div v-if="!imageUrl">
              <v-gravatar :email="email" />
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
              v-model="userData.nickname"
            />
          </div>
          <div class="form-group">
            <label for="exampleFormControlInput1">새 비밀번호</label>
            <input
              type="password"
              class="form-control"
              id="exampleFormControlInput1"
              v-model="userData.password"
            />
          </div>
          <div class="form-group">
            <label for="exampleFormControlTextarea1">소개</label>
            <textarea
              v-model="userData.introduce"
              class="form-control"
              id="exampleFormControlTextarea1"
              rows="3"
            ></textarea>
          </div>
          <div class="row justify-content-around">
            <v-btn @click="editUserinfo" color="success">수정하기</v-btn>
            <v-btn @click="deleteUser" color="error">탈퇴하기</v-btn>
          </div>
        </form>
      </div>
      <div class="col-6">
        <h3>운영중인 블로그</h3>
        <div class="card" v-for="item in myblog" :key="item">
          <div class="card-body">
            <h5 class="card-title">{{item.blogname}}</h5>
            <v-btn @click="moveToBlog(item)" color>블로그 가기</v-btn>
          </div>
        </div>
        <div>
          <br />
          <h3>개설 현황</h3>
          <div class="card-body">
            <h5></h5>
            <h5 class="card-title">{{canCreateBlogNum}}개의 블로그를 더 운영할 수 있습니다.</h5>
            <h7>운영중인 블로그: {{myblog.length}}개</h7>
            <br />
            <button @click="moveToBlogCreate" class="btn btn-dark">새 블로그 만들기</button><hr>
            <h6>더 많은 블로그가 필요하십니까?</h6>
            <v-row justify="center">
              <v-dialog v-model="dialog" persistent max-width="290">
                <template v-slot:activator="{ on, attrs }">
                  <v-chip v-bind="attrs" v-on="on" class="ma-2" color="orange" text-color="white">
                    Premium
                    <v-icon right>mdi-star</v-icon>
                  </v-chip>
                </template>
                <v-card>
                  <v-card-title class="headline">Use Google's location service?</v-card-title>
                  <v-card-text>Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.</v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="green darken-1" text @click="dialog = false">Disagree</v-btn>
                    <v-btn color="green darken-1" text @click="dialog = false">Agree</v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-row>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { mapActions, mapState, mapGetters } from "vuex";

export default {
  data() {
    return {
      accessToken: this.$store.state.authToken,
      email: "",
      userData: {
        accessToken: this.$store.state.authToken,
        imageUrl: null,
        introduce: null,
        nickname: null,
        password: null,
      },
      dialog: false,
    };
  },
  methods: {
    ...mapActions(["logout", "getMyBlog", "moveToBlog"]),
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
          this.userData.nickname = res.data.nickname;
          this.userData.imageUrl = res.data.imageUrl;
          this.userData.introduce = res.data.introduce;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    editUserinfo() {
      axios
        .put(`${this.$store.state.HOST}/account/modify`, this.userData)
        .then((res) => {
          location.reload();
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deleteUser() {
      axios
        .delete(`${this.$store.state.HOST}/account/withdrawal`, {
          data: this.$store.state.authToken,
        })
        .then((res) => {
          console.log(res);
          setTimeout(() => {
            this.logout();
          }, 1000);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    moveToBlogCreate() {
      this.$router.push({ name: "BlogCreate" });
    },
  },
  mounted() {
    this.getUserinfo();
    this.getMyBlog();
  },
  // created() {
  //   this.getUserinfo();
  // },
  computed: {
    ...mapState(["myblog"]),
    ...mapGetters(["canCreateBlogNum"]),
  },
};
</script>

<style>
</style>