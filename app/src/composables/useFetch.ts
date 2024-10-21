import type { HTTPHeaderOptions } from '@/types/types';
import { isFetchError } from '@/utilities/predicates';
import useStateStore from '@/stores/state';
import useUserStore from '@/stores/user';
import useEmailStore from '@/stores/email';
import { storeToRefs } from 'pinia';

const useFetch = async (
  resource?: string, options?: HTTPHeaderOptions
) => {
  const stateStore = useStateStore();
  const userStore = useUserStore();
  const emailStore = useEmailStore();
  const { state } = storeToRefs(stateStore);
  const { user } = storeToRefs(userStore);
  const { emails } = storeToRefs(emailStore);

  const API = import.meta.env.VITE_SERVER;

  state.value.isPending = true;
  state.value.error = null;    

  try {
    const response = options
    ? await fetch(API + resource, options)
    : await fetch(API + resource)

    const res = await response.json();

    if (res.status === 'error') {
      throw new Error(res.message)

    } else if (res.token) {
      localStorage.setItem('id', res.token.id);
      localStorage.setItem('token', res.token.token);
      localStorage.removeItem('emails');

    } else if (res.user) {
      user.value = res.user

    } else if (res.emails?.length) {
      emails.value = res.emails[0]

    } else {
      alert(res.message)
    }

  } catch (err: unknown) {
    
    if (isFetchError(err)) {
      state.value.error = err    
    }

  } finally {
    state.value.isPending = false;
  }
}

export default useFetch;
