<template>
  <NavigacijaGlava/>

  <div class="produkt">
    <v-btn flat size="large" rounded="0" class="produkt-btn" >PO TJEDNU</v-btn>
    <v-btn flat size="large" rounded="0" class="produkt-btn" style="margin-left: 10px;">MJESECU</v-btn>
    <v-btn flat size="large" rounded="0" class="produkt-btn" style="margin-left: 10px;">GODINI</v-btn>
    <hr class="underline">
  </div>


</template>

<script>
import NavigacijaGlava from '@/components/NavigacijaGlava.vue'

export default{
  name:'App',
  components:{NavigacijaGlava},
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

</style>