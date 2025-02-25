<template>
</template>
<script setup lang='ts'>
import { useRouter } from 'vue-router'
import { onMounted } from 'vue'
const router = useRouter()

const goPage = () => {
  if (localStorage.getItem('username') === null || localStorage.getItem('username') === '') {
    router.push('/login')
  } else {
    router.push('/overview')
  }
}
onMounted(() => {
  goPage()
})
</script>