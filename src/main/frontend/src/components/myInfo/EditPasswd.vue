<template>
  <!-- Modal -->
  <div
    class="modal fade"
    id="editPasswdModal"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="editPasswdModal"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="editPasswdModal">
            <i class="bi bi-person-fill-gear"></i>비밀 번호 변경
          </h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="reset"
          ></button>
        </div>
        <div class="modal-body">
          <div style="margin: 15px 0">
            8자리 이상 영어, 숫자, 특수문자의 조합이여야 합니다
          </div>
          <!-- 모달 안 내용 -->
          <h5>새 비밀번호</h5>
          <div class="input-group">
            <span class="input-group-text" id="basic-addon1"
              ><i class="bi bi-person-fill"></i
            ></span>
            <input
              type="password"
              class="form-control"
              aria-label="password"
              aria-describedby="basic-addon1"
              v-model="newPasswd"
            />
          </div>
          <!-- 유효값 경고 -->
          <div style="height: 40px">
            <div v-show="newPasswd.length > 0">
              <p class="pass" v-if="passwdValidChk">사용 가능한 비밀번호</p>
              <p class="warning" v-else>사용 불 가능한 비밀번호</p>
            </div>
          </div>
          <!-- 유효값 경고 -->
          <h5>비밀 번호 확인</h5>
          <div class="input-group">
            <span class="input-group-text" id="basic-addon2"
              ><i class="bi bi-envelope-at-fill"></i
            ></span>
            <input
              type="password"
              class="form-control"
              aria-label="passwordCheck"
              aria-describedby="basic-addon2"
              v-model="newPasswdCheck"
            />
          </div>
          <!-- 유효값 경고 -->
          <div style="height: 40px">
            <div v-show="newPasswdCheck.length > 0">
              <p class="pass" v-if="passwdSame">비밀 번호가 일치 합니다</p>
              <p class="warning" v-else>비밀 번호가 일치 하지 않습니다</p>
            </div>
          </div>
          <!-- 유효값 경고 -->
          <!-- 모달 안 내용 -->
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
          <button
            type="button"
            class="btn btn-outline-success"
            @click="myInfoSubmit"
          >
            수정 완료
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
const api = "http://localhost:8080"; //스프링부트 주소
// 유효성 검사
// 정규식 에러 해결
// eslint-disable-next-line
const passwdPattern =
  /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
const user = JSON.parse(sessionStorage.getItem("logined")); //유저 정보

export default {
  name: "editPasswd",
  components: {},
  data() {
    return {
      newPasswd: "",
      newPasswdCheck: "",
    };
  },
  methods: {
    //비밀번호 변경 메소드
    myInfoSubmit() {
      if (this.passwdValidChk && this.passwdSame) {
        axios
          .post(`${api}/member/changePwd`, {
            id: user.sub,
            newPassword: this.newPasswd,
          })
          .then((response) => {
            console.log(response);
            this.newPasswd = "";
            this.newPasswdCheck = "";
            console.log("로그아웃");
            alert("비밀번호가 변경되었습니다.");
            sessionStorage.removeItem("logined");
            sessionStorage.removeItem("accessToken");
            sessionStorage.removeItem("refreshToken");
            sessionStorage.removeItem("refresh");
            window.location.href = `/`;
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    reset() {
      //닫기 클릭시 값 리셋 메소드
      this.newPasswd = "";
      this.newPasswdCheck = "";
    },
  },
  computed: {
    passwdValidChk() {
      //비밀번호 유효성 검사
      if (passwdPattern.test(this.newPasswd) === false) {
        return false;
      } else {
        return true;
      }
    },
    passwdSame() {
      //비밀번호 일치 검사
      if (this.newPasswd === this.newPasswdCheck) {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>
<style>
.dropdowns {
  display: flex;
  gap: 50px;
  justify-content: flex-start;
}
h5 {
  text-align: start;
}
</style>
