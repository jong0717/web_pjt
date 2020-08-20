<template>
  <div class="container list">
    <div class="row justify-content-around">
      <v-btn @click="movePage" large color="blue-grey darken-2" class="text-white">글쓰기</v-btn>
      <v-btn @click="reload" large color="blue-grey darken-2" class='text-white'>전체 목록 보기</v-btn>
    </div>
    <div class="list-cards row d-flex justify-content-around">
      <v-card
        class="mx-auto my-12 card mb-3"
        max-width="374"
        v-for="(item, index) in newPosts"
        :key="index + '_posts'"
      >
        <v-img height="250" :src="'https://storage.googleapis.com/getblog/'+item.img"></v-img>
        <!-- <v-img height="250" :src="'@/assets/post/'+item.img"></v-img>
        <v-img height="250" :src="'/public/files/'+item.img"></v-img>
        <img :src="require(`@/assets/post/${imgURL}`)">
        <img :src="'/public/files/'+item.img">
        <p>{{item.img}}</p> -->
        <v-card-title>
          <!-- <router-link :to="{name:'Read', query:{}}"> -->
          <router-link :to="'/read?pno='+item.pno" class="routerlink">
            <h5 class="card-title">{{item.title}}</h5>
          </router-link>
        </v-card-title>
          <p>{{ item.createDate | moment("YYYY.MM.DD")}}</p>
        <v-card-text>
          <v-row align="center" class="mx-0">
            {{ item.tag }}

            
          </v-row>


          <!-- <div v-html="item.content"></div> -->
        </v-card-text>

        <v-divider class="mx-4"></v-divider>


        <v-card-actions>
          {{ item.heart }}
          <v-btn v-if="!item.clickHeart" icon color="black">
            <v-icon @click="like(item.pno)">mdi-heart</v-icon>
          </v-btn>
          <v-btn v-else icon color="pink">
            <v-icon @click="like(item.pno)">mdi-heart</v-icon>
          </v-btn>
        </v-card-actions>
      </v-card>
    </div>
    <div v-if="!this.$store.state.searchFlag" id="bottomSensor"></div>
  </div>
</template>

<script src="https://cdnjs.cloudflare.com/ajax/libs/scrollmonitor/1.2.0/scrollMonitor.js"></script>
<script>
import moment from "moment";
import { mapGetters, mapActions } from "vuex";

export default {
  name: "List",
  computed: {
    ...mapGetters(["posts", "newPosts"]),
    // ...mapState(["searchFlag"]),
  },
  created() {
  },
  mounted() {
    this.addScrollWatcher();
    this.$store.state.renderNum = 1;
    this.$store.state.bid = this.$route.params.bid
    if(!window.location.hash) {
        window.location = window.location + '#loaded';
        window.location.reload();
    }
    this.$store.dispatch("getBlogName", this.$route.params.bid)

  },
  updated() {
    this.loadUntilViewportIsFull();
  },
  methods: {
    ...mapActions(['like']),
    movePage() {
      this.$router.push({ name:'Create' });
    },
    getFormatDate(createDate) {
      return moment(new Date(createDate)).format("YYYY.MM.DD");
    },
    addScrollWatcher() {
      const bottomSensor = document.querySelector("#bottomSensor");
      const watcher = scrollMonitor.create(bottomSensor);
      watcher.enterViewport(() => {
        // console.log("___BOTTOM___");
        if (this.$store.state.searchFlag === false && (typeof(this.$route.params.bid) === "number" || typeof(this.$route.params.bid) === "string")) {
          setTimeout(() => { // Main이랑 Detail에서 자꾸 여기 함수 실행돼서 막으려고 이렇게 해놓았습니다.
            this.$store.dispatch("getPOSTs", this.$route.params.bid);
          }, 500);
        }
      });
    },
    loadUntilViewportIsFull() {
      const bottomSensor = document.querySelector("#bottomSensor");
      const watcher = scrollMonitor.create(bottomSensor);
      if (watcher.isFullyInViewport && this.$store.state.searchFlag === false && (typeof(this.$route.params.bid) === "number" || typeof(this.$route.params.bid) === "string")) {
        this.$store.dispatch("getPOSTs", this.$route.params.bid);
      }
    },
    reload() {
      this.$router.go();
    },
  },
  data: () => {
    return {
      bid: this.$route.params.bid
    }
  },
  goToDetail() {
    this.$router.push({name:'Read', params:{bid:this.$route.params.bid}})
  }
};
</script>
<style scoped>
.routerlink {
  color:black;
}
.list {
  /* border-right: 0.1em solid #eee; */
  padding: 0.5em;
}
.list-card {
  padding: 10px;
  margin: 10px;
  width: 100%;
  float: left;
}
.list-cards {
  text-align: center;
  margin: 10px;
  padding: 10px;
}
.card {
  display: inline-block;
}
.writeBtn {
  color:greenyellow !important;
}
</style>
