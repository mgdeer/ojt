<template>
  <!-- 사이드바 -->
  <div id="sidebar-wrapper">
    <ul class="sidebar-nav">
      <div class="sidebar-brand"><i class="bi bi-gear"></i> 관리</div>
      <li>
        <router-link :to="`/management/member/${position}`"
          ><i class="bi bi-people-fill"></i> 회원 관리</router-link
        >
      </li>
      <li>
        <router-link :to="`/management/product/${position}`"
          ><i class="bi bi-boxes"></i> 제품 관리</router-link
        >
      </li>
      <!-- <li><a href="#">메뉴 3</a></li>
      <li><a href="#">메뉴 4</a></li>
      <li><a href="#">메뉴 5</a></li> -->
    </ul>
  </div>
  <!-- /사이드바 -->
</template>
<script>
export default {
  name: "sideBar",
  components: {},
  data() {
    return {
      isLogin: true,
      position: "관리자",
    };
  },
};
</script>
<style>
/* 사이드바 스타일 */
#sidebar-wrapper {
  position: fixed;
  width: 250px;
  height: 100%;
  margin-left: -250px;
  background: #002333;
  overflow-x: hidden;
  overflow-y: auto;
}
.sidebar-nav {
  width: 250px;
  margin: 0;
  padding: 0;
  list-style: none;
}

.sidebar-nav li {
  text-indent: 1.5em;
  line-height: 2.8em;
}

.sidebar-nav li a {
  display: block;
  text-decoration: none;
  color: #999;
}

.sidebar-nav li a:hover {
  color: #fff;
  background: rgba(255, 255, 255, 0.2);
}

.sidebar-nav > .sidebar-brand {
  margin-left: 25px;
  color: #fff;
  font-size: 1.3em;
  line-height: 3em;
}
</style>
