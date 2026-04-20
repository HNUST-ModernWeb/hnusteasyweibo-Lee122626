<template>
  <div>
    <div class="page-box">
      <h2>首页</h2>
      <p>这里展示微博信息流。</p>
    </div>

    <div class="post-list">
      <PostCard
        v-for="post in posts"
        :key="post.id"
        :post="post"
      />
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { storeToRefs } from 'pinia'
import { useWeiboStore } from '../stores/weibo'
import PostCard from '../components/PostCard.vue'

const store = useWeiboStore()
const { posts } = storeToRefs(store)

onMounted(() => {
  store.fetchPosts()
})
</script>