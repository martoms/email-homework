<script setup lang="ts">
  import { toRefs } from 'vue';
  defineProps<{
    id: string;
  }>()

  const modelValue = defineModel<string>('modelValue')
</script>

<template>
 <textarea
    :name='id'
    v-model='modelValue'
 />
</template>

<style scoped>

</style>
