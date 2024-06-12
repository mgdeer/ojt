<template>
  <div class="myInfo">
    <div class="myInfoContents">
      <div class="myInfoTitle"><i class="bi bi-person-circle"></i>내 정보</div>
      <div class="infoTexts">
        <i class="bi bi-person-fill"></i>{{ userInfo.name }}
      </div>
      <div class="infoRow">
        <div>
          <div class="infoTexts" v-if="userInfo.role === 'ROLE_CHIEF'">
            <i class="bi bi-person-badge-fill"></i>최고 관리자
          </div>
          <div
            class="infoTexts"
            v-else-if="userInfo.role === 'ROLE_ADMINISTRATOR'"
          >
            <i class="bi bi-person-badge-fill"></i>관리자
          </div>
          <div class="infoTexts" v-else>
            <i class="bi bi-person-badge-fill"></i>사원
          </div>
        </div>
        <div>
          <div class="infoTexts">
            <i class="bi bi-person-workspace"></i>{{ userInfo.department }}
          </div>
        </div>
        <div>
          <div class="infoTexts">
            <i class="bi bi-cash"></i>{{ userInfo.salary }}만원
          </div>
        </div>
      </div>
      <div>
        <div class="infoTexts">
          <i class="bi bi-envelope-at-fill"></i>{{ userInfo.email }}
        </div>
      </div>
      <div>
        <div class="infoTexts">
          <i class="bi bi-phone-fill"></i>{{ userInfo.phone }}
        </div>
      </div>
      <!-- 버튼 -->
      <div class="myInfoBtns">
        <button
          type="button"
          class="btn btn-outline-warning myInfoBtn"
          data-bs-toggle="modal"
          data-bs-target="#editMyinfoModal"
        >
          정보 수정
        </button>
        <EditMyInfo />
        <button
          type="button"
          class="btn btn-outline-warning myInfoBtn"
          data-bs-toggle="modal"
          data-bs-target="#editPasswdModal"
        >
          비밀 번호 변경
        </button>
        <EditPasswd />
      </div>
      <!-- 버튼 -->
    </div>
  </div>
</template>
<script>
import EditMyInfo from "./EditMyInfo.vue";
import EditPasswd from "./EditPasswd.vue";
import axios from "axios";
const api = "http://localhost:8080"; //스프링부트 주소
const user = JSON.parse(sessionStorage.getItem("logined")); // 로그인한 유저 정보

export default {
  name: "myInfo",
  components: {
    EditMyInfo,
    EditPasswd,
  },
  data() {
    return {
      userInfo: {},
    };
  },
  mounted() {
    //유저 정보 받아오기
    axios
      .get(`${api}/member/${user.sub}`)
      .then((response) => {
        console.log(response);
        this.userInfo = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
    console.log(this.userInfo);
  },
};
</script>
<style>
.myInfo {
  height: 70vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.myInfoTitle {
  font-size: 30px;
  padding: 10px 0;
  border-bottom: 1px solid;
}
.myInfoContents {
  margin: 20px auto;
  gap: 30px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 20px;
  border-radius: 20px;
  box-shadow: 0 7px 14px rgba(0, 0, 0, 0.25), 0 5px 5px rgba(0, 0, 0, 0.22);
}
.bi {
  margin-right: 5px;
}
.infoRow {
  display: flex;
  gap: 50px;
}
.infoTexts {
  font-size: 18px;
}
.myInfoBtns {
  display: flex;
  justify-content: space-evenly;
}
.myInfoBtn {
  color: black;
  box-shadow: 0 7px 14px rgba(0, 0, 0, 0.25), 0 5px 5px rgba(0, 0, 0, 0.22);
}
</style>
