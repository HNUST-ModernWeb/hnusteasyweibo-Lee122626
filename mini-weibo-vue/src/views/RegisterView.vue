<template>
  <div class="login-page">
    <div class="login-card">
      <h2>注册 Mini Weibo</h2>

      <input
        v-model="nickname"
        class="profile-input"
        placeholder="请输入昵称"
      />

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

      <button class="publish-btn login-btn" @click="handleRegister">注册</button>

      <p v-if="error" class="error-text">{{ error }}</p>
      <p v-if="success" class="success-text">注册成功，正在跳转到登录页...</p>

      <p class="switch-text">
        已有账号？
        <span class="switch-link" @click="goLogin">去登录</span>
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

const nickname = ref('')
const username = ref('')
const password = ref('')
const error = ref('')
const success = ref(false)

function goLogin() {
  router.push('/login')
}

async function handleRegister() {
  error.value = ''
  success.value = false

  const n = nickname.value.trim()
  const u = username.value.trim()
  const p = password.value.trim()

  if (!n) {
    error.value = '昵称不能为空'
    return
  }

  if (!u) {
    error.value = '用户名不能为空'
    return
  }

  if (!p) {
    error.value = '密码不能为空'
    return
  }

  try {
    await store.register(u, p, n)
    success.value = true
    setTimeout(() => {
      router.push('/login')
    }, 1000)
  } catch (e) {
    error.value = e?.response?.data?.message || '注册失败'
  }
}
</script>