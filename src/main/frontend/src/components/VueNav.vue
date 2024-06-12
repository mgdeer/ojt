<template>
  <LoginModal />
  <nav
    style="position: sticky; top: 0; z-index: 100"
    class="navbar navbar-expand-lg navbar-dark bg-dark"
  >
    <div class="container-fluid">
      <router-link
        class="navbar-brand"
        v-if="isLogin"
        :to="`/${userInfo.userId}`"
        ><span>OJT Project</span></router-link
      >
      <router-link class="navbar-brand" v-else to="/"
        ><span>OJT Project</span></router-link
      >
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <!-- 왼쪽 네비 링크 -->
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link
              class="nav-link"
              v-if="isLogin"
              :to="`/product/${userInfo.userId}`"
              >제품 정보</router-link
            >
            <router-link class="nav-link" v-else to="/product"
              >제품 정보</router-link
            >
          </li>
        </ul>
        <!-- 왼쪽 네비 링크 -->
        <!-- 오른쪽 네비 링크 -->
        <ul class="d-flex navbar-nav" style="gap: 10px">
          <li class="nav-item">
            <router-link
              v-if="isLogin"
              class="nav-link"
              :to="`/myinfo/${userInfo.userId}`"
              >내 정보</router-link
            >
          </li>
          <li class="nav-item">
            <router-link
              v-if="
                userInfo.position === 'ROLE_ADMINISTRATOR' ||
                userInfo.position === 'ROLE_CHIEF'
              "
              class="nav-link"
              :to="`/management/member/${userInfo.userId}`"
              >관리</router-link
            >
          </li>
          <li class="nav-item">
            <button
              v-if="!isLogin"
              type="button"
              class="btn btn-outline-success"
              data-bs-toggle="modal"
              data-bs-target="#loginModal"
            >
              로그인
            </button>
            <button
              v-else
              type="button"
              class="btn btn-outline-danger"
              @click="logout"
            >
              로그아웃
            </button>
          </li>
        </ul>
        <!-- 오른쪽 네비 링크 -->
      </div>
    </div>
  </nav>
</template>
<script>
import axios from "axios";
import VueJwtDecode from "vue-jwt-decode";
import LoginModal from "./LoginModal.vue";
const api = "http://localhost:8080"; //스프링부트 주소
const user = JSON.parse(sessionStorage.getItem("logined")); // 로그인한 유저 정보

export default {
  name: "VueNav",
  data() {
    return {
      isLogin: false,
      userInfo: {
        userId: "",
        position: "",
      },
    };
  },
  components: {
    LoginModal,
  },
  methods: {
    logout() {
      //로그아웃 클릭시 메소드
      console.log("로그아웃");
      sessionStorage.removeItem("logined");
      sessionStorage.removeItem("accessToken");
      sessionStorage.removeItem("refreshToken");
      sessionStorage.removeItem("refresh");
      this.isLogin = false;
      this.userInfo = {
        userId: "",
        position: "",
      };
      window.location.href = `/`;
    },
  },
  mounted() {
    // 로그인 정보 관리
    if (user !== null) {
      this.isLogin = true;
      this.userInfo.userId = user.sub;
      this.userInfo.position = user.auth;
      let setTimeRun =
        JSON.parse(sessionStorage.getItem("refresh")) - new Date();
      setInterval(() => {
        //시간마다 토큰 리셋
        let accessToken = sessionStorage.getItem("accessToken");
        let refreshToken = sessionStorage.getItem("refreshToken");
        axios
          .post(`${api}/member/reissue`, {
            accessToken: accessToken,
            refreshToken: refreshToken,
          })
          .then(function (res) {
            // console.log(res);
            let accessToken = res.data.accessToken;
            let refreshToken = res.data.refreshToken;
            sessionStorage.setItem("accessToken", accessToken);
            sessionStorage.setItem("refreshToken", refreshToken);
            //vue-jwt-decode 사용해서 JWT를 decode 한다.
            let decodedToken = VueJwtDecode.decode(accessToken);
            let date = new Date();
            let refresh = date.setMinutes(date.getMinutes() + 25);
            // console.log(decodedToken);
            // console.log("리프레시 시간 :", refresh);
            sessionStorage.setItem("logined", JSON.stringify(decodedToken));
            sessionStorage.setItem("refresh", refresh);
          })
          .catch(function (err) {
            console.log(err);
          });
      }, setTimeRun);
    }
    // console.log("유져", user);
  },
};
</script>
<style></style>
