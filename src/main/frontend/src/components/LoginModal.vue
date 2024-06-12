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
          <button type="submit" class="btn btn-outline-success" @click="submit">
            로그인
          </button>
        </div>
        <!-- 버튼 -->
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios"; // 로그인한 유저 정보
import VueJwtDecode from "vue-jwt-decode"; //jwt
const api = "http://localhost:8080"; //스프링부트 주소

export default {
  name: "loginModal",
  data() {
    return {
      userId: "",
      passwd: "",
      validCheck: false,
    };
  },
  methods: {
    submit() {
      //로그인 클릭시 메소드
      this.validCheck = true;
      axios
        .post(`${api}/member/login`, {
          id: this.userId,
          password: this.passwd,
        })
        .then(function (res) {
          console.log(res);
          let accessToken = res.data.accessToken;
          let refreshToken = res.data.refreshToken;
          sessionStorage.setItem("accessToken", accessToken);
          sessionStorage.setItem("refreshToken", refreshToken);
          //vue-jwt-decode 사용해서 JWT를 decode 한다.
          let decodedToken = VueJwtDecode.decode(accessToken);
          let date = new Date();
          let refresh = date.setMinutes(date.getMinutes() + 25);
          console.log(decodedToken);
          console.log("리프레시 시간 :", refresh);
          let config = {
            headers: {
              Authorization: `Bearer ${accessToken}`,
            },
          };
          sessionStorage.setItem("logined", JSON.stringify(decodedToken));
          sessionStorage.setItem("refresh", refresh);
          //화면 전환
          if (JSON.parse(sessionStorage.getItem("logined")).temp === "x") {
            window.location.href = `/${decodedToken.sub}`;
          } else {
            alert("비밀번호를 변경 해주세요");
            window.location.href = `/myinfo/${decodedToken.sub}`;
          }
          axios
            .get("/admin", config)
            .then((response) => {
              console.log("여기", response);
            })
            .catch((error) => {
              console.log("여기", error);
            });
        })
        .catch(function (err) {
          alert("사번 혹은 비밀번호를 확인해주세요");
          console.log(err);
        });
    },
    reset() {
      // 닫기 클릭시 메소드
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
