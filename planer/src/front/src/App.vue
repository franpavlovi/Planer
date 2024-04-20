<template>
  <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
    <router-link to="/auth/prijava">Prijava</router-link>
    <router-link to="/auth/registracija">Registracija</router-link>
    <router-link to="/termini">Termini</router-link>
    <router-link to="/zadaci">Zadaci</router-link>
    <router-link to="/termini/dodaj">Dodaj termin</router-link>
    <router-link to="/zadaci/dodaj">Dodaj zadatak</router-link>
  </nav>
  <router-view/>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;
  padding: 10px;
  border-radius: 4px;
}

nav a.router-link-exact-active {
  color: #42b983;

}
</style>
<script setup>
</script>