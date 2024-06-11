<template>
  <div id="chart">
    <apexchart
      type="bar"
      height="350"
      :options="chartOptions"
      :series="series"
    ></apexchart>
  </div>
</template>
<script>
import VueApexCharts from "vue3-apexcharts";

export default {
  name: "distributedColumns",
  props: {
    avgArr: Array,
  },
  components: {
    apexchart: VueApexCharts,
  },
  data() {
    return {
      series: [
        {
          name: "평균 연봉",
          data: this.avgArr,
        },
      ],
      chartOptions: {
        chart: {
          type: "bar",
          height: 350,
        },
        title: {
          text: "각 부서별 연봉 평균",
          style: {
            fontSize: "22px",
            fontWeight: "bold",
          },
        },
        colors: ["#056CF2", "#04D98B", "#F2A71B"],
        plotOptions: {
          bar: {
            horizontal: false,
            columnWidth: "55%",
            endingShape: "rounded",
            distributed: true,
          },
        },
        dataLabels: {
          enabled: false,
        },
        stroke: {
          show: true,
          width: 2,
          colors: ["transparent"],
        },
        xaxis: {
          categories: ["영업", "프론트엔드", "백엔드"],
          labels: {
            style: {
              colors: ["#056CF2", "#04D98B", "#F2A71B"],
              fontSize: "12px",
            },
          },
        },
        yaxis: {
          title: {
            text: "단위 만원",
          },
        },
        fill: {
          opacity: 1,
        },
        tooltip: {
          y: {
            formatter: function (val) {
              return val + " 만원";
            },
          },
        },
      },
    };
  },
};
</script>
<style></style>
