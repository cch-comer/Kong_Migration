<template>
  <div class="containers">
    <div class="header">
      <input
        type="text"
        placeholder="Filter by exact name or ID"
        class="filter-input"
      >
      <button class="new-service-btn">
        + New {{ createBtnName }}
      </button>
    </div>
    <table class="service-table">
      <thead>
        <tr>
          <th
            v-for="col in tableColumns"
            :key="col.id"
          >
            {{ col.name }}
          </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="data in tableData"
          :key="data.id"
          @click="goRouter(data.id, to)"
        >
          <td>
            <span v-if="data.username === null">-</span>
            <strong v-else>{{ data.username }}</strong>
          </td>
          <td>
            <span v-if="data.custom_id === null">-</span>
            <span v-else>{{ data.custom_id }}</span>
          </td>
          <td>
            <span v-if="data.tags === null">-</span>
            <span
              v-for="tag in data.tags"
              :key="tag"
              class="tag"
            >{{ tag }}</span>
          </td>
          <td style="cursor: pointer">
            ...
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

defineProps({
  tableColumns: Array,
  tableData: Object,
  createBtnName: String,
  to: String,
})

function getWid() {
  return localStorage.getItem('ws') ? localStorage.getItem('ws') : 'default'
}

const goRouter = (id, to) => {
  const route = {
    name: to + '-detail',
    params: { id, wid: getWid() },
  }
  router.push(route)
}

</script>

<style scoped>
.containers {
  width: 100%;
  margin: auto;
  padding: 20px;
  border: solid 1px #E0E4EA;
}
.header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}
.filter-input {
  flex: 1;
  padding: 8px;
  margin-right: 10px;
  border: solid 1px #E0E4EA;
  border-radius: 8px;
}
.new-service-btn {
  background-color: #0044F4;
  color: white;
  border: none;
  padding: 8px 12px;
  cursor: pointer;
  border-radius: 5px;
  font-size: 18px;
}
.service-table {
  width: 100%;
  border-collapse: collapse;
}
.service-table th {
  font-size: 14px;
  font-weight: 600;
  color: #6C7489;
  padding: 10px;
  text-align: left;
}

.service-table > tbody > tr {
  cursor: pointer;
}

.service-table > tbody > tr:hover {
  background-color: #EEFAFF;
}

.service-table td {
  padding: 10px;
  border-top: 1px solid #E0E4EA;
  text-align: left;
  font-size: 14px;
}

.tag {
  display: inline-block;
  background-color: #EEFAFF;
  padding: 2px 5px;
  margin: 2px;
  border-radius: 3px;
  color: #0063F7;
  font-size: 12px;
  font-weight: 650;
}

.protocol-path {
  display: inline-block;
  background-color: #E0E4EA;
  color: #474B47;
  padding: 2px 8px;
  margin: 2px;
  margin-right: 8px;
  border-radius: 3px;
  font-size: 12px;
  font-weight: 500;
}

.switch {
  position: relative;
  display: inline-block;
  width: 50px;
  height: 24px;
}

.switch input {
  opacity: 0;
  width: 0;
  height: 0;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  transition: 0.4s;
  border-radius: 34px;
}

.slider:before {
  position: absolute;
  content: "";
  height: 16px;
  width: 16px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  transition: 0.4s;
  border-radius: 50%;
}

input:checked + .slider {
  background-color: #0066ff;
}

input:checked + .slider:before {
  transform: translateX(26px);
}
</style>