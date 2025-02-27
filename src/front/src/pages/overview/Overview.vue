<template>
  <PageHeader :title="t('entities.overview.list.title')">
    <template #below-title>
      <SupportText>
        {{ t('entities.overview.description') }}
          <button class="new-workspace">{{ t('entities.overview.create.form.title') }}</button>
      </SupportText>
    </template>
  </PageHeader>
  <SeparateLine/>
  <OverviewGrid/>
  <Traffics/>
  <SeparateLine/>
  <WorkspaceOverview/>
</template>

<script setup lang="ts">
import { useI18n } from '@/composables/useI18n'
import PageHeader from '@/components/PageHeader.vue'
import SeparateLine from '@/components/SeparateLine.vue'
import Traffics from '@/components/Traffics.vue'
import OverviewGrid from '@/components/OverviewGrid.vue'
import WorkspaceOverview from '@/components/WorkspaceOverview.vue'


const { t } = useI18n()

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
