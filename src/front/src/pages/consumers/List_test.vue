<template>
  <PageHeader :title="t('entities.consumer.list.title')">
    <template #below-title>
      <SupportText>
        {{ t('entities.consumer.description') }}
        <KExternalLink :href="docsLink">
          {{ t('global.learn.more') }}
        </KExternalLink>
      </SupportText>
    </template>
  </PageHeader>
  <KongTable
      :table-columns="tableColumns"
      create-btn-name="Consumer"
      :table-data="tableData"
      to="consumer"
  />
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import KongTable from '@/table/Table_ConsumerList.vue'
import { useI18n } from '@/composables/useI18n'
import { useDocsLink } from '@/composables/useDocsLink'
import { EntityType } from '@/types'
import PageHeader from '@/components/PageHeader.vue'
import SupportText from '@/components/SupportText.vue'
import axios from 'axios'

defineOptions({
  name: 'ConsumerList',
})

const { t } = useI18n()
const docsLink = useDocsLink(EntityType.Route)
const tableData = ref({})

const tableColumns = ref([
  { id: 1, name: 'Username' },
  { id: 2, name: 'Custom ID' },
  { id: 3, name: 'Tags' },
])

function getWsId() {
  return localStorage.getItem('wsId') ? localStorage.getItem('wsId') : ''
}

async function getConsumer() {
  const data = {
    wsId: getWsId(),
  }
  const response = await axios.post('/api/consumer/list',data)
  if (response.status === 200) {
    if (response.data.length < 1) {
      tableData.value = {}
    } else {
      tableData.value = response.data
    }
  }
}

onMounted(async () => {
  await getConsumer()
})
</script>
