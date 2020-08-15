<template>
  <div class="container">
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-text-field v-model="title" :counter="10" :rules="nameRules" label="제목" required></v-text-field>

      <v-textarea outlined name="input-7-4" label="내용" value v-model="content"></v-textarea>
      <input type="file" name="photo" id="photo" />
    </v-form>

    <div>
      <button
        class="btn btn-primary"
        id="registerBtn"
        v-if="type == 'create'"
        @click="checkHandler"
      >등록</button>&nbsp;
      <button class="btn btn-primary" id="updateBtn" v-else @click="checkHandler">수정</button>
      <button class="btn btn-primary" id="listBtn" @click="moveList">목록</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "post-Form",
  components: {},
  props: {
    type: { type: String },
  },
  data: function () {
    return {
      pno: "",
      uid: this.$store.state.uid.uid,
      title: "",
      content: "",
      heart: 0,
      createDate: "",
    };
  },
  methods: {
    checkHandler() {
      let err = true;
      let msg = "";
      !this.uid &&
        ((msg = "작성자를 입력해주세요"),
        (err = false),
        this.$refs.uid.focus());
      err &&
        !this.title &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type == "create" ? this.createHandler() : this.updateHandler();
    },
    createHandler() {
      var formData = new FormData();
      var photoFile = document.getElementById("photo");
      formData.append("files", photoFile.files[0]);
      this.$http
        .post(
          `${this.$store.state.HOST}/api/post/insert?content=${this.content}&heart=${this.heart}&title=${this.title}&uid=${this.uid}`,
          formData
        )
        .then(() => {
          alert("등록이 완료되었습니다.");
          this.moveList();
        })
        .catch((err) => {
          alert("등록 처리시 에러가 발생했습니다.");
          console.log(err);
          console.log(formData);
        });
    },
    updateHandler() {
      this.$http
        .put(`${this.$store.state.HOST}/api/post/modify/${this.pno}`, {
          pno: this.pno,
          uid: this.uid,
          title: this.title,
          content: this.content,
          heart: 0,
          createDate: this.createDate,
        })
        .then(() => {
          alert("수정이 완료되었습니다.");
          this.moveList();
        })
        .catch(() => {
          alert("수정 처리시 에러가 발생했습니다.");
        });
    },
    moveList() {
      this.$router.push("/temp1");
    },
  },
  created() {
    if (this.type === "update") {
      this.$http
        .get(`${this.$store.state.HOST}/api/post/${this.$route.query.pno}`)
        .then(({ data }) => {
          this.pno = data.pno;
          this.uid = data.uid;
          this.title = data.title;
          this.content = data.content;
          this.createDate = data.createDate;
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
    }
  },
};
</script>

<style>
#registerBtn {
  font-family: "Jua", sans-serif;
  background-color: gray;
  border-color: gray;
  margin-right: 7px;
}

#updateBtn {
  font-family: "Jua", sans-serif;
  background-color: gray;
  border-color: gray;
  margin-right: 7px;
}

#listBtn {
  font-family: "Jua", sans-serif;
  background-color: gray;
  border-color: gray;
}

#createForm {
  margin-left: 0%;
  margin-right: 0%;
  width: 50%;
  font-family: "Jua", sans-serif;
}
</style>
