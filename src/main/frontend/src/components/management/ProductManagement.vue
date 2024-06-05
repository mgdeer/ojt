<template>
  <div id="page-wrapper">
    <SideBar />
    <!-- 본문 -->
    <div id="page-content-wrapper">
      <div class="container-fluid">
        <h1><i class="bi bi-boxes"></i> 제품 관리</h1>
        <div class="searchOptions">
          <div class="headerTitle">검색 옵션</div>
          <div class="form-check">
            <input
              class="form-check-input"
              type="checkbox"
              value=""
              id="allCheckbox"
            />
            <label class="form-check-label" for="allCheckbox"> 전체 </label>
          </div>
          <div class="form-check">
            <input
              class="form-check-input"
              type="checkbox"
              value=""
              id="product1"
            />
            <label class="form-check-label" for="product1"> 제품1 </label>
          </div>
          <div class="form-check">
            <input
              class="form-check-input"
              type="checkbox"
              value=""
              id="product2"
            />
            <label class="form-check-label" for="product2"> 제품2 </label>
          </div>
          <div class="form-check">
            <input
              class="form-check-input"
              type="checkbox"
              value=""
              id="product3"
            />
            <label class="form-check-label" for="product3"> 제품3</label>
          </div>
        </div>
        <div class="searchInput">
          <div class="headerTitle">제품 검색</div>
          <form class="d-flex">
            <input
              class="form-control me-2"
              type="search"
              placeholder="Search"
              aria-label="Search"
            />
            <button class="btn btn-outline-success" type="submit">
              Search
            </button>
          </form>
        </div>
      </div>
    </div>
    <!-- /본문 -->
  </div>
</template>
<script>
import SideBar from "./SideBar.vue";
export default {
  name: "productManagement",
  data() {
    return {
      position: "관리자",
    };
  },
  components: {
    SideBar,
  },
};
</script>
<style>
#page-wrapper {
  padding-left: 250px;
}

#page-content-wrapper {
  width: 100%;
  padding: 20px;
}
.searchOptions {
  display: flex;
  align-items: center;
  gap: 20px;
}
.searchInput {
  display: flex;
  align-items: center;
  gap: 20px;
}
.headerTitle {
  padding: 0 20px 0 0;
  border-right: 1px solid black;
}
</style>
