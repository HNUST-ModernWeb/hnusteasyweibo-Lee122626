import axios from 'axios'

const request = axios.create({
  baseURL: 'http://localhost:8080',
  timeout: 5000
})

export function registerApi(data) {
  return request.post('/api/auth/register', data)
}

export function loginApi(data) {
  return request.post('/api/auth/login', data)
}

export function getPostList(userId) {
  return request.get('/api/post/list', {
    params: { userId }
  })
}

export function createPost(data) {
  return request.post('/api/post', data)
}

export function deletePostById(id) {
  return request.delete(`/api/post/${id}`)
}

export function getUserInfo(id) {
  return request.get(`/api/auth/user/${id}`)
}

export function updateUserProfile(data) {
  return request.put('/api/auth/user', data)
}

export function getComments(postId) {
  return request.get(`/api/post/${postId}/comments`)
}

export function createComment(postId, data) {
  return request.post(`/api/post/${postId}/comments`, data)
}

export function favoritePost(id, userId) {
  return request.post(`/api/post/${id}/favorite`, null, {
    params: { userId }
  })
}

export function unfavoritePost(id, userId) {
  return request.delete(`/api/post/${id}/favorite`, {
    params: { userId }
  })
}

export function likePost(id, userId) {
  return request.post(`/api/post/${id}/like`, null, {
    params: { userId }
  })
}

export function unlikePost(id, userId) {
  return request.delete(`/api/post/${id}/like`, {
    params: { userId }
  })
}