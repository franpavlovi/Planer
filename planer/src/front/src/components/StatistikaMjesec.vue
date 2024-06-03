<template>
  <div>
    <BarChart :chartData="chartData" :chartOptions="chartOptions"/>
  </div>
</template>

<script>
import BarChart from "@/components/BarChart.vue";

export default {
  name: 'StatistikaMjesec',
  components: { BarChart },
  data() {
    return {
      chartData: {
        labels: ['OBAVLJENI', 'NEOBAVLJENI'],
        datasets: [{ data: [100, 50] }]
      },
      chartOptions: {
        responsive: true
      }
    }
  }
}
</script>
