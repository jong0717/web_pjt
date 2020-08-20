<template>
  <div>
    <v-carousel cycle height="400" hide-delimiter-background show-arrows-on-hover>
      <v-carousel-item v-for="(slide, i) in newPosts" :key="i">
        <v-row v-show="i<4" class="fill-height" align="center" justify="center">
          <v-img
            class="imagine h-100"
            :src="'https://storage.googleapis.com/getblog/'+slide.img"
            alt
          ></v-img>
        </v-row>
      </v-carousel-item>
    </v-carousel>

    <h1 class="mt-15">Category 1</h1>
    <button>더보기</button>
    <hr />

    <div v-for="(item, index) in newPosts" :key="index + '_posts'" class="listBlock">
      <router-link :to="'/read2?pno='+item.pno"><h4>{{ item.title }}</h4></router-link>
      <p>{{ item.content }}</p>
      {{ item.createDate }}
      <hr class="w-50 mx-auto" />
    </div>
    <hr />
    <div v-if="!this.$store.state.searchFlag" id="bottomSensor"></div>
  </div>
</template>

<script src="https://cdnjs.cloudflare.com/ajax/libs/scrollmonitor/1.2.0/scrollMonitor.js"></script>
<script>
import moment from "moment";
import { mapGetters } from "vuex";

export default {
  name: "Home",
  computed: {
    ...mapGetters(["posts", "newPosts"]),
  },
  mounted() {
    this.addScrollWatcher();
    // this.$store.dispatch("getPOSTs", this.$route.params.bid)
    this.$store.state.bid = this.$route.params.bid
    this.$store.state.renderNum = 2;
    if(!window.location.hash) {
        window.location = window.location + '#loaded';
        window.location.reload();
    }
  },
  updated() {
    this.loadUntilViewportIsFull();
  },
  methods: {
    moveToMain() {
      this.$router.push("/");
    },
    movePage() {
      this.$router.push({ name: "Create2" });
    },
    getFormatDate(createDate) {
      return moment(new Date(createDate)).format("YYYY.MM.DD");
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
      background: 'https://images.unsplash.com/photo-1494438639946-1ebd1d20bf85?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1047&q=80',
      drawer: null,
    };
  },
};
</script>

<style scoped>
* {
  margin: 0px;
  padding: 0px;
}
.dat{
  display: flex;
  justify-content: flex-end;
  font-size: 0.7em;
  margin-right: 20px;
}
.listBlock {
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
  display: inline-block;
  width: 40%;
}
.mynavi{
  background-color: #cee3ca;
}
</style>
