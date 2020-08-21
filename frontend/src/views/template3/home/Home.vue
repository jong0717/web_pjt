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
          <router-link :to="{ name:'List3', params:{bid: this.$store.state.bid} }"><svg width="1em" style="color:white" height="1em" viewBox="0 0 16 16" class="bi bi-list-ul" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" d="M5 11.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm-3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm0 4a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
          </svg></router-link>
          <router-link v-if="this.$store.state.uid.uid==this.$store.state.bloguid" :to="{ name:'Create3', params:{bid: this.$store.state.bid} }"><svg width="1em" style="color:white" height="1em" viewBox="0 0 16 16" class="bi bi-pencil" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" d="M11.293 1.293a1 1 0 0 1 1.414 0l2 2a1 1 0 0 1 0 1.414l-9 9a1 1 0 0 1-.39.242l-3 1a1 1 0 0 1-1.266-1.265l1-3a1 1 0 0 1 .242-.391l9-9zM12 2l2 2-9 9-3 1 1-3 9-9z"/>
            <path fill-rule="evenodd" d="M12.146 6.354l-2.5-2.5.708-.708 2.5 2.5-.707.708zM3 10v.5a.5.5 0 0 0 .5.5H4v.5a.5.5 0 0 0 .5.5H5v.5a.5.5 0 0 0 .5.5H6v-1.5a.5.5 0 0 0-.5-.5H5v-.5a.5.5 0 0 0-.5-.5H3z"/>
          </svg></router-link>
          <router-link :to="{ name:'VisitCreate3', params:{bid: this.$store.state.bid} }"><svg width="1em" style="color:white" height="1em" viewBox="0 0 16 16" class="bi bi-book" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" d="M3.214 1.072C4.813.752 6.916.71 8.354 2.146A.5.5 0 0 1 8.5 2.5v11a.5.5 0 0 1-.854.354c-.843-.844-2.115-1.059-3.47-.92-1.344.14-2.66.617-3.452 1.013A.5.5 0 0 1 0 13.5v-11a.5.5 0 0 1 .276-.447L.5 2.5l-.224-.447.002-.001.004-.002.013-.006a5.017 5.017 0 0 1 .22-.103 12.958 12.958 0 0 1 2.7-.869zM1 2.82v9.908c.846-.343 1.944-.672 3.074-.788 1.143-.118 2.387-.023 3.426.56V2.718c-1.063-.929-2.631-.956-4.09-.664A11.958 11.958 0 0 0 1 2.82z"/>
            <path fill-rule="evenodd" d="M12.786 1.072C11.188.752 9.084.71 7.646 2.146A.5.5 0 0 0 7.5 2.5v11a.5.5 0 0 0 .854.354c.843-.844 2.115-1.059 3.47-.92 1.344.14 2.66.617 3.452 1.013A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.276-.447L15.5 2.5l.224-.447-.002-.001-.004-.002-.013-.006-.047-.023a12.582 12.582 0 0 0-.799-.34 12.96 12.96 0 0 0-2.073-.609zM15 2.82v9.908c-.846-.343-1.944-.672-3.074-.788-1.143-.118-2.387-.023-3.426.56V2.718c1.063-.929 2.631-.956 4.09-.664A11.956 11.956 0 0 1 15 2.82z"/>
          </svg></router-link>
          <router-link :to="{ name:'UpdateTmp3', params:{bid: this.$store.state.bid} }"><svg width="1em" style="color:white"  height="1em" viewBox="0 0 16 16" class="bi bi-arrow-repeat" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" d="M2.854 7.146a.5.5 0 0 0-.708 0l-2 2a.5.5 0 1 0 .708.708L2.5 8.207l1.646 1.647a.5.5 0 0 0 .708-.708l-2-2zm13-1a.5.5 0 0 0-.708 0L13.5 7.793l-1.646-1.647a.5.5 0 0 0-.708.708l2 2a.5.5 0 0 0 .708 0l2-2a.5.5 0 0 0 0-.708z"/>
            <path fill-rule="evenodd" d="M8 3a4.995 4.995 0 0 0-4.192 2.273.5.5 0 0 1-.837-.546A6 6 0 0 1 14 8a.5.5 0 0 1-1.001 0 5 5 0 0 0-5-5zM2.5 7.5A.5.5 0 0 1 3 8a5 5 0 0 0 9.192 2.727.5.5 0 1 1 .837.546A6 6 0 0 1 2 8a.5.5 0 0 1 .501-.5z"/>
          </svg></router-link>
        </div>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar
      app
      dark
      class="appbar d-flex justify-content-end">
      <v-btn outlined @click="moveToPage" class="mx-1"><svg style="color:white" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-house-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M8 3.293l6 6V13.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5V9.293l6-6zm5-.793V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z"/>
  <path fill-rule="evenodd" d="M7.293 1.5a1 1 0 0 1 1.414 0l6.647 6.646a.5.5 0 0 1-.708.708L8 2.207 1.354 8.854a.5.5 0 1 1-.708-.708L7.293 1.5z"/>
</svg></v-btn>
      <router-link to="/user/mypage"><v-btn outlined class="mx-1"><svg width="1em" style="color:white;" height="1em" viewBox="0 0 16 16" class="bi bi-person-lines-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm7 1.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5zm-2-3a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm0-3a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm2 9a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5z"/>
</svg></v-btn></router-link>
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
@font-face {
    font-family: 'ImcreSoojin';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.3/ImcreSoojin.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

* {
  font-family: 'ImcreSoojin';
}

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
