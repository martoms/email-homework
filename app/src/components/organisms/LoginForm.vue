<script setup lang="ts">
  import useLogin from '@/composables/useLogin';
  import { ref, toRefs } from 'vue';

  const user = ref('');
  const password = ref('');

  const { handleSubmit } = useLogin(user, password);

</script>

<template>
 <div class="form">
  <VForm @submit='handleSubmit'>
    <div class="group">
      <VFormLabel for='user' text='Username / Email:'/>
      <VFormInput
        id='user'
        v-model='user'
      />
    </div>
    <div class="group">
      <VFormLabel for='password' text='Password:'/>
      <VFormInput
        id='password'
        v-model='password'
      />
    </div>
    <VButton
      type='submit'
      text='Login'
    />
  </VForm>
 </div>
</template>

<style scoped>

</style>
