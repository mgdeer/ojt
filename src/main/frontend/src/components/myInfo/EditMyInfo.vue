<template>
  <!-- Modal -->
  <div
    class="modal fade"
    id="editMyinfoModal"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="editMyinfoModal"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="editMyinfoModal">
            <i class="bi bi-person-fill-gear"></i> 내 정보 수정
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
          <!-- 모달 안 내용 -->
          <h5>이름</h5>
          <div class="input-group" style="margin-bottom: 40px">
            <span class="input-group-text" id="basic-addon1"
              ><i class="bi bi-person-fill"></i
            ></span>
            <input
              type="text"
              class="form-control"
              placeholder="이름을 입력해주세요"
              aria-label="Username"
              aria-describedby="basic-addon1"
              v-model="memberInfo.name"
            />
          </div>
          <h5>이메일</h5>
          <div class="input-group">
            <span class="input-group-text" id="basic-addon2"
              ><i class="bi bi-envelope-at-fill"></i
            ></span>
            <input
              type="text"
              class="form-control"
              placeholder="이메일을 입력해주세요"
              aria-label="Useremail"
              aria-describedby="basic-addon2"
              v-model="memberInfo.email"
            />
          </div>
          <!-- 유효값 경고 -->
          <div style="height: 40px">
            <div v-show="memberInfo.email.length > 0">
              <p class="pass" v-if="emailValidChk">사용 가능한 이메일</p>
              <p class="warning" v-else>사용 불가능한 이메일</p>
            </div>
          </div>
          <!-- 유효값 경고 -->
          <h5>전화 번호</h5>
          <div class="input-group">
            <span class="input-group-text" id="basic-addon3"
              ><i class="bi bi-phone-fill"></i
            ></span>
            <input
              type="text"
              class="form-control"
              placeholder="010-0000-0000"
              aria-label="Userphonenum"
              aria-describedby="basic-addon3"
              v-model="memberInfo.phone"
            />
          </div>
          <!-- 유효값 경고 -->
          <div style="height: 40px">
            <div v-show="memberInfo.phone.length > 0">
              <p class="pass" v-if="telValidChk">사용 가능한 번호</p>
              <p class="warning" v-else>사용 불가능한 번호</p>
            </div>
          </div>
          <div v-if="!inputCheck" class="warning">입력 값을 확인해주세요.</div>
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
const api = "http://localhost:8080"; // 스프링부트 주소
// 유효성 검사
// 정규식 에러 해결
// eslint-disable-next-line
const emailPattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-za-z0-9\-]+/;
const phoneNumpattern =
  /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
const user = JSON.parse(sessionStorage.getItem("logined")); //로그인된 유저 정보

export default {
  name: "editMyInfo",
  components: {},
  data() {
    return {
      memberInfo: {
        //입력 받을 값
        id: user.sub, //아이디는 유저정보에서 가져옴
        name: "",
        email: "",
        phone: "",
      },
      inputCheck: true,
    };
  },
  methods: {
    //내정보 수정 메소드
    myInfoSubmit() {
      if (
        this.memberInfo.name.length > 0 &&
        this.emailValidChk &&
        this.telValidChk
      ) {
        console.log(this.memberInfo);
        axios
          .put(`${api}/member/me`, this.memberInfo)
          .then((response) => {
            console.log(response);
          })
          .catch((error) => {
            console.log(error);
          });
        this.memberInfo = {
          id: "",
          name: "",
          email: "",
          phone: "",
        };
        window.location.href = `/myinfo/${
          JSON.parse(sessionStorage.getItem("logined")).sub
        }`;
      } else {
        this.inputCheck = false;
      }
    },
    // 역할 값 메소드
    setPosition(v) {
      this.memberInfo.position = v;
    },
    // 부서 값 메소드
    setDepartment(v) {
      this.memberInfo.department = v;
    },
    reset() {
      // 닫기 클릭시 메소드
      this.memberInfo = {
        name: "",
        email: "",
        phone: "",
      };
    },
  },
  computed: {
    //이메일 유효성 검사
    emailValidChk() {
      if (emailPattern.test(this.memberInfo.email) === false) {
        return false;
      } else {
        return true;
      }
    },
    //번호 유효성 검사
    telValidChk() {
      if (phoneNumpattern.test(this.memberInfo.phone) === false) {
        return false;
      } else {
        return true;
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
