<template>
  <div>
    <i class="fas fa-bars fa-lg" @click.stop="drawer = !drawer"></i>
    <b-sidebar class="side w-75 xl" bg-variant="dark" title="Menu" text-variant="light" v-model="drawer">
      <div class="px-3 py-2 menu">
      <div>
        <h4 class="recentposts"><strong>최신글</strong></h4>
        <div v-for="(item,index) in newPosts" :key="index">
          <div v-if="index<5">
            <router-link :to="'/read3?pno='+item.pno" class="routerlink">
              {{ item.title }}
            </router-link>
          </div>
        </div>
      </div>
      <div>
        <p>태그</p> 
      </div>
      <div class="d-flex justify-content-around mb-4">
          <router-link :to="{ name:'List3', params:{bid: this.$store.state.bid} }"><i class="fas fa-list"></i></router-link>
          <router-link :to="{ name:'Create3', params:{bid: this.$store.state.bid} }"><i class="fas fa-pen"></i></router-link>
          <router-link :to="{ name:'VisitCreate3', params:{bid: this.$store.state.bid} }"><i class="fas fa-book"></i></router-link>
          <router-link :to="{ name:'UpdateTmp3', params:{bid: this.$store.state.bid} }"><i class="fas fa-sync-alt"></i></router-link>
        </div>
      </div>
    </b-sidebar>
    </div>
</template>

<script>
import { mapGetters } from "vuex";
  export default {
    name: 'Sidebar',
    data () {
      return {
        drawer: null,

      }
    },
    computed: {
    ...mapGetters(["newPosts"]),

    },
    mounted() {
    this.$store.state.renderNum = 3;
  },
  }
</script>


<style scoped>
i:hover {
  cursor: pointer;
}

i {
  color: white;
}
.fa-bars {
  position: fixed;
  top:20px;
  left: 20px;
  z-index: 100;
  color:white;
}
/* .b-sidebar {
  width:1000px !important;
  opacity: 0.7;
  background-color: black !important;
} */
/* .side {
  width:1000px !important;
  opacity: 0.7 ;
  /* background-color: black !important; */
  /* z-index: 100; }*/
.side {
  width:1000px !important;
  opacity: 0.9;
}
.menu {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100%;
}
.recentposts {
  color:#95a2c2;
}
.routerlink {
  color:white;;
}

</style>