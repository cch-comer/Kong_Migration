<template>
  <AppLayout
      v-if="!hideComponents && !hideComponents2"
      :sidebar-top-items="sidebarItems"
  >
    <template #navbar-right>
      <div style="color: #C20A0A">
        {{ username }}
      </div>
      <k-button
          size="large"
          @click="logout"
      >
        Logout
      </k-button>
    </template>
    <template #sidebar-header>
      <NavbarLogo />
    </template>
    <router-view />
  </AppLayout>
  <AppLayout
      v-else-if="!hideComponents && hideComponents2"
      :sidebar-top-items="sidebarItems2"
  >
    <template #navbar-right>
      <div style="color: #C20A0A">
        {{ username }}
      </div>
      <k-button
          size="large"
          @click="logout"
      >
        Logout
      </k-button>
    </template>
    <template #sidebar-header>
      <NavbarLogo />
    </template>
    <router-view />
  </AppLayout>
  <router-view v-else />
</template>

<script setup lang="ts">
import { watch, computed, onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'
import { storeToRefs } from 'pinia'
import { AppLayout, type SidebarPrimaryItem } from '@kong-ui-public/app-layout'
import { useInfoStore } from '@/stores/info'
import NavbarLogo from '@/components/NavbarLogo.vue'
import axios from 'axios'


const route = useRoute()
const infoStore = useInfoStore()
const { isHybridMode } = storeToRefs(infoStore)
const username = ref('')
const hideComponents = computed(() => route.name === 'loginPage' || route.name === 'where')
const hideComponents2 = computed(() => route.name === 'overview' || route.name === 'portals' || route.name === 'vitals' || route.name === 'users' || route.name === 'create-workspace')
const sidebarItems = computed<Array<SidebarPrimaryItem>>(() => [
  {
    name: 'test',
    to: { name: 'test', params: { wid: getWid() } },
    key: 'test',
    active: route.meta?.entity === 'test',
  },
  {
    name: 'Dashboard',
    to: { name: 'dashboard', params: { wid: getWid() } },
    key: 'Dashboard',
    active: route.meta?.entity === 'dashboard',
  },
  {
    name: 'Gateway Services',
    to: { name: 'service-list', params: { wid: getWid() } },
    key: 'Gateway Services',
    active: route.meta?.entity === 'service',
  },
  {
    name: 'Routes',
    to: { name: 'route-list', params: { wid: getWid() } },
    key: 'Routes',
    active: route.meta?.entity === 'route',
  },
  {
    name: 'Consumers',
    to: { name: 'consumer-list', params: { wid: getWid() } },
    key: 'Consumers',
    active: route.meta?.entity === 'consumer',
  },
  {
    name: 'Plugins',
    to: { name: 'plugin-list', params: { wid: getWid() } },
    key: 'Plugins',
    active: route.meta?.entity === 'plugin',
  },
  {
    name: 'Upstreams',
    to: { name: 'upstream-list', params: { wid: getWid() } },
    key: 'Upstreams',
    active: route.meta?.entity === 'upstream',
  },
  {
    name: 'Certificates',
    to: { name: 'certificate-list' },
    key: 'Certificates',
    active: route.meta?.entity === 'certificate',
  },
  {
    name: 'CA Certificates',
    to: { name: 'ca-certificate-list' },
    key: 'CA Certificates',
    active: route.meta?.entity === 'ca-certificate',
  },
  {
    name: 'SNIs',
    to: { name: 'sni-list', params: { wid: getWid() } },
    key: 'SNIs',
    active: route.meta?.entity === 'sni',
  },
  {
    name: 'Vaults',
    to: { name: 'vault-list' },
    key: 'Vaults',
    active: route.meta?.entity === 'vault',
  },
  {
    name: 'Keys',
    to: { name: 'key-list' },
    key: 'Keys',
    active: route.meta?.entity === 'key',
  },
  {
    name: 'Key Sets',
    to: { name: 'key-set-list', params: { wid: getWid() } },
    key: 'Key Sets',
    active: route.meta?.entity === 'key-set',
  },
  ...(
      isHybridMode.value
          ? [
            // {
            //   name: 'Data Plane Nodes',
            //   to: { name: 'data-plane-nodes' },
            //   key: 'Data Plane Nodes',
            //   active: route.meta?.entity === 'data-plane-node',
            // },
          ]
          : []
  ),
])

const sidebarItems2 = computed<Array<SidebarPrimaryItem>>(() => [
  {
    name: 'Workspace',
    to: { name: 'overview' },
    key: 'overview',
    active: route.name === 'overview',
  },
  {
    name: 'Dev Portals',
    to: { name: 'portals' },
    key: 'portals',
    active: route.name === 'portals',
  },
  {
    name: 'Vitals',
    to: { name: 'vitals' },
    key: 'vitals',
    active: route.name === 'vitals',
  },
  {
    name: 'Users',
    to: { name: 'users' },
    key: 'users',
    active: route.name === 'users',
  },
])

function getWid() {
  return localStorage.getItem('ws') ? localStorage.getItem('ws') : 'default'
}

const logout = async () => {
  try {
    const response = await axios.post('/api/logout', null, {
      withCredentials: true,
    })

    if (response.status === 200) {
      localStorage.clear()
      alert('logout success')
      route.push({ name: 'where' })
    } else {
      localStorage.clear()
      alert('session is expired')
    }

  } catch (error) {
    alert('logout error')
  }
}

watch(() => route.path, () => {
  username.value = localStorage.getItem('username') || 'Guest'
})

onMounted(() => {
})

</script>

<style scoped lang="scss">
.app-title {
  color: #fff;
  margin: 0;
  font-size: 20px;
}

:deep(.kong-ui-app-layout-content-inner) {
  position: relative;
  min-height: 100%;
  padding: 32px 40px 80px !important;
}

:deep(.json-content.k-code-block) {
  border-top-left-radius: $kui-border-radius-0 !important;
  border-top-right-radius: $kui-border-radius-0 !important;
}
</style>
