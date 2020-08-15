<template>
  <div>
    <i class="fas fa-bars fa-lg" @click.stop="drawer = !drawer"></i>
    <b-sidebar class="side w-75 xl" bg-variant="dark" title="Menubar" text-variant="light" v-model="drawer">
      <div class="px-3 py-2">
        <p>
          Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis
          in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.
        </p>
     
      </div>
    </b-sidebar>
    </div>
</template>

<script>
  export default {
    name: 'Sidebar',
    data () {
      return {
        drawer: null,

      }
    },
  }
</script>


<style scoped>
i:hover {
  cursor: pointer;
}

.fa-bars {
  position: fixed;
  top:30px;
  left: 20px;
  z-index: 100;
  color:white;
}
/* .b-sidebar {
  width:1000px !important;
  opacity: 0.7;
  background-color: black !important;
} */
/* .side {
  width:1000px !important;
  opacity: 0.7 ;
  /* background-color: black !important; */
  /* z-index: 100; }*/
.side {
  width:1000px !important;
  opacity: 0.7;
}
</style>