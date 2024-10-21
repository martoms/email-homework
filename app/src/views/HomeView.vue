<script setup lang="ts">
  import LoginForm from '@/components/organisms/LoginForm.vue';
</script>

<template>
  <main>
    <VH1 text='Home' />
    <LoginForm />
    <router-link to='/users/registration'>
      Register
    </router-link>
  </main>
</template>
