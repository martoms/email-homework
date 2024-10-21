export const isFetchError = (err: unknown): err is Error => {
  return typeof err === 'object' && err !== null && err instanceof Error
}