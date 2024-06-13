<template>
  <NavigacijaGlava/>

  <div class="dodaj-termin">
    <v-btn flat size="large" rounded="0" class="termini-btn" @click="prikazi = true">DODAJ TERMIN</v-btn>
    <hr class="underline">
  </div>

  <div class="month-navigation">
    <v-btn class="termini-btn" @click="prevMonth">Prethodni mjesec</v-btn>
    <div class="tekst">
    <span class="tekst">{{ monthNames[currentMonth] }} {{ currentYear }}</span>
    </div>
    <v-btn class="termini-btn" @click="nextMonth">Sljedeći mjesec</v-btn>
  </div>

  <div class="v-kalendar" id="calendar">
    <div v-for="day in daysInCurrentMonth" :key="day.date" class="day">
      <span class="date">{{ day.date }}</span>
      <div v-for="event in day.events" :key="event.name" class="event">
        {{ event.name }}
      </div>
    </div>
  </div>

  <ModalDodajTermin v-if="prikazi" @close="zatvori" @termin-created="fetchEvents"/>
</template>

<script>
import NavigacijaGlava from '@/components/NavigacijaGlava.vue'
import ModalDodajTermin from "@/components/ModalDodajTermin.vue";
import axios from 'axios';

export default {
  name: 'TerminiView',
  components: { NavigacijaGlava, ModalDodajTermin },

  data() {
    return {
      prikazi: false,
      events: [],
      calendarDays: [],
      currentMonth: new Date().getMonth(),
      currentYear: new Date().getFullYear(),
      monthNames: ["SIJEČANJ", "VELJAČA", "OŽUJAK", "TRAVANJ", "SVIBANJ", "LIPANJ", "SRPANJ", "KOLOVOZ", "RUJAN", "LISTOPAD", "STUDENI", "PROSINAC"]

    }
  },

  computed: {
    daysInCurrentMonth() {
      const daysInMonth = new Date(this.currentYear, this.currentMonth + 1, 0).getDate();
      const startDay = new Date(this.currentYear, this.currentMonth, 1).getDay();
      const days = [];

      for (let i = 0; i < daysInMonth + startDay; i++) {
        if (i >= startDay) {
          const date = i - startDay + 1;
          const dayEvents = this.events.filter(event => {
            const eventDate = new Date(event.start);
            return eventDate.getFullYear() === this.currentYear && eventDate.getMonth() === this.currentMonth && eventDate.getDate() === date;
          });
          days.push({ date, events: dayEvents });
        } else {
          days.push({ date: '', events: [] });
        }
      }
      return days;
    }
  },

  mounted() {
    this.fetchEvents();
  },

  methods: {
    zatvori() {
      this.prikazi = false;
    },
    async fetchEvents() {
      try {
        const response = await axios.get('/api/termini');
        this.events = response.data.map(termin => ({
          name: termin.naziv,
          start: new Date(termin.pocetak),
          end: new Date(termin.kraj)
        }));
      } catch (error) {
        console.error("Error fetching events:", error);
      }
    },
    prevMonth() {
      if (this.currentMonth === 0) {
        this.currentMonth = 11;
        this.currentYear--;
      } else {
        this.currentMonth--;
      }
    },
    nextMonth() {
      if (this.currentMonth === 11) {
        this.currentMonth = 0;
        this.currentYear++;
      } else {
        this.currentMonth++;
      }
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

.month-navigation {
  display: flex;
  justify-content: space-between;
  margin: 20px 30px;
}

.v-kalendar {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  gap: 5px;
  background-color: #ddd;
  width: 1500px;
  height: 800px;
  margin: 20px auto;
  border: 2px solid #999;
  box-shadow: 2px 2px 10px rgba(0,0,0,0.3);
}

.day {
  background-color: #f9f9f9;
  padding: 10px;
  text-align: right;
  position: relative;
  min-height: 120px;
}

.day .date {
  font-size: 1em;
  color: #888;
}

.event {
  background-color: #87ceeb;
  color: #fff;
  padding: 5px 10px;
  border-radius: 5px;
  font-size: 1em;
  margin: 5px 0;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.tekst {
  flex: 1;
  text-align: center;
  margin: 0 10px;
  font-family: 'Roboto', sans-serif;
  font-weight: bold;
  font-size: 1.2em;
  color: #2c3e50;
}
</style>
