<template>
  <div>
    <create-form type="create" />
  </div>
</template>

<script>
import CreateForm from '@/components/include/Form.vue';
export default {
  name: 'Create',
  components: {
    CreateForm,
  },
  methods: {
    
  },
  mounted() {
    this.$store.state.renderNum = 3;
  }
};
</script>

<style></style>
