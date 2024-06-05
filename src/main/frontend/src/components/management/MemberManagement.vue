<template>
  <div id="page-wrapper">
    <SideBar />
    <!-- 본문 -->
    <div id="page-content-wrapper">
      <div class="container-fluid">
        <h1><i class="bi bi-people-fill"></i> 사원 관리</h1>
        <!-- 토탈 수치 -->
        <div class="summaryInfo">
          <div class="chiefNum">
            <div class="numsTitle">최고 관리자</div>
            <div class="nums">{{ chiefNum }}</div>
          </div>
          <div class="managerNum">
            <div class="numsTitle">관리자</div>
            <div class="nums">{{ managerNum }}</div>
          </div>
          <div class="userNum">
            <div class="numsTitle">사원</div>
            <div class="nums">{{ userNum }}</div>
          </div>
        </div>
        <!-- 토탈 수치 -->
        <!-- 그래프 -->
        <div class="graphs">
          <div>
            <div id="chart">
              <apexchart
                type="line"
                height="350"
                :options="chartOptions"
                :series="series"
              ></apexchart>
            </div>
          </div>
        </div>
        <!-- 그래프 -->
        <!-- 체크박스, 서치 -->
        <ul class="options list-group">
          <li class="searchOptions list-group-item">
            <div class="headerTitle">역할 필터</div>
            <div class="form-check">
              <input
                class="form-check-input"
                type="checkbox"
                value=""
                id="allCheckboxPosition"
                @change="positionRestClear"
                v-model="checkedPosition.allCheckPosition"
              />
              <label class="form-check-label" for="allCheckbox"> 전체 </label>
            </div>
            <div class="form-check">
              <input
                class="form-check-input"
                type="checkbox"
                value=""
                id="chiefCheckbox"
                @change="positionAllClear"
                v-model="checkedPosition.chiefCheckPosition"
              />
              <label class="form-check-label" for="chiefCheckbox">
                최고 관리자
              </label>
            </div>
            <div class="form-check">
              <input
                class="form-check-input"
                type="checkbox"
                value=""
                id="managerCheckbox"
                @change="positionAllClear"
                v-model="checkedPosition.managerCheckPosition"
              />
              <label class="form-check-label" for="managerCheckbox">
                관리자
              </label>
            </div>
            <div class="form-check">
              <input
                class="form-check-input"
                type="checkbox"
                value=""
                id="userCheckbox"
                @change="positionAllClear"
                v-model="checkedPosition.userCheckPosition"
              />
              <label class="form-check-label" for="userCheckbox"> 사원 </label>
            </div>
          </li>
          <li class="searchOptions list-group-item">
            <div class="headerTitle">부서 필터</div>
            <div class="form-check">
              <input
                class="form-check-input"
                type="checkbox"
                value=""
                id="allCheckboxDepartment"
                @change="departmentRestClear"
                v-model="checkedDepartment.allCheckDepartment"
              />
              <label class="form-check-label" for="allCheckboxDepartment">
                전체
              </label>
            </div>
            <div class="form-check">
              <input
                class="form-check-input"
                type="checkbox"
                value=""
                id="salesCheckbox"
                @change="departmentAllClear"
                v-model="checkedDepartment.salesCheckDepartment"
              />
              <label class="form-check-label" for="salesCheckbox"> 영업 </label>
            </div>
            <div class="form-check">
              <input
                class="form-check-input"
                type="checkbox"
                value=""
                id="frontCheckbox"
                @change="departmentAllClear"
                v-model="checkedDepartment.frontCheckDepartment"
              />
              <label class="form-check-label" for="frontCheckbox">
                프론트엔드
              </label>
            </div>
            <div class="form-check">
              <input
                class="form-check-input"
                type="checkbox"
                value=""
                id="backCheckbox"
                @change="departmentAllClear"
                v-model="checkedDepartment.backCheckDepartment"
              />
              <label class="form-check-label" for="backCheckbox">
                백엔드
              </label>
            </div>
          </li>
        </ul>
        <div class="filterBtn">
          <div>
            <div style="margin-bottom: 10px">
              필터링 역할 :
              {{ checkedPosition.allCheckPosition ? "전체" : "" }}
              {{ checkedPosition.chiefCheckPosition ? "최고 관리자" : "" }}
              {{ checkedPosition.managerCheckPosition ? "관리자" : "" }}
              {{ checkedPosition.userCheckPosition ? "사원" : "" }}
            </div>
            <div>
              필터링 부서 :
              {{ checkedDepartment.allCheckDepartment ? "전체" : "" }}
              {{ checkedDepartment.salesCheckDepartment ? "영업" : "" }}
              {{ checkedDepartment.frontCheckDepartment ? "프론트엔드" : "" }}
              {{ checkedDepartment.backCheckDepartment ? "백엔드" : "" }}
            </div>
          </div>
          <button
            class="btn btn-outline-success"
            type="submit"
            @click="setList"
          >
            필터 적용
          </button>
        </div>
        <ul class="options list-group">
          <li class="searchInput list-group-item">
            <div class="headerTitle">이름 검색</div>
            <form class="d-flex">
              <input
                class="form-control me-2"
                type="search"
                placeholder="이름을 입력해주세요"
                aria-label="Search"
                v-model="searchNameValue"
              />
              <button
                class="btn btn-outline-success"
                type="submit"
                @click="searchName"
              >
                Search
              </button>
            </form>
          </li>
        </ul>
        <!-- 체크박스, 서치 -->
        <!-- 리스트 해더 -->
        <div class="listHeader">
          <h4 class="listTitle">사원 리스트</h4>
          <!-- Button trigger modal -->
          <button
            type="button"
            class="btn btn-outline-success"
            data-bs-toggle="modal"
            data-bs-target="#addMemberModal"
          >
            + 사원 추가
          </button>
          <!-- 모달 -->
          <AddMember />
          <!-- 모달 -->
        </div>
        <!-- 리스트 해더 -->
        <!-- 리스트 -->
        <div class="listItems">
          <table class="table">
            <thead>
              <tr>
                <th class="tableText" scope="col">사원 번호</th>
                <th class="tableText" scope="col">이름</th>
                <th class="tableText" scope="col">역할</th>
                <th class="tableText" scope="col">부서</th>
                <th class="tableText" scope="col">연봉</th>
                <th class="tableText" scope="col">연락처</th>
                <th class="tableText" scope="col">수정 날짜</th>
                <th class="tableText" scope="col">생성 날짜</th>
                <th class="tableText" scope="col">상태</th>
                <th class="tableText" scope="col">수정 및 삭제</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="member in finalFilter" :key="member.id">
                <th class="tableText" scope="row">{{ member.id }}</th>
                <td class="tableText">{{ member.name }}</td>
                <td class="tableText">{{ member.position }}</td>
                <td class="tableText">{{ member.department }}</td>
                <td class="tableText">{{ member.salary }}만원</td>
                <td class="tableText">
                  <div class="tavleContact">
                    <div>{{ member.email }}</div>
                    <div>{{ member.phoneNum }}</div>
                  </div>
                </td>
                <td class="tableText">{{ member.editDate }}</td>
                <td class="tableText">{{ member.createDate }}</td>
                <!-- 비밀번호를 변경 했을 경우 채크 아닐경우 x -->
                <td class="tableText">
                  <i style="color: green" class="bi bi-check-circle"></i>
                  /
                  <i style="color: red" class="bi bi-x-circle"></i>
                </td>
                <td class="tableText">
                  <div class="listBtns">
                    <i
                      class="bi bi-pencil-square"
                      type="button"
                      data-bs-toggle="modal"
                      data-bs-target="#eidtMemberModal"
                      @click="eidtMember(member.id)"
                    ></i>
                    <EditMember :editMemberNum="editMemberNum" />
                    <i
                      style="color: red"
                      class="bi bi-person-dash"
                      type="button"
                      data-bs-toggle="modal"
                      data-bs-target="#deleteMemberModal"
                      @click="deleteMember(member.id, member.name)"
                    ></i>
                    <DeleteMember
                      :deleteMemberNum="deleteMemberNum"
                      :deleteMemberName="deleteMemberName"
                    />
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <!-- 리스트 -->
      </div>
    </div>
    <!-- /본문 -->
  </div>
</template>
<script>
import SideBar from "./SideBar.vue";
import VueApexCharts from "vue3-apexcharts";
import AddMember from "./AddMember.vue";
import EditMember from "./EditMember.vue";
import DeleteMember from "./DeleteMember.vue";

export default {
  name: "memberManagement",
  data() {
    return {
      series: [
        {
          name: "사원",
          data: [0, 0, 0, 0, 0],
        },
      ],
      chartOptions: {
        chart: {
          height: 350,
          type: "line",
          zoom: {
            enabled: false,
          },
        },
        dataLabels: {
          enabled: false,
        },
        stroke: {
          curve: "straight",
        },
        grid: {
          row: {
            colors: ["#f3f3f3", "transparent"], // takes an array which will be repeated on columns
            opacity: 0.5,
          },
        },
        yaxis: {
          min: 0,
          max: 10,
        },
        xaxis: {
          categories: ["2020", "2021", "2022", "2023", "2024"],
        },
      },
      userNumYears: [0, 0, 0, 0, 0],
      editMemberNum: "",
      deleteMemberNum: "",
      deleteMemberName: "",
      checkedPosition: {
        allCheckPosition: true,
        chiefCheckPosition: false,
        managerCheckPosition: false,
        userCheckPosition: false,
      },
      checkedDepartment: {
        allCheckDepartment: true,
        salesCheckDepartment: false,
        frontCheckDepartment: false,
        backCheckDepartment: false,
      },
      searchNameValue: "",
      members: [
        {
          id: 1,
          email: "kim123@naver.com",
          phoneNum: "01012341234",
          department: "프론트엔드",
          salary: 6000,
          name: "김유성",
          position: "최고 관리자",
          createDate: "2020-06-04",
          editDate: "",
        },
        {
          id: 2,
          email: "ryu123@naver.com",
          phoneNum: "01012341234",
          department: "백엔드",
          salary: 4000,
          name: "류지혁",
          position: "관리자",
          createDate: "2020-06-04",
          editDate: "",
        },
        {
          id: 3,
          email: "chu123@naver.com",
          phoneNum: "01012341234",
          department: "백엔드",
          salary: 4500,
          name: "추해성",
          position: "관리자",
          createDate: "2021-06-04",
          editDate: "",
        },
        {
          id: 4,
          email: "Choi123@naver.com",
          phoneNum: "01012341234",
          department: "프론트엔드",
          salary: 4400,
          name: "최하훈",
          position: "관리자",
          createDate: "2021-06-04",
          editDate: "",
        },
        {
          id: 5,
          email: "Tak123@naver.com",
          phoneNum: "01012341234",
          department: "프론트엔드",
          salary: 4000,
          name: "탁지석",
          position: "관리자",
          createDate: "2021-06-04",
          editDate: "",
        },
        {
          id: 6,
          email: "young123@naver.com",
          phoneNum: "01012341234",
          department: "프론트엔드",
          salary: 3800,
          name: "최은영",
          position: "사원",
          createDate: "2021-06-04",
          editDate: "",
        },
        {
          id: 7,
          email: "sim123@naver.com",
          phoneNum: "01012341234",
          department: "프론트엔드",
          salary: 3900,
          name: "심승연",
          position: "사원",
          createDate: "2021-06-04",
          editDate: "",
        },
        {
          id: 8,
          email: "kang23@naver.com",
          phoneNum: "01012341234",
          department: "프론트엔드",
          salary: 3600,
          name: "강하빈",
          position: "사원",
          createDate: "2021-06-04",
          editDate: "",
        },
        {
          id: 9,
          email: "jungsoo123@naver.com",
          phoneNum: "01012341234",
          department: "프론트엔드",
          salary: 4000,
          name: "강정수",
          position: "사원",
          createDate: "2022-06-04",
          editDate: "",
        },
        {
          id: 10,
          email: "jin123@naver.com",
          phoneNum: "01012341234",
          department: "프론트엔드",
          salary: 4200,
          name: "류진욱",
          position: "사원",
          createDate: "2022-06-04",
          editDate: "",
        },
        {
          id: 11,
          email: "nam123@naver.com",
          phoneNum: "01012341234",
          department: "프론트엔드",
          salary: 3000,
          name: "남은용",
          position: "사원",
          createDate: "2022-06-04",
          editDate: "",
        },
        {
          id: 12,
          email: "abc123@naver.com",
          phoneNum: "01012341234",
          department: "백엔드",
          salary: 3000,
          name: "김유성",
          position: "사원",
          createDate: "2022-06-04",
          editDate: "",
        },
        {
          id: 13,
          email: "yang23@naver.com",
          phoneNum: "01012341234",
          department: "영업",
          salary: 3100,
          name: "양현웅",
          position: "사원",
          createDate: "2023-06-04",
          editDate: "",
        },
        {
          id: 14,
          email: "woo123@naver.com",
          phoneNum: "01012341234",
          department: "백엔드",
          salary: 3200,
          name: "추현우",
          position: "사원",
          createDate: "2023-06-04",
          editDate: "",
        },
        {
          id: 15,
          email: "joo23@naver.com",
          phoneNum: "01012341234",
          department: "백엔드",
          salary: 3500,
          name: "심원주",
          position: "사원",
          createDate: "2024-06-04",
          editDate: "",
        },
        {
          id: 16,
          email: "no123@naver.com",
          phoneNum: "01012341234",
          department: "백엔드",
          salary: 3500,
          name: "노범우",
          position: "사원",
          createDate: "2024-06-04",
          editDate: "",
        },
        {
          id: 17,
          email: "park123@naver.com",
          phoneNum: "01012341234",
          department: "영업",
          salary: 3200,
          name: "박영호",
          position: "사원",
          createDate: "2024-06-04",
          editDate: "",
        },
        {
          id: 18,
          email: "pung123@naver.com",
          phoneNum: "01012341234",
          department: "프론트엔드",
          salary: 3300,
          name: "풍혜정",
          position: "사원",
          createDate: "2024-06-04",
          editDate: "",
        },
        {
          id: 19,
          email: "hong123@naver.com",
          phoneNum: "01012341234",
          department: "프론트엔드",
          salary: 3400,
          name: "홍경윤",
          position: "사원",
          createDate: "2024-06-04",
          editDate: "",
        },
        {
          id: 20,
          email: "seol123@naver.com",
          phoneNum: "01012341234",
          department: "프론트엔드",
          salary: 3600,
          name: "설은자",
          position: "사원",
          createDate: "2024-06-04",
          editDate: "",
        },
      ],
      positionFilter: [],
      finalFilter: [],
    };
  },
  components: {
    SideBar,
    AddMember,
    EditMember,
    DeleteMember,
    apexchart: VueApexCharts,
  },
  methods: {
    searchName(e) {
      e.preventDefault();
      console.log(this.searchNameValue);
      this.finalFilter = this.members.filter(
        (v) => v.name === this.searchNameValue
      );
    },
    setList() {
      if (this.checkedPosition.allCheckPosition) {
        this.positionFilter = this.members;
      } else {
        //하나만 선택된 경우
        if (
          this.checkedPosition.chiefCheckPosition &&
          !this.checkedPosition.managerCheckPosition &&
          !this.checkedPosition.userCheckPosition
        ) {
          this.positionFilter = this.members.filter(
            (v) => v.position === "최고 관리자"
          );
        }
        if (
          !this.checkedPosition.chiefCheckPosition &&
          this.checkedPosition.managerCheckPosition &&
          !this.checkedPosition.userCheckPosition
        ) {
          this.positionFilter = this.members.filter(
            (v) => v.position === "관리자"
          );
        }
        if (
          !this.checkedPosition.chiefCheckPosition &&
          !this.checkedPosition.managerCheckPosition &&
          this.checkedPosition.userCheckPosition
        ) {
          this.positionFilter = this.members.filter(
            (v) => v.position === "사원"
          );
        }
        //2개 선택된 경우
        if (
          this.checkedPosition.chiefCheckPosition &&
          this.checkedPosition.managerCheckPosition &&
          !this.checkedPosition.userCheckPosition
        ) {
          this.positionFilter = this.members.filter(
            (v) => v.position === "최고 관리자" || v.position === "관리자"
          );
        }
        if (
          this.checkedPosition.chiefCheckPosition &&
          !this.checkedPosition.managerCheckPosition &&
          this.checkedPosition.userCheckPosition
        ) {
          this.positionFilter = this.members.filter(
            (v) => v.position === "최고 관리자" || v.position === "사원"
          );
        }
        if (
          !this.checkedPosition.chiefCheckPosition &&
          this.checkedPosition.managerCheckPosition &&
          this.checkedPosition.userCheckPosition
        ) {
          this.positionFilter = this.members.filter(
            (v) => v.position === "관리자" || v.position === "사원"
          );
        }
        //3개 다 선택된경우
        if (
          this.checkedPosition.chiefCheckPosition &&
          this.checkedPosition.managerCheckPosition &&
          this.checkedPosition.userCheckPosition
        ) {
          this.checkedPosition.allCheckPosition = true;
          this.positionRestClear();
          this.positionFilter = this.members;
        }
      }
      console.log(this.positionFilter);
      // 부서
      if (this.checkedDepartment.allCheckDepartment) {
        this.finalFilter = this.positionFilter;
      } else {
        //하나만 선택된 경우
        if (
          this.checkedDepartment.salesCheckDepartment &&
          !this.checkedDepartment.frontCheckDepartment &&
          !this.checkedDepartment.backCheckDepartment
        ) {
          this.finalFilter = this.positionFilter.filter(
            (v) => v.department === "영업"
          );
        }
        if (
          !this.checkedDepartment.salesCheckDepartment &&
          this.checkedDepartment.frontCheckDepartment &&
          !this.checkedDepartment.backCheckDepartment
        ) {
          this.finalFilter = this.positionFilter.filter(
            (v) => v.department === "프론트엔드"
          );
        }
        if (
          !this.checkedDepartment.salesCheckDepartment &&
          !this.checkedDepartment.frontCheckDepartment &&
          this.checkedDepartment.backCheckDepartment
        ) {
          this.finalFilter = this.positionFilter.filter(
            (v) => v.department === "백엔드"
          );
        }
        //2개 선택된 경우
        if (
          this.checkedDepartment.salesCheckDepartment &&
          this.checkedDepartment.frontCheckDepartment &&
          !this.checkedDepartment.backCheckDepartment
        ) {
          this.finalFilter = this.positionFilter.filter(
            (v) => v.department === "영업" || v.department === "프론트엔드"
          );
        }
        if (
          this.checkedDepartment.salesCheckDepartment &&
          !this.checkedDepartment.frontCheckDepartment &&
          this.checkedDepartment.backCheckDepartment
        ) {
          this.finalFilter = this.positionFilter.filter(
            (v) => v.department === "영업" || v.department === "백엔드"
          );
        }
        if (
          !this.checkedDepartment.salesCheckDepartment &&
          this.checkedDepartment.frontCheckDepartment &&
          this.checkedDepartment.backCheckDepartment
        ) {
          this.finalFilter = this.positionFilter.filter(
            (v) => v.department === "프론트엔드" || v.department === "백엔드"
          );
        }
        //3개 다 선택된경우
        if (
          this.checkedDepartment.salesCheckDepartment &&
          this.checkedDepartment.frontCheckDepartment &&
          this.checkedDepartment.backCheckDepartment
        ) {
          this.checkedDepartment.allCheckDepartment = true;
          this.departmentRestClear();
          this.finalFilter = this.positionFilter;
        }
      }
      console.log(this.finalFilter);
    },
    eidtMember(v) {
      this.editMemberNum = v;
    },
    deleteMember(num, name) {
      this.deleteMemberNum = num;
      this.deleteMemberName = name;
    },
    positionAllClear() {
      if (
        this.checkedPosition.chiefCheckPosition ||
        this.checkedPosition.managerCheckPosition ||
        this.checkedPosition.userCheckPosition
      ) {
        this.checkedPosition.allCheckPosition = false;
      }
    },
    positionRestClear() {
      if (this.checkedPosition.allCheckPosition) {
        this.checkedPosition.chiefCheckPosition = false;
        this.checkedPosition.managerCheckPosition = false;
        this.checkedPosition.userCheckPosition = false;
      }
    },
    departmentAllClear() {
      if (
        this.checkedDepartment.salesCheckDepartment ||
        this.checkedDepartment.frontCheckDepartment ||
        this.checkedDepartment.backCheckDepartment
      ) {
        this.checkedDepartment.allCheckDepartment = false;
      }
    },
    departmentRestClear() {
      if (this.checkedDepartment.allCheckDepartment) {
        this.checkedDepartment.salesCheckDepartment = false;
        this.checkedDepartment.frontCheckDepartment = false;
        this.checkedDepartment.backCheckDepartment = false;
      }
    },
  },
  computed: {
    chiefNum() {
      let count = 0;
      for (let i = 0; i < this.members.length; i++) {
        this.members[i].position === "최고 관리자" && count++;
      }
      return count;
    },
    managerNum() {
      let count = 0;
      for (let i = 0; i < this.members.length; i++) {
        this.members[i].position === "관리자" && count++;
      }
      return count;
    },
    userNum() {
      let count = 0;
      for (let i = 0; i < this.members.length; i++) {
        this.members[i].position === "사원" && count++;
      }
      return count;
    },
  },
  mounted() {
    for (let i = 0; i < this.members.length; i++) {
      this.members[i].createDate.slice(0, 4) === "2020" &&
        this.userNumYears[0]++;
      this.members[i].createDate.slice(0, 4) === "2021" &&
        this.userNumYears[1]++;
      this.members[i].createDate.slice(0, 4) === "2022" &&
        this.userNumYears[2]++;
      this.members[i].createDate.slice(0, 4) === "2023" &&
        this.userNumYears[3]++;
      this.members[i].createDate.slice(0, 4) === "2024" &&
        this.userNumYears[4]++;
    }
    this.series = [{ name: "사원", data: this.userNumYears }];
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
.summaryInfo {
  margin: 20px 0;
  display: flex;
  justify-content: space-around;
}
.chiefNum {
  border-radius: 10px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
}
.managerNum {
  border-radius: 10px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
}
.userNum {
  border-radius: 10px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
}
.numsTitle {
  padding: 10px;
  font-size: 18px;
  font-weight: bold;
  width: 150px;
  text-align: center;
}
.nums {
  text-align: center;
  padding: 10px;
}
.graphs {
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
}
.options {
  margin: 20px 0;
}
.searchOptions {
  height: 60px;
  display: flex;
  align-items: center;
  gap: 20px;
}
.searchInput {
  height: 60px;
  display: flex;
  align-items: center;
  gap: 20px;
}
.headerTitle {
  padding: 0 20px 0 0;
  border-right: 1px solid black;
}
.listHeader {
  display: flex;
  width: 100%;
  margin: 10px 0;
  justify-content: space-between;
}
.listTitle {
  margin-right: var(--bs-gutter-x, 0.75rem);
  margin-left: var(--bs-gutter-x, 0.75rem);
}
.tableText {
  text-align: center;
}
.table {
}
.listBtns {
  display: flex;
  gap: 10px;
  align-items: center;
  justify-content: center;
}
.filterBtn {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-left: 10px;
}
</style>
