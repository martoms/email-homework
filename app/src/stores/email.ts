import type { Email } from '@/types/types';
import { ref, computed, watch } from 'vue'
import { defineStore } from 'pinia'

const useEmailStore = defineStore('email', () => {
  const emails = ref<Email[]>([]);

  watch(emails, (newVal, oldVal) => {
    localStorage.setItem('emails', JSON.stringify(emails.value))
    const emailsFromStorage = localStorage.getItem('emails');
    if (emailsFromStorage !== null) {
      if (JSON.stringify(newVal) !== JSON.stringify(oldVal)) {
       emails.value = JSON.parse(emailsFromStorage)
      }
    }
  })

  return { emails }
})

export default useEmailStore;