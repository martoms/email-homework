<script setup lang="ts">
  import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
    <router-link to='/'>
      Home
    </router-link>
  </header>

  <RouterView />
</template>

<style scoped>

</style>
