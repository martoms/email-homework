<script setup lang="ts">
  import { toRefs } from 'vue';
  const props = withDefaults(defineProps<{
    id: string;
    type?: string;
  }>(), {
    type: 'text'
  })

  const { type } = toRefs(props)
  const modelValue = defineModel('modelValue')
</script>

<template>
 <input
    :type
    :name='id'
    v-model='modelValue'
 >
</template>

<style scoped>

</style>
