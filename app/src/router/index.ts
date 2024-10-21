import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import RegisterView from '@/views/RegisterView.vue'
import DashboardView from '@/views/DashboardView.vue'
import EmailView from '@/views/EmailView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/users/registration',
      name: 'register',
      component: RegisterView
    },
    {
      path: '/dashboard/:id',
      name: 'dashboard',
      component: DashboardView
    },
    {
      path: '/emails/:id',
      name: 'emails',
      component: EmailView
    }
  ]
})

export default router
