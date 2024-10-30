// 각 게시판별 게시물을 저장할 객체
let boardPosts = {
    'index': [],
    'dog': [], 
    'cat': []
};

// 현재 페이지의 게시판 타입 확인
let currentBoard = window.location.pathname.split('/').pop().replace('.html','');
if (currentBoard === '') currentBoard = 'index';

let currentRotateX = 0;
let currentRotateY = 0;

document.getElementById('post-form').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const title = document.getElementById('post-title').value;
    const content = document.getElementById('post-content').value;
    const videoUrl = document.getElementById('post-video').value;
    
    // YouTube URL 변환
    const videoId = getYoutubeVideoId(videoUrl);
    const embedUrl = `https://www.youtube.com/embed/${videoId}`;
    
    // 중복 게시물 체크
    const isDuplicate = Object.values(boardPosts).some(posts => 
        posts.some(post => 
            post.title === title || post.videoUrl === embedUrl
        )
    );

    if (isDuplicate) {
        alert('이미 동일한 제목이나 동영상 URL을 가진 게시물이 존재합니다.');
        return;
    }
    
    // 현재 게시판에만 게시물 추가
    boardPosts[currentBoard].push({ 
        title, 
        content, 
        videoUrl: embedUrl,
        timestamp: new Date().getTime(), // 정렬을 위한 타임스탬프 추가
        board: currentBoard // 게시판 정보 추가
    });

    // localStorage에 저장
    localStorage.setItem('boardPosts', JSON.stringify(boardPosts));
    
    this.reset();
    renderPosts();
});

// 페이지 로드시 저장된 게시물 불러오기
window.addEventListener('load', function() {
    const savedPosts = localStorage.getItem('boardPosts');
    if (savedPosts) {
        boardPosts = JSON.parse(savedPosts);
        renderPosts();
    }
});

function getYoutubeVideoId(url) {
    const regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=)([^#\&\?]*).*/;
    const match = url.match(regExp);
    return (match && match[2].length === 11) ? match[2] : null;
}

// 게시물 삭제 함수 추가
function deletePost(postIndex, board) {
    boardPosts[board].splice(postIndex, 1);
    localStorage.setItem('boardPosts', JSON.stringify(boardPosts));
    renderPosts();
}

function renderPosts() {
    const container = document.querySelector('.container');
    container.innerHTML = ''; // 기존 게시물 초기화
    
    let postsToRender = [];
    
    // admin.html인 경우 모든 게시판의 게시물 표시
    if (currentBoard === 'admin') {
        Object.values(boardPosts).forEach(posts => {
            postsToRender = postsToRender.concat(posts);
        });
    } else {
        // 일반 게시판의 경우 해당 게시판 게시물만 표시
        postsToRender = boardPosts[currentBoard];
    }
    
    // 최신 게시물이 위로 오도록 정렬
    postsToRender.sort((a, b) => b.timestamp - a.timestamp);
    
    postsToRender.forEach((post, index) => {
        const card = document.createElement('div');
        card.className = 'post-card';
        
        // admin 페이지에서는 게시판 정보와 삭제 버튼 표시
        const adminControls = currentBoard === 'admin' ? 
            `<div class="admin-controls">
                <div class="board-tag">${post.board}</div>
                <button onclick="deletePost(${index}, '${post.board}')" class="delete-btn">삭제</button>
             </div>` : '';
        
        card.innerHTML = `
            ${adminControls}
            <div class="video-container">
                <iframe 
                    src="${post.videoUrl}"
                    frameborder="0" 
                    allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" 
                    allowfullscreen
                ></iframe>
            </div>
            <div class="post-content">
                <h3>${post.title}</h3>
                <p>${post.content}</p>
            </div>
        `;
        
        addHoverEffect(card);
        container.appendChild(card);
    });
}

function addHoverEffect(element) {
    element.addEventListener('mousemove', (e) => {
        const rect = element.getBoundingClientRect();
        const x = e.clientX - rect.left;
        const y = e.clientY - rect.top;
        
        const rotateX = 20 * ((y - rect.height / 2) / rect.height);
        const rotateY = -20 * ((x - rect.width / 2) / rect.width);
        
        element.style.transform = `
            perspective(1000px) 
            rotateX(${rotateX}deg) 
            rotateY(${rotateY}deg)
            scale(1.02)
        `;
    });
    
    element.addEventListener('mouseleave', () => {
        element.style.transform = 'perspective(1000px) rotateX(0) rotateY(0) scale(1)';
    });
}

// CSS 스타일 추가
const style = document.createElement('style');
style.textContent = `
    .container {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
        gap: 2rem;
        padding: 2rem;
        max-width: 1200px;
        margin: 0 auto;
    }

    .post-card {
        background: white;
        border-radius: 12px;
        overflow: hidden;
        box-shadow: 0 4px 15px rgba(0,0,0,0.1);
        transition: transform 0.3s ease, box-shadow 0.3s ease;
        cursor: pointer;
        position: relative;
    }

    .admin-controls {
        position: absolute;
        top: 10px;
        right: 10px;
        z-index: 1;
        display: flex;
        gap: 10px;
    }

    .board-tag {
        background: rgba(0,0,0,0.7);
        color: white;
        padding: 4px 8px;
        border-radius: 4px;
    }

    .delete-btn {
        background: #ff4444;
        color: white;
        border: none;
        padding: 4px 8px;
        border-radius: 4px;
        cursor: pointer;
    }

    .delete-btn:hover {
        background: #cc0000;
    }

    .post-content {
        padding: 1.5rem;
    }

    .post-content h3 {
        margin: 0 0 1rem 0;
        font-size: 1.25rem;
        color: #333;
    }

    .post-content p {
        margin: 0;
        color: #666;
        line-height: 1.5;
    }

    .video-container {
        position: relative;
        width: 100%;
        padding-bottom: 56.25%;
        height: 0;
        overflow: hidden;
    }

    .video-container iframe {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
    }

    @media (max-width: 768px) {
        .container {
            grid-template-columns: 1fr;
            padding: 1rem;
        }
    }
`;

document.head.appendChild(style);