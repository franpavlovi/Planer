<template>
  <div>
    <BarChart :chartData="chartData" :chartOptions="chartOptions"/>
  </div>
</template>

<script>
import BarChart from "@/components/BarChart.vue";

export default {
  name: 'StatistikaTjedan',
  components: { BarChart },
  data() {
    return {
      chartData: {
        labels: ['OBAVLJENI', 'NEOBAVLJENI'],
        datasets: [{ data: [40, 20] }]
      },
      chartOptions: {
        responsive: true
      }
    }
  }
}
</script>
