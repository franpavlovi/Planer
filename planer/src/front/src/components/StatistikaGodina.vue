<template>
  <div class="statistika-godina">
    <BarChart :chartData="chartData" :chartOptions="chartOptions"/>
  </div>

  <div class="navigacija-godina">
    <v-btn class="zadaci-btn" @click="godinaUnazad">Prethodna godina</v-btn>
    <v-btn class="zadaci-btn" @click="godinaUnaprijed">Sljedeća godina</v-btn>
  </div>

  <div class="produktivnost">
    <h1>PRODUKTIVNOST</h1>
    <p>
      Ove godine imali ste {{ obavljeniZadaci }} zadataka koje ste obavili i {{ neobavljeniZadaci }} koje niste obavili,
      što je u postotku {{ postotak }}%.
    </p>
  </div>
</template>

<script>
import BarChart from "@/components/BarChart.vue";
import axios from 'axios';

export default {
  name: 'StatistikaGodina',
  components: { BarChart },
  data() {
    return {
      chartData: {
        labels: ['OBAVLJENI', 'NEOBAVLJENI'],
        datasets: [{ data: [0, 0] }],
      },
      chartOptions: {
        responsive: true,
      },
      zadaci: [],
      trenutnaGodina: '',
      obavljeniZadaci: 0,
      neobavljeniZadaci: 0,
      postotak: 0,
    };
  },
  mounted() {
    this.dohvatiTrenutnuGodinu();
    this.izlistajZadatke();
  },
  methods: {
    dohvatiTrenutnuGodinu() {
      const danas = new Date();
      const year = danas.getFullYear();
      this.trenutnaGodina = `${year}`;
    },
    izlistajZadatke() {
      const year = parseInt(this.trenutnaGodina, 10);
      axios.get('/api/zadaci/godina', {
        params: {
          year: year
        }
      })
          .then(response => {
            this.zadaci = response.data;
            this.updateChart();
          })
          .catch(error => {
            console.error('Došlo je do greške pri dohvaćanju zadataka:', error);
          });
    },
    updateChart() {
      const obavljeni = this.zadaci.filter(zadatak => zadatak.status).length;
      const neobavljeni = this.zadaci.filter(zadatak => !zadatak.status).length;

      this.obavljeniZadaci = obavljeni;
      this.neobavljeniZadaci = neobavljeni;
      this.postotak = ((obavljeni / (obavljeni + neobavljeni)) * 100).toFixed(2);

      this.chartData = {
        ...this.chartData,
        datasets: [{ data: [obavljeni, neobavljeni] }]
      };
    },
    godinaUnazad() {
      const year = parseInt(this.trenutnaGodina, 10);
      const novaGodina = year - 1;
      this.trenutnaGodina = `${novaGodina}`;
      this.izlistajZadatke();
    },
    godinaUnaprijed() {
      const year = parseInt(this.trenutnaGodina, 10);
      const novaGodina = year + 1;
      this.trenutnaGodina = `${novaGodina}`;
      this.izlistajZadatke();
    }
  }
}
</script>

<style scoped>
.statistika-godina {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.navigacija-godina {
  display: flex;
  justify-content: space-between;
  margin: 20px 30px;
}

.zadaci-btn {
  background-color: #f5f5f5 !important;
  color: black !important;
  text-transform: uppercase;
}
</style>
