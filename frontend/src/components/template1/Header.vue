<template>
  <div class="header">
    <div class="inner">
      <nav class="navbar navbar-expand-lg navbar-light bg-white">
      <!-- <div class="d-flex justify-content-between"> -->
        <div class="i">
          <v-btn @click="moveToMain" class="ma-2" color="dark darken-2" dark>
            <!-- <v-icon dark left>mdi-arrow-left</v-icon>Main -->
            <i class="fas fa-home mr-1"></i>메인
          </v-btn>
        </div>
        <!-- <router-link to="/" class="navbar-brand"><strong>SS_log</strong></router-link> -->
        <div id="navbarname" class="animate__animated animate__bounce">
          <a class="navbarname" href="/">
            <h4>{{ blogname }}</h4>
          </a>
        </div>
        <div
          class="collapse navbar-collapse d-flex justify-content-end"
          id="navbarSupportedContent"
        >
          <form class="form-inline my-2 my-lg-0">
            <input
              class="form-control mr-sm-2 fas"
              type="text"
              placeholder="Search"
              aria-label="Search"
              v-model="searchInput"
            />
            <v-btn @click="search(searchInput)" class="searchBtn px-0" tile color="dark" dark>
              <i class="fas fa-search"></i>
            </v-btn>
            <ul class="navbar-nav mr-auto">
              <span v-if="!isLogIn" class="d-flex justify-content-end">
                <li class="nav-item active">
                  <router-link to="/user/join" class="nav-link">회원가입</router-link>
                </li>
                <li class="nav-item">
                  <button
                    type="button"
                    class="btn btn-white"
                    data-toggle="modal"
                    data-target="#exampleModal"
                  >로그인</button>
                </li>
              </span>
              <span v-if="isLogIn" class="d-flex justify-content-end">
                <li class="nav-item active">
                  <button type="button" class="btn btn-outline-secondary" @click="logout">로그아웃</button>
                  <!-- <v-gravatar email="somebody@somewhere.com" :size="30"/> -->
                </li>
              </span>
            </ul>
          </form>
        </div>
      </nav>
      <div
        class="modal fade"
        id="exampleModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">로그인</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <Login />
            </div>
          </div>
        </div>
      </div>
      <nav id="router">
        <ul>
          <li class="routerlist">
            <router-link class="routerlink" :to="{ name:'List' }">목록</router-link>
          </li>
          <li class="routerlist">
            <router-link class="routerlink" :to="{ name:'MyPage' }">MyPage</router-link>
          </li>
          <li class="routerlist">
            <router-link class="routerlink" :to="{ name:'VisitCreate' }">방명록</router-link>
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions, mapState } from "vuex";
import Login from "@/views/user/Login.vue";
// import $ from 'jquery'

export default {
  name: "Header",
  components: {
    Login,
  },
  data() {
    return {
      searchInput: "",
    };
  },
  methods: {
    ...mapActions(["logout", "search"]),
    moveToMain() {
      this.$router.push('/')
    }
  },
  computed: {
    ...mapGetters(["isLogIn"]),
    ...mapState(["blogname"]),
  },
  mounted() {
    this.$store.state.renderNum = 1;
  },
};
</script>

<style scoped>
h4 {
  font-size: 2rem;
}

.header {
  border-bottom: 1px solid #eee;
}

.searchBtn {
  min-width: 36px !important;
  height: 36px;
}

#router {
  margin: 0 0;
  height: 66px;
  overflow-x: auto;
  overflow-y: hidden;
}
.routerlist {
  list-style: none;
  float: left;
  padding: 0 26px;
  margin-top: 20px;
}

.inner {
  position: relative;
  max-width: 100%;
  margin: 0 auto;
}
#router ul {
  display: inline-block;
  margin-left: -730px;
  vertical-align: top;
  width: 400px;
  padding: 0;
  height: 66px;
}
#router ul li {
  height: 66px;
}

.fixec-top {
  padding: 23px 0;
}
.routerlink {
  color: #424242;
}
.router-link-exact-active {
  color: black;
  text-decoration-line: underline;
}
.navbarname {
  color: black;
  font-family: "Nanum Myeongjo";
  display: flex;
  margin-left:4rem;
}
.navbarname:hover {
  animation: bounce;
  animation-duration: 1.5s;
}
.i {
  margin-left:2rem;
}
#navbarSupportedContent {
  margin-right:2rem;
}
</style>
