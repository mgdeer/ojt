<template>
  <!-- Modal -->
  <div
    class="modal fade"
    id="editMemberModal"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="editMemberModal"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="editMemberModal">
            <i class="bi bi-person-fill-gear"></i> 사원 정보 수정
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
            <button
              type="button"
              class="btn btn-outline-danger"
              @click="emailDup"
            >
              중복 확인
            </button>
          </div>
          <!-- 유효값 경고 -->
          <div style="height: 40px">
            <div>
              <p class="pass" v-if="emailValidChk && emailDupCheck">
                사용 가능한 이메일
              </p>
              <p class="warning" v-else>사용 불가능한 이메일(중복 확인 필요)</p>
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
            <button
              type="button"
              class="btn btn-outline-danger"
              @click="phoneNumDup"
            >
              중복 확인
            </button>
          </div>
          <!-- 유효값 경고 -->
          <div style="height: 40px">
            <div>
              <p class="pass" v-if="telValidChk && phoneDupCheck">
                사용 가능한 번호
              </p>
              <p class="warning" v-else>사용 불가능한 번호(중복 확인 필요)</p>
            </div>
          </div>
          <!-- 유효값 경고 -->
          <h5>연봉(단위 만원)</h5>
          <div class="input-group" style="margin-bottom: 40px">
            <span class="input-group-text" id="basic-addon4"
              ><i class="bi bi-cash"></i
            ></span>
            <input
              type="text"
              class="form-control"
              placeholder="연봉을 입력해주세요"
              aria-label="Usersalary"
              aria-describedby="basic-addon4"
              v-model="memberInfo.salary"
            />
          </div>
          <!-- 역할 부서 -->
          <div style="margin: 0 0 40px 0">
            역할 및 부서를 다시 선택해주세요.
          </div>
          <div class="dropdowns" style="margin-bottom: 40px">
            <div class="dropdownBox">
              <h5>역할</h5>
              <div class="dropdown">
                <button
                  class="btn btn-outline-secondary dropdown-toggle"
                  type="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                  v-if="
                    memberInfo.role === 'ROLE_ADMINISTRATOR' ||
                    memberInfo.role === '관리자'
                  "
                >
                  <i class="bi bi-person-badge-fill"></i> 역할 : 관리자
                </button>
                <button
                  class="btn btn-outline-secondary dropdown-toggle"
                  type="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                  v-else-if="
                    memberInfo.role === 'ROLE_USER' ||
                    memberInfo.role === '사원'
                  "
                >
                  <i class="bi bi-person-badge-fill"></i> 역할 : 사원
                </button>
                <button
                  class="btn btn-outline-secondary dropdown-toggle"
                  type="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                  v-else
                >
                  <i class="bi bi-person-badge-fill"></i> 역할 : 최고 관리자
                </button>
                <ul class="dropdown-menu">
                  <li>
                    <div
                      v-if="loginedPosion === 'ROLE_CHIEF'"
                      class="dropdown-item"
                      @click="setPosition('관리자')"
                    >
                      관리자
                    </div>
                  </li>
                  <li>
                    <div class="dropdown-item" @click="setPosition('사원')">
                      사원
                    </div>
                  </li>
                </ul>
              </div>
            </div>
            <div class="dropdownBox">
              <h5>부서</h5>
              <div class="dropdown">
                <button
                  class="btn btn-outline-secondary dropdown-toggle"
                  type="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  <i class="bi bi-person-workspace"></i> 부서 :
                  {{ memberInfo.department }}
                </button>
                <ul class="dropdown-menu">
                  <li>
                    <div class="dropdown-item" @click="setDepartment('영업')">
                      영업
                    </div>
                  </li>
                  <li>
                    <div
                      class="dropdown-item"
                      @click="setDepartment('프론트엔드')"
                    >
                      프론트엔드
                    </div>
                  </li>
                  <li>
                    <div class="dropdown-item" @click="setDepartment('백엔드')">
                      백엔드
                    </div>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <!-- 역할 부서 -->
          <!-- 유효값 경고 -->
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
            @click="infoSubmit"
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
// 정규식 에러 해결
// eslint-disable-next-line
const emailPattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-za-z0-9\-]+/;
const phoneNumpattern =
  /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/;

//세션의 로그인된 유저 정보
const user = JSON.parse(sessionStorage.getItem("logined"));

//엑시오스 주소
const api = "http://localhost:8080";

export default {
  name: "editMember",
  components: {},
  props: {
    editMemberInfo: Object,
  },

  data() {
    return {
      checkInfo: {},
      memberInfo: {
        name: "",
        email: "",
        phone: "",
        salary: "",
        role: "",
        department: "",
      },
      inputCheck: true,
      loginedPosion: "",
      emailDupCheck: false,
      phoneDupCheck: false,
    };
  },
  watch: {
    editMemberInfo: function (newVal, oldVal) {
      console.log("message 변경됨:", newVal, oldVal);
      this.memberInfo = JSON.parse(JSON.stringify(newVal));
      this.checkInfo = JSON.parse(JSON.stringify(newVal));
      console.log(this.memberInfo);
    },
  },
  methods: {
    emailDup() {
      if (this.checkInfo.email === this.memberInfo.email) {
        this.emailDupCheck = true;
      } else {
        axios
          .get(`${api}/member/emailCheck/${this.memberInfo.email}`)
          .then((response) => {
            console.log(response.data);
            this.emailDupCheck = response.data;
            console.log(this.emailDupCheck);
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    phoneNumDup() {
      if (this.checkInfo.phone === this.memberInfo.phone) {
        this.phoneDupCheck = true;
      } else {
        axios
          .get(`${api}/member/phoneCheck/${this.memberInfo.phone}`)
          .then((response) => {
            console.log(response);
            this.phoneDupCheck = response.data;
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    //수정 버튼 눌렀을 때 메소드
    infoSubmit() {
      if (this.memberInfo.role === "관리자") {
        this.memberInfo.role = "ROLE_ADMINISTRATOR";
      } else {
        this.memberInfo.role = "ROLE_USER";
      }
      if (
        this.memberInfo.name.length > 0 &&
        this.memberInfo.salary.length > 0 &&
        this.memberInfo.role.length > 0 &&
        this.memberInfo.department.length > 0 &&
        this.emailValidChk &&
        this.telValidChk
      ) {
        console.log(this.memberInfo);
        axios
          .put(
            `${api}/member/${
              JSON.parse(JSON.stringify(this.editMemberInfo)).id
            }`,
            this.memberInfo
          )
          .then((response) => {
            console.log(response);
          })
          .catch((error) => {
            console.log(error);
          });
        //데이터 전송 필요.
        window.location.href = `/management/member/${
          JSON.parse(sessionStorage.getItem("logined")).sub
        }`;
      } else {
        this.inputCheck = false;
      }
    },
    // 역할 값 메소드
    setPosition(v) {
      this.memberInfo.role = v;
    },
    // 부서 값 메소드
    setDepartment(v) {
      this.memberInfo.department = v;
    },
    //닫기 버튼 눌렀을 때 메소드
    reset() {
      this.inputCheck = true;
      //받아온 기본 값으로 변경
      this.memberInfo = {
        name: "",
        email: "",
        phone: "",
        salary: "",
        position: "",
        department: "",
      };
    },
  },
  computed: {
    // 이메일 인풋 유효성 검사
    emailValidChk() {
      if (emailPattern.test(this.memberInfo.email) === false) {
        return false;
      } else {
        return true;
      }
    },
    // 번호 인풋 유효성 검사
    telValidChk() {
      if (phoneNumpattern.test(this.memberInfo.phone) === false) {
        return false;
      } else {
        return true;
      }
    },
  },
  mounted() {
    //로그인된 유저의 포지션
    if (user !== null) {
      this.loginedPosion = user.auth;
    }
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
