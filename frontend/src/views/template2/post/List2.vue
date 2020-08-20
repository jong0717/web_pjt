<template>
  <div>
    <v-carousel cycle height="400" hide-delimiter-background show-arrows-on-hover>
      <v-carousel-item v-for="(slide, i) in newPosts" :key="i">
        <v-row class="fill-height" align="center" justify="center">
          
          <v-img
            class=" h-100"
            :src="'https://storage.googleapis.com/getblog/'+slide.img"
            alt 
          >
          </v-img>
          <!-- </router-link> -->
        </v-row>
      </v-carousel-item>
    </v-carousel>

    <h1 class="mt-15">전체글</h1>
    <button>더보기</button>
    <hr />

    <div v-for="(item, index) in newPosts" :key="index + '_posts'" class="listBlock">
      <router-link :to="'/read2?pno='+item.pno">
        <h2 style="color:#1bbf5b">{{ item.title }}</h2>
      </router-link>
      <div class="contentible" v-html="item.content"></div>
      <div class="d-flex justify-content-around">
        <div>
          {{ item.heart }}
          <v-btn v-if="!item.clickHeart" icon color="black">
            <v-icon @click="like(item.pno)">mdi-heart</v-icon>
          </v-btn>
          <v-btn v-else icon color="pink">
            <v-icon @click="like(item.pno)">mdi-heart</v-icon>
          </v-btn>
        </div>
        <div class="date d-flex align-items-center">
          {{ item.createDate | moment('YY.MM.DD HH:mm')}}
        </div>
      </div>
      <hr class="w-50 mx-auto" />
    </div>
    <hr />
    <div v-if="!this.$store.state.searchFlag" id="bottomSensor"></div>
  </div>
</template>

<script src="https://cdnjs.cloudflare.com/ajax/libs/scrollmonitor/1.2.0/scrollMonitor.js"></script>
<script>
import moment from "moment";
import { mapActions,mapGetters } from "vuex";

export default {
  name: "Home",
  computed: {
    ...mapGetters(["posts", "newPosts"]),
  },
  mounted() {
    this.addScrollWatcher();
    // this.$store.dispatch("getPOSTs", this.$route.params.bid)
    this.$store.state.bid = this.$route.params.bid;
    this.$store.state.renderNum = 2;
    if (!window.location.hash) {
      window.location = window.location + "#loaded";
      window.location.reload();
    }
    this.$store.dispatch("getBlogName", this.$route.params.bid)
  },
  updated() {
    this.loadUntilViewportIsFull();
  },
  methods: {
    ...mapActions(['like']),
    moveToMain() {
      this.$router.push("/");
    },
    movePage() {
      this.$router.push({ name: "Create2" });
    },
    getFormatDate(createDate) {
      return moment(new Date(createDate)).format("YYYY.MM.DD ");
    },
    addScrollWatcher() {
      const bottomSensor = document.querySelector("#bottomSensor");
      const watcher = scrollMonitor.create(bottomSensor);
      watcher.enterViewport(() => {
        // console.log("___BOTTOM2___");
        if (
          this.$store.state.searchFlag === false &&
          (typeof this.$route.params.bid === "number" ||
            typeof this.$route.params.bid === "string")
        ) {
          setTimeout(() => {
            this.$store.dispatch("getPOSTs", this.$route.params.bid);
          }, 500);
        }
      });
    },
    loadUntilViewportIsFull() {
      const bottomSensor = document.querySelector("#bottomSensor");
      const watcher = scrollMonitor.create(bottomSensor);
      if (
        watcher.isFullyInViewport &&
        this.$store.state.searchFlag === false &&
        (typeof this.$route.params.bid === "number" ||
          typeof this.$route.params.bid === "string")
      ) {
        this.$store.dispatch("getPOSTs", this.$route.params.bid);
      }
    },
    reload() {
      this.$router.go();
    },
  },
  data: () => {
    return {
      background:
        "https://images.unsplash.com/photo-1494438639946-1ebd1d20bf85?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1047&q=80",
      drawer: null,
      NNum: 4,
    };
  },
};
</script>

<style scoped>
.date {
  font-size:20px;
}
* {
  margin: 0px;
  padding: 0px;
}
.contentible {
  display: inline-block;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  height: 1.2em;
  width: 100%;
}
.dat {
  display: flex;
  justify-content: flex-end;
  font-size: 0.7em;
  margin-right: 20px;
}
.listBlock {
  display: inline-block;
  width: 40%;
}
.mynavi {
  background-color: #cee3ca;
}
</style>