<template>
  <div>
    <h2 class="text-align-left">전체글</h2>
      <hr>
        <v-card class="elevation-2 ">
          <v-card-title class="headline">Lorem ipsum</v-card-title>
          <v-card-text>
            Lorem ipsum dolor sit amet, no nam oblique veritus. Commune scaevola imperdiet nec ut, sed euismod convenire principes at. Est et nobis iisque percipit, an vim zril disputando voluptatibus, vix an salutandi sententiae.
          </v-card-text>
        </v-card>
  </div>
</template>

<script>
  export default {
    data: () => ({

    }),
    mounted() {
      this.$store.state.renderNum = 3;
    },
  }
</script>

<style scoped>

</style>