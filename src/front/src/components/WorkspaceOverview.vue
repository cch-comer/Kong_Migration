
<template>
  <div class="workspaces-section">
    <div class="workspaces-header">
      <h4>Workspace Lists</h4>
      <div class="view-toggle">
        <span>View:</span>
        <div class="toggle-buttons">
          <button
              :class="['toggle-btn', { active: viewMode === 'grid' }]"
              @click="changeMode('grid')"
          >
            <img :src="gridPath" :class="'grid-icon'"/>
          </button>
          <button
              :class="['toggle-btn', { active: viewMode === 'list' }]"
              @click="changeMode('list')"
          >
            <img :src="listPath" :class="'list-icon'"/>
          </button>
        </div>
      </div>
    </div>

  <!--  <div v-if="viewMode === 'grid'" class="workspaces-grid">
      <div v-for="workspace in workspaces" :key="workspace.id" class="workspace-card">
        <div class="workspace-header">
          <div class="workspace-tag" :class="workspace.tag.toLowerCase()">{{ workspace.tag }}</div>
          <div class="workspace-name">{{ workspace.name }}</div>
        </div>
        <div class="workspace-stats">
          <div class="stat-item">
            <span class="stat-value">{{ workspace.requests }}</span>
            <span class="stat-label">Requests</span>
          </div>
          <div class="stat-item">
            <span class="stat-value">{{ workspace.errorRate }}%</span>
            <span class="stat-label">Error Rate</span>
          </div>
        </div>
        <div class="workspace-chart">
          <div v-if="workspace.hasData" class="chart-placeholder"></div>
          <div v-else class="workspace-no-data">
            <div class="workspace-no-data-text">
              <p>Add a Gateway Service to see its traffic here</p>
            </div>
          </div>
        </div>
        <div class="workspace-footer">
          <div class="footer-stat">
            <span>{{ workspace.consumers }}</span> Consumers
          </div>
          <div class="footer-stat">
            <span>{{ workspace.gatewayServices }}</span> Gateway Services
          </div>
        </div>
      </div>
    </div>-->

  <!--  <div v-else class="workspaces-list">
      <table>
        <thead>
        <tr>
          <th>Workspace</th>
          <th>Requests</th>
          <th>Error Rate</th>
          <th>Consumers</th>
          <th>Gateway Services</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>
            <div class="workspace-info">
              <div class="workspace-tag" :class="workspace.tag.toLowerCase()">{{ workspace.tag }}</div>
              <span>{{ workspace.name }}</span>
            </div>
          </td>
          <td>100</td>
          <td>3%</td>
          <td>consumers</td>
          <td>workspace.gatewayServices</td>
        </tr>
        </tbody>
      </table>
    </div>-->

    <div v-if="viewMode === 'grid'" class="workspaces-grid">
      <div v-for="workspace in props.data" :key="workspace.id" class="workspace-card" @click="goDetail(workspace.id, workspace.name)">
        <div class="workspace-header">
          <div class="workspace-na-grid" :style="{backgroundColor: JSON.parse(workspace.meta.value).color}">{{ workspace.naData }}</div>
          <div class="workspace-name">{{ workspace.name }}</div>
        </div>
        <div class="workspace-stats">
          <div class="stat-item">
            <span class="stat-label item-request">245 Requests</span>
          </div>
          <div class="stat-item item-error">
            <span class="stat-label item-error">2.5% Error Rate</span>
          </div>
        </div>
        <div class="workspace-chart">
          <div class="workspace-no-data">
            <div class="workspace-no-data-text">
              <p>No data available, Try using a longer Timeframe</p>
            </div>
          </div>
        </div>
        <div class="workspace-footer">
          <div class="footer-stat">
            <span>{{ workspace.consumercount }}</span> Consumers
          </div>
          <div class="footer-stat">
            <span>{{ workspace.servicecount }}</span> Gateway Services
          </div>
        </div>
      </div>
    </div>

    <div v-else class="workspaces-list">
      <table>
        <thead>
        <tr>
          <th></th>
          <th>Workspace</th>
          <th>Requests</th>
          <th>Error Rate</th>
          <th>Consumers</th>
          <th>Gateway Services</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="workspace in props.data" :key="workspace.id" @click="testing(workspace.name)">
          <td style="text-align: center"><div class="workspace-na-list" :style="{backgroundColor: JSON.parse(workspace.meta.value).color}">{{ workspace.naData }}</div></td>
          <td>
            <div class="workspace-info">
              <span>{{ workspace.name }}</span>
            </div>
          </td>
          <td>15478</td>
          <td>3%</td>
          <td>{{ workspace.consumercount }}</td>
          <td>{{ workspace.servicecount }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup lang="ts">

import { ref } from 'vue'
import gridPath from '@/assets/grid.svg'
import listPath from '@/assets/list.svg'
import { useRouter } from 'vue-router'

const router = useRouter()
const viewMode = ref('grid')
const changeMode = (now) => {
  viewMode.value = now
}

const props = defineProps({
  data: Array<Record<string, any>>,
})

function goDetail(wid, wname) {
  const ws = localStorage.getItem('ws')
  if (ws !== null && ws !== undefined) {
    localStorage.removeItem('ws')
    localStorage.removeItem('wsId')
  }
  localStorage.setItem('ws', wname)
  localStorage.setItem('wsId', wid)
  router.push({ name: 'dashboard', params: { wid: wname } })
}

</script>

<style lang="scss">

.workspaces-section {
  margin-top: 32px;
}

.workspaces-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
}

.view-toggle {
  display: flex;
  align-items: center;
  gap: 8px;
}

.view-toggle span {
  color: #666;
}

.toggle-buttons {
  display: flex;
  gap: 4px;
}

.toggle-btn {
  padding: 6px;
  border: 1px solid #ddd;
  background: white;
  cursor: pointer;
  border-radius: 4px;
}

.toggle-btn.active {
  background: #f0f0f0;
}

.grid-icon, .list-icon {
  display: inline-block;
  width: 16px;
  height: 16px;
  background-size: contain;
}

.workspaces-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 24px;
  margin-bottom: 200px;
}

.workspace-card {
  background: white;
  border-radius: 8px;
  border: 2px solid rgba(0, 0, 0, 0.05);
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.05);
  padding: 16px;
  cursor: pointer;
  transition: all 0.2s ease;
}

.workspace-card:hover {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  transform: translateY(-1px);
}

.workspace-header {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 16px;
}

.workspace-na-grid {
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 500;
  color: white;
}

.workspace-na-list {
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
  font-weight: 500;
  color: white;
  width: 30%;
  display: inline-block;
  text-align: center;
}

.workspace-name {
  font-weight: 500;
  color: #1f2937;
}

.workspace-stats {
  display: flex;
  gap: 24px;
  margin-bottom: 16px;
  justify-content: space-between;
}

.stat-item {
  display: flex;
  flex-direction: column;
}

.stat-label {
  font-size: 15px;
  color: #666;
}

.stat-value {
  font-size: 14px;
  font-weight: 500;
  color: #1f2937;
}

.item-request {
  color: blue
}

.item-error {
  color: red
}
.workspace-chart {
  height: 120px;
  position: relative;
  background: #f9fafb;
  border-radius: 4px;
  margin-bottom: 16px;
  overflow: hidden;
}

.workspace-no-data {
  position: absolute;
  inset: 0;
  display: flex;
  flex-direction: column;
}

.workspace-no-data-text {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1;
  font-size: 12px;
  color: #666;
}

.workspace-chart-background {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  height: 60%;
  background-repeat: no-repeat;
  background-size: cover;
  background-position: bottom;
}

.workspace-footer {
  display: flex;
  gap: 24px;
  color: #666;
  font-size: 12px;
  justify-content: space-between;
}

.footer-stat span {
  color: #1f2937;
  font-weight: 500;
}

/* List View Styles */
.workspaces-list {
  background: white;
  border-radius: 8px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.05);
  margin-bottom: 300px;
}

.workspaces-list table {
  width: 100%;
  border-collapse: collapse;
}

.workspaces-list th {
  text-align: left;
  padding: 12px 16px;
  font-weight: 500;
  color: #666;
  border-bottom: 1px solid #eee;
}

.workspaces-list td {
  padding: 12px 16px;
  border-bottom: 1px solid #eee;
}

.workspace-info {
  display: flex;
  align-items: center;
  gap: 8px;
}

.workspaces-list tr {
  transition: background-color 0.2s ease;
}

.workspaces-list table tbody tr {
  cursor: pointer;
}

.workspaces-list tr:hover {
  background-color: #f8f9fa;
}

</style>