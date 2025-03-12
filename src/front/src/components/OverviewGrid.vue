<template>
  <div class="container-overview">
    <div class="overview">Overview</div>
    <div class="content">
      <div class="grid">
        <InfoCard title="API Services" :value="props.service" />
        <InfoCard title="API Consumers" :value="props.consumer" />
        <InfoCard title="API Requests" :value="36846" />
        <InfoCard title="Workspace" :value="props.workspace" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import InfoCard from '@/components/InfoCard.vue'

const props = defineProps({
  service: Number,
  consumer: Number,
  workspace: Number,
})
</script>

<style lang="scss">

.container-overview {
  padding: 24px;
  background: white;
  border: 1px solid #E0E0E0;
  margin-bottom: 30px;
}

.content {
  margin-top: 16px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 24px;
  width: 100%;
}

.overview {
  font-weight: bold;
  font-size: 20px;
  margin-bottom: 40px;
}

</style>