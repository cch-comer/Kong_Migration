<!-- eslint-disable -->
<template>
  <div class="login-box">
    <div class="login-box-plus">
      <img class="login-logo" src="@/assets/logo.svg?external">
      <div class="login-container">
        <h5 style="text-align: center">Login</h5>
        <div class="login-text">Username</div>
        <input v-model="username" type="text" />
        <div class="login-text">Password</div>
        <input v-model="password" type="password" />
        <button class="login-button" @click="login">Login</button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const username = ref('')
const password = ref('')
const router = useRouter()

const login = async () => {
  try {
    const response = await fetch('/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
      credentials: 'include',
      body: new URLSearchParams({
        username: username.value,
        password: password.value,
      }),
    })

    if (response.ok) {
      localStorage.setItem('username', username.value)
      await router.push('/')
    } else {
      const errorData = await response.json()
      alert('Login failed : ' + errorData.error)
    }
  } catch (error) {
    alert('Login error : ask Administrator')
  }
}
</script>

<style scoped lang="scss">
.login-text {
  text-align: left;
  width: 100%;
  font-size: 14px;
  font-weight: 500;
  margin-bottom: 0px;
}

.login-box {
  background-color: #000933;
  width: 100%;
  height: 100%;
}

.login-logo {
  display: flex;
  width: 200px;
  margin: auto;
  margin-bottom: 13px;
}

.login-container {
  display: flex;
  flex-direction: column;
  gap: 10px;
  width: 400px;
  /*margin: auto;*/
  padding: 30px;
  border: 1px solid #ccc;
  border-radius: 10px;
  box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
  /*position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);*/
  background-color: white;
}

.login-box-plus {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

input {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 30px;
  cursor: pointer;
  margin-top: 10px;

  &:hover {
    background-color: #0056b3;
  }
}
</style>