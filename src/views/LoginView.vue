<template>
  <div class="login-page">
    <div class="login-card">
      <h2>登录 Mini Weibo</h2>

      <input
        v-model="username"
        class="profile-input"
        placeholder="请输入用户名"
      />

      <input
        v-model="password"
        type="password"
        class="profile-input"
        placeholder="请输入密码"
      />

      <button class="publish-btn login-btn" @click="handleLogin">登录</button>

      <p v-if="error" class="error-text">{{ error }}</p>
      <p v-if="success" class="success-text">登录成功，正在跳转...</p>

      <p class="switch-text">
        还没有账号？
        <span class="switch-link" @click="goRegister">去注册</span>
      </p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useWeiboStore } from '../stores/weibo'

const router = useRouter()
const store = useWeiboStore()

const username = ref('')
const password = ref('')
const error = ref('')
const success = ref(false)

function goRegister() {
  router.push('/register')
}

async function handleLogin() {
  error.value = ''
  success.value = false

  const u = username.value.trim()
  const p = password.value.trim()

  if (!u) {
    error.value = '用户名不能为空'
    return
  }

  if (!p) {
    error.value = '密码不能为空'
    return
  }

  try {
    await store.login(u, p)
    success.value = true
    setTimeout(() => {
      router.push('/home')
    }, 800)
  } catch (e) {
    error.value = e?.response?.data?.message || '登录失败'
  }
}
</script>