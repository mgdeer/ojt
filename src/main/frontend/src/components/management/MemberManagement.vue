<template>
  <div id="page-wrapper">
    <!-- 사이드바 -->
    <SideBar />
    <!-- 사이드바 -->
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
        <div class="charts">
          <SimpleDonut class="circle" :totalNumArr="totalNumArr" />
          <div class="stick">
            <StickChart
              class="chart"
              :userNumYears="userNumYears"
              :maxNum="maxNum"
            />
            <DistributedColumns class="chart" :avgArr="avgArr" />
          </div>
        </div>
        <!-- 그래프 -->
        <!-- 체크박스, 서치 -->
        <ul class="options list-group">
          <!-- 열할 체크박스 -->
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
          <!-- 열할 체크박스 -->
          <!-- 부서 체크박스 -->
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
          <!-- 서치바 -->
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
                class="btn btn-outline-success manageBtns"
                type="submit"
                @click="searchName"
              >
                Search
              </button>
              <button
                class="btn btn-outline-secondary manageBtns"
                id="resetBtn"
                style="margin-left: 10px"
                type="submit"
                @click="searchNameReset"
              >
                Reset
              </button>
            </form>
          </li>
          <!-- 서치바 -->
        </ul>
        <!-- 필터링 버튼 -->
        <div class="filterBtn">
          <button
            class="btn btn-outline-success manageBtns"
            type="submit"
            @click="setList"
          >
            필터 적용
          </button>
        </div>
        <!-- 필터링 버튼 -->
        <!-- 부서 체크박스 -->
        <!-- 체크박스, 서치 -->
        <!-- 리스트 해더 -->
        <div class="listHeader">
          <h4 class="listTitle">사원 리스트</h4>
          <!-- 사원추가 모달 버튼 -->
          <button
            type="button"
            class="btn btn-outline-success manageBtns"
            data-bs-toggle="modal"
            data-bs-target="#addMemberModal"
          >
            사원 추가
          </button>
          <!-- 사원추가 모달 버튼 -->
          <!-- 사원추가 모달 -->
          <AddMember />
          <!-- 사원추가 모달 -->
        </div>
        <!-- 리스트 해더 -->
        <!-- 리스트 -->
        <div class="listItems">
          <table class="table">
            <!-- 테이블 해더 -->
            <thead>
              <tr>
                <th class="tableText" scope="col">사원 번호</th>
                <th class="tableText" scope="col">이 름</th>
                <th class="tableText" scope="col">역 할</th>
                <th class="tableText" scope="col">부 서</th>
                <th class="tableText" scope="col">
                  <div class="dropdown">
                    <div
                      type="button"
                      data-bs-toggle="dropdown"
                      aria-expanded="false"
                    >
                      연 봉 <i class="bi bi-caret-down-fill"></i>
                    </div>
                    <ul class="dropdown-menu">
                      <li>
                        <div
                          class="dropdown-item"
                          @click="salarySort('오름 차순')"
                        >
                          오름 차순
                        </div>
                      </li>
                      <li>
                        <div
                          class="dropdown-item"
                          @click="salarySort('내림 차순')"
                        >
                          내림 차순
                        </div>
                      </li>
                    </ul>
                  </div>
                </th>
                <th class="tableText" scope="col">연락처</th>
                <th class="tableText" scope="col">수정 날짜</th>
                <th class="tableText" scope="col">
                  <div class="dropdown">
                    <div
                      type="button"
                      data-bs-toggle="dropdown"
                      aria-expanded="false"
                    >
                      생성 날짜 <i class="bi bi-caret-down-fill"></i>
                    </div>
                    <ul class="dropdown-menu">
                      <li>
                        <div
                          class="dropdown-item"
                          @click="dateSort('오름 차순')"
                        >
                          오름 차순
                        </div>
                      </li>
                      <li>
                        <div
                          class="dropdown-item"
                          @click="dateSort('내림 차순')"
                        >
                          내림 차순
                        </div>
                      </li>
                    </ul>
                  </div>
                </th>
                <th class="tableText" scope="col">
                  <div class="dropdown">
                    <div
                      type="button"
                      data-bs-toggle="dropdown"
                      aria-expanded="false"
                    >
                      상 태 <i class="bi bi-caret-down-fill"></i>
                    </div>
                    <ul class="dropdown-menu">
                      <li>
                        <div
                          class="dropdown-item"
                          @click="isEdited('정렬 해제')"
                        >
                          정렬 해제
                        </div>
                      </li>
                      <li>
                        <div
                          class="dropdown-item"
                          @click="isEdited('수정완료')"
                        >
                          수정 완료
                        </div>
                      </li>
                      <li>
                        <div class="dropdown-item" @click="isEdited('수정전')">
                          수정 전
                        </div>
                      </li>
                    </ul>
                  </div>
                </th>
                <th class="tableText" scope="col">수정 및 삭제</th>
              </tr>
            </thead>
            <!-- 테이블 해더 -->
            <!-- 테이블 바디 -->
            <tbody>
              <tr v-for="member in finalFilter" :key="member.id">
                <th class="tableText" scope="row">{{ member.id }}</th>
                <td class="tableText">{{ member.name }}</td>
                <td class="tableText" v-if="member.role === 'ROLE_CHIEF'">
                  최고 관리자
                </td>
                <td
                  class="tableText"
                  v-else-if="member.role === 'ROLE_ADMINISTRATOR'"
                >
                  관리자
                </td>
                <td class="tableText" v-else>사원</td>
                <td class="tableText">{{ member.department }}</td>
                <td class="tableText">{{ member.salary }}만원</td>
                <td class="tableText">
                  <div class="tavleContact">
                    <div>{{ member.email }}</div>
                    <div>{{ member.phoneNum }}</div>
                  </div>
                </td>
                <td class="tableText">{{ member.modDate.slice(0, 10) }}</td>
                <td class="tableText">{{ member.createdDate.slice(0, 10) }}</td>
                <!-- 비밀번호를 변경 했을 경우 채크 아닐경우 x -->
                <td class="tableText">
                  <i
                    v-if="member.temp === 'x'"
                    style="color: green"
                    class="bi bi-check-circle"
                  ></i>
                  <i v-else style="color: red" class="bi bi-x-circle"></i>
                </td>
                <td class="tableText">
                  <div
                    v-if="
                      loginedPosion === 'ROLE_ADMINISTRATOR' &&
                      member.role === '사원'
                    "
                    class="listBtns"
                  >
                    <i
                      class="bi bi-pencil-square"
                      type="button"
                      data-bs-toggle="modal"
                      data-bs-target="#editMemberModal"
                      @click="eidtMember(member.id)"
                    ></i>
                    <EditMember :editMemberInfo="editMemberInfo" />
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
                  <div
                    v-else-if="loginedPosion === 'ROLE_CHIEF'"
                    class="listBtns"
                  >
                    <i
                      class="bi bi-pencil-square"
                      type="button"
                      data-bs-toggle="modal"
                      data-bs-target="#editMemberModal"
                      @click="eidtMember(member.id)"
                    ></i>
                    <EditMember :editMemberInfo="editMemberInfo" />
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
            <!-- 테이블 바디 -->
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
import AddMember from "./AddMember.vue";
import EditMember from "./EditMember.vue";
import DeleteMember from "./DeleteMember.vue";
import SimpleDonut from "./SimpleDonut.vue";
import DistributedColumns from "./DistributedColumns.vue";
import StickChart from "./StickChart.vue";
import axios from "axios";

//엑시오스 주소
const api = "http://localhost:8080";

//로그인 유져 확인
const user = JSON.parse(sessionStorage.getItem("logined"));

export default {
  name: "memberManagement",
  data() {
    return {
      //라인 차트 값
      userNumYears: [0, 0, 0, 0, 0],
      maxNum: 0,
      //막대 차트 값
      avgArr: [0, 0, 0],
      //원형 차트 값
      totalNumArr: [0, 0, 0],

      loginedPosion: "",
      editMemberInfo: {},
      deleteMemberNum: "",
      deleteMemberName: "",
      salarySortValue: "",
      //필터 채크박스
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
      //필터 채크박스
      //검색 인풋
      searchNameValue: "",
      //검색 인풋
      members: [],
      //필터 적용 배열
      positionFilter: [],
      finalFilter: [],
      //필터 적용 배열
    };
  },
  components: {
    SideBar,
    AddMember,
    EditMember,
    DeleteMember,
    SimpleDonut,
    DistributedColumns,
    StickChart,
  },
  methods: {
    //이름 검색 메소드
    searchName(e) {
      e.preventDefault();
      this.finalFilter = this.members.filter(
        (v) => v.name === this.searchNameValue
      );
    },
    //이름 검색 리셋 메소드
    searchNameReset() {
      this.finalFilter = this.members;
      this.searchNameValue = "";
    },
    //필터 적용 메소드
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
            (v) => v.role === "ROLE_CHIEF"
          );
        }
        if (
          !this.checkedPosition.chiefCheckPosition &&
          this.checkedPosition.managerCheckPosition &&
          !this.checkedPosition.userCheckPosition
        ) {
          this.positionFilter = this.members.filter(
            (v) => v.role === "ROLE_ADMINISTRATOR"
          );
        }
        if (
          !this.checkedPosition.chiefCheckPosition &&
          !this.checkedPosition.managerCheckPosition &&
          this.checkedPosition.userCheckPosition
        ) {
          this.positionFilter = this.members.filter(
            (v) => v.role === "ROLE_USER"
          );
        }
        //2개 선택된 경우
        if (
          this.checkedPosition.chiefCheckPosition &&
          this.checkedPosition.managerCheckPosition &&
          !this.checkedPosition.userCheckPosition
        ) {
          this.positionFilter = this.members.filter(
            (v) => v.role === "ROLE_CHIEF" || v.role === "ROLE_ADMINISTRATOR"
          );
        }
        if (
          this.checkedPosition.chiefCheckPosition &&
          !this.checkedPosition.managerCheckPosition &&
          this.checkedPosition.userCheckPosition
        ) {
          this.positionFilter = this.members.filter(
            (v) => v.role === "ROLE_CHIEF" || v.role === "ROLE_USER"
          );
        }
        if (
          !this.checkedPosition.chiefCheckPosition &&
          this.checkedPosition.managerCheckPosition &&
          this.checkedPosition.userCheckPosition
        ) {
          this.positionFilter = this.members.filter(
            (v) => v.role === "ROLE_ADMINISTRATOR" || v.role === "ROLE_USER"
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
    },
    //수정 맴버 아이디 프롭스 메소드
    eidtMember(v) {
      axios
        .get(`${api}/member/${v}`)
        .then((response) => {
          console.log(response);
          this.editMemberInfo = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    //삭제 맴버 아이디, 이름 프롭스 메소드
    deleteMember(num, name) {
      this.deleteMemberNum = num;
      this.deleteMemberName = name;
    },
    //필터 중 어느것이라도 클릭되면 천체가 해제 되는 메소드
    positionAllClear() {
      if (
        this.checkedPosition.chiefCheckPosition ||
        this.checkedPosition.managerCheckPosition ||
        this.checkedPosition.userCheckPosition
      ) {
        this.checkedPosition.allCheckPosition = false;
      }
    },
    //필터 3개 모두 클릭됬을 때 천체로 변환 메소드
    positionRestClear() {
      if (this.checkedPosition.allCheckPosition) {
        this.checkedPosition.chiefCheckPosition = false;
        this.checkedPosition.managerCheckPosition = false;
        this.checkedPosition.userCheckPosition = false;
      }
    },
    //필터 중 어느것이라도 클릭되면 천체가 해제 되는 메소드
    departmentAllClear() {
      if (
        this.checkedDepartment.salesCheckDepartment ||
        this.checkedDepartment.frontCheckDepartment ||
        this.checkedDepartment.backCheckDepartment
      ) {
        this.checkedDepartment.allCheckDepartment = false;
      }
    },
    //필터 3개 모두 클릭됬을 때 천체로 변환 메소드
    departmentRestClear() {
      if (this.checkedDepartment.allCheckDepartment) {
        this.checkedDepartment.salesCheckDepartment = false;
        this.checkedDepartment.frontCheckDepartment = false;
        this.checkedDepartment.backCheckDepartment = false;
      }
    },
    //연봉 정렬 메소드
    salarySort(e) {
      if (e === "오름 차순") {
        this.finalFilter = this.finalFilter.sort((a, b) => a.salary - b.salary);
      } else if (e === "내림 차순") {
        this.finalFilter = this.finalFilter.sort((a, b) => b.salary - a.salary);
      }
      console.log(e);
    },
    //생성 날짜 정렬 메소드
    dateSort(e) {
      if (e === "오름 차순") {
        this.finalFilter = this.finalFilter.sort(
          (a, b) =>
            new Date(a.createdDate).getTime() -
            new Date(b.createdDate).getTime()
        );
      } else if (e === "내림 차순") {
        this.finalFilter = this.finalFilter.sort(
          (a, b) =>
            new Date(b.createdDate).getTime() -
            new Date(a.createdDate).getTime()
        );
      }
      console.log(e);
    },
    //상태 정렬 메소드
    isEdited(e) {
      if (e === "수정완료") {
        this.finalFilter = this.members;
        this.finalFilter = this.finalFilter.filter((v) => v.isValid === true);
      } else if (e === "수정전") {
        this.finalFilter = this.members;
        this.finalFilter = this.finalFilter.filter((v) => v.isValid === false);
      } else {
        this.finalFilter = this.members;
      }
      console.log(e);
    },
  },
  computed: {
    //날짜 포맷
    dateFormat(v) {
      let date = new Date(v);
      return date.toLocaleString();
    },
    //데이터에서 최고 관리자 숫자 계산 메소드
    chiefNum() {
      let count = 0;
      for (let i = 0; i < this.members.length; i++) {
        this.members[i].role === "ROLE_CHIEF" && count++;
      }
      return count;
    },
    //데이터에서 관리자 숫자 계산 메소드
    managerNum() {
      let count = 0;
      for (let i = 0; i < this.members.length; i++) {
        this.members[i].role === "ROLE_ADMINISTRATOR" && count++;
      }
      return count;
    },
    //데이터에서 사원 숫자 계산 메소드
    userNum() {
      let count = 0;
      for (let i = 0; i < this.members.length; i++) {
        this.members[i].role === "ROLE_USER" && count++;
      }
      return count;
    },
  },
  mounted() {
    axios
      .get(`${api}/member/getAllMember`)
      .then((response) => {
        // console.log(response.data);
        this.members.push(...response.data);
        for (let i = 0; i < this.members.length; i++) {
          this.members[i].createdDate.slice(0, 4) === "2020" &&
            this.userNumYears[0]++;
          this.members[i].createdDate.slice(0, 4) === "2021" &&
            this.userNumYears[1]++;
          this.members[i].createdDate.slice(0, 4) === "2022" &&
            this.userNumYears[2]++;
          this.members[i].createdDate.slice(0, 4) === "2023" &&
            this.userNumYears[3]++;
          this.members[i].createdDate.slice(0, 4) === "2024" &&
            this.userNumYears[4]++;
        }
        this.maxNum = Math.max(...this.userNumYears);
        // console.log(this.userNumYears);
        // console.log(this.maxNum);
        //데이터에서 연봉 평균 계산 메소드
        //영업 평균
        let salesSum = 0;
        let salesTotalCount = 0;
        for (let i = 0; i < this.members.length; i++) {
          if (this.members[i].department === "영업") {
            salesTotalCount = salesTotalCount + 1;
            salesSum = salesSum + this.members[i].salary;
          }
        }
        this.avgArr[0] = Math.floor(salesSum / salesTotalCount);
        //프론트 평균
        let frontSum = 0;
        let frontTotalCount = 0;
        for (let i = 0; i < this.members.length; i++) {
          if (this.members[i].department === "프론트엔드") {
            frontTotalCount = frontTotalCount + 1;
            frontSum = frontSum + this.members[i].salary;
          }
        }
        this.avgArr[1] = Math.floor(frontSum / frontTotalCount);
        //백엔드 평균
        let backSum = 0;
        let backTotalCount = 0;
        for (let i = 0; i < this.members.length; i++) {
          if (this.members[i].department === "백엔드") {
            backTotalCount = backTotalCount + 1;
            backSum = backSum + this.members[i].salary;
          }
        }
        this.avgArr[2] = Math.floor(backSum / backTotalCount);

        //데이터에서 영업 부서 숫자 계산 메소드
        let salesCount = 0;
        for (let i = 0; i < this.members.length; i++) {
          this.members[i].department === "영업" && salesCount++;
        }
        this.totalNumArr[0] = salesCount;

        //데이터에서 프론트 부서 숫자 계산 메소드
        let frontCount = 0;
        for (let i = 0; i < this.members.length; i++) {
          this.members[i].department === "프론트엔드" && frontCount++;
        }
        this.totalNumArr[1] = frontCount;

        //데이터에서 백엔드 부서 숫자 계산 메소드
        let backCount = 0;
        for (let i = 0; i < this.members.length; i++) {
          this.members[i].department === "백엔드" && backCount++;
        }
        this.totalNumArr[2] = backCount;
      })
      .catch((error) => {
        console.log(error);
      });
    //리스트 세팅
    this.setList();
    // 로그인된 사람의 역할
    if (user !== null) {
      this.loginedPosion = user.auth;
    }
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
  margin: 12px 0;
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
  margin: 30px 0 10px 0;
  justify-content: space-between;
}
.listTitle {
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
  align-items: center;
  justify-content: end;
  border-bottom: 1px solid gray;
  padding-bottom: 30px;
}
.manageBtns a {
  text-decoration: none;
  color: gray;
}
#resetBtn a:hover {
  color: white;
}
.charts {
  display: grid;
  align-items: center;
  grid-template-columns: repeat(auto-fit, minmax(400px, 1fr));
  grid-auto-rows: minmax(100px, auto);
  gap: 20px;
}
</style>
