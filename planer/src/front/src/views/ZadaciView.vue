<template>
  <v-app>
  <NavigacijaGlava/>


    <div class="dodaj-zadatak">
    <v-btn flat size="large" rounded="0" class="zadaci-btn" @click="dodajzadatakview">DODAJ ZADATAK</v-btn>
    <hr class="underline">
    </div>

  </v-app>
</template>

<script>
import NavigacijaGlava from '@/components/NavigacijaGlava.vue'
import router from "@/router";

export default {
  name:'App',
  components:{NavigacijaGlava},

  methods:{
    dodajzadatakview(){
      router.push('/zadaci/dodaj')
    },

  }
}

</script>

<style scoped>
.zadaci-btn{
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


</style>

