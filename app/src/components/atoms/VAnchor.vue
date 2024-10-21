<script setup lang="ts">
  defineProps<{
    text: string
  }>()
</script>

<template>
  <a>
    {{ text }}
  </a>
</template>

<style scoped>

</style>
