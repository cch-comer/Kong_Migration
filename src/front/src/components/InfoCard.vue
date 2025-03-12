<template>
  <div class="info-container">
    <h3 class="info-title">{{ title }}</h3>
    <p class="info-value">{{ value }}</p>
    </div>
</template>

<script setup lang="ts">
defineProps<{
  title: string
  value: number
}>()
</script>

<style scoped lang="scss">
.info-container {
  border-left: 2px solid #3972d5;
  padding: 1px 8px 3px;
  display: block;
  height: 80%;
}

.info-title {
  font-size: 18px;
  font-weight: 400;
  color: #BEBEBE;
}

.info-value {
  font-size: 16px;
  font-weight: 600;
}
</style>