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
        <v-img height="250" src="https://cdn.vuetifyjs.com/images/cards/cooking.png"></v-img>
        <!-- <v-img height="250" :src="'@/assets/post/'+item.img"></v-img>
        <v-img height="250" :src="'/public/files/'+item.img"></v-img>
        <img :src="require(`@/assets/post/${imgURL}`)">
        <img :src="'/public/files/'+item.img">
        <p>{{item.img}}</p> -->
        <v-card-title>
          <router-link :to="'/read?pno='+item.pno">
            <h5 class="card-title">{{item.title}}</h5>
          </router-link>
        </v-card-title>

        <v-card-text>
          <v-row align="center" class="mx-0">
            <v-rating :value="4.5" color="amber" dense half-increments readonly size="14"></v-rating>

            <div class="grey--text ml-4">4.5 (413)</div>
          </v-row>

          <div class="my-4 subtitle-1">$ • Italian, Cafe</div>

          <div>{{item.content}}</div>
        </v-card-text>

        <v-divider class="mx-4"></v-divider>

        <v-card-title>Tonight's availability</v-card-title>

        <v-card-actions>
          {{ item.heart }}
          <v-btn icon color="black">
            <v-icon>mdi-heart</v-icon>
          </v-btn>
          <v-btn icon color="pink">
            <v-icon>mdi-heart</v-icon>
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
    this.$store.state.renderNum = 1;
  },
  updated() {
    this.loadUntilViewportIsFull();
  },
  methods: {
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
      this.$router.go();
    },
  },
  data: () => {
    return {
    }
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
.card {
  display: inline-block;
}
.writeBtn {
  color:greenyellow !important;
}
</style>
