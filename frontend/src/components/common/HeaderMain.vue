<template>
  <div class="d-flex justify-content-between mt-4 mx-5">
    <h1>GetBlog.com</h1>
    <div v-if="!isLogIn">
      <router-link to="/user/login"><v-btn rounded color="#283593" outlined>로그인</v-btn></router-link>
    </div>
    <div  v-if="isLogIn">
      <v-btn rounded color="#283593" outlined @click="logout">로그아웃</v-btn>
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
  },
};
</script>

<style>
</style>