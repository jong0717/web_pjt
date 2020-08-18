<template>
  <!-- <table class="table table-sm table-bordered">
    <thead>
        <tr>
        <th scope="col">블로그이름</th>
        <th scope="col">테마</th>
        </tr>
    </thead>
    <tbody class="table" v-for='item in myblog' :key="item">
        <tr>
        <a style="color:black !important" @click="moveToBlog(item)"><td>{{ item.blogname }}</td></a>
        <td>{{ item.template_num }}</td>
        </tr>
    </tbody>
  </table>-->
  <table class="table table-bordered">
    <thead>
      <tr>
        <!-- <th scope="col">#</th> -->
        <th scope="col">블로그이름</th>
        <th scope="col">테마</th>
        <th scope="col">이동</th>
        <th scope="col">삭제</th>
      </tr>
    </thead>
    <tbody v-for="item in myblog" :key="item">
      <tr>
        <!-- <th scope="row">1</th> -->
        <td>{{ item.blogname }}</td>
        <td>{{ item.template_num }}</td>
        <td><i @click="moveToBlog(item)" class="far fa-caret-square-right"></i></td>
        <td><b-button @click="deleteBlog(item.bid)" variant="outline-danger">삭제하기</b-button></td>
      </tr>

    </tbody>
  </table>
</template>

<script>
import axios from "axios"
import { mapActions, mapState } from "vuex";
export default {
  data() {
    return {

    }},
  methods: {
    ...mapActions(["moveToBlog"]),
    deleteBlog(bid) {
      console.log(bid)
      axios.delete(`${this.$store.state.HOST}/api/blog/` + bid)
      .then((res) => {
        alert("삭제가 완료되었습니다")
        console.log(res)
        this.$router.go()
      })
      .catch((err) => {
        alert("삭제 처리시 에러가 발생했습니다.")
        console.log(err)
      })
    }
  },
  computed: {
    ...mapState(["myblog"]),
  },
};
</script>

<style scoped>
i:hover {
  cursor:pointer;
}
</style>