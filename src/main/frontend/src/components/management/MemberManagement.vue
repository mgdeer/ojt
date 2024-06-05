<template>
  <div id="page-wrapper">
    <SideBar />
    <!-- 본문 -->
    <div id="page-content-wrapper">
      <div class="container-fluid">
        <h1><i class="bi bi-people-fill"></i> 회원 관리</h1>
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
            <div class="numsTitle">사용자</div>
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
                id="chiefCheckbox"
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
              />
              <label class="form-check-label" for="userCheckbox">
                사용자
              </label>
            </div>
          </li>
          <li class="searchInput list-group-item">
            <div class="headerTitle">이름 검색</div>
            <form class="d-flex">
              <input
                class="form-control me-2"
                type="search"
                placeholder="이름을 입력해주세요"
                aria-label="Search"
              />
              <button class="btn btn-outline-success" type="submit">
                Search
              </button>
            </form>
          </li>
        </ul>

        <!-- 체크박스, 서치 -->
        <!-- 리스트 해더 -->
        <div class="listHeader">
          <h4 class="listTitle">회원 리스트</h4>
          <!-- Button trigger modal -->
          <button
            type="button"
            class="btn btn-outline-success"
            data-bs-toggle="modal"
            data-bs-target="#addMemberModal"
          >
            + 회원 추가
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
                <th class="tableText" scope="col">회원 번호</th>
                <th class="tableText" scope="col">이름</th>
                <th class="tableText" scope="col">역할</th>
                <th class="tableText" scope="col">이메일</th>
                <th class="tableText" scope="col">생성 날짜</th>
                <th class="tableText" scope="col">수정 및 삭제</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="member in members" :key="member.id">
                <th class="tableText" scope="row">{{ member.id }}</th>
                <td class="tableText">{{ member.name }}</td>
                <td class="tableText">{{ member.position }}</td>
                <td class="tableText">{{ member.email }}</td>
                <td class="tableText">{{ member.createDate }}</td>
                <td class="tableText">
                  <div class="listBtns">
                    <i class="bi bi-pencil-square"></i>
                    <i class="bi bi-person-dash"></i>
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

export default {
  name: "memberManagement",
  data() {
    return {
      series: [
        {
          name: "사용자",
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
      position: "관리자",
      members: [
        {
          id: 1,
          email: "kim123@naver.com",
          name: "김유성",
          position: "최고 관리자",
          createDate: "2020-06-04",
        },
        {
          id: 2,
          email: "ryu123@naver.com",
          name: "류지혁",
          position: "관리자",
          createDate: "2020-06-04",
        },
        {
          id: 3,
          email: "chu123@naver.com",
          name: "추해성",
          position: "관리자",
          createDate: "2021-06-04",
        },
        {
          id: 4,
          email: "Choi123@naver.com",
          name: "최하훈",
          position: "관리자",
          createDate: "2021-06-04",
        },
        {
          id: 5,
          email: "Tak123@naver.com",
          name: "탁지석",
          position: "관리자",
          createDate: "2021-06-04",
        },
        {
          id: 6,
          email: "young123@naver.com",
          name: "최은영",
          position: "사용자",
          createDate: "2021-06-04",
        },
        {
          id: 7,
          email: "sim123@naver.com",
          name: "심승연",
          position: "사용자",
          createDate: "2021-06-04",
        },
        {
          id: 8,
          email: "kang23@naver.com",
          name: "강하빈",
          position: "사용자",
          createDate: "2021-06-04",
        },
        {
          id: 9,
          email: "jungsoo123@naver.com",
          name: "강정수",
          position: "사용자",
          createDate: "2022-06-04",
        },
        {
          id: 10,
          email: "jin123@naver.com",
          name: "류진욱",
          position: "사용자",
          createDate: "2022-06-04",
        },
        {
          id: 11,
          email: "nam123@naver.com",
          name: "남은용",
          position: "사용자",
          createDate: "2022-06-04",
        },
        {
          id: 12,
          email: "abc123@naver.com",
          name: "김유성",
          position: "사용자",
          createDate: "2022-06-04",
        },
        {
          id: 13,
          email: "yang23@naver.com",
          name: "양현웅",
          position: "사용자",
          createDate: "2023-06-04",
        },
        {
          id: 14,
          email: "woo123@naver.com",
          name: "추현우",
          position: "사용자",
          createDate: "2023-06-04",
        },
        {
          id: 15,
          email: "joo23@naver.com",
          name: "심원주",
          position: "사용자",
          createDate: "2024-06-04",
        },
        {
          id: 16,
          email: "no123@naver.com",
          name: "노범우",
          position: "사용자",
          createDate: "2024-06-04",
        },
        {
          id: 17,
          email: "park123@naver.com",
          name: "박영호",
          position: "사용자",
          createDate: "2024-06-04",
        },
        {
          id: 18,
          email: "pung123@naver.com",
          name: "풍혜정",
          position: "사용자",
          createDate: "2024-06-04",
        },
        {
          id: 19,
          email: "hong123@naver.com",
          name: "홍경윤",
          position: "사용자",
          createDate: "2024-06-04",
        },
        {
          id: 20,
          email: "seol123@naver.com",
          name: "설은자",
          position: "사용자",
          createDate: "2024-06-04",
        },
      ],
    };
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
        this.members[i].position === "사용자" && count++;
      }
      return count;
    },
  },
  components: {
    SideBar,
    apexchart: VueApexCharts,
    AddMember,
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
    this.series = [{ name: "사용자", data: this.userNumYears }];
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
</style>
