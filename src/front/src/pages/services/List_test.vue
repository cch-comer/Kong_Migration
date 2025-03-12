<template>
  <PageHeader :title="t('entities.service.list.title')">
    <template #below-title>
      <SupportText>
        {{ t('entities.service.description') }}
        <KExternalLink :href="docsLink">
          {{ t('global.learn.more') }}
        </KExternalLink>
      </SupportText>
    </template>
  </PageHeader>
  <kongTable
    :table-columns="tableColumns"
    create-btn-name="Gateway Service"
    :table-data="tableData"
    to="service"
  />
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import kongTable from '@/table/Table_ServiceList.vue'
import { useI18n } from '@/composables/useI18n'
import { useDocsLink } from '@/composables/useDocsLink'
import { EntityType } from '@/types'
import PageHeader from '@/components/PageHeader.vue'
import SupportText from '@/components/SupportText.vue'
import axios from 'axios'

defineOptions({
  name: 'ServiceList',
})

const { t } = useI18n()
const docsLink = useDocsLink(EntityType.GatewayService)
const tableData = ref({})

function getWsId() {
  return localStorage.getItem('wsId') ? localStorage.getItem('wsId') : ''
}


const tableColumns = ref([
  { id: 1, name: 'Name' },
  { id: 2, name: 'Protocol' },
  { id: 3, name: 'Host' },
  { id: 4, name: 'Port' },
  { id: 5, name: 'Path' },
  { id: 6, name: 'Enabled' },
  { id: 7, name: 'Tags' },
  { id: 8, name: 'Last Modified' },
])

async function getService() {
  const data = {
    wsId: getWsId(),
  }
  const response = await axios.post('/api/service/list',data)
  if (response.status === 200) {
    if (response.data.length < 1) {
      tableData.value = {}
    } else {
      tableData.value = response.data
    }
  }
}

onMounted(async () => {
  await getService()
})

</script>
