<template>
  <div>
    <div class="imgCon">
      <img src="@/assets/main.png" alt="mainImage" />
      <div class="startBtn">
        <router-link to="/blogcreate">
          <button type="button" class="btn btn-outline-dark">시작하기</button>
        </router-link>
      </div>
    </div>
    <div>
      <h1>소개글</h1>
      <v-timeline>
        <v-timeline-item v-for="(year, i) in years" :key="i" :color="year.color" small>
          <template v-slot:opposite>
            <span :class="`headline font-weight-bold ${year.color}--text`" v-text="year.year"></span>
          </template>
          <div class="py-4">
            <h2 :class="`headline font-weight-light mb-4 ${year.color}--text`">Lorem ipsum</h2>
            <div>Lorem ipsum dolor sit amet, no nam oblique veritus. Commune scaevola imperdiet nec ut, sed euismod convenire principes at. Est et nobis iisque percipit, an vim zril disputando voluptatibus, vix an salutandi sententiae.</div>
          </div>
        </v-timeline-item>
      </v-timeline>
      <v-footer dark padless>
        <v-card flat tile class="indigo lighten-1 white--text text-center">
          <v-card-text>
            <v-btn v-for="icon in icons" :key="icon" class="mx-4 white--text" icon>
              <v-icon size="24px">{{ icon }}</v-icon>
            </v-btn>
          </v-card-text>

          <v-card-text
            class="white--text pt-0"
          >Phasellus feugiat arcu sapien, et iaculis ipsum elementum sit amet. Mauris cursus commodo interdum. Praesent ut risus eget metus luctus accumsan id ultrices nunc. Sed at orci sed massa consectetur dignissim a sit amet dui. Duis commodo vitae velit et faucibus. Morbi vehicula lacinia malesuada. Nulla placerat augue vel ipsum ultrices, cursus iaculis dui sollicitudin. Vestibulum eu ipsum vel diam elementum tempor vel ut orci. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.</v-card-text>

          <v-divider></v-divider>

          <v-card-text class="white--text">
            {{ new Date().getFullYear() }} —
            <strong>GetBlog</strong>
          </v-card-text>
        </v-card>
      </v-footer>
    </div>
  </div>
</template>

<script>
export default {
  name: "main",
  mounted() {
    this.$store.state.renderNum = 0;
  },
  data: () => ({
    years: [
      {
        color: "cyan",
        year: "1960",
      },
      {
        color: "green",
        year: "1970",
      },
      {
        color: "pink",
        year: "1980",
      },
      {
        color: "amber",
        year: "1990",
      },
      {
        color: "orange",
        year: "2000",
      },
    ],
    icons: [
        'mdi-facebook',
        'mdi-twitter',
        'mdi-linkedin',
        'mdi-instagram',
      ],
  }),
};
</script>

<style scoped>
.imgCon {
  /* width: 80vw; */
}
img {
  width: 100%;
}

.startBtn {
  margin-bottom: 5vh;
  width: 100%;
  border-radius: 1rem !important;
}
</style>