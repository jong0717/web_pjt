<template>
    <div>
      <hr>
      <h1 class="mt-2">소개</h1>
      <v-timeline>
        <v-timeline-item v-for="(history, i) in historys" :key="i" :color="history.color" small>
          <template v-slot:opposite>
            <span :class="`headline font-weight-bold ${history.color}--text`" v-text="history.year"></span>
          </template>
          <div class="py-4">
            <h2 :class="`headline font-weight-light mb-4 ${history.color}--text`">{{ history.title }}</h2>
            <div>{{ history.content }}</div>
          </div>
        </v-timeline-item>
      </v-timeline>
      <v-footer dark padless>
        <v-card flat tile class="indigo lighten-1 white--text text-center w-100">
          <v-card-text class="py-0">
            <v-btn v-for="icon in icons" :key="icon" class="mx-4 white--text" icon>
              <v-icon size="24px">{{ icon }}</v-icon>
            </v-btn>
          </v-card-text>

          <v-card-text
            class="white--text py-0"
          ><h2 class="mt-0"><strong>GetBlog.com</strong></h2>
          <p class="my-0">우) 광주 광산구 하남산단6번로 107</p>
          <p class="mb-0">TEL:02-1234-4566</p>
          </v-card-text>
          <v-divider></v-divider>
    
          <v-card-text class="white--text pt-0">
            COPYRIGHT (c) 2017-{{ new Date().getFullYear() }}. All rights reserved. 
          </v-card-text>
        </v-card>
      </v-footer>
    </div>
</template>

<script>
export default {
  name: "main",
  mounted() {
    this.$store.state.renderNum = 0;
  },
  data: () => ({
    // years: [
    //   {
    //     color: "cyan",
    //     year: "2017",
    //   },
    //   {
    //     color: "green",
    //     year: "2018",
    //   },
    //   {
    //     color: "pink",
    //     year: "2019",
    //   },
    //   {
    //     color: "amber",
    //     year: "2020",
    //   },
    // ],
    historys:[
      {
        title: "회사설립",
        content:"By 안재완,윤승재,홍순종,심유민,최재혁",
        color: "cyan",
        year: "2017",
      },
      {
        title: "GetBlog서비스시작",
        content:"템플릿 제공을 통한 블로그 생성 서비스",
        color: "green",
        year: "2018",
      },
      {
        title: "템플릿 업데이트",
        content:"템플릿 디자인 추가",
        color: "pink",
        year: "2019",
      },
      {
        title: "템플릿 변경 서비스 제작",
        content:"템플릿간 스킨 변경하여 여러가지 스타일의 블로그를 제공",
        color: "amber",
        year: "2020",
      },
    ],
    icons: [
        'mdi-facebook',
        // 'mdi-twitter',
        // 'mdi-linkedin',
        'mdi-instagram',
      ],
  }),
};
</script>

<style scoped>
img {
    width: 100%;
}

.startBtn {
    margin-bottom: 5vh;
    width: 100%;
    border-radius: 1rem !important;
}

</style>