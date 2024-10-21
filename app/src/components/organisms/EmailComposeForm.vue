<script setup lang="ts">
  import useComposeEmail from '@/composables/useComposeEmail';
  import { ref, toRefs } from 'vue';

  const recepient = ref('');
  const title = ref('');
  const content = ref('');

  const { handleSubmit } = useComposeEmail(recepient, title, content);
</script>

<template>
 <div class="form">
  <VH3 text='Compose Email' />
  <VForm @submit='handleSubmit'>
    <div class="group">
      <VFormLabel for='recepient' text='Recepient:'/>
      <VFormInput
        id='recepient'
        v-model='recepient'
        type='email'
      />
    </div>
    <div class="group">
      <VFormLabel for='title' text='Title:'/>
      <VFormInput
        id='title'
        v-model='title'
      />
    </div>
    <div class="group">
      <VFormLabel for='content' text='Content:'/>
      <VFormTextarea
        id='content'
        v-model='content'
      />
    </div>
    <VButton
      type='submit'
      text='Send'
    />
  </VForm>
 </div>
</template>

<style scoped>

</style>
