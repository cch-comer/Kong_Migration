<template>
  <PageHeader
    :title="t('entities.route.list.title')"
  >
    <template #below-title>
      <SupportText>
        {{ t('entities.route.description') }}
        <KExternalLink :href="docsLink">
          {{ t('global.learn.more') }}
        </KExternalLink>
      </SupportText>
    </template>
  </PageHeader>
  <KongTable
    :table-columns="tableColumns"
    create-btn-name="Route"
    :table-data="tableData"
    to="route"
  />
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import KongTable from '@/table/Table_RouteList.vue'
import { useI18n } from '@/composables/useI18n'
import { useDocsLink } from '@/composables/useDocsLink'
import { EntityType } from '@/types'
import PageHeader from '@/components/PageHeader.vue'
import SupportText from '@/components/SupportText.vue'
import axios from 'axios'

defineOptions({ name: 'RouteList' })

const { t } = useI18n()
const docsLink = useDocsLink(EntityType.Route)
const tableData = ref({})

const tableColumns = ref([
  { id: 1, name: 'Name' },
  { id: 2, name: 'Protocols' },
  { id: 3, name: 'Hosts' },
  { id: 4, name: 'Methods' },
  { id: 5, name: 'Paths' },
  { id: 7, name: 'Tags' },
  { id: 8, name: 'Last Modified' },
])

function getWsId() {
  return localStorage.getItem('wsId') ? localStorage.getItem('wsId') : ''
}

async function getRoute() {
  const data = {
    wsId: getWsId(),
  }
  const response = await axios.post('/api/route/list',data)
  if (response.status === 200) {
    if (response.data.length < 1) {
      tableData.value = {}
    } else {
      tableData.value = response.data
    }
  }
}

onMounted(async () => {
  await getRoute()
})
</script>
