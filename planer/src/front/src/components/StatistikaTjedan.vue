<template>
  <div class="statistika-tjedan">

    <BarChart :chartData="chartData" :chartOptions="chartOptions"/>

  </div>

  <div class="navigacija-tjedan">

    <v-btn class="zadaci-btn" @click="tjedanUnazad">Prethodni tjedan</v-btn>
    <v-btn class="zadaci-btn" @click="tjedanUnaprijed">Sljedeći tjedan</v-btn>

  </div>

  <div class="produktivnost">

    <h1>PRODUKTIVNOST</h1>
    <p>
      Ovaj tjedan imali ste {{ obavljeniZadaci}} zadataka koje se obavili i {{neobavljeniZadaci}} koje niste obavili
      što je u postotku {{postotak}}.
    </p>

  </div>


</template>

<script>
import BarChart from "@/components/BarChart.vue";
import axios from 'axios';

export default {
  name: 'StatistikaTjedan',
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
      pocetakTjedna: '',
      obavljeniZadaci: 0,
      neobavljeniZadaci: 0,
      postotak: 0,
    };
  },
  mounted() {
    this.dohvatiPocetakTjedna();
    this.izlistajZadatke();
  },
  methods: {
    dohvatiPocetakTjedna() {
      const danas = new Date();
      const danUTjednu = danas.getDay() === 0 ? 6 : danas.getDay() - 1;
      const pocetakTjedna = new Date(danas.setDate(danas.getDate() - danUTjednu));
      this.pocetakTjedna = pocetakTjedna.toISOString().split('T')[0];
    },
    izlistajZadatke() {
      axios.get('/api/zadaci/tjedan', {
        params: {
          pocetakTjedna: this.pocetakTjedna
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
    tjedanUnazad() {
      const pocetakTjedna = new Date(this.pocetakTjedna);
      pocetakTjedna.setDate(pocetakTjedna.getDate() - 7);
      this.pocetakTjedna = pocetakTjedna.toISOString().split('T')[0];
      this.izlistajZadatke();
    },
    tjedanUnaprijed() {
      const pocetakTjedna = new Date(this.pocetakTjedna);
      pocetakTjedna.setDate(pocetakTjedna.getDate() + 7);
      this.pocetakTjedna = pocetakTjedna.toISOString().split('T')[0];
      this.izlistajZadatke();
    }
  }
}
</script>

<style scoped>
.statistika-tjedan {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.navigacija-tjedan {
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
