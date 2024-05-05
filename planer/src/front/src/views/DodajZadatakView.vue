<template>
  <NavigacijaGlava/>

  <v-form @submit.prevent="kreirajZadatak" class="obrub">
    <v-container class="forma">

      <v-container class="inputi">

        <h1 class="text-left">DODAJ ZADATAK</h1>
        <v-divider class="v-divider"></v-divider>

        <p class="text-left">NAZIV</p>
        <v-text-field v-model="zadatak.naziv" label="unesite naziv" variant="outlined"></v-text-field>

        <p class="text-left">OPIS</p>
        <v-text-field v-model="zadatak.opis" label="unesite opis" variant="outlined"></v-text-field>

        <p class="text-left">DATUM</p>
        <v-text-field v-model="zadatak.dt" label="unesite datum" variant="outlined" type="datetime-local"></v-text-field>


        <v-btn size="x-large" class="mt-2" color="success" type="submit" block>DODAJ</v-btn>
      </v-container>

    </v-container>
  </v-form>

</template>

<script>
import NavigacijaGlava from '@/components/NavigacijaGlava.vue'
import axios from 'axios';
import router from '../router';


export default {

  name:'App',
  components:{NavigacijaGlava},

  data(){
    return{

      zadatak: {
        naziv:'',
        opis:'',
        dt:''
      }
    };
  },

  methods:{
    kreirajZadatak(){

      axios.post('/api/zadaci/dodaj', this.zadatak)
          .then(response =>{

            alert("Uspjesno kreiran termin" + response.data)
            router.push('/zadaci')
          })
          .catch(error =>{
            alert("Greska" + error.response.data)
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
</style>