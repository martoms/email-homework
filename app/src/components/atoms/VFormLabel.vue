<script setup lang="ts">
  defineProps<{
    text: string;
  }>()
</script>

<template>
  <label>
    {{ text }}
  </label>
</template>

<style scoped>

</style>
