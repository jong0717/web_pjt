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
        style="width:100%"
      />
    </div>

    <label for="content">내용</label>
    <!-- <v-container class="form-group fill-height pt-0" fluid> -->
      <v-row align="center" justify="center">
        <v-col class="text-center">
          <!-- Bold -->
          <div class="justify-content-between" fluid>
            <!-- buttons -->
            <div class="editorbuttons border border-bottom">
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
            </div>

            <div class="col-1-none"></div>

            <div
              class="editortext editorDIV form-control"
              type="text"
              id="content"
              ref="content"
              placeholder="내용을 입력하세요"
              style='height: auto; min-height: 200px;'
              contenteditable="true"
            ></div>

            <div class="editorHTMLDIV"></div>
          </div>
        </v-col>
      </v-row>
    <!-- </v-container> -->
    <file-pond
      name="test"
      ref="pond"
      label-idle="Drop files here..."
      allow-multiple="true"
      accepted-file-types="image/jpeg, image/png"
      v-bind:server="server"
      v-bind:files="myFiles"
      v-on:init="handleFilePondInit"
    />

    <div class="form-group">
      <label for="exampleFormControlFile1"></label>
      <div>
        <img v-bind:src="defalutImg" alt width="180" height="180" />
      </div>
      <input type="file" id="files" ref="files" v-on:change="handleFileUpload()" multiple />
    </div>
    <div>
      <label for="tags-basic">Type a new tag and press enter</label>
      <b-form-tags input-id="tags-basic" v-model="tags" class="mb-2"></b-form-tags>
      <p>Value: {{ tags }}</p>
    </div>
    <button @click="getTag">getTag</button>
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
import vueFilePond from "vue-filepond";
import "filepond/dist/filepond.min.css";
import "filepond-plugin-image-preview/dist/filepond-plugin-image-preview.min.css";
import FilePondPluginFileValidateType from "filepond-plugin-file-validate-type";
const FilePond = vueFilePond(FilePondPluginFileValidateType);

$(document).ready(function () {
  $(".editorHTMLDIV").hide();
});

export default {
  name: "post-Form",
  props: {
    type: { type: String },
    source: String,
  },
  components: {
    FilePond,
  },
  data: function () {
    return {
      pno: "",
      uid: this.$store.state.uid.uid,
      title: "",
      content: "",
      heart: 0,
      createDate: "",
      bid: this.$route.params.bid,
      tags: [],
      tag: '',
      html_switch: false,
      drawer: null,
      defalutImg:
        "//img1.daumcdn.net/thumb/C300x300/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Ftistory_admin%2Fblog%2Fadmin%2Fprofile_default_06.png",
      files: [],
      flag: true,
      selectImg: "",
      myFiles: [],
      server: `${this.$store.state.HOST}/api/image`,
    };
  },
  methods: {
    convertToHTML: function () {
      this.content = document.getElementById("content").innerHTML;
      this.html_switch = true;
      console.log(this.content)
      $(".editorHTMLDIV").text($(".editorDIV").html());
      $(".editorHTMLDIV").show();
      $(".editorDIV").hide();
    },
    convertToEditor: function () {
      this.content = document.getElementById("content").innerText;
      this.html_switch = false;
      console.log(this.content)
      $(".editorDIV").html($(".editorHTMLDIV").text());
      $(".editorDIV").show();
      $(".editorHTMLDIV").hide();
    },
    checkHandler() {
      this.content = document.getElementById("content").innerText;
      let err = true;
      let msg = "";

      if (!err) alert(msg);
      else this.type == "create" ? this.createHandler() : this.updateHandler();
    },
    createHandler() {
      console.log("글쓰기실행");
      this.getTag()
      let i;
      for (i = 0; i < this.files.length; i++) {
        let formData = new FormData();
        // formData.append("pno", this.pno);
        formData.append("uid", this.uid);
        formData.append("title", this.title);
        formData.append("content", this.content);
        formData.append("heart", this.heart);
        // formData.append("createDate", this.createDate);
        formData.append("files", this.files[i]);
        formData.append("bid", this.bid);
        formData.append("tag", this.tag);
        this.$http
          .post(`${this.$store.state.HOST}/api/post/insert`, formData, {
            headers: {
              "Content-Type": "multipart/form-data",
            },
          })
          .then((res) => {
            alert("등록이 완료되었습니다.");
            // this.$router.push("/temp1");
            this.$router.go(-1);
            console.log(res, "SUCCESS!!");
          })
          .catch((err) => {
            // this.$router.push("/temp1");
            this.$router.go(-1);
            console.log(err, "FAILURE!!");
          });
        // this.$router.push("/temp1");
        // this.$router.go(-1)
      }
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
          this.$router.go(-1);
        })
        .catch(() => {
          alert("수정 처리시 에러가 발생했습니다.");
        });
    },
    moveList() {
      this.$router.push("/temp1");
    },
    handleFileUpload() {
      this.files = this.$refs.files.files;
      console.log(this.files);
      alert(this.files[0].name);
      this.defalutImg = this.files[0].name;
    },
    handleFilePondInit: function () {
      console.log("FilePond has initialized");
      this.$refs.pond.getFiles();
    },
    getTag() {
      let i
      for (i = 0; i < (this.tags).length; i++) {
        if (i === (this.tags).length-1) {
          this.tag = this.tag + this.tags[i]
          break
        }
        this.tag = this.tag + this.tags[i] + ':' 
      }
      console.log(this.tag)
    }
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

