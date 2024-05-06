<template>

  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-container">

  <v-form @submit.prevent="kreirajTermin" class="obrub">
    <v-container class="forma">

      <v-container class="inputi">

        <h1 class="text-left">DODAJ TERMIN</h1>
        <v-divider class="v-divider"></v-divider>

        <p class="text-left">NAZIV</p>
        <v-text-field v-model="termin.naziv" label="unesite naziv" variant="outlined"></v-text-field>

        <p class="text-left">LOKACIJA</p>
        <v-text-field v-model="termin.lokacija" label="unesite lokaciju" variant="outlined"></v-text-field>

        <p class="text-left">DATUM</p>
        <v-text-field v-model="termin.datum" label="unesite datum" variant="outlined" type="date"></v-text-field>

        <p class="text-left">POCETAK</p>
        <v-text-field v-model="termin.pocetak" label="unesite pocetak" variant="outlined" type="time"></v-text-field>

        <p class="text-left">KRAJ</p>
        <v-text-field v-model="termin.kraj" label="unesite kraj" variant="outlined" type="time"></v-text-field>


        <v-btn size="x-large" class="mt-2" color="success" type="submit" block>DODAJ</v-btn>
      </v-container>

    </v-container>
  </v-form>

      </div>
    </div>
  </transition>

</template>


<script>
import axios from 'axios';

export default {
  name:'App',

  data() {
    return {
      termin: {
        datum: '',
        naziv: '',
        lokacija: '',
        pocetak: '',
        kraj: ''
      }
    };
  },

  methods: {
    kreirajTermin() {

      let pocetakISOformat = this.termin.datum + 'T' + this.termin.pocetak + ':00';
      let krajISOformat = this.termin.datum + 'T' + this.termin.kraj + ':00';

      this.termin.pocetak = pocetakISOformat;
      this.termin.kraj = krajISOformat;

      axios.post('/api/termini/dodaj', this.termin)
          .then(response => {
            alert("Uspjesno kreiran termin" + response.data);
            this.$emit('close');
          })
          .catch(error => {
            alert("Greska" + error.response.data);
          });
    }
  }
}
</script>

<style scoped>
.obrub{
  border: 2px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  padding: 20px;
  transition: box-shadow 0.3s ease;
  width: 800px;
  margin: auto;
}

.obrub:hover {
  box-shadow: 0 6px 12px rgba(0,0,0,0.1);
}

.inputi p {
  margin-bottom: 8px;
}

.v-divider {
  margin-bottom: 28px;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  color: #606060;
}

.form-group input {
  width: 100%;
  padding: 0.8rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  background: #fafafa;
  transition: border-color 0.3s;
  box-sizing: border-box;
}

.form-group input:focus {
  border-color: #0056b3;
  background: #fff;
}

.modal-mask {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* Tamna pozadina */
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal-container {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  transition: all 0.3s ease; /* Animacija */
}

/* CSS tranzicija za pojavljivanje/nestajanje modalne komponente */
.modal-enter, .modal-leave-to {
  opacity: 0;
  transform: scale(0.8);
}
.modal-enter-active, .modal-leave-active {
  opacity: 1;
  transform: scale(1);
}
</style>


