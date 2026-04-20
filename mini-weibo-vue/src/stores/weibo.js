import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import {
  registerApi,
  loginApi,
  getPostList,
  createPost,
  deletePostById,
  getUserInfo,
  getComments,
  createComment,
  favoritePost,
  unfavoritePost,
  likePost,
  unlikePost,
  updateUserProfile
} from '../api/weibo'

const STORAGE_KEY = 'mini-weibo-user'

export const useWeiboStore = defineStore('weibo', () => {
  const savedUser = localStorage.getItem(STORAGE_KEY)
  const currentUser = ref(savedUser ? JSON.parse(savedUser) : null)

  const profile = ref({
    id: null,
    nickname: '',
    bio: ''
  })

  const posts = ref([])

  const isLogin = computed(() => !!currentUser.value)
  const currentUserId = computed(() => currentUser.value?.id || null)
  const postCount = computed(() => posts.value.length)

  function saveUser(user) {
    currentUser.value = user
    localStorage.setItem(STORAGE_KEY, JSON.stringify(user))
  }

  function logout() {
    currentUser.value = null
    profile.value = {
      id: null,
      nickname: '',
      bio: ''
    }
    localStorage.removeItem(STORAGE_KEY)
  }

  async function register(username, password, nickname) {
  await registerApi({
    username,
    password,
    nickname
  })
}

  async function login(username, password) {
    const res = await loginApi({ username, password })
    const user = res.data.data
    saveUser(user)
    await fetchUserInfo()
  }

  async function fetchPosts() {
  const res = await getPostList(currentUserId.value)
  posts.value = res.data.data || []
  }

  async function fetchUserInfo() {
    if (!currentUserId.value) return
    const res = await getUserInfo(currentUserId.value)
    profile.value = res.data.data
  }

  async function publishPost(content, image = '') {
    if (!currentUserId.value) {
      throw new Error('请先登录')
    }

    await createPost({
      userId: currentUserId.value,
      content,
      image
    })
    await fetchPosts()
  }

  async function toggleLike(id) {
  if (!currentUserId.value) {
    throw new Error('请先登录')
  }

  const target = posts.value.find(item => item.id === id)
  if (!target) return

  if (target.liked) {
    await unlikePost(id, currentUserId.value)
  } else {
    await likePost(id, currentUserId.value)
  }

  await fetchPosts()
  }

  async function updateProfile(nickname, bio) {
    if (!currentUserId.value) {
      throw new Error('请先登录')
    }

    await updateUserProfile({
      id: currentUserId.value,
      nickname,
      bio
    })

    await fetchUserInfo()

    if (currentUser.value) {
      const newUser = {
        ...currentUser.value,
        nickname: profile.value.nickname,
        bio: profile.value.bio
      }
      saveUser(newUser)
    }
  }

  async function deletePost(id) {
    await deletePostById(id)
    await fetchPosts()
  }

  async function fetchComments(postId) {
  const res = await getComments(postId)
  return res.data.data || []
}

async function publishComment(postId, content) {
  if (!currentUserId.value) {
    throw new Error('请先登录')
  }

  await createComment(postId, {
    userId: currentUserId.value,
    content
  })
  }

  async function toggleFavorite(id) {
  if (!currentUserId.value) {
    throw new Error('请先登录')
  }

  const target = posts.value.find(item => item.id === id)
  if (!target) return

  target.favorited = !!target.favorited

  if (target.favorited) {
    await unfavoritePost(id, currentUserId.value)
    target.favorited = false
  } else {
    await favoritePost(id, currentUserId.value)
    target.favorited = true
    }
    
    async function toggleLike(id) {
    if (!currentUserId.value) {
        throw new Error('请先登录')
      }
      
      const target = posts.value.find(item => item.id === id)
    if (!target) return

    target.liked = !!target.liked

    if (target.liked) {
    await unlikePost(id, currentUserId.value)
    target.liked = false
    } else {
    await likePost(id, currentUserId.value)
    target.liked = true
    }

   await fetchPosts()
  }
}

  return {
  currentUser,
  profile,
  posts,
  isLogin,
  currentUserId,
    postCount,
  toggleFavorite,
  fetchComments,
  publishComment,
  register,
  login,
  logout,
  fetchPosts,
  fetchUserInfo,
  publishPost,
    toggleLike,
  toggleLike,
  updateProfile,
  deletePost
  }
})