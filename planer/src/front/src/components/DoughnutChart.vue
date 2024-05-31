<template>
  <div>
    <doughnut-chart :chart-data="chartData" :options="chartOptions"></doughnut-chart>
  </div>
</template>

<script>
import { Doughnut } from 'vue-chartjs';
import { Chart as ChartJS, Title, Tooltip, Legend, ArcElement } from 'chart.js';

ChartJS.register(Title, Tooltip, Legend, ArcElement);

export default {
  name: 'DoughnutChart',
  components: {
    'doughnut-chart': Doughnut
  },
  props: {
    chartData: {
      type: Object,
      required: true
    },
    chartOptions: {
      type: Object,
      required: true
    }
  }
};
</script>

<style scoped>
</style>
