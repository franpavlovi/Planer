<template>
  <div class="statistika-mjesec">
    <BarChart :chartData="chartData" :chartOptions="chartOptions"/>
  </div>

  <div class="navigacija-mjesec">
    <v-btn class="zadaci-btn" @click="mjesecUnazad">Prethodni mjesec</v-btn>
    <v-btn class="zadaci-btn" @click="mjesecUnaprijed">Sljedeći mjesec</v-btn>
  </div>

  <div class="produktivnost">
    <h1>PRODUKTIVNOST</h1>
    <p>
      Ovaj mjesec imali ste {{ obavljeniZadaci }} zadataka koje ste obavili i {{ neobavljeniZadaci }} koje niste obavili,
      što je u postotku {{ postotak }}%.
    </p>
  </div>
</template>

<script>
import BarChart from "@/components/BarChart.vue";
import axios from 'axios';

export default {
  name: 'StatistikaMjesec',
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
      trenutniMjesec: '',
      obavljeniZadaci: 0,
      neobavljeniZadaci: 0,
      postotak: 0,
    };
  },
  mounted() {
    this.dohvatiTrenutniMjesec();
    this.izlistajZadatke();
  },
  methods: {
    dohvatiTrenutniMjesec() {
      const danas = new Date();
      const year = danas.getFullYear();
      const month = danas.getMonth() + 1; // mjeseci su 0-indeksirani
      this.trenutniMjesec = `${year}-${String(month).padStart(2, '0')}`;
    },
    izlistajZadatke() {
      const [year, month] = this.trenutniMjesec.split('-');
      axios.get('/api/zadaci/mjesec', {
        params: {
          year: parseInt(year, 10),
          month: parseInt(month, 10)
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
    mjesecUnazad() {
      const [year, month] = this.trenutniMjesec.split('-').map(Number);
      const noviDatum = new Date(year, month - 2); // idemo mjesec unazad
      const noviGodina = noviDatum.getFullYear();
      const noviMjesec = noviDatum.getMonth() + 1;
      this.trenutniMjesec = `${noviGodina}-${String(noviMjesec).padStart(2, '0')}`;
      this.izlistajZadatke();
    },
    mjesecUnaprijed() {
      const [year, month] = this.trenutniMjesec.split('-').map(Number);
      const noviDatum = new Date(year, month); // idemo mjesec unaprijed
      const noviGodina = noviDatum.getFullYear();
      const noviMjesec = noviDatum.getMonth() + 1;
      this.trenutniMjesec = `${noviGodina}-${String(noviMjesec).padStart(2, '0')}`;
      this.izlistajZadatke();
    }
  }
}
</script>

<style scoped>
.statistika-mjesec {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.navigacija-mjesec {
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
