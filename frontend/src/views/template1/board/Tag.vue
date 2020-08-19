<template>
  <div>
    <h1>태그 페이지입니다.</h1>
    <b-button pill variant="outline-secondary">Button</b-button>
  </div>
</template>

<script>
export default {
  name: "Tag",
  mounted() {
    this.$store.state.renderNum = 1;
  },
}
</script>

<style>

</style>