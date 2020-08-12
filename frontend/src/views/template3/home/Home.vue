<template>
    <div class="backimg">
      <v-navigation-drawer
        v-model="drawer"
        :color="color"
        :expand-on-hover="expandOnHover"
        :mini-variant="miniVariant"
        :right="right"
        :permanent="permanent"
        :src="bg"
        absolute
        dark

      >
        <v-list
          dense
          nav
          class="py-0"
        >
          <v-list-item two-line :class="miniVariant && 'px-0'">
            <!-- <v-list-item-avatar>
              <img src="https://randomuser.me/api/portraits/men/81.jpg">
            </v-list-item-avatar> -->
            <!-- <i class="fas fa-bars size:2x"></i> -->
            <v-list-item-content >
              <!-- <v-list-item-title>Application</v-list-item-title>
              <v-list-item-subtitle>Subtext</v-list-item-subtitle> -->
            </v-list-item-content>
              <!-- <input
              class="form-control searchInput"
              type="text"
              placeholder="Search"
              aria-label="Search"
              v-model="searchInput"
            /> -->
            <v-text-field :rules="rules" placeholder="Search" v-model="searchInput" class='SEARCH'></v-text-field>
            <i class="fas fa-search" @click="search(searchInput)"></i>
          </v-list-item>

          <v-divider></v-divider>

          <v-list-item
            v-for="item in items"
            :key="item.title"
            link
          >
            <v-list-item-icon>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>

            <v-list-item-content>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-navigation-drawer>
      <Sidebar/>
      </div>
</template>

<script>
import Sidebar from '@/components/template3/Sidebar.vue'
import { mapActions } from "vuex"
export default {
  name: 'home',
  components: {
    Sidebar
  },
  data() {
    return {
      searchInput:'',
      drawer: true,
      items: [
        { title: "Dashboard", icon: "mdi-view-dashboard" },
        { title: "Photos", icon: "mdi-image" },
        { title: "About", icon: "mdi-help-box" },
      ],
      color: "primary",
      colors: ["primary", "blue", "success", "red", "teal"],
      right: false,
      permanent: true,
      miniVariant: false,
      expandOnHover: false,
      background: true,
    };
  },
  methods: {
    ...mapActions(["search"])
  },
  computed: {
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
  width:60%;
  height: 60%;
}
</style>
