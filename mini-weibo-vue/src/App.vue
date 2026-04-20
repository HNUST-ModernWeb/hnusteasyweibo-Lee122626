<template>
  <div>
    <AppHeader />

    <main class="app-main">
      <div class="layout">
        <UserSidebar />

        <section class="main-content">
          <router-view />
        </section>

        <RightPanel />
      </div>
    </main>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import AppHeader from './components/AppHeader.vue'
import UserSidebar from './components/UserSidebar.vue'
import RightPanel from './components/RightPanel.vue'
import { useWeiboStore } from './stores/weibo'

const store = useWeiboStore()

onMounted(() => {
  if (store.isLogin) {
    store.fetchUserInfo()
    store.fetchPosts()
  }
})
</script>