<template>
  <div>
    <VueNav></VueNav>
    <router-view />
  </div>
</template>

<script>
import VueNav from "./components/VueNav.vue";

export default {
  name: "App",
  components: {
    VueNav,
  },
};
</script>

<style>
.warning {
  color: red;
}
</style>
