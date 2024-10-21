<script setup lang="ts">
  import type { Gender } from '@/types/types';
  import useRegister from '@/composables/useRegister';
  import { ref } from 'vue';

  const username = ref('');
  const email = ref('');
  const password = ref('');
  const firstname = ref('');
  const middlename = ref('');
  const lastname = ref('');
  const gender = ref<Gender>('M');
  const birthdate = ref('');

  const options = [
    [ 'M', "Male" ],
    [ 'F', "Female" ]
  ]

  const { handleSubmit } = useRegister(
    username,
    email,
    password,
    firstname,
    middlename,
    lastname,
    gender,
    birthdate
  )

</script>

<template>
  <div class="form">
    <VForm @submit='handleSubmit'>
      <div class="group">
        <VFormLabel for='username' text='Username:' />
        <VFormInput
          id='username'
          v-model='username'
        />
      </div>
      <div class="group">
        <VFormLabel for='email' text='Email:' />
        <VFormInput
          id='email'
          v-model='email'
        />
      </div>
      <div class="group">
        <VFormLabel for='password' text='Password:' />
        <VFormInput
          id='password'
          v-model='password'
          type='password'
        />
      </div>
      <div class="group">
        <VFormLabel for='firstname' text='First Name:' />
        <VFormInput
          id='firstname'
          v-model='firstname'
        />
      </div>
      <div class="group">
        <VFormLabel for='middlename' text='Middle Name:' />
        <VFormInput
          id='middlename'
          v-model='middlename'
        />
      </div>
      <div class="group">
        <VFormLabel for='lastname' text='Last Name:' />
        <VFormInput
          id='lastname'
          v-model='lastname'
        />
      </div>
      <div class="group">
        <VFormLabel for='gender' text='Gender:' />
        <VFormSelect
          id='gender'
          v-model='gender'
          :options
        />
      </div>
      <div class="group">
        <VFormLabel for='birthdate' text='Birth Date:' />
        <VFormInput
          id='birthdate'
          v-model='birthdate'
          type='date'
        />
      </div>
      <VButton
        type='submit'
        text='Register'
      />
    </VForm>
  </div>
</template>

<style scoped>

</style>
