import type { HTTPHeaderOptions } from '@/types/types';
import useStateStore from '@/stores/state';
import { storeToRefs } from 'pinia';
import { nonEmpty } from '@/utilities/utilities';
import useFetch from '@/composables/useFetch';
import { useRouter } from 'vue-router';
import type { Ref } from 'vue';
import { computed } from 'vue';

const useLogin = (user: Ref, password: Ref) => {
  const stateStore = useStateStore();
  const { state } = storeToRefs(stateStore);

  const router = useRouter();

  const loginForm = computed(() => {
    return {
      user: user.value,
      password: password.value
    }
  })

  const handleSubmit = () => {
    if (nonEmpty(loginForm.value)) {
      localStorage.removeItem('token');
      localStorage.removeItem('id');

      const options: HTTPHeaderOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(loginForm.value)
      }

      useFetch('users/login', options).then(_ => afterFetch())
    } else alert('All fields must be filled')
  }

  function afterFetch() {
    if (localStorage.getItem('token')) {
      resetFields();
      const id = localStorage.getItem('id')
      router.push(`/dashboard/${id}`)
    } else if (state.value.error !== null) {
      alert(state.value.error.message)
    }
  }

  function resetFields() {
    user.value = '';
    password.value = '';
  }

  return { handleSubmit }
}

export default useLogin;