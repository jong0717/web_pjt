<template>
  <div class="container">
    <div class="row">
      <div class="col">
        <h3>기본 정보</h3>
        <form class="wrap_set">
          <div class="form-group">
            <div>
              <v-gravatar email="somebody@somewhere.com" />
            </div>
          </div>
          <div class="form-group">
            <label for="exampleFormControlInput1">이메일</label>
            <input
              type="email"
              class="form-control"
              id="exampleFormControlInput1"
              placeholder="name@example.com"
            />
          </div>
          <div class="form-group">
            <label for="nickname">닉네임</label>
            <input
              type="text"
              class="form-control"
              id="exampleFormControlInput2"
              placeholder="nickname"
            />
          </div>
          <div class="form-group">
            <label for="exampleFormControlTextarea1">소개</label>
            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
          </div>
        </form>
        {{ userid }}님의 마이페이지입니다.
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
</style>