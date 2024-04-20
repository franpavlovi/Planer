<template>
  <section class="zadatak-section">
    <div class="container">
      <div class="card">
        <div class="card-body">
          <div class="form-container">
            <h1 class="text-center">Unesi podatke</h1>
            <form @submit.prevent="kreirajZadatak" class="forma">

              <div class="form-group">
                <label for="naziv">Naziv</label>
                <input id="naziv" v-model="zadatak.naziv" type="text" placeholder="Unesite naziv">
              </div>

              <div class="form-group">
                <label for="opis">Opis</label>
                <input id="opis" v-model="zadatak.opis" type="text" placeholder="Unesite opis">
              </div>

              <div class="form-group">
                <label for="dt">Vrijeme i datum</label>
                <input id="dt" v-model="zadatak.dt" type="text" placeholder="Unesite u formatu yyyy-mm-ddT00:00:00">
              </div>

              <div class="form-group">
                <button type="submit" class="submit-button">Dodaj zadatak</button>
              </div>

            </form>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import axios from 'axios';
import router from '../router';

export default {

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

      axios.post('/zadaci/dodaj', this.zadatak)
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
.zadatak-section {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f4f4f9;
}

.container {
  width: 90%;
  max-width: 500px;
}

.card {
  background: white;
  border-radius: 12px;
  box-shadow: 0 8px 16px rgba(0,0,0,0.1);
  overflow: hidden;
}

.card-body {
  padding: 2rem;
}

.text-center {
  text-align: center;
  color: #333;
  margin-bottom: 2rem;
  font-weight: 300;
}

.form-group {
  margin-bottom: 1.5rem;
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

.submit-button {
  width: 100%;
  padding: 1rem;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.submit-button:hover {
  background-color: #0056b3;
}

.card-body {
  padding: 1rem;
}

</style>