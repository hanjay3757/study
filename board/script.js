let posts = [];

document.getElementById('post-form').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const title = document.getElementById('post-title').value;
    const content = document.getElementById('post-content').value;
    const videoUrl = document.getElementById('post-video').value;
    
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
    container.innerHTML = ''; // 기존 게시물 초기화

    posts.forEach((post, index) => {
        const card = document.createElement('div');
        card.className = 'post-card';
        
        card.innerHTML = `
            <div class="video-container">
                <iframe 
                    src="${post.videoUrl}"
                    frameborder="0" 
                    allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" 
                    allowfullscreen
                ></iframe>
            </div>
            <div class="post-content">
                <button class="delete-btn" onclick="deletePost(${index})">삭제</button>
            </div>
        `;
        
        addHoverEffect(card); // 3D 호버 효과 추가
        container.appendChild(card);
    });
}

function editPost(index) {
    const post = posts[index];
    document.getElementById('post-title').value = post.title;
    document.getElementById('post-content').value = post.content;
    document.getElementById('post-video').value = post.videoUrl.replace('https://www.youtube.com/embed/', '');

    deletePost(index); // 수정하기 위해 해당 게시물 삭제
}

function deletePost(index) {
    posts.splice(index, 1);
    renderPosts();
}

function addHoverEffect(element) {
    let requestId;
    let currentRotateX = 0;
    let currentRotateY = 0;

    const updateRotation = (targetRotateX, targetRotateY) => {
        currentRotateY += (targetRotateY - currentRotateY) * 0.1; // 10% 보간
        currentRotateX += (targetRotateX - currentRotateX) * 0.1; // 10% 보간

        element.style.transform = `
            perspective(350px) 
            rotateX(${currentRotateX}deg) 
            rotateY(${currentRotateY}deg)
        `;
        
        requestId = requestAnimationFrame(() => updateRotation(targetRotateX, targetRotateY));
    };

    const handleMouseMove = (e) => {
        const rect = element.getBoundingClientRect();
        const x = e.clientX - rect.left; // 마우스 X 좌표
        const y = e.clientY - rect.top; // 마우스 Y 좌표

        // 회전 각도 계산
        const targetRotateY = ((x / rect.width) - 0.5) * 40; // Y 축 회전 각도
        const targetRotateX = ((y / rect.height) - 0.5) * -40; // X 축 회전 각도

        // 회전 업데이트 호출
        if (!requestId) {
            updateRotation(targetRotateX, targetRotateY);
        }
    };

    element.addEventListener('mousemove', handleMouseMove);

    element.addEventListener('mouseout', () => {
        cancelAnimationFrame(requestId); // 애니메이션 프레임 취소
        requestId = null;

        // 회전 초기화 애니메이션
        const resetAnimation = () => {
            if (Math.abs(currentRotateX) < 0.1 && Math.abs(currentRotateY) < 0.1) {
                element.style.transform = `perspective(350px) rotateX(0deg) rotateY(0deg)`;
                return;
            }

            currentRotateX += (0 - currentRotateX) * 0.1; // 목표 각도 0으로 설정
            currentRotateY += (0 - currentRotateY) * 0.1;

            requestAnimationFrame(resetAnimation);
            element.style.transform = `perspective(350px) rotateX(${currentRotateX}deg) rotateY(${currentRotateY}deg)`;
        };

        resetAnimation(); // 초기화 애니메이션 시작
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

    .edit-btn, .delete-btn {
        margin-top: 1rem;
        padding: 0.5rem 1rem;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    .edit-btn {
        background-color: #4CAF50; /* Green */
        color: white;
    }

    .delete-btn {
        background-color: #f44336; /* Red */
        color: white;
    }

    @media (max-width: 768px) {
        .container {
            grid-template-columns: 1fr;
            padding: 1rem;
        }
    }
`;

document.head.appendChild(style);
