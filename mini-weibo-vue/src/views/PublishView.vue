<template>
  <div class="page-box">
    <h2>发布页</h2>

    <textarea
      v-model="content"
      class="editor"
      placeholder="分享一下今天的学习进度吧..."
    ></textarea>

    <div class="upload-box">
      <label class="upload-btn">
        选择图片
        <input type="file" accept="image/*" @change="handleFileChange" hidden />
      </label>
    </div>

    <div v-if="previewImage" class="preview-box">
      <img :src="previewImage" alt="预览图" class="preview-image" />
      <button class="remove-btn" @click="removeImage">移除图片</button>
    </div>

    <div class="publish-bar">
      <span>{{ content.length }}/140</span>
      <button class="publish-btn" @click="handlePublish">发布微博</button>
    </div>

    <p v-if="error" class="error-text">{{ error }}</p>
    <p v-if="success" class="success-text">发布成功，已同步到首页。</p>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useWeiboStore } from '../stores/weibo'

const store = useWeiboStore()

const content = ref('')
const error = ref('')
const success = ref(false)
const previewImage = ref('')

function handleFileChange(event) {
  const file = event.target.files[0]
  if (!file) return

  const reader = new FileReader()
  reader.onload = () => {
    previewImage.value = reader.result
  }
  reader.readAsDataURL(file)
}

function removeImage() {
  previewImage.value = ''
}

async function handlePublish() {
  if (!store.isLogin) {
  error.value = '请先登录'
  return
  }

  error.value = ''
  success.value = false

  const value = content.value.trim()

  if (!value) {
    error.value = '内容不能为空'
    return
  }

  if (value.length > 140) {
    error.value = '内容不能超过 140 字'
    return
  }

  try {
    await store.publishPost(value, previewImage.value)
    content.value = ''
    previewImage.value = ''
    success.value = true
  } catch (e) {
    error.value = '发布失败，请检查后端服务'
  }
}
</script>