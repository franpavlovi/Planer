<template>
  <NavigacijaGlava/>

  <div class="dodaj-termin">
    <v-btn flat size="large" rounded="0" class="termini-btn" @click="dodajterminview">DODAJ TERMIN</v-btn>
    <hr class="underline">
  </div>


</template>

<script>
import NavigacijaGlava from '@/components/NavigacijaGlava.vue'
import router from '../router';


export default {

  name:'App',
  components:{NavigacijaGlava},

  methods: {
    dodajterminview(){
      router.push('/termini/dodaj')
    },

  }
}

</script>

<style scoped>
.termini-btn{
  background-color: #f5f5f5 !important;
  color: black !important;
  text-transform: uppercase;
}

.dodaj-termin {
  text-align: left;
  margin-left: 30px;
  margin-right: 30px;
}

.underline {
  border: none;
  border-top: 1px solid black;
}
</style>