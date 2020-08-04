<template>
  <div class="container list">
    <button class="btn btn-light" @click="movePage">글쓰기</button>
    
    <div class="list-cards row d-flex justify-content-around">
      <div class="card border-info mb-3" style="max-width: 18rem;" v-for="(item, index) in posts" :key="index + '_posts'">
        <div class="card-header bg-white"><strong><h4 class='mb-0'>Card title</h4></strong></div>
        <img src="https://picsum.photos/600/300/?image=1" alt />
        <div class="card-body text-info">
          <router-link :to="'/read?pno='+item.pno"><h5 class="card-title">{{item.title}}</h5></router-link>
          <p class="card-text">{{item.content}}</p>
          <p>{{getFormatDate(item.createDate)}}</p>
        </div>     
      </div>
    </div>
    
      <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
          <li class="page-item">
            <a class="page-link " href="#" aria-label="Previous">
              <!-- <span aria-hidden="true">&laquo;</span> -->
              <i class="fas fa-angle-left"></i>
            </a>
          </li>
          <li class="page-item"><a class="page-link" href="#">1</a></li>
          <li class="page-item"><a class="page-link" href="#">2</a></li>
          <li class="page-item"><a class="page-link" href="#">3</a></li>
          <li class="page-item">
            <a class="page-link" href="#" aria-label="Next">
              <!-- <span aria-hidden="true">&raquo;</span> -->
              <i class="fas fa-angle-right"></i>
            </a>
          </li>
        </ul>
      </nav>
  </div>
</template>

<script>
import moment from 'moment';
//import { mapGetters } from 'vuex';

export default {
  name: "Search",
  computed: {
  //...mapGetters(['posts']),
  },
  data: () => {
    return {  
      posts: [],
    }
  },
  created() {
    this.searchByTitle()
  },
  methods: {
    movePage() {
      this.$router.push('/create')
    },
    getFormatDate(createDate) {
      return moment(new Date(createDate)).format('YYYY.MM.DD');
    },
    searchByTitle(){
        this.$http.get(`${this.$store.state.HOST}/api/post/search/${this.$route.params.title}`)
        .then(response => {
          console.log(response);
          this.posts = response.data;
        })
        .catch((err) => {
          console.log(err);
          console.log('에러가 발생했습니다.');
        });
    }
  },
};
</script>
<style>
.list {
  border-right: 0.1em solid #eee;
  padding: 0.5em;
}
.list-card {
  padding: 10px;
  margin: 10px;
  width: 100%;
  float: left;
}
.list-cards {
  text-align: center;
  margin: 10px;
  padding: 10px;

}
</style>