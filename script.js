const state = {
  profile: {
    nickname: "tr同学",
    bio: "正在完成微博项目第一阶段原型。"
  },
  posts: [
    {
      id: 1,
      content: "今天先把微博项目第一阶段页面原型搭出来，目标是首页、发布页、个人主页全部跑通。",
      time: "刚刚",
      likes: 12,
      liked: false
    },
    {
      id: 2,
      content: "CSS 先用卡片布局，后面第二阶段再拆成 Vue 组件。",
      time: "10分钟前",
      likes: 8,
      liked: false
    },
    {
      id: 3,
      content: "JavaScript 这阶段重点做表单验证、动态渲染和一些简单交互效果。",
      time: "30分钟前",
      likes: 15,
      liked: false
    }
  ]
};

const navButtons = document.querySelectorAll(".nav-btn");
const pages = {
  home: document.getElementById("page-home"),
  publish: document.getElementById("page-publish"),
  profile: document.getElementById("page-profile")
};

navButtons.forEach((btn) => {
  btn.addEventListener("click", () => {
    navButtons.forEach((b) => b.classList.remove("active"));
    btn.classList.add("active");
    Object.values(pages).forEach((page) => page.classList.remove("active"));
    pages[btn.dataset.page].classList.add("active");
  });
});

const feed = document.getElementById("feed");
const profileFeed = document.getElementById("profileFeed");
const statPosts = document.getElementById("statPosts");

function renderPosts() {
  const html = state.posts
    .map(
      (post) => `
        <article class="post">
          <div class="post-header">
            <div class="mini-user">
              <div class="mini-avatar">${state.profile.nickname.charAt(0)}</div>
              <div>
                <strong>${state.profile.nickname}</strong>
                <div class="muted" style="font-size:13px;">${post.time}</div>
              </div>
            </div>
            <span class="muted"># 学习记录</span>
          </div>
          <div class="post-content">${escapeHtml(post.content)}</div>
          <div class="post-actions">
            <button class="chip ${post.liked ? "liked" : ""}" data-like-id="${post.id}">
              ${post.liked ? "已赞" : "点赞"} ${post.likes}
            </button>
            <button class="chip">评论</button>
            <button class="chip">收藏</button>
          </div>
        </article>
      `
    )
    .join("");

  const empty = '<div class="empty">还没有内容，去发布第一条微博吧。</div>';

  feed.innerHTML = html || empty;
  profileFeed.innerHTML = html || empty;
  statPosts.textContent = state.posts.length;

  document.querySelectorAll("[data-like-id]").forEach((btn) => {
    btn.addEventListener("click", () => {
      const id = Number(btn.dataset.likeId);
      const target = state.posts.find((item) => item.id === id);
      target.liked = !target.liked;
      target.likes += target.liked ? 1 : -1;
      renderPosts();
    });
  });
}

function escapeHtml(text) {
  const div = document.createElement("div");
  div.textContent = text;
  return div.innerHTML;
}

const postContent = document.getElementById("postContent");
const wordCount = document.getElementById("wordCount");
const publishBtn = document.getElementById("publishBtn");
const publishError = document.getElementById("publishError");
const publishSuccess = document.getElementById("publishSuccess");

postContent.addEventListener("input", () => {
  wordCount.textContent = postContent.value.length;
  publishError.style.display = "none";
  publishSuccess.style.display = "none";
});

publishBtn.addEventListener("click", () => {
  const content = postContent.value.trim();

  if (!content || content.length > 140) {
    publishError.style.display = "block";
    publishSuccess.style.display = "none";
    return;
  }

  state.posts.unshift({
    id: Date.now(),
    content,
    time: "刚刚",
    likes: 0,
    liked: false
  });

  postContent.value = "";
  wordCount.textContent = "0";
  publishError.style.display = "none";
  publishSuccess.style.display = "block";
  renderPosts();
});

const nicknameInput = document.getElementById("nicknameInput");
const bioInput = document.getElementById("bioInput");
const bioCount = document.getElementById("bioCount");
const saveProfileBtn = document.getElementById("saveProfileBtn");
const profileError = document.getElementById("profileError");
const profileSuccess = document.getElementById("profileSuccess");

function syncProfileUI() {
  const initial = state.profile.nickname.charAt(0) || "T";

  document.getElementById("sideNickname").textContent = state.profile.nickname;
  document.getElementById("profileNickname").textContent = state.profile.nickname;
  document.getElementById("sideBio").textContent = state.profile.bio;
  document.getElementById("profileBio").textContent = state.profile.bio;
  document.getElementById("sideAvatar").textContent = initial;
  document.getElementById("profileAvatar").textContent = initial;

  nicknameInput.value = state.profile.nickname;
  bioInput.value = state.profile.bio;
  bioCount.textContent = state.profile.bio.length;

  renderPosts();
}

bioInput.addEventListener("input", () => {
  bioCount.textContent = bioInput.value.length;
  profileError.style.display = "none";
  profileSuccess.style.display = "none";
});

nicknameInput.addEventListener("input", () => {
  profileError.style.display = "none";
  profileSuccess.style.display = "none";
});

saveProfileBtn.addEventListener("click", () => {
  const nickname = nicknameInput.value.trim();
  const bio = bioInput.value.trim();

  if (!nickname || bio.length > 60) {
    profileError.style.display = "block";
    profileSuccess.style.display = "none";
    return;
  }

  state.profile.nickname = nickname;
  state.profile.bio = bio;
  profileError.style.display = "none";
  profileSuccess.style.display = "block";
  syncProfileUI();
});

renderPosts();
syncProfileUI();