<template>
  <div class="container list">
    <button class="btn btn-light" @click="movePage">글쓰기</button>

    <div class="list-cards row d-flex justify-content-around">
      <div
        class="card border-info mb-3"
        style="max-width: 18rem;"
        v-for="(item, index) in newPosts"
        :key="index + '_posts'"
      >
        <div class="card-header bg-white">
          <strong>
            <h4 class="mb-0">Card title</h4>
          </strong>
        </div>
        <img src="https://picsum.photos/600/300/?image=1" alt />
        <div class="card-body text-info">
          <p>{{item.pno}}</p>
          <router-link :to="'/read?pno='+item.pno">
            <h5 class="card-title">{{item.title}}</h5>
          </router-link>
          <p class="card-text">{{item.content}}</p>
          <p>{{getFormatDate(item.createDate)}}</p>
        </div>
      </div>
    </div>
    <div v-if="!this.$store.state.searchFlag" id="bottomSensor"></div>
  </div>
</template>

<script src="https://cdnjs.cloudflare.com/ajax/libs/scrollmonitor/1.2.0/scrollMonitor.js"></script>
<script>
import moment from "moment";
import { mapGetters } from "vuex";

export default {
  name: "List",
  computed: {
    ...mapGetters(["posts", "newPosts"]),
    // ...mapState(["searchFlag"]),
  },
  // created() {
  //   this.$store.dispatch('getPOSTs')
  // },
  mounted() {
    this.addScrollWatcher();
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
  },
  data: () => {
    return {};
  },
};
</script>
<style>
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
</style>