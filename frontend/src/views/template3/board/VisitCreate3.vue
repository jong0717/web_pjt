<template>
  <div class="pt-5" id="visit">
    <div class="form-group">
      <!-- <label for="exampleFormControlTextarea1" class="font-weight-bold text-dark">방명록</label> -->
      <h6>
        <strong>GuestBook</strong>
      </h6>
      <!-- <hr class="m-auto"> <br> -->
      <textarea
        class="form-control mt-4"
        id="exampleFormControlTextarea1"
        rows="3"
        v-model="content"
        placeholder="여러분의 소중한 댓글을 입력해 주세요."
      ></textarea>
    </div>
    <div class="text-right">
      <!-- <button type="submit" class="btn btn-dark tile" @click="createVisit">등록</button> -->
      <v-btn class="ma-2 text-white" color="#9aa6c0" @click="createVisit">등록</v-btn>
    </div>
    <!-- <VisitPage3 :visitlist="visitlist" /> -->
    <p>글목록</p>
    <ul v-for="(item,index) in guestbooks" :key="index +'_guestbooks'" class="mylist">
      <hr />
      <li>
        <div class="d-flex justify-content-between">
          <div>
            <b-avatar variant="dark mr-3" size="lg"></b-avatar>
            <span class="nickname">
              <a href="#" class="text-dark">{{ item.writerNickname }}</a>
            </span>
            <!-- <span class="date ml-5 text-secondary">{{getFormatDate(item.createTime)}}</span> -->
            <p>등록일 : {{ createDate | moment('YYYY-MM-DD HH:mm:ss') }}</p>
          </div>
          <span class="control">수정/삭제</span>
        </div>
        <div class="d-flex justify-content-around">
          <span>
            <p class="my-0">
              {{ item.content }}
              <br />
            </p>
          </span>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
// import axios from "axios";
// import VisitPage3 from "@/views/template3/board/VisitPage3.vue";
import moment from "moment";
export default {
  name: "VisitCreate3",
  data() {
    return {
      content: "",
      uid: this.$store.state.uid.uid,
      bid: this.$route.params.bid,
      // create_date:'',
      guestbooks:[],
    };
  },
  components: {
    // VisitPage3,
  },
  methods: {
    createVisit() {
      console.log("click");
      this.$http
        .post(`${this.$store.state.HOST}/guestbook/insert`, {
          blogId: this.$route.params.bid,
          content: this.content,
          writerUid: this.$store.state.uid.uid,
          createTime:"2020-08-20T04:12:32.183Z",
        })
        .then((res) => {
          console.log('등록');
          console.log(res);
          this.getGuestBooks()
          // this.$router.go();
        })
        .catch((err) => {
          console.log(err);
        });
      // this.visitlist.push(this.content)
      // this.content = null
    },
    getGuestBooks() {
      console.log('불러완')
        this.$http.get(`${this.$store.state.HOST}/guestbook/list/?blogId=${this.bid}`)
        .then((res) => {
            console.log(res);
            this.guestbooks = res.data;
        })
        .catch((err) => {
            console.log(err)
            console.log("에러가 발생했습니다.");
        })
    },
    getFormatDate(createTime) {
      return moment(new Date(createTime)).format("YYYY.MM.DD HH:MM:SS");
    },
  },
  mounted() {
    this.$store.state.renderNum = 3;
  },
  created() {
    this.getGuestBooks();
  },
};
</script>

<style scoped>
#visit {
  width: 90%;
}
h6 {
  text-align: left;
}
.mylist {
    list-style: none;
    padding-inline-start: 0px;
}

p {
    display: block;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
}
</style>