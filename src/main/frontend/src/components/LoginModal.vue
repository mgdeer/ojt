<template>
  <div
    class="modal fade"
    id="loginModal"
    tabindex="-1"
    aria-labelledby="loginModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="loginModalLabel">로그인</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="reset"
          ></button>
        </div>
        <div class="modal-body">
          <div class="mb-3">
            <label for="exampleFormControlInput1" class="form-label"
              >Email address</label
            >
            <input
              type="email"
              class="form-control"
              id="emailInput"
              placeholder="name@example.com"
              v-model="email"
            />
          </div>
          <div class="mb-3">
            <label for="inputPassword" class="col-sm-2 col-form-label"
              >Password</label
            >
            <input
              type="password"
              class="form-control"
              id="inputPassword"
              v-model="passwd"
            />
          </div>
          <p v-if="validCheck && !isValid" class="warning">
            이메일 혹은 비밀번호가 일치 하지 않습니다
          </p>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-outline-secondary"
            data-bs-dismiss="modal"
            @click="reset"
          >
            닫기
          </button>
          <button type="button" class="btn btn-outline-success" @click="submit">
            로그인
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "loginModal",
  data() {
    return {
      email: "",
      passwd: "",
      validCheck: false,
      isValid: false,
    };
  },
  methods: {
    submit() {
      console.log(this.email, this.passwd);
      this.validCheck = true;
      if (this.email === "gustn400@naver.com" && this.passwd === "hyun1991!") {
        this.isValid = true;
      }
      this.email = "";
      this.passwd = "";
      // axios로 백엔드 전달 값이 맞는지 확인하고 돌아온 값이 트루라면 로그인된 홈화면으로 이동
      // fales라면 아이디나 비밀번호가 틀렸다는 문구 보여주기
    },
    reset() {
      this.validCheck = false;
      this.email = "";
      this.passwd = "";
    },
  },
};
</script>
<style></style>
