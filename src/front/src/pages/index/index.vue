<template>
</template>
<script setup lang="ts">
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()

const goPage = () => {
  if ((localStorage.getItem('username') === null || localStorage.getItem('username') === '')) {
    router.push({ name: 'loginPage' })
  } else {
    router.push({ name: 'overview' })
  }
}
onMounted(() => {
  goPage()
})
</script>