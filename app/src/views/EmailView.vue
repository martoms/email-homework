<script setup lang="ts">
  import useEmailStore from '@/stores/email';
  import { storeToRefs } from 'pinia';
  import useRetrieveEmails from '@/composables/useRetrieveEmails';
  import { readableDate } from '@/utilities/utilities';
  import { onBeforeMount, computed } from 'vue';
  import { useRouter, useRoute } from 'vue-router';

  onBeforeMount(() => {
    const router = useRouter();
    const token = localStorage.getItem('token');
    token || router.push('/')
  })

  const emailStore = useEmailStore();
  const { emails } = storeToRefs(emailStore);

  const email = computed(() => {
    const route = useRoute();
    const id = route.params.id;
    return emails.value.find(email => email?.idEmail === id)
  })

</script>

<template>
  <main>
    <VH1 text='Email' />
    <VH2 :text='email?.title' />
    <VTextP>
      From:
      {{ email?.emailUser }}
    </VTextP>
    <VTextP>
      {{ email && readableDate(email.dateCreated) }}
    </VTextP>
    <div class="content">
      <VTextP>Content:</VTextP>
      {{ email?.content }}
    </div>
  </main>
</template>

<style scoped>

</style>
