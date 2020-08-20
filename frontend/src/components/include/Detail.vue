<template>
  <div>
    <div class="inner">
      <div class="hgroup">
        <div class="category">
          <h1>{{ post.title }}</h1>
          <div class="post-meta">
            <span style="margin-right:1em">{{ this.nickname }}</span> |
            <span style="margin-left:1em; margin-right:1em">{{ getFormatDate(post.createDate) }}</span> |
            
            <svg style="margin-left:1em;" @click="CopyUrlToClipboard()" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-share-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
              <path fill-rule="evenodd" d="M12.024 3.797L4.499 7.56l-.448-.895 7.525-3.762.448.894zm-.448 9.3L4.051 9.335 4.5 8.44l7.525 3.763-.448.894z"/>
              <path fill-rule="evenodd" d="M13.5 5a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5zm0 11a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5zm-11-5.5a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5z"/>
            </svg>
          </div>
          <hr>
        </div>
      </div>
      <div class="content">
        <div v-html="post.content"></div>
      </div>
    </div>
    <!-- <div><h1>여기에 나와야 돼</h1></div> -->
    <hr>
    <div class="text-center">
      <!-- <button @click="back" class="btn btn-primary" id="listBtn">목록</button> -->
      <router-link to=""><v-btn color="green lighten-2" fab dark @click="back" id="listBtn">목록</v-btn></router-link>
      <router-link :to="'/update?pno=' + post.pno">
        <v-btn color="amber lighten-1" fab dark id="updateBtn">수정</v-btn>
      </router-link>
      <router-link :to="'/delete?pno=' + post.pno">
        <v-btn color="red lighten-1" fab dark id="deleteBtn">삭제</v-btn>
      </router-link>
    </div>
    <br />
    
    <br />

    <!-- 댓글 리스트 -->
    댓글
    <hr />

    <div class="media container" v-for="(item, index) in replies" :key="index + '_replies'">
      <img
        src="https://lh4.googleusercontent.com/proxy/gt2zedUM7WSUSi0IFaUweagmtganvNSRnkR2LZBuTlg-awQyO8sbZBibRcK7klnwx_8uM5djgeiWsMfpD5xJI_GtpZijxoH0qrAv0Srp4GWwu6UqKafqJf53xgPXEg80qFzA3DvlQcHlYFq6G-OFFhxLrQ"
        class="mr-3 read-icon rounded-circle"
        alt="..."
      />
      <!-- {{item}} -->
      <div class="media-body">
        <h5 class="mt-0">{{item.replyer}}</h5>
        {{getFormatDate(item.createDate)}}
        <p>{{item.replytext}}</p>
      </div>
      <!-- <button class="btn btn-primary" id="ReplyeditBtn" @click="editHandler">수정</button> -->
      <template>
          <v-dialog v-model="dialog" persistent max-width="600px">
            <template v-slot:activator="{ on, attrs }">
              <!-- <v-btn
                color="primary"
                dark
                v-bind="attrs"
                v-on="on"
              >
                Open Dialog
              </v-btn> -->
      <v-icon color="amber lighten-1" v-bind="attrs" v-on="on" class="mr-2">fas fa-edit</v-icon>
            </template>
            <v-card>
              <v-card-title>
                <span class="headline">댓글 수정</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <div>
                      <div>
                        <input type="text" name="replyer" id="replyer" placeholder="작성자" v-model="replyer" />
                      </div>
                    <div>
                      <textarea
                        name="replytext"
                        id="replytext"
                        cols="20"
                        rows="3"
                        placeholder="댓글수정"
                        v-model="replytext"
                        
                      ></textarea>
                    </div>
                    <!-- <button class="btn btn-primary" id="ReplyAddBtn" @click="addHandler">등록</button> -->
                  </div>
                  </v-row>
                </v-container>
                <!-- <small>*indicates required field</small> -->
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue-grey darken-4" text @click="dialog = false">취소</v-btn>
                <v-btn color="amber accent-4" text @click="editHandler">확인</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
      </template>
      <!-- <button class="btn btn-primary" id="ReplydelBtn" @click="deleteHandler(item.rno)">삭제</button> -->
      <v-icon color="red lighten-1" @click="deleteHandler(item.rno)">fas fa-trash-alt</v-icon>
    </div>
    <hr />
    <template>
      <div class="container">
        <div>
          <input type="text" name="replyer" id="replyer" placeholder="작성자" v-model="replyer">
        </div>
        <div class="d-flex justify-content-center align-items-center">
        <div>
          <textarea
            name="replytext"
            id="replytext"
            cols="25"
            rows="3"
            placeholder="여러분의 소중한 댓글을 입력해주세요."
            v-model="replytext"
            class="form-control w-100"
          ></textarea>
          <!-- <textarea id="replytext" class="form-control mt-4" id="exampleFormControlTextarea1" rows="3" v-model="content" placeholder="여러분의 소중한 댓글을 입력해주세요."></textarea> -->
        <!-- <button class="btn btn-primary" id="ReplyAddBtn" @click="addHandler">등록</button> -->
        <!-- <div class='text-center'> -->
        </div>
          <v-btn class="ml-4 " fab color="blue darken-1" dark @click="addHandler">등록</v-btn>
          </div>
        <!-- </div> -->
      </div>
    </template>
  </div>
</template>

<script>
import moment from "moment";
import { mapGetters } from "vuex";
export default {
  name: "Detail",
  props: {
    type: { type: String },
  },
  computed: {
    ...mapGetters(["post"]),
  },
  data: function () {
    return {
      dialog: false,
      nickname: "",
      replies: [],
      rno: "",
      pno: "",
      replytext: "",
      replyer: "",
      createDate: "",
      post: {},
    };
  },
  created() {
    this.getNickname(), this.getReplies();
  },
  mounted() {
    this.getPost()
  },
  methods: {
    CopyUrlToClipboard() {
      var obShareUrl = document.createElement("textarea")
      obShareUrl.value = window.document.location.href;

      obShareUrl.select();
      document.execCommand("copy");

      alert(obShareUrl.value+" 를 클립보드에 복사하였습니다.");
      obShareUrl.removeChild(obShareUrl);
    },
    getFormatDate(createDate) {
      return moment(new Date(createDate)).format("YYYY.MM.DD");
    },
    getNickname() {
      this.$http
        .get(
          `${this.$store.state.HOST}/api/post/detail/${this.$route.query.pno}`
        )
        .then(({ data }) => {
          this.nickname = data;
        })
        .catch(() => {
          console.log("에러가 발생했습니다.");
        });
    },
    getReplies() {
      this.$http
        .get(
          `${this.$store.state.HOST}/api/reply/list/${this.$route.query.pno}`
        )
        .then((response) => {
          console.log(response);
          this.replies = response.data;
        })
        .catch((err) => {
          console.log(err);
          console.log("에러가 발생했습니다.");
        });
    },
    addHandler() {
      this.$http
        .post(`${this.$store.state.HOST}/api/reply/insert`, {
          rno: this.rno,
          pno: this.$route.query.pno,
          replytext: this.replytext,
          replyer: this.replyer,
          createDate: this.createDate,
        })
        .then((response) => {
          alert("등록이 완료되었습니다.");
          console.log(response);
          // console.log(createDate)
          this.$router.go();
        })
        .catch(() => {
          alert("등록 처리시 에러가 발생했습니다.");
        });
    },
    editHandler() {
      this.$http
        .put(`${this.$store.state.HOST}/api/reply/modify/${this.rno}`, {
          replyer: this.replyer,
          replytext: this.replytext,
          createDate: this.createDate,
        })
        .then(() => {
          alert("댓글 수정이 완료되었습니다.");
        })
        .catch(() => {
          alert("수정 처리시 에러가 발생했습니다.");
        });
    },
    deleteHandler(rno) {
      this.$http
        .delete(`${this.$store.state.HOST}/api/reply/` + rno)
        .then((response) => {
          alert("삭제가 완료되었습니다.");
          console.log(response);
          this.$router.go();
        })
        .catch(() => {
          alert("삭제 처리시 에러가 발생했습니다.");
        });
    },
    getPost() {
      this.$http
      .get(`${this.$store.state.HOST}/api/post/${this.$route.query.pno}`, {
        params: {
          accessToken: this.$store.state.authToken,
          pno: this.$route.query.pno
        }
      })
      .then((res) => {
        console.log(res)
        this.post = res.data
      })
      .catch((err) => {
        console.log(err)
      })
    },
    back() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
.read-icon {
  height: 6ch;
  width: 6ch;
  margin: 0px;
  margin-right: 10px;
}
.media {
  padding: 0;
  margin: 0;
  /* margin-left:; */
}
.media-body {
  text-align: left;
}
#updateBtn {
  margin: 0px 5px;
}

#replytext {
  border:solid 1px;
  width : 500px !important;
  height: 56px !important;
  /* padding-top:0; */

}
.post-meta {
  display: flex;
  justify-content: flex-start;
}
.category {
  display: block;
  text-align: left;
}
</style>