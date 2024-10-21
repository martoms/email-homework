import type { State } from '@/types/types';
import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

const useStateStore = defineStore('state', () => {
  const state = ref<State>({
    isPending: false,
    error: null
  })
 

  return { state }
})

export default useStateStore;