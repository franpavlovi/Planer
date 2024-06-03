<template>
  <NavigacijaGlava/>

  <div class="produkt">
    <v-btn flat size="large" rounded="0" class="produkt-btn" @click="trenutni = 'tjedan'">PO TJEDNU</v-btn>
    <v-btn flat size="large" rounded="0" class="produkt-btn" style="margin-left: 10px;" @click="trenutni = 'mjesec'">MJESECU</v-btn>
    <v-btn flat size="large" rounded="0" class="produkt-btn" style="margin-left: 10px;" @click="trenutni = 'godina'">GODINI</v-btn>
    <hr class="underline">
  </div>

  <div class="statistika-container">
    <StatistikaTjedan v-if="trenutni === 'tjedan'"/>
    <StatistikaMjesec v-if="trenutni === 'mjesec'"/>
    <StatistikaGodina v-if="trenutni === 'godina'"/>
  </div>
</template>

<script>
import NavigacijaGlava from '@/components/NavigacijaGlava.vue'
import StatistikaTjedan from "@/components/StatistikaTjedan.vue";
import StatistikaMjesec from "@/components/StatistikaMjesec.vue";
import StatistikaGodina from "@/components/StatistikaGodina.vue";

export default {
  name: 'StatistikaView',
  components: {
    NavigacijaGlava,
    StatistikaTjedan,
    StatistikaMjesec,
    StatistikaGodina
  },
  data() {
    return {
      trenutni: 'tjedan'
    }
  }
}
</script>

<style scoped>
.produkt-btn {
  background-color: #f5f5f5 !important;
  color: black !important;
  text-transform: uppercase;
}

.produkt{
  text-align: left;
  margin-left: 30px;
  margin-right: 30px;
}

.underline {
  border: none;
  border-top: 1px solid black;
}

.statistika-container{
  margin-top: 30px;
  margin-left: 30px;
  height: 1500px;
  width: 1200px;
}
</style>