import type { HTTPHeaderOptions } from '@/types/types';
import { useRouter } from 'vue-router';
import useFetch from './useFetch';

const useFetchUserData = () => {

  const router = useRouter();
  const token = localStorage.getItem('token');
  const id = localStorage.getItem('id');

  if (token == null || id === null) {
    alert('You need to login first!')
    router.push('/')
  } else {
    const options: HTTPHeaderOptions = {
      method: 'GET',
      headers: { 'Authorization': token }
    }

    useFetch(`users/${id}`, options) 
  }
}

export default useFetchUserData;