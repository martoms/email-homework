<script setup lang="ts">
  defineProps<{
    text: string;
  }>()
</script>

<template>
  <button>
    {{ text }}
  </button>
</template>

<style scoped>

</style>
