<script setup lang="ts">
  import useUserStore from '@/stores/user';
  import { storeToRefs } from 'pinia';
  import { computed, toRefs } from 'vue';
  
  const userStore = useUserStore();
  const { user } = storeToRefs(userStore);

  const username = computed(() => {
    return `@${user.value?.username}`
  })

  const fullname = computed(() => {
    return `
      ${user.value?.firstname} ${user.value?.middlename} ${user.value?.lastname}
    `
  })
</script>

<template>
 <div id="user">
   <VH2 :text='username'/>
   <div class="info">
     <VTextP class='name'>
      Name: {{ fullname }}
     </VTextP>
   </div>
 </div>
</template>

<style scoped>

</style>
