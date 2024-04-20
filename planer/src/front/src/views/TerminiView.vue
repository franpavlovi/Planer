<template>
  <header><h1>TERMINI</h1></header>

  <table>
    <tr>
      <td @click="dodajterminview">
        <h3>DODAJ TERMIN</h3>
      </td>
    </tr>

    <tr>
      <td @click="zadaciview">
        <h3>VIDI ZADATKE</h3>
      </td>
    </tr>

    <tr>
      <td>
        <h3>VIDI PRODUKTIVNOST</h3>
      </td>
    </tr>
  </table>


</template>

<script>
import router from '../router';

export default {
  methods: {
    dodajterminview(){
      router.push('/termini/dodaj')
    },
    zadaciview(){
      router.push('/zadaci')
    }
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
