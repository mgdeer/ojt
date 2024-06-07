<template>
  <div>
    <apexchart
      type="donut"
      :options="chartOptions"
      :series="series"
    ></apexchart>
  </div>
</template>
<script>
import VueApexCharts from "vue3-apexcharts";

export default {
  name: "simpleDonut",
  props: {
    salesNum: Number,
    frontNum: Number,
    backNum: Number,
  },
  components: {
    apexchart: VueApexCharts,
  },
  data() {
    return {
      series: [this.salesNum, this.frontNum, this.backNum],
      chartOptions: {
        chart: {
          type: "donut",
        },
        title: {
          text: "각 부서별 사원 비율",
          style: {
            fontSize: "22px",
            fontWeight: "bold",
          },
        },
        labels: ["영업", "프론트엔드", "백엔드"],
        responsive: [
          {
            breakpoint: 480,
            options: {
              chart: {
                width: 200,
              },

              legend: {
                position: "bottom",
              },
            },
          },
        ],
      },
    };
  },
};
</script>
<style></style>
