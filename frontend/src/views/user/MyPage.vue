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
            <!-- <v-btn @click="deleteUser" color="error">탈퇴하기</v-btn> -->
            <!-- <v-btn color="error">탈퇴하기</v-btn> -->
            <v-bottom-sheet v-model="sheet">
              <template v-slot:activator="{ on, attrs }">
                <v-btn color="error" dark v-bind="attrs" v-on="on">탈퇴하기</v-btn>
              </template>
              <v-sheet class="text-center" height="200px">
                <v-btn class="mt-6" text color="error" @click="sheet = !sheet">close</v-btn>
                <div class="my-3">탈퇴하면 작성한 글들이 모두 사라집니다. 그래도 탈퇴하시겠습니까?</div>
                <v-btn @click="deleteUser" color="error">탈퇴하기</v-btn>
              </v-sheet>
            </v-bottom-sheet>
          </div>
        </form>
      </div>
      <div class="col-6">
        <h3>운영중인 블로그</h3>
        <!-- <div class="card" v-for="item in myblog" :key="item">
          <div class="card-body">
            <h5 class="card-title">{{item.blogname}}</h5>
            <p>사용중인 테마:{{item.template_num}}</p>
            <v-btn @click="moveToBlog(item)" color>블로그 가기</v-btn>
          </div>
          </div> -->
        <BlogTable />
        <div>
          <br />
          <h3>개설 현황</h3>
          <div class="card-body">
            <h5></h5>
            <h5 class="card-title">{{canCreateBlogNum}}개의 블로그를 더 운영할 수 있습니다.</h5>
            <h7>운영중인 블로그: {{myblog.length}}개</h7>
            <br />
            <button @click="moveToBlogCreate" class="btn btn-dark">새 블로그 만들기</button>
            <hr />
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
                  <v-card-title class="headline">카카오페이 결제 안내</v-card-title>
                  <img
                    src="https://t1.daumcdn.net/kakaopay/tesla/20181010/pg_web/images/logo_pay.png"
                    alt
                  />
                  <v-card-text>
                    QR코드를 스캔하면
                    결제 페이지로 이동합니다.
                  </v-card-text>
                  <img
                    src="data:image/png;base64,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"
                    alt
                  />
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="green darken-1" text @click="dialog = false">닫기</v-btn>
                    <!-- <v-btn color="green darken-1" text @click="dialog = false">Agree</v-btn> -->
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
import BlogTable from '@/views/user/BlogTable.vue'
export default {
  components:{
    BlogTable
  },
  data() {
    return {
      sheet:false,
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
    this.$store.state.renderNum = 0;
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