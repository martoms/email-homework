<script setup lang="ts">
  defineEmits(['submit'])
</script>

<template>
 <form @submit.prevent="$emit('submit')">
   <slot></slot>
 </form>
</template>

<style scoped>

</style>
