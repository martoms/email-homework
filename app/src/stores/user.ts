import type { User } from '@/types/types';
import { defineStore } from 'pinia';
import { ref } from 'vue';

const useUserStore = defineStore('user', () => {
  const user = ref<User | null>(null);

  return { user }
})

export default useUserStore;