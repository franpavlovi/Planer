import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import PrijavaView from '../views/PrijavaView.vue'
import RegistracijaView from '../views/RegistracijaView.vue'
import NotFoundView from "../views/NotFoundView.vue";
import TerminiView from "../views/TerminiView.vue";
import ZadaciView from "../views/ZadaciView.vue"
import DodajTerminView from "@/views/DodajTerminView.vue";
import DodajZadatakView from "@/views/DodajZadatakView.vue";
import StatistikaView from "@/views/StatistikaView.vue"

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/auth/prijava',
    name: 'prijava',
    component: PrijavaView
  },
  {
    path: '/auth/registracija',
    name: 'registracija',
    component: RegistracijaView
  },
  {
    path: '/about',
    name: 'about',
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path:'/:catchAll(.*)',
    name:'NotFound',
    component: NotFoundView
  },
  {
    path: '/termini',
    name: 'termini',
    component: TerminiView
  },
  {
    path:'/zadaci',
    name: 'zadaci',
    component: ZadaciView
  },
  {
    path:'/termini/dodaj',
    name:'dodajtermin',
    component: DodajTerminView,
  },
  {
    path:'/zadaci/dodaj',
    name:'dodajzadatak',
    component: DodajZadatakView
  },
  {
    path:'/statistika',
    name: 'statistika',
    component: StatistikaView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
