<template>
  <v-app>
    <v-navigation-drawer v-model="drawer" app :src="background">
      <v-list dense>
        <!-- home -->
        <v-list-item link @click="moveToMain">
          <v-list-item-action>
            <v-icon>mdi-home</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>
              <div style="color:white;font-size:1.5em">Home</div>
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <!-- contact -->
        <v-list-item link>
          <v-list-item-action>
            <v-icon>mdi-email</v-icon>
          </v-list-item-action>

          <v-list-item-content>
            <v-list-item-title>
              <div style="color:white;font-size:1.5em">최근 쓴 글</div>
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <div v-for="(item, index) in newPosts" :key="index + '_posts'">
          <div v-if="index<4" class="my-4 subtitle-1" style="margin:0;">
            <router-link :to="'/read2?pno='+item.pno">
              <div class="listTitle">{{ item.title }}</div>
            </router-link>
            <div class="dat" style="color:#97fabf;font-size:1em ">{{getFormatDate(item.createDate)}}</div>
          </div>
        </div>

        <v-list-item>
          <v-list-item-action>
            <v-icon>mdi-pencil</v-icon>
          </v-list-item-action>

          <v-list-item-content>
            <v-list-item-title class="btn" @click="movePage">
              <div style="color:white;font-size:1.5em">글쓰기</div>
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item>
          <v-list-item-action>
            <v-icon>mdi-flag</v-icon>
          </v-list-item-action>

          <v-list-item-content>
            <router-link :to="{ name:'UpdateTmp2', params:{bid: this.$store.state.bid} }"><v-list-item-title class="btn">템플릿</v-list-item-title></router-link>
          </v-list-item-content>
          
        </v-list-item>

      </v-list>
    </v-navigation-drawer>
    <!-- navbar -->
    <v-app-bar app color="#19ce60" dark >
      <div class="d-flex justify-space-between align-items-center">
          <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
          <v-toolbar-title class='pl-0'><h1>{{ blogname }}</h1></v-toolbar-title>
        <div>
          <form class="form-inline my-2 my-lg-0">
            <input
              class="form-control fas"
              type="text"
              placeholder="Search"
              aria-label="Search"
              v-model="searchInput"
            />
            <v-btn @click="search(searchInput)" outlined class="searchBtn px-0 mx-1" tile color="dark" dark>
              검색
            </v-btn>
            <v-btn outlined @click="logout" class="mx-1">로그아웃</v-btn>
          </form>
        </div>
        </div>
    </v-app-bar>

    <v-main class="mainbg">
      <div class="container list">
        <!-- 시작 -->

        <router-view />
      </div>
    </v-main>
  </v-app>
</template>

<script src="https://cdnjs.cloudflare.com/ajax/libs/scrollmonitor/1.2.0/scrollMonitor.js"></script>
<script>
import moment from "moment";
import { mapGetters, mapActions, mapState } from "vuex";
import Login from "@/views/user/Login.vue";

export default {
  name: "Home2",
  components: {
    Login,
  },
  computed: {
    ...mapGetters(["posts", "newPosts", "isLogIn"]),
    ...mapState(["blogname"]),
  },
  mounted() {
    this.addScrollWatcher();
    // this.$store.dispatch("getPOSTs", this.$route.params.bid)
    this.$store.state.renderNum = 2;
  },
  updated() {
    this.loadUntilViewportIsFull();
  },
  methods: {
    ...mapActions(["logout", "search"]),

    moveToMain() {
      this.$router.push({ name: "List2", params:{bid:this.$store.state.bid} });
    },
    movePage() {
      this.$router.push({ name: "Create2", params:{bid:this.$store.state.bid} });
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
      background:
        "https://images.unsplash.com/photo-1494438639946-1ebd1d20bf85?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1047&q=80",
      drawer: null,
      searchInput: "",
    };
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Gaegu&display=swap");
/* .v-toolbar__content {
  display: flex;
  justify-content: space-between !important;
} */

* {
  margin: 0px;
  padding: 0px;
  font-family: "Gaegu", cursive;
  font-size: 1.2em;
}
.dat {
  display: flex;
  justify-content: flex-end;
  font-size: 0.7em;
  margin-right: 1.5em;
}
.listBlock {
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
  display: inline-block;
  width: 40%;
}
.mynavi {
  background-color: #cee3ca;
}
.mainbg {
  background-color: #ecebe8;
}
.searchBtn {
  min-width: 36px !important;
  height: 36px;
}
.my-4 {
  margin-top: 0 !important;
  margin-bottom: 0 !important;
}
.listTitle {
  color: #97fabf;
  display: flex;
  justify-content: flex-start;
  margin-left: 2em;

}
</style>
