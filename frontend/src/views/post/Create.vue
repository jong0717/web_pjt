<template>
  <div>
    <create-form type="create" />
  </div>
</template>

<script>
import CreateForm from '@/components/include/Form.vue';
import { mapMutations } from 'vuex';
export default {
  name: 'Create',
  components: {
    CreateForm,
  },
  methods: {
    ...mapMutations(['setRenderNum'])
  },
  // mounted() {
  //   setRenderNum()
  // }
};
</script>

<style></style>
