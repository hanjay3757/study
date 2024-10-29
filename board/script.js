let posts = [];
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
    
    posts.push({ title, content, videoUrl: embedUrl });
    this.reset();
    renderPosts();
});

function getYoutubeVideoId(url) {
    const regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=)([^#\&\?]*).*/;
    const match = url.match(regExp);
    return (match && match[2].length === 11) ? match[2] : null;
}

function renderPosts() {
    const container = document.querySelector('.container');
    const postList = document.getElementById('post-list');
    
    // 새로운 포스트 카드 생성
    const newPost = posts[posts.length - 1];
    const card = document.createElement('div');
    card.className = 'post-card';
    
    card.innerHTML = `
        <div class="post-content">
            <h3>${newPost.title}</h3>
            <p>${newPost.content.slice(0, 100)}${newPost.content.length > 100 ? '...' : ''}</p>
        </div>
        <div class="video-container">
            <iframe 
                src="${newPost.videoUrl}"
                frameborder="0" 
                allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" 
                allowfullscreen
            ></iframe>
        </div>
    `;
    
    // 3D 호버 효과 추가
    addHoverEffect(card);
    
    // 컨테이너에 직접 추가
    container.appendChild(card);
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