<script setup lang="ts">
  import useEmailStore from '@/stores/email';
  import { storeToRefs } from 'pinia';
  import useRetrieveEmails from '@/composables/useRetrieveEmails';
  import { readableDate } from '@/utilities/utilities';
  import { onMounted, computed } from 'vue';

  onMounted(() => {
    useRetrieveEmails()
  })

  const emailStore = useEmailStore();
  const { emails } = storeToRefs(emailStore);

  const recentEmails = computed(() => {
    return emails.value.sort((a, b) => b.dateCreated.localeCompare(a.dateCreated))
  })
</script>

<template>
  <div id="inbox">
    <VH3 text='Inbox' />
    <VUList>
      <template v-if='emails.length'>
        <VList v-for='email in recentEmails' :key='email.idEmail'>
          <router-link :to='`/emails/${email.idEmail}`'>
            {{ email.title }}
          </router-link>
          <VTextP>
            {{ email.emailUser }}
          </VTextP>
          <VTextP>
            {{ readableDate(email.dateCreated) }}
          </VTextP>
          <hr>
        </VList>
      </template>
    </VUList>
  </div>
</template>

<style scoped>

</style>
