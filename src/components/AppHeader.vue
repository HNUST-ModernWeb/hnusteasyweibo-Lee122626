<template>
  <header class="topbar">
    <div class="topbar-inner">
      <h1 class="logo">Mini Weibo</h1>

      <nav class="nav">
        <RouterLink to="/home" class="nav-link">首页</RouterLink>
        <RouterLink to="/publish" class="nav-link">发布</RouterLink>
        <RouterLink to="/profile" class="nav-link">个人主页</RouterLink>

        <template v-if="!isLogin">
          <RouterLink to="/login" class="nav-link">登录</RouterLink>
          <RouterLink to="/register" class="nav-link">注册</RouterLink>
        </template>

        <button v-else class="nav-link logout-btn" @click="handleLogout">退出</button>
      </nav>
    </div>
  </header>
</template>

<script setup>
import { computed } from 'vue'
import { useRouter, RouterLink } from 'vue-router'
import { useWeiboStore } from '../stores/weibo'

const router = useRouter()
const store = useWeiboStore()

const isLogin = computed(() => store.isLogin)

function handleLogout() {
  store.logout()
  router.push('/login')
}
</script>