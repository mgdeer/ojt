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
//엑시오스 임포트
import axios from "axios";
//jwt
import VueJwtDecode from "vue-jwt-decode";
//엑시오스 주소
const api = "http://localhost:8080";

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
      //데이터 전송 필요.
      axios
        .post(`${api}/member/login`, {
          id: this.userId,
          password: this.passwd,
        })
        .then(function (res) {
          console.log(res);
          let token = res.data.accessToken;
          localStorage.setItem("token", token);

          //vue-jwt-decode 사용해서 JWT를 decode 한다.
          let decodedToken = VueJwtDecode.decode(token);
          console.log(decodedToken);
          // 이게 유저정보인지?
          let config = {
            headers: {
              Authorization: `Bearer ${token}`,
            },
            // 토큰 기한 설정이 무슨말인지? exp로 기한설정이 되어있는거 같던데?
            // Authorization 을 다시 보내서 유저정보를 받고 로그인 시키면 해당 유저 정보는 세션에 저장?
          };
          axios
            .get("/admin", config)
            .then((response) => {
              console.log("여기", response);
              // 정보 들어오는게 없는데??
              let userInfo = {
                // nickname: response.data.nickname,
                // username: response.data.username,
              };
              console.log(userInfo);
            })
            .catch((error) => {
              console.log("여기", error);
            });
          //인풋 폼 리셋
          this.userId = "";
          this.passwd = "";
        })
        .catch(function (err) {
          console.log(err);
        });
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
