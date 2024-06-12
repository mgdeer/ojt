<template>
  <div class="chart">
    <div>
      <div>
        <apexchart
          type="line"
          height="350"
          :options="chartOptions"
          :series="series"
        ></apexchart>
      </div>
    </div>
  </div>
</template>
<script>
import VueApexCharts from "vue3-apexcharts";

export default {
  name: "stickChart",
  props: {
    userNumYears: Array,
    maxNum: Number,
  },
  components: {
    apexchart: VueApexCharts,
  },
  data() {
    return {
      //라인 차트
      series: [
        {
          name: "사원",
          data: this.userNumYears,
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
        title: {
          text: "사원 증감 추세",
          style: {
            fontSize: "22px",
            fontWeight: "bold",
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
          max: this.maxNum + 10,
        },
        xaxis: {
          categories: ["2020", "2021", "2022", "2023", "2024"],
        },
      },
      //라인 차트
    };
  },
};
</script>
<style></style>
