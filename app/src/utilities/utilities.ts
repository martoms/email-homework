import type { DateTimeFormatOptions } from '@/types/types';

export const nonEmpty = (formFields: Object) => {
  return Object.values(formFields).every(input => input !== '' && input !== undefined)
}

export const readableDate = (str: string) => {
  const format: DateTimeFormatOptions  = { month: 'long', day: 'numeric', year: 'numeric', hour: 'numeric', minute: 'numeric' }
  return new Date(str).toLocaleDateString(undefined, format)
}