<template>
  <div id="navMain" class="d-flex justify-content-between mt-4 mx-5 align-items-center">
    <router-link to="/" class="router-link"><img src="@/assets/로고.png" alt="" width="200px" ></router-link>
    <div v-if="!isLogIn">
      <router-link to="/user/login" class="routerlink"><v-btn rounded color="#283593" outlined>로그인</v-btn></router-link>
    </div>
    <div  v-if="isLogIn">
      <v-btn rounded color="#283593" outlined @click="logout">로그아웃</v-btn> |
      <router-link to="/user/mypage" class="routerlink"><v-btn rounded color="#283593" outlined>내 블로그 정보</v-btn></router-link>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
    name:'header',
    computed: {
    ...mapGetters(["isLogIn"]),
  },
    methods: {
    ...mapActions(["logout", "search"]),
    created() {
      this.$store.state.renderNum = '0'
  },
    mounted() {
      this.$store.state.renderNum = '0'
    }
  },
};
</script>

<style scoped>
.routerlink {
  color:#283593;
}
#navMain {
  margin:16px !important;
}

</style>