<template>
  <div>
    <div class="container mt-3">
      <v-sheet class="mx-auto" elevation="8" max-width="800">
        <v-slide-group v-model="model" class="pa-4" show-arrows>
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
          <v-sheet v-if="model != null" color="grey lighten-4" height="180" tile>
            <v-row class="fill-height" align="center" justify="center">
              <h3 class="title">template {{ model+1 }}</h3>
            </v-row>
          </v-sheet>
        </v-expand-transition>
      </v-sheet>

      <div class="input-group flex-nowrap mt-3">
        <div class="input-group-prepend">
          <span class="input-group-text" id="addon-wrapping">블로그Get</span>
        </div>
        <input
          v-model="selectedData.blogname"
          type="text"
          class="form-control"
          placeholder="블로그 이름을 입력하세요."
          aria-label="Username"
          aria-describedby="addon-wrapping"
        />
      </div>
      <!-- <div class="input-group mb-3 mt-2">
        <div class="input-group-prepend">
          <label class="input-group-text" for="inputGroupSelect01">디자인Get</label>
        </div>
        <select v-model="selectedData.template_num" class="custom-select" id="inputGroupSelect01">
          <option selected>원하는 template을 고르세요.</option>
          <option value="1">template1</option>
          <option value="2">template2</option>
          <option value="3">template3</option>
        </select>
      </div>-->
      <!-- <button @click="moveToblog(selectedData)" class="ml-1 B-CreateBtn">Get</button> -->
      <v-btn @click="createBlog(selectedData)" x-large rounded outlined color="success" class='mt-3'>시작하기</v-btn>
      <button @click="test(selectedData)">test</button>
    </div>
  </div>
</template>

<script>
// import Main from '@/views/home/Main.vue'
// import BlogList from '@/components/main/BlogList.vue'
import { mapActions } from "vuex";

export default {
  name: "blogcreate",
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
        template_num: this.model,
        blogname: "",
        bid: "",
        uid: this.$store.state.uid.uid,
        visitors_num: 0,
      },
    };
  },
  methods: {
    ...mapActions(["createBlog"]),
    test(payload) {
      console.log(payload)
    }
  },
  mounted() {
    this.$store.state.renderNum = 0;
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