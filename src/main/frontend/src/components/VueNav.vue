<template>
  <LoginModal />
  <nav
    style="position: sticky; top: 0; z-index: 100"
    class="navbar navbar-expand-lg navbar-dark bg-dark"
  >
    <div class="container-fluid">
      <router-link class="navbar-brand" to="/"
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
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link class="nav-link active" aria-current="page" to="/"
              >Home</router-link
            >
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/product">제품 정보</router-link>
          </li>
        </ul>
        <ul class="d-flex navbar-nav">
          <li class="nav-item">
            <router-link
              v-if="isLogin && position === '사용자'"
              class="nav-link"
              :to="`/myinfo/${userName}`"
              >내 정보</router-link
            >
          </li>
          <li class="nav-item">
            <router-link
              v-if="isLogin && position === '관리자'"
              class="nav-link"
              :to="`/management/member/${position}`"
              >회원 정보</router-link
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
      </div>
    </div>
  </nav>
</template>
<script>
import LoginModal from "./LoginModal.vue";
export default {
  name: "VueNav",
  data() {
    return {
      userName: "bob",
      isLogin: true,
      position: "관리자",
    };
  },
  components: {
    LoginModal,
  },
  methods: {
    logout() {
      console.log("로그아웃");
    },
  },
};
</script>
<style></style>
