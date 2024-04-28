<template>
  <section class="login-section">
    <div class="container">
      <div class="card">
        <div class="card-body">
          <div class="content">
            <div class="form-container">
              <h1 class="text-center">Prijava</h1>
              <form @submit.prevent="prijava" class="login-form">
                <!-- Email -->
                <div class="form-group">
                  <label for="email">Email</label>
                  <input id="email" v-model="korisnik.email" type="email" placeholder="Unesite email" required>
                </div>

                <!-- Lozinka -->
                <div class="form-group">
                  <label for="password">Lozinka</label>
                  <input id="password" v-model="korisnik.lozinka" type="password" placeholder="Unesite lozinku" required>
                </div>

                <button type="submit" class="submit-button">Prijavi se</button>
              </form>
              <p class="link-text">
                <a href="/auth/registracija">Nemate račun? Kliknite ovdje za registraciju</a>
              </p>
            </div>
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
    prijava() {
      axios.post('/api/auth/prijava', this.korisnik)
          .then(response => {
            alert(response.data);
            router.push('/termini');
          })
          .catch(error => {
            alert(error.response.data)
          });
    }
  }
}
</script>


<style scoped>
.login-section {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #eee;
}

.container {
  width: 90%;
  max-width: 400px; /* Adjusted max width for login */
  margin: auto;
}

.card {
  background: white;
  border-radius: 25px;
  box-shadow: 0 4px 8px rgba(0,0,0,0.1);
}

.card-body {
  padding: 2rem;
}

.content {
  display: flex;
  flex-direction: column;
}

.form-container {
  width: 100%;
}

.text-center {
  text-align: center;
}

.form-group {
  margin-bottom: 1rem;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
}

.form-group input {
  width: 100%;
  padding: 0.8rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-sizing: border-box;
}

.submit-button {
  width: 100%;
  padding: 1rem;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  margin-top: 1rem;
}

.link-text {
  text-align: center;
  margin-top: 1rem;
}

.link-text a {
  color: #007bff;
  text-decoration: none;
}
</style>
