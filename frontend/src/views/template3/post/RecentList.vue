<template>
  <div>
    <h2 class="text-align-left">전체글</h2>
      <hr>
        <v-card class="elevation-2" v-for="(item,index) in newPosts" :key="index">
          <v-card-title class="headline">
            <router-link>{{ item.title }}</router-link>
          </v-card-title>
          <v-card-text>
            {{ item.content }}
          </v-card-text>
        </v-card>
  </div>
</template>

<script src="https://cdnjs.cloudflare.com/ajax/libs/scrollmonitor/1.2.0/scrollMonitor.js"></script>
<script>
import moment from "moment"
import { mapGetters } from "vuex"
  export default {
    computed: {
    ...mapGetters(["posts", "newPosts"]),
    // ...mapState(["searchFlag"]),
  },
    data: () => ({

    }),
      mounted() {
    this.addScrollWatcher();
    this.$store.state.renderNum = 3
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
        console.log("___BOTTOM___");
        if (this.$store.state.searchFlag === false) {
          setTimeout(() => {
            this.$store.dispatch("getPOSTs");
          }, 500);
        }
      });
    },
    loadUntilViewportIsFull() {
      const bottomSensor = document.querySelector("#bottomSensor");
      const watcher = scrollMonitor.create(bottomSensor);
      if (watcher.isFullyInViewport && this.$store.state.searchFlag === false) {
        this.$store.dispatch("getPOSTs");
      }
    },
    reload() {
      this.$router.go()
    },
  }
  }
</script>

<style scoped>

</style>