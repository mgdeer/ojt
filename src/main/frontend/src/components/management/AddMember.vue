<template>
  <!-- Modal -->
  <div
    class="modal fade"
    id="addMemberModal"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="addMemberModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="addMemberModalLabel">
            <i class="bi bi-person-fill-add"></i> 사원 추가
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
              v-model="memberInfo.phoneNum"
            />
          </div>
          <!-- 유효값 경고 -->
          <div style="height: 40px">
            <div v-show="memberInfo.phoneNum.length > 0">
              <p class="pass" v-if="telValidChk">사용 가능한 번호</p>
              <p class="warning" v-else>사용 불가능한 번호</p>
            </div>
          </div>
          <!-- 유효값 경고 -->
          <h5>연봉(단위 만원)</h5>
          <div class="input-group" style="margin-bottom: 40px">
            <span class="input-group-text" id="basic-addon4"
              ><i class="bi bi-cash"></i
            ></span>
            <input
              type="number"
              class="form-control"
              placeholder="연봉을 입력해주세요"
              aria-label="Usersalary"
              aria-describedby="basic-addon4"
              v-model="memberInfo.salary"
            />
          </div>
          <!-- 역할 부서 -->
          <div class="dropdowns" style="margin-bottom: 40px">
            <div class="dropdownBox">
              <h5>역할</h5>
              <div class="dropdown">
                <button
                  class="btn btn-outline-secondary dropdown-toggle"
                  type="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  <i class="bi bi-person-badge-fill"></i> 역할 :
                  {{ memberInfo.position }}
                </button>
                <ul class="dropdown-menu">
                  <li>
                    <div
                      v-if="loginedPosion === '최고 관리자'"
                      class="dropdown-item"
                      @click="setPosition('ROLE_ADMINISTRATOR')"
                    >
                      관리자
                    </div>
                  </li>
                  <li>
                    <div
                      class="dropdown-item"
                      @click="setPosition('ROLE_USER')"
                    >
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
            사원 추가
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
const user = JSON.parse(sessionStorage.getItem("setUser"));
const api = "http://localhost:8080";

export default {
  name: "addMember",
  components: {},
  data() {
    return {
      memberInfo: {
        name: "",
        email: "",
        phoneNum: "",
        salary: 0,
        position: "",
        department: "",
      },
      loginedPosion: "",
      inputCheck: true,
    };
  },
  methods: {
    //추가 버튼 눌렀을 때 메소드
    infoSubmit() {
      if (
        this.memberInfo.name.length > 0 &&
        this.memberInfo.salary > 0 &&
        this.memberInfo.position.length > 0 &&
        this.memberInfo.department.length > 0 &&
        this.emailValidChk &&
        this.telValidChk
      ) {
        console.log(this.memberInfo);
        axios
          .post(`${api}/create`, {
            name: this.memberInfo.name,
            role: this.memberInfo.position,
            department: this.memberInfo.department,
            email: this.memberInfo.email,
            phone: this.memberInfo.phoneNum,
            salary: this.memberInfo.salary,
          })
          .then(function (response) {
            console.log(response);
            //인풋 폼 리셋
            this.memberInfo = {
              id: 0,
              passwd: "",
              name: "",
              email: "",
              phoneNum: "",
              salary: "",
              position: "",
              department: "",
              createDate: "",
              editeDate: "",
            };
            // window.location.href = `/management/member/${
            //   JSON.parse(sessionStorage.getItem("setUser")).userName
            // }`;
          })
          .catch(function (error) {
            console.log(error);
          });
        //데이터 전송 필요.
      } else {
        this.inputCheck = false;
      }
    },
    //닫기 버튼 눌렀을 때 메소드
    reset() {
      this.inputCheck = true;
      this.memberInfo = {
        id: 0,
        passwd: "",
        name: "",
        email: "",
        phoneNum: "",
        salary: "",
        position: "",
        department: "",
        createDate: "",
        editeDate: "",
      };
    },
    // 역할 값 메소드
    setPosition(v) {
      this.memberInfo.position = v;
    },
    // 부서 값 메소드
    setDepartment(v) {
      this.memberInfo.department = v;
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
    // 전화번호 인풋 유효성 검사
    telValidChk() {
      if (phoneNumpattern.test(this.memberInfo.phoneNum) === false) {
        return false;
      } else {
        return true;
      }
    },
  },
  mounted() {
    //로그인된 유저의 포지션
    this.loginedPosion = user.position;
  },
};
</script>
<style>
.dropdowns {
  display: flex;
  gap: 50px;
  justify-content: flex-start;
}
</style>
