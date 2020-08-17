<template>
  <v-app id="inspire">
    <v-navigation-drawer v-model="drawer" app>
      <v-list dense>
        <!-- home -->
        <v-list-item link>
          <v-list-item-action>
            <v-icon>mdi-home</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item-content>
        </v-list-item>

        <!-- contact -->
        <v-list-item link>
          <v-list-item-action>
            <v-icon>mdi-email</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>최근 쓴 글</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-asadfasdf div v-for="(item, index) in newPosts" :key="index + '_posts'">
          <router-link :to="'/read?pno='+item.pno">
            <p v-if="index<5" class="recently my-4 subtitle-1">{{item.title}}</p>
            
          </router-link>
        </v-list-asadfasdf>
      </v-list>
    </v-navigation-drawer>

    <!-- navbar -->
    <v-app-bar app color="indigo" dark>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>Application</v-toolbar-title>
    </v-app-bar>

    <v-main>
      <div class="container list">
        <div class="row justify-content-around">
          <v-btn @click="movePage" large color="primary">글쓰기</v-btn>
          <v-btn @click="reload" large color="primary">전체 목록 보기</v-btn>
        </div>

        <!-- 시작 -->
        <b-media
          right-align
          class="m-4"
          vertical-align="center"
          v-for="(item, index) in newPosts"
          :key="index + '_posts'"
        >
          <template v-slot:aside>
            <b-img width="80" alt="placeholder" src="https://cdn.vuetifyjs.com/images/cards/cooking.png"></b-img>
          </template>

          <div v-for="(item, index) in newPosts" :key="index + '_posts'">
            <div v-if="index=0"></div>
            {{ item }}
          </div>
          <router-link :to="'/read?pno='+item.pno">
            <h5 class="mt-0 mb-1">{{ index+1 }}. {{item.title}}</h5>
            <!-- <h5 v-if="index%2==0" class="mt-0 mb-1">{{ index+1 }}. {{item.title}}</h5> -->
          </router-link>
          <p class="mb-0">
            {{ item.content }}
          </p>
        </b-media>
        <div v-if="!this.$store.state.searchFlag" id="bottomSensor"></div>
      </div>
    </v-main>
  </v-app>
</template>

<script src="https://cdnjs.cloudflare.com/ajax/libs/scrollmonitor/1.2.0/scrollMonitor.js"></script>
<script>
import moment from "moment";
import { mapGetters } from "vuex";

export default {
  name: "List",
  computed: {
    ...mapGetters(["posts", "newPosts"]),
  },
  mounted() {
    this.addScrollWatcher();
    this.$store.state.renderNum = 2;
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
      drawer: null,
    };
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
</style>
