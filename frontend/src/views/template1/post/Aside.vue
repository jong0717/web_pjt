<template>
  <div class="">
    <div class="container">
      <router-link class="routerlink" to="/">분류 전체 보기</router-link>
      <hr>
      <div class="aside-top"><a href="#services" class="aside-top">최근 인기글</a></div><br>
      <div class="aside-top"><a href="#clients" class="aside-top" >최근 댓글</a></div>
      <div class="aside-top"><a href="#contact" class="aside-top">태그</a></div>
      <hr class="aside-hr">
      <!-- <h4>전체 방문자 : 0</h4> -->
      <h3>Total : 0</h3>

      <h5>Today : 0</h5>

    </div>
  </div>
</template>

<script>
export default {
  name: "Aside",
};
</script>
<style scoped>
.aside {
  width: auto;
  padding-top: 20%;
  border-left: 0.1em solid #eee;
  padding: 0.5em;
}
.aside-top {
  justify-content: center;
  align-content: space-around;
  height: 100%;
  margin-bottom: 20px;
  padding-bottom: 50px;
  color:black;
}
.aside-hr {
  width: 200px;
}
.routerlink {
  color:black;
}
</style>