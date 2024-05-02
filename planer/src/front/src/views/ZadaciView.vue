<template>
  <v-app>
    <NavigacijaGlava/>

    <div class="dodaj-zadatak">
      <v-btn flat size="large" rounded="0" class="zadaci-btn" @click="dodajZadatakView">DODAJ ZADATAK</v-btn>
      <hr class="underline">
    </div>

    <div class="lista-zadataka">
      <v-card>
        <v-card-title>LISTA ZADATAKA</v-card-title>
        <v-card-item v-for="zadatak in zadaci" :key="zadatak.id">
          Naziv zadatka:{{ zadatak.naziv }}
          <br>
          Opis zadatka: {{ zadatak.opis }}
          <br>
          Status zadatka: {{ zadatak.status }}
          <br>
          Datum završetka: {{ zadatak.dt }}
          <br><br>
        </v-card-item>
      </v-card>
    </div>

  </v-app>
</template>

<script>
import NavigacijaGlava from '@/components/NavigacijaGlava.vue'
import router from "@/router";
import axios from "axios";

export default {
  name: 'App',
  components: { NavigacijaGlava },

  data() {
    return {
      zadaci: []
    };
  },

  mounted() {
    this.izlistajZadatke();
  },

  methods: {
    dodajZadatakView() {
      router.push('/zadaci/dodaj');
    },

    izlistajZadatke() {

      axios.get('/api/zadaci')
          .then(response => {
            this.zadaci = response.data;
          })
          .catch(error => {
            console.error('Došlo je do greške pri dohvaćanju zadataka:', error);
          });
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
</style>
