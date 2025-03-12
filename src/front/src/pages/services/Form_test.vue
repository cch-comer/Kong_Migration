<template>
  <PageHeader
    :title="isEditing ? t('entities.service.edit.form.title') : t('entities.service.create.form.title')"
  />
  <KongTable
  />
</template>

<script setup lang="ts">
import { computed, ref } from 'vue'
import KongTable from '@/table/Table_ServiceForm.vue'
import { useRoute, useRouter } from 'vue-router'
import { useFormRedirectOnCancel } from '@/composables/useFormRedirect'
import { useI18n } from '@/composables/useI18n'

defineOptions({
  name: 'ServiceForm',
})

const route = useRoute()
const router = useRouter()
const { t } = useI18n()
const tableData = ref({})

const id = computed(() => (route.params.id as string) ?? '')
const isEditing = computed(() => !!id.value)

/*const routeOnCancel = useFormRedirectOnCancel(
  isEditing.value
    ? { name: 'service-detail', params: { id: id.value } }
    : { name: 'service-list' },
)*/


</script>
