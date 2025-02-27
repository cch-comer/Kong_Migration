<template>
  <div class="traffic-controls">
    <div class="left-group">
      <p>Total Traffic</p>
      <p>36,846</p>
    </div>
    <div style="font-size: 15px;">Timeframe :</div>
      <KSelect
          v-model="selectedTimeframe"
          :items="timeframeOptions"
          class="timeframe-select"
          data-testid="timeframe-select"
      />
      <KCheckbox v-model="isUtc" class="utc-checkbox" data-testid="utc-checkbox">UTC</KCheckbox>
  </div>

  <div class="chart-container">
    <div class="traffic-chart">
      <div class="no-data">
        <div class="no-data-text">
          <p>No data available, Try using a longer timeframe</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { KCheckbox } from '@kong/kongponents'
import { ref } from 'vue'

const isUtc = ref(false)

const timeframeOptions = [
  { label: 'Last 5 Minutes', value: '5m' },
  { label: 'Last 30 Minutes', value: '30m' },
  { label: 'Last 60 Minutes', value: '60m' },
  { label: 'Last 6 Hours', value: '6h' },
  { label: 'Last 12 Hours', value: '12h' },
]

// 현재 선택된 timeframe
const selectedTimeframe = ref(timeframeOptions[1].value)

</script>

<style lang="scss">

.traffic-controls {
  display: flex;
  align-items: center;
  gap: 16px;
  margin-bottom: 16px;
}

.left-group {
  margin-right: auto;
}

.left-group > p:first-child {
  font-weight: 500;
  font-size: 20px;
}

.left-group > p:last-child {
  text-align: center;
  display: block;
}

.timeframe-select {
  width: 180px !important;
}

.utc-checkbox {
}

.chart-container {
  height: 400px;
  position: relative;
  background: #f8f9fa;
  border-radius: 8px;
}

.traffic-chart {
  height: 100%;
  position: relative;
  background: #f8f9fa;
  border-radius: 8px;
}

.no-data {
  position: absolute;
  inset: 0;
  display: flex;
  flex-direction: column;
}

.no-data-text {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1;
  font-size: 20px;
}
</style>