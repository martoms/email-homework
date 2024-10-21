<script setup lang="ts">
  import { onBeforeMount } from 'vue';
  import useFetchUserData from '@/composables/useFetchUserData';
  import VUser from '@/components/organisms/VUser.vue';
  import VUserInbox from '@/components/organisms/VUserInbox.vue';
  import EmailComposeForm from '@/components/organisms/EmailComposeForm.vue';

  onBeforeMount(() => {
    useFetchUserData()
  })

</script>

<template>
  <main>
    <VH1 text='Dashboard' />
    <VUser/>
    <EmailComposeForm />
    <VUserInbox />
  </main>
</template>

<style scoped>

</style>
