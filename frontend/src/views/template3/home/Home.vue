<template>
    <div>
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
          class="py-0 backimg"
        >
        <div>
          <v-list-item two-line :class="miniVariant && 'px-0'">

            <v-list-item-content >

            </v-list-item-content>

            <v-text-field :rules="rules" placeholder="Search" v-model="searchInput" class='SEARCH'></v-text-field>
            <i class="fas fa-search" @click="search(searchInput)"></i>
          </v-list-item>
          <v-divider></v-divider>
          </div>

          <div>
            <v-avatar color="light">
              <v-icon dark>mdi-account-circle</v-icon>
            </v-avatar> 
            <h2>홍뽀로블로그</h2>
          <!-- <v-list-item
            v-for="item in items"
            :key="item.title"
            link
            
          >
            <v-list-item-icon>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>
            
            <v-list-item-content>
              <v-list-item-title><h1>{{ item.title }}</h1></v-list-item-title>
            </v-list-item-content>
          </v-list-item> -->
          </div>
          <div class='d-flex justify-content-around mb-4'>
            <i class="fas fa-pen"></i>
            <i class="fas fa-book"></i>
            <i class="fas fa-code"></i>
            <i class="fas fa-user-cog"></i>
          </div>
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
        // { title: "홍뽀로블로그", icon: "mdi-account-circle" },
        // { title: "Photos", icon: "mdi-image" },
        // { title: "About", icon: "mdi-help-box" },
      ],
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
i {
  color:white;
}

.backimg {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100%;
}
/* .footicons {
  
} */

</style>
