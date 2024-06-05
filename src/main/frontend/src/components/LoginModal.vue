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
              >사원 번호</label
            >
            <input
              type="text"
              class="form-control"
              id="employeeNumInput"
              placeholder="사원 번호를 입력해주세요"
              v-model="employeeNum"
            />
          </div>
          <div class="mb-3">
            <label for="inputPassword" class="col-sm-2 col-form-label"
              >비밀 번호</label
            >
            <input
              type="password"
              class="form-control"
              id="inputPassword"
              v-model="passwd"
            />
          </div>
          <p v-if="validCheck && !isValid" class="warning">
            사원 번호 혹은 비밀번호가 일치 하지 않습니다
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
      employeeNum: "",
      passwd: "",
      validCheck: false,
      isValid: false,
    };
  },
  methods: {
    submit() {
      console.log(this.employeeNum, this.passwd);
      this.validCheck = true;
      if (this.employeeNum === "20240001" && this.passwd === "hyun1991!") {
        this.isValid = true;
      }
      this.employeeNum = "";
      this.passwd = "";
      // axios로 백엔드 전달 값이 맞는지 확인하고 돌아온 값이 트루라면 로그인된 홈화면으로 이동
      // fales라면 아이디나 비밀번호가 틀렸다는 문구 보여주기
    },
    reset() {
      this.validCheck = false;
      this.employeeNum = "";
      this.passwd = "";
    },
  },
};
</script>
<style></style>
