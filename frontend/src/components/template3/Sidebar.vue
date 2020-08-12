<template>
  <!-- <v-sheet
    height="400"
    class="overflow-hidden"
    style="position: relative;"
  > -->
  <div>
    <v-container class="fill-height">
      <v-row
        align="center"
        justify="center"
        
      >
        <!-- <v-btn
          color=""
          dark
          @click.stop="drawer = !drawer"
           
        > -->
        <i class="fas fa-bars fa-lg" @click.stop="drawer = !drawer"></i>
        <!-- </v-btn> -->
      </v-row>
    </v-container>

    <v-navigation-drawer
      v-model="drawer"
      absolute
      temporary
    >
      <v-list-item>
        <v-list-item-avatar>
          <v-img src="https://randomuser.me/api/portraits/men/78.jpg"></v-img>
        </v-list-item-avatar>

        <v-list-item-content>
          <v-list-item-title>John Leider</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-list dense>

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
    </div>
  <!-- </v-sheet> -->
</template>

<script>
  export default {
    name: 'Sidebar',
    data () {
      return {
        drawer: null,
        items: [
          { title: 'Home', icon: 'dashboard' },
          { title: 'About', icon: 'question_answer' },
        ],
      }
    },
  }
</script>


<style>
i:hover {
  cursor: pointer;
}

.fa-bars {
  position: fixed;
  top:30px;
  left: 20px;
  z-index: 100;
  color:white;

}
</style>