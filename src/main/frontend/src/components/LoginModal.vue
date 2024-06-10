<template>
  <div
    class="modal fade"
    id="loginModal"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
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
        <!-- 모달 내부 -->
        <div class="modal-body">
          <div class="mb-3">
            <label for="exampleFormControlInput1" class="form-label"
              >사원 번호</label
            >
            <input
              type="text"
              class="form-control"
              id="userIdInput"
              placeholder="사원 번호를 입력해주세요"
              v-model="userId"
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

          <!-- 유효성 경고 -->
          <p v-if="validCheck && !isValid" class="warning">
            사원 번호 혹은 비밀번호가 일치 하지 않습니다
          </p>
          <!-- 유효성 경고 -->
        </div>
        <!-- 모달 내부 -->
        <!-- 버튼 -->
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-outline-secondary"
            data-bs-dismiss="modal"
            @click="reset"
          >
            닫기
          </button>
          <button
            type="submit"
            class="btn btn-outline-success"
            @click="submit"
            :data-bs-dismiss="isValid && 'modal'"
          >
            로그인
          </button>
        </div>
        <!-- 버튼 -->
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "loginModal",
  data() {
    return {
      userId: "",
      passwd: "",
      validCheck: false,
      isValid: false,
      userInfo: {
        userId: 20240001,
        userName: "박현수",
        position: "최고 관리자",
        email: "gustn400@naver.com",
        phoneNum: "010-4435-5724",
        salary: 6000,
        department: "영업",
      },
    };
  },
  methods: {
    //로그인 클릭시 메소드
    submit() {
      this.validCheck = true;
      console.log(this.userId, this.passwd);
      //데이터 전송 필요.

      //백엔드에서 리턴된 값이 true
      if (this.userId === "20240001" && this.passwd === "hyun1991!") {
        this.isValid = true;
        //세션 저장
        sessionStorage.setItem("setUser", JSON.stringify(this.userInfo));
        //인풋 폼 리셋
        this.userId = "";
        this.passwd = "";
        window.location.href = `/${
          JSON.parse(sessionStorage.getItem("setUser")).userName
        }`;
      } else {
        this.isValid = false;
      }
      // axios로 백엔드 전달 값이 맞는지 확인하고 돌아온 값이 트루라면 로그인된 홈화면으로 이동
      // fales라면 아이디나 비밀번호가 틀렸다는 문구 보여주기
    },
    // 닫기 클릭시 메소드
    reset() {
      this.validCheck = false;
      this.userId = "";
      this.passwd = "";
    },
  },
};
</script>
<style>
.hide {
  display: none;
}
</style>
