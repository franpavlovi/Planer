<template>
  <section class="termin-section">
  <div class="container">
    <div class="card">
      <div class="card-body">
        <div class="form-container">
          <h1 class="text-center">Unesi podatke</h1>
          <form @submit.prevent="kreirajTermin" class="forma">

           <div class="form-group">
             <label for="datum">Datum</label>
             <input id="datum" v-model="termin.datum" type="text" placeholder="year-month-day" required>
           </div>

            <div class="form-group">
              <label for="naziv">Naziv</label>
              <input id="naziv" v-model="termin.naziv" type="text" required>
            </div>

            <div class="form-group">
              <label for="lokacija">Lokacija</label>
              <input id="lokacija" v-model="termin.lokacija" type="text" required>
            </div>

            <div class="form-group">
              <label for="pocetak">Pocetak</label>
              <input id="pocetak" v-model="termin.pocetak" type="time" required>
            </div>

            <div class="form-group">
              <label for="kraj">Kraj</label>
              <input id="kraj" v-model="termin.kraj" type="time" required>
            </div>

            <div class="form-group">
              <button type="submit" class="submit-button">Dodaj termin</button>
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

      axios.post('/termini/dodaj', this.termin)
          .then(response => {
            alert("Uspjesno kreiran termin" + response.data);
            router.push('/termini');
          })
          .catch(error => {
            alert("Greska" + error.response.data);
          });
    }
  }
}
</script>

<style scoped>
.termin-section {
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


