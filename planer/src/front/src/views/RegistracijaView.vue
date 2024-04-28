<template>
  <section class="registration-section">
    <div class="container">
      <div class="card">
        <div class="card-body">
          <!-- Forma -->
          <div class="form-container">
            <h1 class="text-center">Registracija</h1>
            <form @submit.prevent="registracija" class="registration-form">
              <div class="form-group">
                <label for="ime">Ime</label>
                <input id="ime" v-model="korisnik.ime" type="text" placeholder="Unesite ime" required>
              </div>
              <div class="form-group">
                <label for="prezime">Prezime</label>
                <input id="prezime" v-model="korisnik.prezime" type="text" placeholder="Unesite prezime" required>
              </div>
              <div class="form-group">
                <label for="email">Email</label>
                <input id="email" v-model="korisnik.email" type="email" placeholder="Unesite email" required>
              </div>
              <div class="form-group">
                <label for="lozinka">Lozinka</label>
                <input id="lozinka" v-model="korisnik.lozinka" type="password" placeholder="Unesite lozinku"
                       pattern="(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{7,}"
                       title="Lozinka mora sadržavati minimalno 7 znakova koji moraju uključivati barem jedno slovo, jedan broj i jedan specijalni znak (!@#$%^&*)"
                       required>
              </div>
              <div class="form-group">
                <label for="potvrdaLozinke">Potvrdi lozinku</label>
                <input id="potvrdaLozinke" v-model="korisnik.potvrdaLozinke" type="password" placeholder="Potvrdite lozinku" required>
              </div>
              <button type="submit" class="submit-button">Registriraj se</button>
            </form>
            <p class="link-text">
              <a href="/auth/prijava">Već imate račun? Prijavite se</a>
            </p>
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
      korisnik: {
        email: '',
        lozinka: ''
      }
    };
  },
  methods: {
    registracija() {
      axios.post('/api/auth/registracija', this.korisnik)
          .then(response => {
            alert(response.data);
            router.push('/auth/prijava');
          })
          .catch(error => {
            alert(error.response.data);
          });
    }
  }
}
</script>


<style scoped>
.registration-section {
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

.link-text {
  text-align: center;
  margin-top: 1.5rem;
}

.link-text a {
  color: #007bff;
  text-decoration: none;
  transition: color 0.3s;
}

.link-text a:hover {
  color: #0056b3;
}

@media (max-width: 768px) {
  .container {
    max-width: 100%;
    padding: 1rem;
  }

  .card-body {
    padding: 1rem;
  }
}
</style>


