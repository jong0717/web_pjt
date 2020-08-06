<template>
  <div>
      <img src="@/assets/main.png" alt="mainimage" class="mainImg">
      <router-link to="/blogcreate"><button type="button" class="btn btn-outline-dark startBtn">시작하기</button></router-link>
  </div>
</template>

<script>
export default {
    name:'main',
    mounted() {
    this.$store.state.renderNum = 0
  }
}
</script>

<style>
.mainImg {
    width:100%;
    height: auto;
}
.startBtn {
    position: relative;
    bottom: 210px;
    border-radius: 1rem !important;
}
.signBtn {
    position: relative;
}
</style>