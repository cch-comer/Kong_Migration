<template>
  <div class="workspace-create-container">
    <h2 class="create-content">Create Workspace</h2>
    <SeparateLine/>
    <!-- Name Input -->
    <label class="name-label">Name</label>
    <input
        v-model="workspaceName"
        type="text"
        class="name-input"
    />
    <p class="name-hold">Workspace name cannot be changed after creation.</p>

    <!-- Avatar Selection -->
    <h3 class="workspace-avatar">Workspace Avatar</h3>
    <div class="avatar-container">
      <div
          v-for="(color, index) in avatarColors"
          :key="index"
          class="select-color"
          :style="{ backgroundColor: color }"
          @click="selectAvatar(color)"
      >
      <span v-if="selectedColor === color" class="color-view">✔</span>
      </div>
    </div>

    <SeparateLine/>
    <h3 class="workspace-avatar">Preview</h3>
    <div class="box-preview">
      <span class="preview-tags" :style="{backgroundColor: selectedColor}">{{ workspaceUpperName }}</span>
      <span class="preview-name">{{ workspaceName }}</span>
    </div>
    <!-- Buttons -->
    <div class="workspace-button">
      <button @click="cancel" class="create-cancel-button">Cancel</button>
      <button @click="createWorkspace" class="create-workspace-button">Create New Workspace</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { watch, ref } from 'vue'
import SeparateLine from '@/components/SeparateLine.vue'
import axios from 'axios'
import { useRouter } from 'vue-router'

const router = useRouter()
const workspaceUpperName = ref('')
const workspaceName = ref('')
const selectedColor = ref('#3b82f6') // Default: Blue
const avatarColors = ref(['#3b82f6', '#6b7280', '#9333ea', '#dc2626', '#10b981', '#1f2937', '#f59e0b', '#2563eb'])


watch(workspaceName, (value) => {
  if (value.includes('-')) {
    const parts = value.split('-')
    workspaceUpperName.value = parts[0].charAt(0).toUpperCase() + parts[1].charAt(0).toUpperCase()
  } else {
    workspaceUpperName.value = value.slice(0, 2).toUpperCase()
  }
})

const selectAvatar = (color) => {
  selectedColor.value = color
}

const cancel = () => {
  router.push({ name: 'overview' })
}

const createWorkspace = async () => {
  const response = await axios.post('/api/workspace/create', {
    name: workspaceName.value,
    color: selectedColor.value,
  })
  if (response.status === 200) {
    await router.push({name: 'overview'})
  }
}
</script>

<style scoped lang="scss">

.workspace-create-container {
  max-width: 45rem;
  margin: auto;
  padding: 2rem;
  background: white;
}

.create-content {
  font-weight: 600;
  color: #0A2B6B;
  margin-bottom: 1rem;
}

.name-label {
  display: block;
  font-size: 20px;
  font-weight: 700;
  color: #4b5563;
}

.name-input {
  width: 80%;
  padding: 0.5rem;
  margin-top: 0.25rem;
  margin-bottom: 0.25rem;
  border: 1px solid #E0E0E0;
}

.name-hold {
  font-size: 0.75rem;
  color: #6b7280;
  margin-top: 0.25rem;
  font-style: italic;
}

.workspace-avatar {
  margin-top: 2rem;
  font-size: 20px;
  font-weight: 700;
  color: #4b5563;
}

.avatar-container {
  display: flex;
  gap: 0.5rem;
  margin-top: 1rem;
}

.select-color {
  width: 2.5rem;
  height: 2rem;
  cursor: pointer;
  margin-right: 0.5rem;
}

.color-view {
  position: relative;
  top: 10%;
  left: 30%;
  color: white;
}

.workspace-button {
  display: flex;
  justify-content: space-between;
  margin-top: 1.5rem;
}

.create-cancel-button {
  width: 7rem;
  padding: 0.5rem 1rem;
  background-color: #ffffff;
  color: #2563eb;
  border: 1px solid #2563eb;
  cursor: pointer;
  border-radius: 25px;
  font-size: 15px;
  font-weight: bold;
}

.create-workspace-button {
  width: 14rem;
  font-weight: bold;
  font-size: 1rem;
  cursor: pointer;
  padding: 0.5rem 1rem;
  background-color: #2563eb;
  color: #ffffff;
  border-radius: 25px;
  border: 1px solid #2563eb;
}

.box-preview {
  justify-content: center;
  display: flex;
  align-items: center;
  height: 200px;
  background: #f3f4f6;
  margin-bottom: 40px;
  margin-top: 30px;
}

.preview-tags {
  align-content: center;
  text-align: center;
  width: 6%;
  height: 40px; /* 높이 조절 가능 */
  margin-right: 1rem;
  color: white;
  font-weight: bold;
}

.preview-name {
  font-weight: 600;
}

/* 색상 스타일 */
.bg-blue { background-color: #3b82f6; }
.bg-gray { background-color: #6b7280; }
.bg-purple { background-color: #9333ea; }
.bg-red { background-color: #dc2626; }
.bg-green { background-color: #10b981; }
.bg-black { background-color: #1f2937; }
.bg-orange { background-color: #f59e0b; }
.bg-indigo { background-color: #2563eb; }
</style>