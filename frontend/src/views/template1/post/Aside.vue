<template>
  <div class>
    <div class="container">
      <router-link class="routerlink" to="/">분류 전체 보기</router-link>
      <hr />
      <div class="aside-top">
        최근 등록글
        <div v-for="(item, index) in newPosts" :key="index + '_posts'">
          <div v-if="index<4" class="my-4 subtitle-1" style="margin:0;">
            <router-link :to="'/read?pno='+item.pno">
              <div class="listTitle">{{ item.title }}</div>
            </router-link>
            <!-- <div class="dat" style="color:#97fabf;font-size:1em ">{{getFormatDate(item.createDate)}}</div> -->
          </div>
        </div>
      </div>
      <br />
      <div class="aside-top">
        최근 댓글
      </div>
      
      <hr class="aside-hr" />
      <!-- <h4>전체 방문자 : 0</h4> -->
      <h3>Total : 0</h3>

      <h5>Today : 0</h5>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "Aside",
  data: function () {
    return {
      count: "",
    };
  },
  computed: {
    ...mapGetters(["newPosts"]),
  },
  // created(){
  //   this.getBlog(),
  //   this.getCount()
  // },
  methods: {
    // getBlog(){
    //   this.$http.get(`${this.$store.state.HOST}/api/blog/bbbb`)
    //     .then(({ data }) => {
    //       this.count = data;
    //     })
    //     .catch(() => {
    //       console.log('에러가 발생했습니다.');
    //     });
    // },
    // getCount(){
    //   this.$http.get(`${this.$store.state.HOST}/api/blog`)
    //     .then(({ data }) => {
    //       this.count = data;
    //     })
    //     .catch(() => {
    //       console.log('에러가 발생했습니다.');
    //     });
    // }
  },
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
  color: black;
}
.aside-hr {
  width: 200px;
}
.routerlink {
  color: black;
}
</style>