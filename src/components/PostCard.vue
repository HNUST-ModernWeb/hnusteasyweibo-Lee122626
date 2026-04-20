<template>
  <article class="post-card">
    <div class="post-top">
      <div>
        <strong>{{ profile.nickname }}</strong>
        <div class="post-time">{{ formatTime(post.createTime || post.time) }}</div>
      </div>
    </div>

    <div class="post-content">
      {{ post.content }}
    </div>

    <div v-if="post.image" class="post-image-wrap">
      <img :src="post.image" alt="微博图片" class="post-image" />
    </div>

    <div class="post-actions">
      <button
        class="action-btn"
        :class="{ liked: post.liked }"
        @click="handleLike"
      >
        {{ post.liked ? '已赞' : '点赞' }} {{ post.likeCount ?? 0 }}
      </button>

      <button class="action-btn" @click="toggleComments">
        评论
      </button>

      <button
      class="action-btn"
      :class="{ liked: post.favorited }"
      @click="handleFavorite"
      >
      {{ post.favorited ? '已收藏' : '收藏' }}
      </button>

      <button class="action-btn danger" @click="handleDelete">
        删除
      </button>
    </div>

    <div v-if="showComments" class="comment-box">
      <div class="comment-header">
        <span>评论区</span>
        <span class="comment-count">{{ comments.length }} 条评论</span>
      </div>

      <div class="comment-input-row">
        <input
          v-model="commentText"
          class="comment-input"
          placeholder="写下你的评论..."
          @keydown.enter="handleComment"
        />
        <button class="action-btn" @click="handleComment">发送</button>
      </div>

      <div v-if="comments.length" class="comment-list">
        <div v-for="item in comments" :key="item.id" class="comment-item">
          <div class="comment-user-row">
            <strong class="comment-user">{{ item.nickname || '匿名用户' }}</strong>
            <span class="comment-time">{{ formatTime(item.createTime) }}</span>
          </div>
          <div class="comment-content">{{ item.content }}</div>
        </div>
      </div>

      <p v-else class="comment-empty">还没有评论，快来抢沙发吧</p>
    </div>
  </article>
</template>

<script setup>
import { ref } from 'vue'
import { storeToRefs } from 'pinia'
import { useWeiboStore } from '../stores/weibo'

const props = defineProps({
  post: {
    type: Object,
    required: true
  }
})

const store = useWeiboStore()
const { profile } = storeToRefs(store)

const showComments = ref(false)
const comments = ref([])
const commentText = ref('')

function formatTime(value) {
  if (!value) return ''
  const date = new Date(value)
  if (Number.isNaN(date.getTime())) return value

  const y = date.getFullYear()
  const m = String(date.getMonth() + 1).padStart(2, '0')
  const d = String(date.getDate()).padStart(2, '0')
  const h = String(date.getHours()).padStart(2, '0')
  const min = String(date.getMinutes()).padStart(2, '0')

  return `${y}-${m}-${d} ${h}:${min}`
}

async function handleDelete() {
  try {
    await store.deletePost(props.post.id)
  } catch (e) {
    alert('删除失败')
  }
}

async function handleLike() {
  try {
    await store.toggleLike(props.post.id)
  } catch (e) {
    alert(e?.response?.data?.message || e.message || '点赞失败')
  }
}

async function toggleComments() {
  showComments.value = !showComments.value
  if (showComments.value) {
    comments.value = await store.fetchComments(props.post.id)
  }
}

async function handleComment() {
  const value = commentText.value.trim()
  if (!value) return

  try {
    await store.publishComment(props.post.id, value)
    commentText.value = ''
    comments.value = await store.fetchComments(props.post.id)
  } catch (e) {
    alert(e?.response?.data?.message || e.message || '评论失败')
  }
}

  async function handleFavorite() {
  try {
    await store.toggleFavorite(props.post.id)
  } catch (e) {
    alert(e?.response?.data?.message || e.message || '收藏失败')
  }
}
</script>