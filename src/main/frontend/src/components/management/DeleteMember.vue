<template>
  <!-- Modal -->
  <div
    class="modal fade"
    id="deleteMemberModal"
    data-bs-backdrop="static"
    data-bs-keyboard="false"
    tabindex="-1"
    aria-labelledby="deleteMemberModalLabal"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="deleteMemberModalLabal">
            <i class="bi bi-person-fill-dash"></i> 사원 삭제
          </h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <!-- 모달 안 내용 -->
          {{ deleteMemberName }}을(를) 삭제 하시겠습니까?
          <!-- 모달 안 내용 -->
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-outline-secondary"
            data-bs-dismiss="modal"
          >
            닫기
          </button>
          <button
            type="button"
            class="btn btn-outline-danger"
            data-bs-dismiss="modal"
            @click="deleteUser"
          >
            삭제
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
//엑시오스 임포트
import axios from "axios";

//엑시오스 주소
const api = "http://localhost:8080";

export default {
  name: "deleteMember",
  components: {},
  props: {
    deleteMemberNum: Number,
    deleteMemberName: String,
  },
  methods: {
    deleteUser() {
      //백엔드 연동 필요
      axios
        .delete(`${api}/member/${this.deleteMemberNum}`)
        .then((response) => {
          console.log(response);
          window.location.href = `/management/member/${this.deleteMemberNum}`;
        })
        .catch((error) => {
          console.log(error);
        });
      console.log("삭제 할 유저 아이디", this.deleteMemberNum);
    },
  },
};
</script>
<style>
.dropdowns {
  display: flex;
  gap: 50px;
  justify-content: flex-start;
}
h5 {
  text-align: start;
}
</style>
