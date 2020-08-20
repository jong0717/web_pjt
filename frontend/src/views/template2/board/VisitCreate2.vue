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
      <v-btn class="ma-2 text-white" color="#19ce60" @click="createVisit">등록</v-btn>
    </div>
    <!-- <VisitPage3 :visitlist="visitlist" /> -->
    <p>글목록</p>
    <ul v-for="(item,index) in guestbooks" :key="index +'_guestbooks'" class="mylist">
      <hr />
      <li>
        <div class="d-flex justify-content-between">
          <div>
            <b-avatar class="avata mr-3" size="lg"></b-avatar>
            <span class="nickname">
              <a href="#" class="text-dark">
                <strong>{{ item.writerNickname }}</strong>
              </a>
            </span>
          </div>
          <template>
            <v-dialog v-model="dialog" persistent max-width="600px">
              <template v-slot:activator="{ on, attrs }">
                <div class="m-0 p-0">
                <!-- <v-icon color="amber lighten-1" v-bind="attrs" v-on="on" class="mr-2">fas fa-edit</v-icon> -->
                <v-btn v-on="on" v-bind="attrs" text class="controlBtn p-0">수정</v-btn>
                /<v-btn @click="deleteHandler(item.gid)" class="controlBtn p-0" text>삭제</v-btn>
                </div>
              </template>
              <v-card>
                <v-card-title>
                  <span class="headline">방명록 수정</span>
                </v-card-title>
                <v-card-text>
                  <v-container>
                    <v-row>
                      <div>
                        <!-- <div>
                          <input
                            type="text"
                            name=""
                            id="replyer"
                            placeholder="작성자"
                            v-model="replyer"
                          />
                        </div> -->
                        <div>
                          <textarea
                            name="visittext"
                            id="visittext"
                            cols="20"
                            rows="3"
                            placeholder="방명록 수정"
                            v-model="content"
                          ></textarea>
                        </div>
                      </div>
                    </v-row>
                  </v-container>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue-grey darken-4" text @click="dialog = false">취소</v-btn>
                  <v-btn color="amber accent-4" text @click="editHandler">확인</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </template>
          <!-- <div class="control m-0 p-0">
            <v-btn text @click="editHandler(item.gid)" class="controlBtn p-0">수정</v-btn>
            /<v-btn @click="deleteHandler(item.gid)" class="controlBtn p-0" text>삭제</v-btn>
          </div> -->
        </div>
        <div class="d-flex justify-content-between">
          <span>
            <p class="visitcontent">{{ item.content }}</p>
          </span>
          <span>
            <p>{{ item.createDate | moment("YYYY.MM.DD")}}</p>
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
      createTime: moment(),
      guestbooks: [],
      dialog:false,
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
          createTime: this.createTime,
        })
        .then((res) => {
          console.log("등록");
          console.log(res);
          this.getGuestBooks();
          this.content = null;
          // this.$router.go();
        })
        .catch((err) => {
          console.log(err);
        });
      // this.visitlist.push(this.content)
      // this.content = null
    },
    getGuestBooks() {
      console.log("불러완");
      this.$http
        .get(`${this.$store.state.HOST}/guestbook/list/?blogId=${this.bid}`)
        .then((res) => {
          console.log(res);
          this.guestbooks = res.data;
        })
        .catch((err) => {
          console.log(err);
          console.log("에러가 발생했습니다.");
        });
    },
    getFormatDate(createTime) {
      return moment(new Date(createTime)).format("YYYY.MM.DD HH:MM:SS");
    },
    editHandler(gid) {
      this.$http
        .put(`${this.$store.state.HOST}/guestbook/modify/${gid}`, {
          content: this.content,
          createDate: this.createDate,
        })
        .then(() => {
          alert("방명록 수정이 완료되었습니다.");
        })
        .catch(() => {
          alert("수정 처리시 에러가 발생했습니다.");
        });
    },
    deleteHandler(gid) {
      this.$http
        .delete(`${this.$store.state.HOST}/guestbook/delete/${gid}`)
        .then((response) => {
          alert("삭제가 완료되었습니다.");
          console.log(response);
          this.$router.go();
        })
        .catch(() => {
          alert("삭제 처리시 에러가 발생했습니다.");
        });
    },
  },
  mounted() {
    this.$store.state.renderNum = 2;
  },
  created() {
    this.getGuestBooks();
  },
};
</script>

<style scoped>
.visitcontent {
  margin-left: 65px;
}
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
.controlBtn {
  width: 36px !important;
  height: 18px !important;
  /* min-width: 36px; */
}

.v-btn:not(.v-btn--round).v-size--default {
  min-width: 0px;
}

.avata {
  background-color:#19ce60;
}
</style>