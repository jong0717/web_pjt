<template>
  <v-app id="inspire">
    <v-navigation-drawer
      v-model="drawer"
      app
      :color="color"
      :expand-on-hover="expandOnHover"
      :mini-variant="miniVariant"
      :right="right"
      :permanent="permanent"
      :src="bg"
      absolute
      dark
      class='Nav'
    >
      <v-list dense nav class="py-0 backimg">
        <div>
          <v-list-item two-line :class="miniVariant && 'px-0'">
          <!-- <i class="fas fa-bars fa-lg" @click.stop="drawer = !drawer"></i> -->
            <v-list-item-content></v-list-item-content>

            <v-text-field :rules="rules" placeholder="Search" v-model="searchInput" class="SEARCH"></v-text-field>
            <i class="fas fa-search" @click="search(searchInput)"></i>
          </v-list-item>
          <v-divider></v-divider>
        </div>

        <div>
          <h1 class="text-white">{{ blogname }}</h1>
          <v-avatar color="light">
            <v-icon dark>mdi-account-circle</v-icon>
          </v-avatar>
          {{this.nickname}}
        </div>
        <div class="d-flex justify-content-around mb-4">
          <router-link :to="{ name:'List3', params:{bid: this.$store.state.bid} }"><i class="fas fa-list"></i></router-link>
          <router-link :to="{ name:'Create3', params:{bid: this.$store.state.bid} }"><i class="fas fa-pen"></i></router-link>
          <router-link :to="{ name:'VisitCreate3', params:{bid: this.$store.state.bid} }"><i class="fas fa-book"></i></router-link>
          <router-link :to="{ name:'UpdateTmp3', params:{bid: this.$store.state.bid} }"><i class="fas fa-sync-alt"></i></router-link>
        </div>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar
      app
      dark
      class="appbar d-flex justify-content-end">
      <v-btn outlined @click="moveToPage" class="mx-1"><i class="fas fa-home"></i></v-btn>
      <router-link to="/user/mypage"><v-btn outlined class="mx-1"><i class="fas fa-user-cog"></i></v-btn></router-link>
      <v-btn outlined @click="logout" class="LogoutBtn mx-1">로그아웃</v-btn>
    </v-app-bar>

    <v-main>
      <v-container
        class="fill-height"
        fluid
      >
        <v-row
          align="center"
          justify="center"
        >
          <v-col class="text-center">

            <Sidebar/>
            <!-- <RecentList /> -->
            <router-view />
          </v-col>
        </v-row>
      </v-container> 
    </v-main>
  </v-app>
</template>

<script>
import Sidebar from "@/components/template3/Sidebar.vue";
// import BootstrapSidebar from '@/components/template3/BootstrapSidebar.vue'
// import RecentList from "@/views/template3/post/RecentList.vue";
import { mapActions, mapState } from "vuex";
export default {
  name: "home",
  components: {
    Sidebar,
    // BootstrapSidebar,
    // RecentList,
  },
  data() {
    return {
      searchInput: "",
      // right: false,
      // permanent: true,
      // miniVariant: false,
      // expandOnHover: false,
      background: true,
      bid: this.$route.params.bid,
      nickname: "",
    };
  },
  methods: {
    ...mapActions(["logout","search"]),
    moveToPage() {
      this.$router.push({ name:'Main' });
    },
    getNickname() {
      this.$http
        .get(
          `${this.$store.state.HOST}/api/post/detail/${this.$route.query.pno}`
        )
        .then(({ data }) => {
          this.nickname = data;
        })
        .catch(() => {
          console.log("에러가 발생했습니다.");
        });
    },
  },
  computed: {
    ...mapState(['blogname']),
    bg() {
      return this.background
        ? "https://i.pinimg.com/originals/5b/4f/12/5b4f120836f452f0f916a7eb885fdd98.jpg"
        : undefined;
    },
  },
  mounted() {
    this.$store.state.renderNum = 3;
  },
  created() {
    this.getNickname();
  },
};
</script>


<style scoped>
/* #background {
  position: relative;
  top: 0;
  left: 0;

  /* Preserve aspet ratio */
/* min-width: 100%;
  min-height: 100%;
} */
/* .v-toolbar__content {
  display: flex;
  justify-content: space-between;
} */
.LogoutBtn {
  padding:0 8px !important;
}
.v-btn:not(.v-btn--round).v-size--default {
  min-width: 36px !important; 
  padding: 0;
}
.searchBtn {
  min-width: 36px !important;
  height: 36px;
}
.SEARCH {
  width: 60%;
  height: 60%;
}
i {
  color: white;
}

.backimg {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100%;
}
.Nav {
  position:fixed;
}
.appbar {
  background-color:#4d6a8c !important;
  height: 70px;
}
.footer {
  background-color:"#c7a0a4"
}
</style>
