<template>
    <div>
        <p>글목록</p>
        <ul v-for="list in visitlist" :key="list" class="mylist">
            <li>{{ list }}</li>
            <hr>
        </ul>

        
    </div>
</template>

<script>

export default {
    name: 'VisitPage',
    props: {
        visitlist: Array
    },
}

</script>

<style scoped>
.mylist {
    list-style: none;
    padding-inline-start: 0px;
}
</style>