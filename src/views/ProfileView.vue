<template>
  <div v-if="isLogin">
    <div class="page-box">
      <h2>个人主页</h2>
      <p><strong>昵称：</strong>{{ profile.nickname }}</p>
      <p><strong>简介：</strong>{{ profile.bio }}</p>
      <p><strong>微博数量：</strong>{{ postCount }}</p>
    </div>

    <div class="page-box profile-box">
      <h3>编辑个人资料</h3>

      <input
        v-model="nickname"
        class="profile-input"
        placeholder="请输入昵称"
      />

      <textarea
        v-model="bio"
        class="editor"
        placeholder="请输入个人简介"
      ></textarea>

      <div class="publish-bar">
        <span>{{ bio.length }}/60</span>
        <button class="publish-btn" @click="handleSave">保存资料</button>
      </div>

      <p v-if="error" class="error-text">{{ error }}</p>
      <p v-if="success" class="success-text">资料更新成功</p>
    </div>
  </div>

  <div v-else class="page-box">
    <h2>个人主页</h2>
    <p>请先登录。</p>
  </div>
</template>

<script setup>
import { computed, ref, watch, onMounted } from 'vue'
import { storeToRefs } from 'pinia'
import { useWeiboStore } from '../stores/weibo'

const store = useWeiboStore()
const { profile, postCount } = storeToRefs(store)

const isLogin = computed(() => store.isLogin)

const nickname = ref('')
const bio = ref('')
const error = ref('')
const success = ref(false)

onMounted(async () => {
  if (!store.isLogin) return
  await store.fetchUserInfo()
  nickname.value = profile.value.nickname || ''
  bio.value = profile.value.bio || ''
})

watch(profile, (newProfile) => {
  nickname.value = newProfile.nickname || ''
  bio.value = newProfile.bio || ''
})

async function handleSave() {
  error.value = ''
  success.value = false

  const name = nickname.value.trim()
  const intro = bio.value.trim()

  if (!name) {
    error.value = '昵称不能为空'
    return
  }

  if (intro.length > 60) {
    error.value = '简介不能超过 60 字'
    return
  }

  try {
    await store.updateProfile(name, intro)
    success.value = true
  } catch (e) {
    error.value = e?.response?.data?.message || '保存失败，请检查后端服务'
  }
}
</script>