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
            <v-row class="fill-height introTmp">
              <h3 class="title">template {{ selectedData.template_num+1 }}</h3>
              <div>
                <p v-if="selectedData.template_num==0">맛집과 여행을 좋아하는 사람들이 사용하기 좋은 폼을 제공하는 템플릿입니다.<br> 당신만의 맛집과 여행지들을 공유해보세요.</p>
                <p v-else-if="selectedData.template_num+1==2">당신이 좋아하는 사진들을 이미지 슬라이드로 제공합니다. <br>좋아하는 사진이나 보이고 싶은 사진들로 블로그를 꾸며보세요.</p>
                <p v-else>사용자가 쓴 글을 타임라인처럼 한 눈에 볼 수 있습니다. 취미나 글들을 자유롭게 쓰고 공유해보세요.</p>
              </div>
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
        template_num: null,
        blogname: "",
        bid: "",
        uid: this.$store.state.uid.uid,
        visitors_num: 0,
      },
    };
  },
  methods: {
    ...mapActions(["createBlog"]),
  },
  mounted() {
    this.$store.state.renderNum = 0;
  },
};
</script>

<style scoped>
.introTmp {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
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