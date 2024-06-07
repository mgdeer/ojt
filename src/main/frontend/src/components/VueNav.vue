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
        :to="`/${userInfo.userName}`"
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
              class="nav-link active"
              aria-current="page"
              v-if="isLogin"
              :to="`/${userInfo.userName}`"
              >Home</router-link
            >
            <router-link
              class="nav-link active"
              aria-current="page"
              v-else
              to="/"
              >Home</router-link
            >
          </li>
          <li class="nav-item">
            <router-link
              class="nav-link"
              v-if="isLogin"
              :to="`/product/${userInfo.userName}`"
              >제품 정보</router-link
            >
            <router-link class="nav-link" v-else to="/product"
              >제품 정보</router-link
            >
          </li>
        </ul>
        <!-- 왼쪽 네비 링크 -->
        <!-- 오른쪽 네비 링크 -->
        <ul class="d-flex navbar-nav">
          <li class="nav-item">
            <router-link
              v-if="isLogin"
              class="nav-link"
              :to="`/myinfo/${userInfo.userName}`"
              >내 정보</router-link
            >
          </li>
          <li class="nav-item">
            <router-link
              v-if="
                userInfo.position === '관리자' ||
                userInfo.position === '최고 관리자'
              "
              class="nav-link"
              :to="`/management/member/${userInfo.userName}`"
              >사원 관리</router-link
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
import LoginModal from "./LoginModal.vue";
// 로그인한 유저 정보
const user = JSON.parse(sessionStorage.getItem("setUser"));

export default {
  name: "VueNav",
  data() {
    return {
      isLogin: false,
      userInfo: {
        userId: "",
        userName: "",
        position: "",
      },
    };
  },
  components: {
    LoginModal,
  },
  methods: {
    //로그아웃 클릭시 메소드
    logout() {
      console.log("로그아웃");
      sessionStorage.removeItem("setUser");
      this.isLogin = false;
      this.userInfo.userId = "";
      this.userInfo.userName = "";
      this.userInfo.position = "";
      window.location.href = `/`;
    },
  },
  mounted() {
    //로그인 정보 관리
    if (user !== null) {
      this.isLogin = true;
      this.userInfo.userId = user.userId;
      this.userInfo.userName = user.userName;
      this.userInfo.position = user.position;
    }
    console.log("유져", user);
  },
};
</script>
<style></style>
