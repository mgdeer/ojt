<template>
  <div id="container">
    <VueNav />
    <router-view />
    <VueFooter id="footer" />
  </div>
</template>

<script>
import VueNav from "./components/VueNav.vue";
import VueFooter from "./components/VueFooter.vue";
export default {
  name: "App",
  components: {
    VueNav,
    VueFooter,
  },
};
</script>

<style>
.warning {
  color: red;
  text-align: start;
}
.pass {
  color: blue;
  text-align: start;
}
#container {
  display: flex;
  flex-direction: column;
  min-height: 100vh; /* viewport의 전체 높이를 차지 */
}

#footer {
  margin-top: auto; /* footer를 #app의 맨 아래로 보냄 */
}
</style>
