<template>
  <v-app>
    <NavigacijaGlava/>

    <div class="dodaj-zadatak">
      <v-btn flat size="large" rounded="0" class="zadaci-btn" @click="prikazi = true">DODAJ ZADATAK</v-btn>
      <hr class="underline">
    </div>

    <ModalDodajZadatak v-if="prikazi" @close="zatvori" />

    <div class="lista-zadataka">
      <v-container>
        <v-row v-for="dan in dani" :key="dan.index">
          <strong>{{ dan.naziv}}</strong>
          <v-col>
            <v-card>
              <v-card-item v-for="zadatak in zadaci" :key="zadatak.id">
                Naziv zadatka:{{ zadatak.naziv }}
                <br>
                Opis zadatka: {{ zadatak.opis }}
                <br>
                Status zadatka: {{ zadatak.status }}
                <br>
                Datum završetka: {{ zadatak.dt }}
              </v-card-item>
            </v-card>
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
      prikazi:false,

      zadaci: [],
      dani: [
        {index:0, naziv:'PONEDJELJAK'},
        {index:1, naziv:'UTORAK'},
        {index:2, naziv:'SRIJEDA'},
        {index:3, naziv:'ČETVRTAK'},
        {index:4, naziv:'PETAK'},
        {index:5, naziv:'SUBOTA'},
        {index:6, naziv:'NEDJELJA'}
      ],
    }
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

      const danUSedmici = danas.getDay();
      const daniProtekli = (7 + danUSedmici - 1) % 7;

      const pocetakTjedna = new Date(danas);
      pocetakTjedna.setDate(danas.getDate() - daniProtekli);

      const pocetakTjednaFormatiran = pocetakTjedna.toISOString().split('T')[0];


      this.pocetakTjedna = pocetakTjednaFormatiran;
    },

    izlistajZadatke() {

      this.dohvatiPocetakTjedna();

      axios.get('/api/zadaci/tjedan', {
        params: {
          pocetakTjedna: this.pocetakTjedna
        }
      })
          .then(response => {
            this.zadaci = response.data;
          })
          .catch(error => {
            console.error('Došlo je do greške pri dohvaćanju zadataka:', error);
          });
    },

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
</style>
