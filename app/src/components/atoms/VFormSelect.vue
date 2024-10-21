<script setup lang="ts">
  import type { SelectOption } from '@/types/types';
  defineProps<{
    id: string;
    options: SelectOption[]
  }>()
</script>

<template>
  <select :name='id'>
    <option
      v-for='option in options'
      :key='option[0]'
    >
      {{ option[1] }} 
    </option>
  </select>
</template>

<style scoped>

</style>
