<template>
  <NavigacijaGlava/>

  <div class="dodaj-termin">
    <v-btn flat size="large" rounded="0" class="termini-btn" @click="prikazi = true">DODAJ TERMIN</v-btn>
    <hr class="underline">
  </div>

  <div class="v-kalendar">
    <v-calendar></v-calendar>
  </div>

  <ModalDodajTermin v-if="prikazi" @close="zatvori" />
</template>

<script>
import NavigacijaGlava from '@/components/NavigacijaGlava.vue'
import ModalDodajTermin from "@/components/ModalDodajTermin.vue";
import { VCalendar } from 'vuetify/labs/VCalendar'

export default {
  name: 'TerminiView',
  components: { NavigacijaGlava, ModalDodajTermin, VCalendar },

  data(){
    return{
      prikazi: false,
    }
  },

  methods: {
    zatvori() {
      this.prikazi = false;
    },
  }
}

</script>

<style scoped>
.termini-btn {
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

.v-kalendar {
  height: 100px;
  width: 1500px;
  margin-left: 30px;
  margin-top: 30px;
}
</style>
