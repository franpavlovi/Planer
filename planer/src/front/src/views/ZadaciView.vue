<template>
  <NavigacijaGlava/>
  <h1>Zadaci</h1>
  <table>
    <tr>
      <td @click="dodajzadatakview">
        DODAJ ZADATAK
      </td>
    </tr>
  </table>
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
td {
  text-align: center;
  background-color: #f8f8f8;
  border: 1px solid black;
  padding: 10px;
  border-radius: 40px;
  box-shadow: 2px 2px 5px rgba(0,0,0,0.4);
  transition: background-color 0.3s, color 0.3s, transform 0.3s;

}

td:hover {
  background-color: #6495ED;
  color: #ffffff;
  transform: scale(1.05);
}

table {
  padding-left: 30px;
  border-collapse: separate;
  border-spacing: 0 25px;
}
</style>

