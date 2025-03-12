<template>
  <PageHeader :title="t('entities.overview.list.title')">
    <template #below-title>
      <SupportText>
        {{ t('entities.overview.description') }}
          <button
              class="new-workspace"
              @click="createWorkspace">{{ t('entities.overview.create.form.title') }}</button>
      </SupportText>
    </template>
  </PageHeader>
  <SeparateLine/>
  <OverviewGrid
    :service="apiServices"
    :consumer="apiConsumers"
    :workspace="workspaces"
  />
  <Traffics/>
  <SeparateLine/>
  <WorkspaceOverview
    :data="workspaceData"/>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useI18n } from '@/composables/useI18n'
import PageHeader from '@/components/PageHeader.vue'
import SeparateLine from '@/components/SeparateLine.vue'
import Traffics from '@/components/Traffics.vue'
import OverviewGrid from '@/components/OverviewGrid.vue'
import WorkspaceOverview from '@/components/WorkspaceOverview.vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()
const { t } = useI18n()
const apiServices = ref(0)
const apiConsumers = ref(0)
const workspaces = ref(0)
const workspaceData = ref()

const createWorkspace = () => {
  router.push({ name: 'create-workspace' })
}

const getOverviewInfo = async () => {
  const response = await axios.post('/api/workspace/list')
  if (response.status === 200) {
    const datas = response.data
    if (datas.length < 1) {
      console.log('Workspace is null !!')
    } else {
      workspaces.value = datas.length
      for (const data of datas) {
        if (data.name.includes('-')) {
          const parts = data.name.split('-')
          data.naData = parts[0].charAt(0).toUpperCase() + parts[1].charAt(0).toUpperCase()
        } else {
          data.naData = data.name.slice(0, 2).toUpperCase()
        }

        apiServices.value += data.servicecount
        apiConsumers.value += data.consumercount

      }
      workspaceData.value = datas

    }


  }
}

function delStorage() {
  const ws = localStorage.getItem('ws')
  if (ws !== null && ws !== undefined) {
    localStorage.removeItem('ws')
    localStorage.removeItem('wsId')
  }
}

onMounted(() => {
  getOverviewInfo()
  delStorage()
})


</script>
<style lang="scss">
.new-workspace {
  padding: 11px 27px;
  background: #1155cb;
  color: white;
  border-radius: 25px;
  border: none;
  cursor: pointer;
  font-weight: bold;
  font-size: 18px;
  position: absolute;
  right: 44px;
  transform: translateY(-50%);
}
</style>/
