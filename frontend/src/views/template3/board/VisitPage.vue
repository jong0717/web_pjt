<template>
    <div>
        <p>글목록</p>
        <ul v-for="list in visitlist" :key="list" class="mylist">
            <hr>
            <li>
                <div class='d-flex justify-content-between'>
                    <div>
                        <b-avatar variant="dark mr-3" size="lg"></b-avatar>
                        <span class="nickname"><a href="#" class="text-dark">user</a></span>
                        <span class="date ml-5 text-secondary">date</span>
                    </div>
                    <span class="control">수정/삭제</span>
                </div>
                <div class="d-flex justify-content-around">
                    <span>
                        <p class="my-0 ">{{ list }}<br></p>
                    </span>
                </div>
            </li>
        </ul>

        <!-- <ul class="list-group list-group-flush" v-for="list in visitlist" :key='list'>
            <li class="list-group-item">{{ list }}</li>
            <hr>
        </ul> -->
        <!-- {{ visitlist }} -->
        
    </div>
</template>

<script>

export default {
    name: 'VisitPage',
    props: {
        visitlist: Array
    },
    // methods: {
    //     visitlist() {
    //         this.$emit('visit')
    //     }
    // }
    // components :{

    // },
    // data() {
    //     return {
    //         // messageFromCreate: this.$route.query.message
    //     }
    // }
}

</script>

<style scoped>
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