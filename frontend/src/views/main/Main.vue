<template>
  <div>
    <div class="imgCon">
      <img
        class="infinite_rotating_logo"
        src="@/assets/Ain.png"
        alt="mainImage"
        style="position:absolute; left:50%; margin-left: -30em; width:60em; height:auto; z-index:1;"
      />
      <router-link to="/blogcreate">
        <img
          src="@/assets/blog2.png"
          alt="mainImage"
          style="position:absolute; transform:translateX(-50%); left:50%; top:28em;  width:25em; height:auto; z-index:2;"
        />
      </router-link>
      <div class="startBtn">
        <router-link to="/blogcreate">
          <button
            type="button"
            class="btn btn-outline-dark"
            style="position:absolute; top:43em; transform:translateX(-50%); left:50%;z-index:3;"
          >시작하기</button>
        </router-link>
      </div>
    </div>
    <div>
      <br />
      <div class="introduce">
        <hr />
        <h2>오늘의 블로그</h2>
        <div justify-content-center>
          <v-card class="recommendBlog elevation-16 my-4 mx-2" width="300">
            <router-link :to="{ name:'List', params:{bid: 1}}"><v-card-title class="headline">맛집라이프</v-card-title></router-link>
            <v-card-text>
              전국을 방방곡곡 돌아다니며 맛집을 찾아 리뷰하는 블로그입니다. 들어오셔서 확인해보세요!
              <div class="text-center mt-12">
                <v-rating
                  v-model="rating1"
                  :full-icon="fullIcon"
                  :half-icon="halfIcon"
                  :half-increments="halfIncrements"
                  :empty-icon="emptyIcon"
                  :color="color"
                  :background-color="bgColor"
                  :readonly="readonly"
                ></v-rating>
              </div>
            </v-card-text>
            <v-divider></v-divider>
          </v-card>
          <v-card class="recommendBlog elevation-16 my-4 mx-2" width="300">
            <router-link :to="{ name:'List2', params:{bid: 3}}"><v-card-title class="headline">알고리즘 풀이노트</v-card-title></router-link>
            <v-card-text>
              코딩테스트를 대비해 매일 하나씩 알고리즘 문제를 풀어 코드를 공유하는 블로그입니다. 
              <div class="text-center mt-12">
                <v-rating
                  v-model="rating2"
                  :full-icon="fullIcon"
                  :half-icon="halfIcon"
                  :half-increments="halfIncrements"
                  :empty-icon="emptyIcon"
                  :color="color"
                  :background-color="bgColor"
                  :readonly="readonly"
                ></v-rating>
              </div>
            </v-card-text>
            <v-divider></v-divider>
          </v-card>
          <v-card class="recommendBlog elevation-16 my-4 mx-2" width="300">
            <router-link :to="{ name:'List3', params:{bid: 2}}"><v-card-title class="headline">취미 블로그</v-card-title></router-link>
            <v-card-text>
              일상적인 내용, 저의 취미생활을 기록해놓은 블로그입니다. 감성 블로그를 지향합니다.
              <div class="text-center mt-12">
                <v-rating
                  v-model="rating3"
                  :full-icon="fullIcon"
                  :half-icon="halfIcon"
                  :half-increments="halfIncrements"
                  :empty-icon="emptyIcon"
                  :color="color"
                  :background-color="bgColor"
                  :readonly="readonly"
                ></v-rating>
              </div>
            </v-card-text>
            <v-divider></v-divider>
          </v-card>
        </div>
        <hr />
        <h2>소개</h2>
        <v-timeline>
          <v-timeline-item v-for="(history, i) in historys" :key="i" :color="history.color" small>
            <template v-slot:opposite>
              <span
                :class="`headline font-weight-bold ${history.color}--text`"
                v-text="history.year"
              ></span>
            </template>
            <div class="py-4">
              <h2
                :class="`headline font-weight-light mb-4 ${history.color}--text`"
              >{{ history.title }}</h2>
              <div>{{ history.content }}</div>
            </div>
          </v-timeline-item>
        </v-timeline>
      </div>
      <v-footer dark padless>
        <v-card flat tile class="indigo lighten-1 white--text text-center w-100">
          <v-card-text class="py-0">
            <v-btn v-for="icon in icons" :key="icon" class="mx-4 white--text" icon>
              <v-icon size="24px">{{ icon }}</v-icon>
            </v-btn>
          </v-card-text>

          <v-card-text class="white--text py-0">
            <h3 class="mt-0">
              <strong>GetBlog.com</strong>
            </h3>
            <p class="my-0">우) 광주 광산구 하남산단6번로 107</p>
            <p class="mb-0">TEL:02-1234-4566</p>
          </v-card-text>
          <v-divider></v-divider>

          <v-card-text
            class="white--text pt-0"
          >COPYRIGHT (c) 2017-{{ new Date().getFullYear() }}. All rights reserved.</v-card-text>
        </v-card>
      </v-footer>
    </div>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
export default {
  name: "main",
  mounted() {
    this.setRenderNum(0);
  },
  methods: {
    ...mapMutations(["setRenderNum"]),
  },
  data: () => ({
    emptyIcon: "mdi-star-outline",
    fullIcon: "mdi-star",
    halfIcon: "mdi-star-half-full",
    halfIncrements: true,
    hover: true,
    length: 5,
    rating1: 4.5,
    rating2: 4,
    rating3: 3.5,
    readonly: true,
    size: 24,
    dense: false,
    color: "yellow darken-3",
    bgColor: "yellow darken-3",

    historys: [
      {
        title: "회사설립",
        content: "By 안재완,윤승재,홍순종,조유민,최재혁",
        color: "cyan",
        year: "2017",
      },
      {
        title: "GetBlog서비스시작",
        content: "템플릿 제공을 통한 블로그 생성 서비스",
        color: "green",
        year: "2018",
      },
      {
        title: "템플릿 업데이트",
        content: "템플릿 디자인 추가",
        color: "pink",
        year: "2019",
      },
      {
        title: "템플릿 변경 서비스 제작",
        content: "템플릿간 스킨 변경하여 여러가지 스타일의 블로그를 제공",
        color: "amber",
        year: "2020",
      },
    ],
    icons: [
      "mdi-facebook",
      // 'mdi-twitter',
      // 'mdi-linkedin',
      "mdi-instagram",
    ],
  }),
};
</script>

<style scoped>
.recommendBlog {
  display: inline-block;
}
h2 {
  color: #283593;
}
img {
  width: 100%;
}
.introduce {
  margin-top: 57em;
}

.startBtn {
  margin-bottom: 5vh;
  width: 100%;
  border-radius: 1rem !important;
}

img.infinite_rotating_logo {
  animation: rotate_image 20s linear infinite;
  transform-origin: 50% 50%;
}

@keyframes rotate_image {
  100% {
    transform: rotate(360deg);
  }
}
</style>