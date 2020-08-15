<template>
  <!-- <v-app id="inspire"> -->
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
          <h2>홍뽀로블로그</h2>
         
        </div>
        <div class="d-flex justify-content-around mb-4">
          <router-link to="/create"><i class="fas fa-pen"></i></router-link>
          <router-link to="/visitcreate"><i class="fas fa-book"></i></router-link>
          <a href=""><i class="fas fa-code"></i></a>
          <i class="fas fa-user-cog"></i>
        </div>
      </v-list>
    </v-navigation-drawer>
    <!-- </v-app> -->
</template>

<script>
import { mapActions } from "vuex";
export default {
  name:'Navigation',
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
    bg() {
      return this.background
        ? "https://i.pinimg.com/originals/5b/4f/12/5b4f120836f452f0f916a7eb885fdd98.jpg"
        : undefined;
    },
  },
}
</script>

<style>

</style>