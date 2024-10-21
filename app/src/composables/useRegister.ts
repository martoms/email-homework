import type { HTTPHeaderOptions } from '@/types/types';
import useStateStore from '@/stores/state';
import { storeToRefs } from 'pinia';
import { nonEmpty } from '@/utilities/utilities';
import useFetch from '@/composables/useFetch';
import { useRouter } from 'vue-router';
import type { Ref } from 'vue';
import { computed } from 'vue';

const useRegister = (
  username: Ref,
  email: Ref,
  password: Ref,
  firstname: Ref,
  middlename: Ref,
  lastname: Ref,
  gender: Ref,
  birthdate: Ref
) => {
  const stateStore = useStateStore();
  const { state } = storeToRefs(stateStore);

  const router = useRouter();

  const signupForm = computed(() => {
    return {
      username: username.value,
      email: email.value,
      password: password.value,
      firstname: firstname.value,
      middlename: middlename.value,
      lastname: lastname.value,
      gender: gender.value,
      birthdate: birthdate.value
    }
  })

  const handleSubmit = () => {
    if (nonEmpty(signupForm.value)) {

      localStorage.removeItem('token');
      localStorage.removeItem('id');

      const options: HTTPHeaderOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(signupForm.value)
      }
      useFetch('users', options).then(_ => afterFetch())

    } else alert('Please fillup all fields')
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
    username.value = '';
    email.value = '';
    password.value = '';
    firstname.value = '';
    middlename.value = '';
    lastname.value = '';
    gender.value = 'M';
    birthdate.value = '';
  }

  return { handleSubmit }
}

export default useRegister;

