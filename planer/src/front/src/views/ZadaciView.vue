<template>
  <v-app>
    <NavigacijaGlava/>

    <div class="dodaj-zadatak">
      <v-btn flat size="large" rounded="0" class="zadaci-btn" @click="prikazi = true">DODAJ ZADATAK</v-btn>
      <hr class="underline">
    </div>

    <div class="navigacija-tjedan">
      <v-btn class="zadaci-btn" @click="tjedanUnazad">Tjedan unazad</v-btn>
      <v-btn class="zadaci-btn" @click="tjedanUnaprijed">Tjedan unaprijed</v-btn>
    </div>

    <ModalDodajZadatak v-if="prikazi" @close="zatvori" @zadatakKreiran="izlistajZadatke"/>

    <div class="lista-zadataka">
      <v-container>
        <v-row v-for="dan in zadaciPoDanima" :key="dan.naziv">
          <v-col>
            <strong>{{ dan.naziv }}</strong>
            <v-spacer></v-spacer>
            <v-card class="custom-card">
              <div v-if="dan.zadaci.length === 0">

                 <p>NEMATE ZADATAKA ZA OVAJ DAN</p>

              </div>
              <v-card-item v-for="zadatak in dan.zadaci" :key="zadatak.id">
                <p>Naziv zadatka: {{ zadatak.naziv }}</p>
                <br>
                <p>Opis zadatka: {{ zadatak.opis }}</p>
                <br>
                <p> Status zadatka:
                  <input type="checkbox" :checked="zadatak.status" @change="oznaci(zadatak.id, $event.target.checked)">
                </p>
                <br>
              </v-card-item>
            </v-card>
            <v-spacer></v-spacer>
            <v-spacer></v-spacer>
            <v-spacer></v-spacer>
            <v-spacer></v-spacer>
            <v-spacer></v-spacer>
          </v-col>
        </v-row>
      </v-container>
    </div>

  </v-app>
</template>

<script>
import NavigacijaGlava from '@/components/NavigacijaGlava.vue'
import ModalDodajZadatak from "@/components/ModalDodajZadatak.vue";
import axios from "axios";

export default {
  name: 'App',
  components: { NavigacijaGlava , ModalDodajZadatak},

  data() {
    return {
      prikazi: false,
      zadaci: [],
      zadaciPoDanima: [],
      dani: [
        { index: 0, naziv: 'PONEDJELJAK' },
        { index: 1, naziv: 'UTORAK' },
        { index: 2, naziv: 'SRIJEDA' },
        { index: 3, naziv: 'ČETVRTAK' },
        { index: 4, naziv: 'PETAK' },
        { index: 5, naziv: 'SUBOTA' },
        { index: 6, naziv: 'NEDJELJA' }
      ],
    };
  },

  mounted() {
    this.dohvatiPocetakTjedna();
    this.izlistajZadatke();
  },

  methods: {
    zatvori() {
      this.prikazi = false;
    },

    dohvatiPocetakTjedna() {
      const danas = new Date();
      const danUSedmici = danas.getDay() === 0 ? 6 : danas.getDay() - 1;
      const pocetakTjedna = new Date(danas.setDate(danas.getDate() - danUSedmici));
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
            this.grupirajZadatkePoDanima();
            console.log('Podaci o zadacima:', this.zadaci);
          })
          .catch(error => {
            console.error('Došlo je do greške pri dohvaćanju zadataka:', error);
          });
    },

    grupirajZadatkePoDanima() {
      this.zadaciPoDanima = this.dani.map(dan => {
        return {
          naziv: dan.naziv,
          zadaci: this.zadaci.filter(zadatak => {
            const datumZadatka = new Date(zadatak.dt);
            const danZadatka = datumZadatka.getDay() === 0 ? 6 : datumZadatka.getDay() - 1;
            return danZadatka === dan.index;
          })
        };
      });
    },

    oznaci(id, status) {
      axios.patch(`/api/zadaci/status/${id}?obavljen=${status}`)
          .then(response => {
            this.zadaci = this.zadaci.map(zadatak => {
              if (zadatak.id === id) {
                zadatak.status = status;
              }
              return zadatak;
            });
            console.log('Status zadatka ažuriran:', response.data);
          })
          .catch(error => {
            console.error('Došlo je do greške pri ažuriranju statusa zadatka:', error);
          });
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
.zadaci-btn {
  background-color: #f5f5f5 !important;
  color: black !important;
  text-transform: uppercase;
}

.dodaj-zadatak {
  text-align: left;
  margin-left: 30px;
  margin-right: 30px;
}

.underline {
  border: none;
  border-top: 1px solid black;
}

.lista-zadataka {
  align-content: center;
  margin-left: 30px;
  margin-right: 30px;
  margin-top: 50px;
}

.navigacija-tjedan {
  display: flex;
  justify-content: space-between;
  margin: 20px 30px;
}

.custom-card {
  background-color: #f5f5f5 !important;
  color: black !important;
}
</style>
