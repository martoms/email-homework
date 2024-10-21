import type { HTTPHeaderOptions } from '@/types/types';
import useFetch from './useFetch';

const useRetrieveEmails = () => {
  const id = localStorage.getItem('id');
  const token = localStorage.getItem('token');

  if (id !== null && token !== null) {
    const options: HTTPHeaderOptions = {
      method: 'GET',
      headers: { 'Authorization': token }
    }

    useFetch(`emails/${id}`, options)
  }
}

export default useRetrieveEmails;