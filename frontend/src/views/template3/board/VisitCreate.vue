<template>
    <div class='pt-5' id="visit">
        <div class="form-group">
            <!-- <label for="exampleFormControlTextarea1" class="font-weight-bold text-dark">방명록</label> -->
            <h6><strong>GuestBook</strong></h6>
            <!-- <hr class="m-auto"> <br> -->
            <textarea class="form-control mt-4" id="exampleFormControlTextarea1" rows="3" v-model="content" placeholder="여러분의 소중한 댓글을 입력해 주세요."></textarea>
        </div>
        <div class='text-right'>
            <!-- <button type="submit" class="btn btn-dark tile" @click="createVisit">등록</button> -->
            <v-btn class="ma-2" color="deep-purple accent-1" @click="createVisit">등록</v-btn>
        </div>
        <VisitPage :visitlist="visitlist"/>
    </div>
    
</template>

<script>
import VisitPage from '@/views/template3/board/VisitPage.vue'
export default {
    name: 'VisitCreate',
    data() {
        return {
            content: '',
            visitlist: [],
        }
    },
    components: {
        VisitPage,
    },
    methods: {
        createVisit() {
            this.visitlist.push(this.content)
            this.content = null

        }
    }
}

</script>

<style scoped>

#visit {
    width: 90%;
}
h6 {
    text-align: left;
}
</style>