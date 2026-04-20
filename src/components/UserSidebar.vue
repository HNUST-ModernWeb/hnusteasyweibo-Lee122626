<template>
  <aside class="sidebar-card">
    <template v-if="isLogin">
      <div class="avatar">
        {{ profile.nickname?.charAt(0) || 'U' }}
      </div>

      <h3 class="sidebar-name">{{ profile.nickname }}</h3>
      <p class="sidebar-bio">{{ profile.bio }}</p>

      <div class="sidebar-stats">
        <div class="stat-item">
          <strong>{{ postCount }}</strong>
          <span>微博</span>
        </div>
        <div class="stat-item">
          <strong>26</strong>
          <span>关注</span>
        </div>
        <div class="stat-item">
          <strong>118</strong>
          <span>粉丝</span>
        </div>
      </div>
    </template>

    <template v-else>
      <h3 class="sidebar-name">未登录</h3>
      <p class="sidebar-bio">请先登录后再体验完整功能。</p>
    </template>
  </aside>
</template>

<script setup>
import { computed, onMounted } from 'vue'
import { storeToRefs } from 'pinia'
import { useWeiboStore } from '../stores/weibo'

const store = useWeiboStore()
const { profile, postCount } = storeToRefs(store)

const isLogin = computed(() => store.isLogin)

onMounted(() => {
  if (store.isLogin) {
    store.fetchUserInfo()
  }
})
</script>