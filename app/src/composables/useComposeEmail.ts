import type { HTTPHeaderOptions } from '@/types/types';
import useStateStore from '@/stores/state';
import { storeToRefs } from 'pinia';
import { nonEmpty } from '@/utilities/utilities';
import useFetch from '@/composables/useFetch';
import type { Ref } from 'vue';
import { computed } from 'vue';

const useComposeEmail = (
  recepient: Ref,
  title: Ref,
  content: Ref
) => {

  const id = localStorage.getItem('id');
  const token = localStorage.getItem('token');

  const stateStore = useStateStore();
  const { state } = storeToRefs(stateStore);

  const emailForm = computed(() => {
    return {
      recepient: recepient.value,
      title: title.value,
      content: content.value
    }
  })

  const handleSubmit = () => {
    if (nonEmpty(emailForm.value)) {
      if (token !== null) {
        const options: HTTPHeaderOptions = {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': token
          },
          body: JSON.stringify(emailForm.value)
        }

        useFetch(`users/${id}`, options).then(_ => afterFetch())
      }
    } else alert('All fields must be filled')
  }

  function afterFetch() {
    if (state.value.error !== null) {
      alert(state.value.error.message)
      state.value.error = null
    } else {
      resetFields();
    }
  }

  function resetFields() {
    recepient.value = '';
    title.value = '';
    content.value = '';
  }

  return { handleSubmit }
}

export default useComposeEmail;