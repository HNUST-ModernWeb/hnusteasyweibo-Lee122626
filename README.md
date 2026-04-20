# 🌟 Mini Weibo 全栈项目

一个基于 **Vue3 + Spring Boot + MySQL** 实现的前后端分离微博系统，支持用户发布动态、点赞、评论等核心功能。

---

## 📌 项目简介

本项目是一个简化版微博系统（Mini Weibo），实现了从前端界面到后端接口再到数据库的完整流程，具备典型的全栈开发结构。

适用于：

* Web 开发课程项目
* 前后端分离实践
* 简历项目展示

---

## 🚀 技术栈

### 前端

* Vue 3
* Vite
* Vue Router
* Pinia
* Axios

### 后端

* Spring Boot
* MyBatis / JPA（根据你实际情况改）
* RESTful API

### 数据库

* MySQL

---

## 📂 项目结构

```
mini-weibo/
├── mini-weibo-vue/      # 前端项目
├── mini-weibo-server/   # 后端项目
├── sql/                 # 数据库脚本
│   └── mini_weibo.sql
└── README.md
```

---

## ✨ 功能实现

### 👤 用户模块

* 用户注册 / 登录
* 获取用户信息
* 个人主页展示

### 📝 微博模块

* 发布微博（支持文本 / 图片）
* 展示微博列表
* 删除微博

### ❤️ 互动功能

* 点赞 / 取消点赞
* 评论功能
* 收藏功能

### 🖥️ 前端特性

* 组件化开发（Vue3）
* 状态管理（Pinia）
* 路由管理（Vue Router）
* 响应式布局（三栏结构）

---

## 🗄️ 数据库设计

主要数据表：

* `tb_user`（用户表）
* `tb_post`（微博表）
* `tb_comment`（评论表）
* `tb_post_like`（点赞表）
* `tb_post_favorite`（收藏表）

初始化脚本见：

```
sql/mini_weibo.sql
```

---

## ⚙️ 项目启动

### 1️⃣ 克隆项目

```bash
git clone https://github.com/HNUST-ModernWeb/hnusteasyweibo-Lee122626.git
```

---

### 2️⃣ 启动后端

```bash
cd mini-weibo-server
```

* 使用 IDEA 打开项目
* 配置数据库连接（application.yml / application.properties）
* 运行 Spring Boot 主类

---

### 3️⃣ 启动前端

```bash
cd mini-weibo-vue
npm install
npm run dev
```

浏览器访问：

```
http://localhost:5173
```

---

### 4️⃣ 导入数据库

打开 MySQL，执行：

```sql
source mini_weibo.sql;
```

或使用 MySQL Workbench 导入。

---

## 📸 项目截图

![alt text](image.png)
![alt text](image-1.png)
![alt text](image-2.png)
---

## 📈 项目亮点

* ✔ 前后端完全分离架构
* ✔ 完整业务流程（用户 → 发布 → 互动）
* ✔ 使用 Pinia 进行状态管理
* ✔ RESTful 风格接口设计
* ✔ 三栏响应式布局设计

---

## 📌 后续优化方向

* 接入 JWT 登录认证
* 图片上传（OSS / 本地存储）
* 实时消息（WebSocket）
* 更完善的权限控制
* UI 美化（Element Plus / Tailwind）

---

## 👨‍💻 作者

* GitHub: https://github.com/HNUST-ModernWeb/hnusteasyweibo-Lee122626

---

## ⭐ 如果觉得不错可以点个 Star！
