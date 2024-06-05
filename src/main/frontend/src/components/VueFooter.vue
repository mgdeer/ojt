<template>
  <div class="navbar-dark bg-dark">
    <router-link class="footerlink" to="/"
      ><h4 class="footerTitle">OJT Project</h4></router-link
    >
    <ul class="footerLink">
      <li>
        <router-link class="footerlink" to="/">Home</router-link>
      </li>
      <li>
        <router-link class="footerlink" to="/product">제품 정보</router-link>
      </li>
    </ul>
    <div class="footerText">
      <div>주소 : 서울특별시 관악구 관악로 1</div>
      <div>전화 : 02-880-5114</div>
    </div>
  </div>
</template>
<script>
export default {
  name: "vueFooter",
  components: {},
};
</script>
<style>
.navbar-dark {
  z-index: 100;
  padding-right: var(--bs-gutter-x, 0.75rem);
  padding-left: var(--bs-gutter-x, 0.75rem);
}

.footerTitle {
  padding: 10px 0;
  color: white;
}
.footerLink {
  margin: 0;
}
.footerlink {
  text-decoration: none;
  color: white;
}
.footerText {
  padding: 10px 30px;
  color: white;
}
</style>
