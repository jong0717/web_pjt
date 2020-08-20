<template>
  <table class="table table-bordered">
    <thead>
      <tr>
        <th scope="col">블로그이름</th>
        <th scope="col">테마</th>
        <th scope="col">이동</th>
        <th scope="col">삭제</th>
      </tr>
    </thead>
    <tbody v-for="item in myblog" :key="item">
      <tr>
        <td>{{ item.blogname }}</td>
        <td>{{ item.template_num }}</td>
        <td>
          <i @click="moveToBlog(item)" class="far fa-caret-square-right"></i>
        </td>
        <p class="deleteBtn" @click="deleteBlog(item.bid)">삭제</p>
        <!-- <v-bottom-sheet v-model="dialog">
          <v-sheet class="text-center" height="200px">
            <v-btn class="mt-6" text color="red" @click="dialog = !dialog">close</v-btn>
            <div class="py-3">삭제하면 블로그에 있는 모든 정보가 사라집니다. 삭제하시겠습니까?</div>
            <b-button @click="deleteBlog(item.bid)" variant="outline-danger">삭제하기</b-button>
          </v-sheet>

        </v-bottom-sheet> -->
        <!-- <v-bottom-sheet v-model="sheet" persistent>
          <template v-slot:activator="{ on, attrs }">
            <p class="deleteBtn" v-bind="attrs" v-on="on">삭제</p>
          </template>
          <v-sheet class="text-center" height="200px">
            <v-btn class="mt-6" text color="error" @click="sheet = !sheet">close</v-btn>
            <div class="my-3">삭제하면 블로그에 있는 모든 정보가 사라집니다. 삭제하시겠습니까?</div>
            <b-button @click="deleteBlog(item.bid)" variant="outline-danger">삭제하기</b-button>
          </v-sheet>
        </v-bottom-sheet>-->
      </tr>
    </tbody>
  </table>
</template>

<script>
import axios from "axios";
import { mapActions, mapState } from "vuex";
export default {
  data() {
    return {
      dialog: false,
      // sheet:false,
    };
  },
  methods: {
    ...mapActions(["moveToBlog"]),
    deleteBlog(bid) {
      console.log(bid);
      axios
        .delete(`${this.$store.state.HOST}/api/blog/` + bid)
        .then((res) => {
          alert("삭제가 완료되었습니다");
          console.log(res);
          this.$router.go();
        })
        .catch((err) => {
          alert("삭제 처리시 에러가 발생했습니다.");
          console.log(err);
        });
    },
    showModal() {
      this.$refs['my-modal'].show()
    },
    hideModal() {
      this.$refs['my-modal'].hide()
    },
    toggleModal() {
      // We pass the ID of the button that we want to return focus to
      // when the modal has hidden
      this.$refs['my-modal'].toggle('#toggle-btn')
    },
  },
  computed: {
    ...mapState(["myblog"]),
  },
};
</script>

<style scoped>
i:hover {
  cursor: pointer;
}
.deleteBtn {
  margin-top: 0px !important;
  margin-bottom: 0px;
  padding: 12px;
  color: red;
}
p:hover {
  cursor: pointer;
}
</style>