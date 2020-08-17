<template>
  <div id="createForm">
    <div class="form-group"></div>

    <div class="form-group">
      <label for="title">제목</label>
      <input
        type="text"
        class="form-control pb-5"
        id="title"
        ref="title"
        placeholder="제목을 입력하세요"
        v-model="title"
      />
    </div>

    <label for="content">내용</label>
    <v-container class="form-group fill-height pt-0" fluid>
      <v-row align="center" justify="center">
        <v-col class="text-center">
          <!-- Bold -->
          <div class="justify-content-between">
            <!-- buttons -->
            <v-row class="editorbuttons border border-bottom">
              <!-- H1 -->
              <v-tooltip top>
                <template
                  class="H1"
                  v-slot:activator="{ on }"
                  onclick="document.execCommand('formatBlock',false,'<h1>')"
                >
                  <v-btn
                    :href="source"
                    onclick="document.execCommand('formatBlock',false,'<h1>')"
                    icon
                    medium
                    target="_blank"
                    v-on="on"
                  >
                    <input type="button" class="BOLD" />
                    <img src="https://img.icons8.com/ios-glyphs/30/000000/header-1.png" />
                  </v-btn>
                </template>
                <span>H1</span>
              </v-tooltip>

              <!-- H2 -->
              <v-tooltip top>
                <template
                  class="H2"
                  v-slot:activator="{ on }"
                  onclick="document.execCommand('formatBlock',false,'<h2>')"
                >
                  <v-btn
                    class="ml-4"
                    :href="source"
                    onclick="document.execCommand('formatBlock',false,'<h2>')"
                    icon
                    medium
                    target="_blank"
                    v-on="on"
                  >
                    <input type="button" class="BOLD" />
                    <img src="https://img.icons8.com/ios-glyphs/30/000000/header-2.png" />
                  </v-btn>
                </template>
                <span>H2</span>
              </v-tooltip>

              <!-- H3 -->
              <v-tooltip top>
                <template
                  class="H3"
                  v-slot:activator="{ on }"
                  onclick="document.execCommand('formatBlock',false,'<h3>')"
                >
                  <v-btn
                    class="ml-4"
                    :href="source"
                    onclick="document.execCommand('formatBlock',false,'<h3>')"
                    icon
                    medium
                    target="_blank"
                    v-on="on"
                  >
                    <input type="button" class="H3" />
                    <img src="https://img.icons8.com/ios-glyphs/30/000000/header-3.png" />
                  </v-btn>
                </template>
                <span>H3</span>
              </v-tooltip>|
              <!-- Bold -->
              <v-tooltip top>
                <template
                  class="BOLD"
                  v-slot:activator="{ on }"
                  onclick="document.execCommand('bold')"
                >
                  <v-btn
                    :href="source"
                    onclick="document.execCommand('bold')"
                    icon
                    medium
                    target="_blank"
                    v-on="on"
                  >
                    <input type="button" class="BOLD" />
                    <i class="fas fa-bold"></i>
                  </v-btn>
                </template>
                <span>Bold</span>
              </v-tooltip>

              <!-- Italic -->
              <v-tooltip top>
                <template
                  class="BOLD"
                  v-slot:activator="{ on }"
                  onclick="document.execCommand('bold')"
                >
                  <v-btn
                    :href="source"
                    icon
                    medium
                    target="_blank"
                    onclick="document.execCommand('Italic')"
                    v-on="on"
                  >
                    <input type="button" class="ITALIC" />
                    <i class="fas fa-italic"></i>
                  </v-btn>
                </template>
                <span>Italic</span>
              </v-tooltip>

              <!-- UNDERBAR -->

              <v-tooltip top>
                <template
                  class="BOLD"
                  v-slot:activator="{ on }"
                  onclick="document.execCommand('createImages')"
                >
                  <v-btn
                    :href="source"
                    icon
                    medium
                    target="_blank"
                    onclick="document.execCommand('createImages')"
                    v-on="on"
                  >
                    <input type="button" class="BAR" />
                    <i class="fas fa-underline"></i>
                  </v-btn>
                </template>
                <span class="UNDERBAR">underbar</span>
              </v-tooltip>

              <!-- BAR -->
              <v-tooltip top>
                <template
                  class="BOLD"
                  v-slot:activator="{ on }"
                  onclick="document.execCommand('bold')"
                >
                  <v-btn
                    :href="source"
                    icon
                    medium
                    target="_blank"
                    onclick="document.execCommand('StrikeThrough')"
                    v-on="on"
                  >
                    <input type="button" class="BAR" />
                    <i class="fas fa-strikethrough"></i>
                  </v-btn>
                </template>
                <span class="strikethrough">strikethrough</span>
              </v-tooltip>

              <!-- Undo -->
              |
              <v-tooltip top>
                <template v-slot:activator="{ on }" onclick="document.execCommand('Undo')">
                  <v-btn
                    :href="source"
                    icon
                    medium
                    target="_blank"
                    onclick="document.execCommand('Undo')"
                    v-on="on"
                  >
                    <input type="button" class="Undo" />
                    <i class="fas fa-undo"></i>
                  </v-btn>
                </template>
                <span class="Undo">Undo</span>
              </v-tooltip>

              <!-- redo -->
              <v-tooltip top>
                <template v-slot:activator="{ on }" onclick="document.execCommand('Redo')">
                  <v-btn
                    :href="source"
                    icon
                    medium
                    target="_blank"
                    onclick="document.execCommand('Redo')"
                    v-on="on"
                  >
                    <input type="button" class="Redo" />
                    <i class="fas fa-redo"></i>
                  </v-btn>
                </template>
                <span class="Redo">Redo</span>
              </v-tooltip>

              <!-- images -->
              |
              <v-tooltip top>
                <template v-slot:activator="{ on }" onclick="document.execCommand('Images')">
                  <v-btn
                    :href="source"
                    icon
                    medium
                    target="_blank"
                    onclick="document.execCommand('images')"
                    v-on="on"
                  >
                    <input type="button" class="Imgaes" />
                    <i class="fas fa-images"></i>
                  </v-btn>
                </template>
                <span class="Images">Images</span>
              </v-tooltip>

              <!-- link -->
              <v-tooltip top>
                <template v-slot:activator="{ on }" onclick="document.execCommand('Link')">
                  <v-btn
                    :href="source"
                    icon
                    medium
                    target="_blank"
                    onclick="document.execCommand('Link')"
                    v-on="on"
                  >
                    <input type="button" class="Link" />
                    <i class="fas fa-link"></i>
                  </v-btn>
                </template>
                <span class="Link">Link</span>
              </v-tooltip>

              <!-- 왼쪽 정렬 -->
              |
              <v-tooltip top>
                <template
                  class="BOLD asdf"
                  v-slot:activator="{ on }"
                  onclick="document.execCommand('bold')"
                >
                  <v-btn
                    :href="source"
                    icon
                    medium
                    target="_blank"
                    onclick="document.execCommand('justifyleft')"
                    v-on="on"
                  >
                    <input type="button" class="asdf" />
                    <i class="fas fa-align-left"></i>
                  </v-btn>
                </template>
                <span class="UNDERBAR">왼쪽 정렬</span>
              </v-tooltip>

              <!-- 가운데 정렬 -->
              <v-tooltip top>
                <template
                  class="BOLD asdf"
                  v-slot:activator="{ on }"
                  onclick="document.execCommand('bold')"
                >
                  <v-btn
                    :href="source"
                    icon
                    medium
                    target="_blank"
                    onclick="document.execCommand('justifycenter')"
                    v-on="on"
                  >
                    <input type="button" icon="fas fa-equals" />
                    <i class="fas fa-align-center"></i>
                  </v-btn>
                </template>
                <span class="UNDERBAR">가운데 정렬</span>
              </v-tooltip>

              <!-- 오른쪽 정렬 -->
              <v-tooltip top>
                <template
                  class="BOLD asdf"
                  v-slot:activator="{ on }"
                  onclick="document.execCommand('bold')"
                >
                  <v-btn
                    :href="source"
                    icon
                    medium
                    target="_blank"
                    onclick="document.execCommand('justifyright')"
                    v-on="on"
                  >
                    <input type="button" class="asdf" />
                    <i class="fas fa-align-right"></i>
                  </v-btn>
                </template>
                <span class="UNDERBAR">오른쪽 정렬</span>
              </v-tooltip>|
              <input
                v-if="html_switch===true"
                type="button"
                value=" Editor"
                @click="convertToEditor"
              />
              <input v-if="html_switch===false" type="button" value=" HTML" @click="convertToHTML" />
            </v-row>

            <div class="col-1-none"></div>

            <v-row
              class="editortext editorDIV form-control"
              type="text"
              id="content"
              ref="content"
              placeholder="내용을 입력하세요"
              contenteditable="true"
            ></v-row>

            <div class="editorHTMLDIV"></div>
          </div>
        </v-col>
      </v-row>
    </v-container>

    <div class="text-right">
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
import $ from "jquery";

$(document).ready(function () {
  $(".editorHTMLDIV").hide();
});

export default {
  name: "post-Form",
  props: {
    type: { type: String },
    source: String,
  },
  data: function () {
    return {
      pno: "",
      uid: this.$store.state.uid.uid,
      title: "",
      content: "",
      heart: 0,
      createDate: "",
      html_switch: false,
      drawer: null,
    };
  },
  methods: {
    convertToHTML: function () {
      this.content = document.getElementById("content").innerHTML;
      this.html_switch = true;
      $(".editorHTMLDIV").text($(".editorDIV").html());
      $(".editorHTMLDIV").show();
      $(".editorDIV").hide();
    },
    convertToEditor: function () {
      this.html_switch = false;
      $(".editorDIV").html($(".editorHTMLDIV").text());
      $(".editorDIV").show();
      $(".editorHTMLDIV").hide();
    },
    checkHandler() {
      this.content = document.getElementById("content").innerHTML;
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
      this.$http
        .post(`${this.$store.state.HOST}/api/post/insert`, {
          pno: this.pno,
          uid: this.uid,
          title: this.title,
          content: this.content,
          heart: 0,
          createDate: this.createDate,
        })
        .then(() => {
          alert("등록이 완료되었습니다.");
          this.moveList();
        })
        .catch(() => {
          alert("등록 처리시 에러가 발생했습니다.");
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
/* 
# {
  font-family: "Jua", sans-serif;
  background-color: gray;
  border-color: gray;
  margin-right: 7px;
} */

#listBtn {
  font-family: "Jua", sans-serif;
  background-color: gray;
  border-color: gray;
}

#createForm {
  margin-left: 5%;
  margin-right: 5%;
  width: 80%;
  font-family: "Jua", sans-serif;
}
</style>

