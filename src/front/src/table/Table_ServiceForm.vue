<template>
  <div class="containers">
    <div class="top-info" style="display: flex;">
      <div class="left-info" style="width: 25%">
        <h2 class="section-title">General Information</h2>
        <p class="section-description">General information will help identify and manage this Gateway Service.</p>
      </div>
      <div class="right-info" style="width: 75%">
        <label>Name</label>
        <div class="input-group">
          <input type="text" placeholder="Enter a unique name" />
        </div>
        <label>Tags</label><span class="hint">e.g. tag1, tag2, tag3</span>
        <div class="input-group">
          <input type="text" placeholder="Enter a list of tags separated by comma" />
        </div>
      </div>
    </div>
    <div class="top-info" style="display: flex; margin-top: 60px;">
      <div class="left-info" style="width: 25%">
        <h2 class="section-title">Service Endpoint</h2>
        <p class="section-description">Define the endpoint for this service by specifying the full URL or by its separate elements.</p>
      </div>
      <div class="right-info" style="width: 75%">
        <div class="radio-group">
          <label>
            <input type="radio" v-model="endpointType" value="full" />
            Full URL
          </label>
          <label>
            <input type="radio" v-model="endpointType" value="components" />
            Protocol, Host, Port and Path
          </label>
        </div>
        <img v-if="endpointType === 'full'"
            src="@/assets/des.png"
            style="width: 12px; margin-right: 5px;"
        >
        <label v-if="endpointType === 'full'">Upstream URL</label>
        <div v-if="endpointType === 'full'" class="input-group">
          <input type="text" placeholder="Enter a URL" />
        </div>
        <button v-if="endpointType === 'full'" class="toggle-advanced" @click="fullAdvanced = !fullAdvanced">
          > View Advanced Fields
        </button>
        <div v-if="fullAdvanced">
          <label>Retries</label>
          <div class="input-group">
            <input type="number" value="5" />
          </div>
          <label>Connection Timeout</label>
          <div class="input-group">
            <input type="number" value="60000" />
          </div>
          <label>Write Timeout</label>
          <div class="input-group">
            <input type="number" value="60000" />
          </div>
          <label>Read Timeout</label>
          <div class="input-group">
            <input type="number" value="60000" />
          </div>
          <label>Client Certificate</label>
          <div class="input-group">
            <input type="text" placeholder="Enter a Certificate ID" />
          </div>
          <label>CA Certificates</label>
          <div class="input-group">
            <input type="text" placeholder="Enter a comma separated list of CA Certificate IDs" />
          </div>
        </div>
        <div v-if="endpointType === 'components'">
          <img src="@/assets/des.png"
               style="width: 12px; margin-right: 5px;"
          >
          <label>Protocol</label>
          <div class="input-group">
            <select>
              <option value="http"> grpc </option>
              <option value="http"> grpcs </option>
              <option value="http"> http </option>
              <option value="http"> https </option>
              <option value="http"> tcp </option>
              <option value="http"> udp </option>
              <option value="http"> tls </option>
              <option value="http"> tls_passthrough </option>
            </select>
          </div>
          <img src="@/assets/des.png"
               style="width: 12px; margin-right: 5px;"
          >
          <label>Host</label>
          <div class="input-group">
            <input type="text" placeholder="Enter host" />
          </div>
          <label>Path</label>
          <div class="input-group">
            <input type="text" placeholder="Enter path" />
          </div>
          <label>Port</label>
          <div class="input-group">
            <input type="number" value="80" />
          </div>
          <button class="toggle-advanced" @click="componentAdvanced = !componentAdvanced">
            > View Advanced Fields
          </button>
          <div v-if="componentAdvanced">
            <label>Retries</label>
            <div class="input-group">
              <input type="number" value="5" />
            </div>
            <label>Connection Timeout</label>
            <div class="input-group">
              <input type="number" value="60000" />
            </div>
            <label>Write Timeout</label>
            <div class="input-group">
              <input type="number" value="60000" />
            </div>
            <label>Read Timeout</label>
            <div class="input-group">
              <input type="number" value="60000" />
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="button-group">
      <button class="cancel">Cancel</button>
      <button class="save" disabled>Save</button>
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue'

const endpointType = ref('full')
const fullAdvanced = ref(false)
const componentAdvanced = ref(false)

watch(() => endpointType.value, () => {
  fullAdvanced.value = false
  componentAdvanced.value = false
})

</script>

<style scoped>

label {
  font-size: 14px;
  font-weight: 600;
  margin-left: 2px;
}

.containers {
  width: 98%;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
}

.section-title {
  font-size: 18px;
  font-weight: bold;
}

.section-description {
  font-size: 14px;
  color: #555;
}

.input-group {
  margin-bottom: 15px;
}

.input-group input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  outline: none;
}

.input-group input:focus {
  border-color: #007BFF7F; /* 테두리 색상 변경 */
  box-shadow: 0 0 10px rgba(0, 123, 255, 0.5); /* 주변에 은은한 효과 */
}

.hint {
  font-size: 12px;
  color: #5A5A5A;
  margin-left: 10px;
}

.radio-group {
  display: flex;
  gap: 10px;
  margin: 10px 0;
}

.toggle-advanced {
  background: none;
  border: none;
  color: blue;
  cursor: pointer;
  margin: 10px 0;
  font-weight: bold;
}

.button-group {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  margin-top: 20px;
}

.cancel {
  background: none;
  border: 2px solid blue;
  border-radius: 12px;
  padding: 8px 12px;
  cursor: pointer;
  color: blue;
  font-weight: bold;
}

.save {
  background: #0044F4;
  color: white;
  border: none;
  border-radius: 12px;
  padding: 7px 15px;
  cursor: pointer;
  font-size: 14px;
}
</style>