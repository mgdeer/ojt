<template>
  <!-- 캐러샐 -->
  <div
    id="carouselExampleAutoplaying"
    class="carousel slide"
    data-bs-ride="carousel"
  >
    <div class="carousel-inner">
      <div
        class="carousel-item active"
        style="
          background-image: url(https://cdn.abouthr.co.kr/news/photo/202110/5034_7751_411.jpg);
          height: 100vh;
          background-size: cover;
          background-position: center;
        "
      ></div>
      <div
        class="carousel-item"
        style="
          background-image: url(https://i0.wp.com/blog.jandi.com/ko/wp-content/uploads/sites/4/2019/02/achievement-agreement-arms-1068523-e1552760878130.jpg?fit=1200%2C675&ssl=1);
          height: 100vh;
          background-size: cover;
          background-position: center;
        "
      ></div>
      <div
        class="carousel-item"
        style="
          background-image: url(https://mobiinsidecontent.s3.ap-northeast-2.amazonaws.com/kr/wp-content/uploads/2022/05/31185058/%ED%99%94%EB%A9%B4-%EC%BA%A1%EC%B2%98-2022-05-31-185047.png);
          height: 100vh;
          background-size: cover;
          background-position: center;
        "
      ></div>
    </div>
    <button
      class="carousel-control-prev"
      type="button"
      data-bs-target="#carouselExampleAutoplaying"
      data-bs-slide="prev"
    >
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button
      class="carousel-control-next"
      type="button"
      data-bs-target="#carouselExampleAutoplaying"
      data-bs-slide="next"
    >
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
  <!-- 캐러샐 -->
</template>
<script>
export default {
  name: "mainLI",
  components: {},
};
</script>
<style></style>
