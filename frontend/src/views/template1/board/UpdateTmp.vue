<template>
  <div>
    <div class="container mt-3">
      <v-sheet class="mx-auto" elevation="8" max-width="800">
        <v-slide-group v-model="selectedData.template_num" class="pa-4" show-arrows>
          <!-- <v-slide-item v-for="n in 3" :key="n"> -->
          <!-- <v-slide-item v-for="image in images" :key="image" v-slot:default="{ active, toggle }"> -->
          <v-slide-item v-slot:default="{ toggle }" class="ma-2" >
            <v-card
              @click="toggle"
              class="tmpcard ma-4"
            >
            <img src="@/assets/template/tmp1.png" alt="" class="imgitem">
<!-- 
              <v-row class="fill-height" align="center" justify="center">
                <v-scale-transition>
                  <v-icon v-if="active" color="white"  v-text="'mdi-close-circle-outline'"></v-icon>
                </v-scale-transition>
              </v-row> -->
            </v-card>
         </v-slide-item>
         <v-slide-item v-slot:default="{ toggle }">
            <v-card
              @click="toggle"
              class="ma-4"
            >
            <img src="@/assets/template/tmp2.png" alt="" class="imgitem">

              <!-- <v-row class="fill-height" align="center" justify="center">
                <v-scale-transition>
                  <v-icon v-if="active" color="white" v-text="'mdi-close-circle-outline'"></v-icon>
                </v-scale-transition>
              </v-row> -->
            </v-card>
         </v-slide-item>
         <v-slide-item v-slot:default="{ toggle }">
            <v-card
              @click="toggle"
              class="ma-4"
            >
            <img src="@/assets/template/tmp3.png" alt="" class="imgitem">
<!-- 
              <v-row class="fill-height " align="center" justify="center">
                <v-scale-transition>
                  <v-icon  class="XBtn" v-if="active" color="white" v-text="'mdi-close-circle-outline'"></v-icon>
                </v-scale-transition>
              </v-row> -->
            </v-card>
         </v-slide-item>
        </v-slide-group>

        <v-expand-transition>
          <v-sheet v-if="selectedData.template_num != null" color="grey lighten-4" height="180" tile>
            <v-row class="fill-height" align="center" justify="center">
              <h3 class="title">template {{ selectedData.template_num+1 }}</h3>
            </v-row>
          </v-sheet>
        </v-expand-transition>
      </v-sheet>
      <v-btn @click="updatetmp(selectedData.template_num)" x-large rounded outlined color="success" class='mt-3'>변경하기</v-btn>
    </div>
  </div>
</template>

<script>
// import Main from '@/views/home/Main.vue'
// import BlogList from '@/components/main/BlogList.vue'

export default {
  name: "UpdateTmp",
  components: {
    // BlogList,
  },
  data() {
    return {
      model:null,
      images:[
        "../../assets/template/tmp1.png","../../assets/template/tmp2.png","../../assets/template/tmp3.png",
      ],
      selectedData: {
        template_num: null,
        blogname: "",
        bid: "",
        uid: this.$store.state.uid.uid,
        visitors_num: 0,
      },
    };
  },
  methods: {
    updatetmp(num) {
      console.log(num+1)
      if (num+1 === 2) {
        this.$router.push({name:'List2', params:this.$store.state.bid})
      } else if (num+1 === 3) {
        this.$router.push({name:'List3', params:this.$store.state.bid})
      } else {
        alert('현재 사용하고 있는 템플릿과 같습니다.')
      }
    }
  },
  mounted() {
    this.$store.state.renderNum = 1;
  },
};
</script>

<style>
.B-CreateBtn {
  background-color: rgb(110, 92, 92);
  border: lightgray;
}
.imgitem {
  width: 220px;
  height: 220px;
}
.XBtn {
  z-index: 100;
}

</style>