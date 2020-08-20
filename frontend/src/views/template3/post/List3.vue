<template>
  <div>
    <h3 class="text-left">전체글({{}})</h3>
    <hr />

    <v-timeline :align-top="alignTop" :reverse="reverse" :dense="dense">
      <v-timeline-item
        v-for="(item,index) in newPosts"
        :key="index"
        :fill-dot="fillDot"
        :hide-dot="hideDot"
        :icon="icon ? 'mdi-star' : ''"
        :icon-color=" iconColor ? 'red lighten-3' : ''"
        :left="left"
        :right="right"
        :small="small"
        color="#ddaeb6"
      >
        <template v-slot:icon>
          <v-avatar v-if="avatar">
            <img src="http://i.pravatar.cc/64" />
          </v-avatar>
        </template>
        <!-- <span slot="opposite">Tus eu perfecto</span> -->
        <v-card class="elevation-2">
          <router-link :to="'/read3?pno='+item.pno" class="routerlink">
            <v-card-title class="headline">{{ item.title }}</v-card-title>
          </router-link>
          <v-card-text>
            <div class="contentable" v-html="item.content"></div>
          </v-card-text>
          <div class="text-right mr-4">{{item.createDate | moment('YY.MM.DD')}}</div>
        </v-card>
      </v-timeline-item>
    </v-timeline>
    <div v-if="!this.$store.state.searchFlag" id="bottomSensor"></div>
  </div>
</template>

<script src="https://cdnjs.cloudflare.com/ajax/libs/scrollmonitor/1.2.0/scrollMonitor.js"></script>
<script>
import moment from "moment";
import { mapGetters } from "vuex";
export default {
  computed: {
    ...mapGetters(["posts", "newPosts"]),
    // ...mapState(["searchFlag"]),
  },
  data: () => ({
    alignTop: true,
    avatar: false,
    dense: true,
    fillDot: false,
    hideDot: false,
    icon: false,
    iconColor: false,
    left: false,
    reverse: false,
    right: false,
    small: false,
  }),
  mounted() {
    this.addScrollWatcher();
    this.$store.state.renderNum = 3;
    this.$store.state.bid = this.$route.params.bid;
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
    movePage() {
      this.$router.push("/create");
    },
    getFormatDate(createDate) {
      return moment(new Date(createDate)).format("YYYY.MM.DD");
    },
    addScrollWatcher() {
      const bottomSensor = document.querySelector("#bottomSensor");
      const watcher = scrollMonitor.create(bottomSensor);
      watcher.enterViewport(() => {
        // console.log("___BOTTOM___");
        if (
          this.$store.state.searchFlag === false &&
          (typeof this.$route.params.bid === "number" ||
            typeof this.$route.params.bid === "string")
        ) {
          setTimeout(() => {
            // Main이랑 Detail에서 자꾸 여기 함수 실행돼서 막으려고 이렇게 해놓았습니다.
            this.$store.dispatch("getPOSTs", this.$route.params.bid);
          }, 500);
        }
      });
    },
    loadUntilViewportIsFull() {
      const bottomSensor = document.querySelector("#bottomSensor");
      const watcher = scrollMonitor.create(bottomSensor);
      if (watcher.isFullyInViewport && this.$store.state.searchFlag === false) {
        this.$store.dispatch("getPOSTs", this.$route.params.bid);
      }
    },
    reload() {
      this.$router.go();
    },
  },
};
</script>

<style scoped>
/* .contentable {
  display: inline-block;
  width: 100%;
  white-space: normal;
  overflow: hidden;
  line-height: 1.2;
  height: 3.6em;

} */
.routerlink {
  color: black !important;
  /* text-decoration: bold; */
}
.v-card__text {
  padding: 8px;
}
.v-card__title {
  padding: 8px;
}
</style>