<template>
</template>
<script setup lang="ts">
import { onMounted } from 'vue'
import { Routes } from '@/utils/Routes'

const { goRoutes } = Routes()

const goPage = () => {
  if ((localStorage.getItem('username') === null || localStorage.getItem('username') === '')) {
    goRoutes('loginPage')
  } else {
    goRoutes('overview')
  }
}
onMounted(() => {
  goPage()
})
</script>