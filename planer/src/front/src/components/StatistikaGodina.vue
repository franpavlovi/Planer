<template>
  <div>
    <BarChart :chartData="chartData" :chartOptions="chartOptions"/>
  </div>
</template>

<script>
import BarChart from "@/components/BarChart.vue";

export default {
  name: 'StatistikaGodina',
  components: { BarChart },
  data() {
    return {
      chartData: {
        labels: ['OBAVLJENI', 'NEOBAVLJENI'],
        datasets: [{ data: [300, 100] }]
      },
      chartOptions: {
        responsive: true
      }
    }
  }
}
</script>
