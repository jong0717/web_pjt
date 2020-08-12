<template>
  <div id="createForm">
    <div class="form-group">
      <label for="uid">작성자</label>
      <input
        type="text"
        class="form-control"
        id="uid"
        ref="uid"
        placeholder="작성자를 입력하세요"
        v-model="uid"
      />
    </div>
    <div class="form-group">
      <label for="title">제목</label>
      <input
        type="text"
        class="form-control"
        id="title"
        ref="title"
        placeholder="제목을 입력하세요"
        v-model="title"
      />
    </div>
    <div class="form-group">
      <label for="content">내용</label>
      <textarea
        type="text"
        class="form-control"
        id="content"
        ref="content"
        placeholder="내용을 입력하세요"
        v-model="content"
      ></textarea>
    </div>
    <div class="form-group">
      <label for="exampleFormControlFile1"></label>
      <div><img v-bind:src="defalutImg" alt="" width="180" height="180"></div>
      <input type="file" id="files" ref="files" v-on:change="handleFileUpload()" multiple />
    </div>
    <div class="text-right">
      <button class="btn btn-primary" id="registerBtn" v-if="type == 'create'" @click="checkHandler">등록</button>&nbsp;
      <button class="btn btn-primary" id="updateBtn" v-else @click="checkHandler">수정</button>
      <button class="btn btn-primary" id="listBtn" @click="moveList">목록</button>
    </div>
  </div>
</template>

<script>

export default {
  name: 'post-Form',
  props: {
    type: { type: String },
  },
  data: function() {
    return {
      pno: '',
      uid: '',
      title: '',
      content: '',
      heart: 0,
      createDate: '',
      defalutImg: '//img1.daumcdn.net/thumb/C300x300/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Ftistory_admin%2Fblog%2Fadmin%2Fprofile_default_06.png',
      files: [],
      flag: true,
      selectImg: '',
    };
  },
  methods: {
    checkHandler() {
      let err = true;
      let msg = '';
      !this.uid &&
        ((msg = '작성자를 입력해주세요'),
        (err = false),
        this.$refs.uid.focus());
      err &&
        !this.title &&
        ((msg = '제목 입력해주세요'), (err = false), this.$refs.title.focus());
      err &&
        !this.content &&
        ((msg = '내용 입력해주세요'),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type == 'create' ? this.createHandler() : this.updateHandler();
    },
    createHandler() {
      let i;
      alert(this.$route.query.pno)
      for (i = 0; i < this.files.length; i++) {
          let formData = new FormData();
          formData.append('pno', this.pno)
          formData.append('uid', this.uid)
          formData.append('title', this.title)
          formData.append('content', this.content)
          formData.append('heart', this.heart)
          formData.append('createDate', this.createDate)
          formData.append('files', this.files[i]);
          this.$http.post(`${this.$store.state.HOST}/api/post/insert`,
                  formData, {
                      headers: {
                          'Content-Type': 'multipart/form-data'
                      },
                  },
              ).then(function() {
                  alert('등록이 완료되었습니다.');
                  this.$router.push('/temp1');
                  console.log('SUCCESS!!');
              })
              .catch(function() {
                this.$router.push('/temp1');
                  console.log('FAILURE!!');
              });
              this.$router.push('/temp1');
      }

    },
    updateHandler() {
      this.$http.put(`${this.$store.state.HOST}/api/post/modify/${this.pno}`, {
          pno: this.pno,
          uid: this.uid,
          title: this.title,
          content: this.content,
          heart: 0,
          createDate: this.createDate,
        })
        .then(() => {
          alert('수정이 완료되었습니다.');
          this.moveList();
        })
        .catch(() => {
          alert('수정 처리시 에러가 발생했습니다.');
        });
    },
    moveList() {
      this.$router.push('/temp1');
    },
    handleFileUpload() {
                    this.files = this.$refs.files.files;
                    console.log(this.files);
                    alert(this.files[0].name)
                    this.defalutImg = this.files[0].name;
        }        
  },
  created() {
    if (this.type === 'update') {
      this.$http.get(`${this.$store.state.HOST}/api/post/${this.$route.query.pno}`)
        .then(({ data }) => {
          this.pno = data.pno;
          this.uid = data.uid;
          this.title = data.title;
          this.content = data.content;
          this.createDate = data.createDate;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    }
  },
};
</script>

<style>
  #registerBtn{
    font-family: 'Jua', sans-serif;
    background-color: gray;
    border-color: gray;
    margin-right: 7px;
  }

  #updateBtn{
    font-family: 'Jua', sans-serif;
    background-color: gray;
    border-color: gray;
    margin-right: 7px;
  }

  #listBtn{
    font-family: 'Jua', sans-serif;
    background-color: gray;
    border-color: gray;
  }

  #createForm{
    margin-left: 25%;
    margin-right:25%;
    width: 50%;
    font-family: 'Jua', sans-serif;
  }
</style>
