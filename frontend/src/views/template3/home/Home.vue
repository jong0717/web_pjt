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
          <v-avatar color="light">
            <v-icon dark>mdi-account-circle</v-icon>
          </v-avatar>
          <h2>{{ blogname }}</h2>
         
        </div>
        <div class="d-flex justify-content-around mb-4">
          <router-link :to="{ name:Home, params: {uid:this.$store.state.uid.uid, bid:3 } }"><i class="fas fa-list"></i></router-link>
          <router-link class="router-link" to="/create"><i class="fas fa-pen"></i></router-link>
          <router-link to="/visitcreate"><i class="fas fa-book"></i></router-link>
          <router-link to="/user/mypage"><i class="fas fa-user-cog"></i></router-link>
        </div>
      </v-list>
    </v-navigation-drawer>

    <!-- <v-app-bar
      app
      color="indigo"
      dark
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>Application</v-toolbar-title>
    </v-app-bar> -->

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
    };
  },
  methods: {
    ...mapActions(["search"]),
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

</style>
