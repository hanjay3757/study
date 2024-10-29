let posts = [];
let currentRotateX = 0; // 현재 X축 회전 값
let currentRotateY = 0; // 현재 Y축 회전 값
let activePostDetail = null; // 현재 활성화된 포스트 상세보기

document.getElementById('post-form').addEventListener('submit', function (event) {
    event.preventDefault();
    const title = document.getElementById('post-title').value;
    const content = document.getElementById('post-content').value;
    const videoUrl = document.getElementById('post-video').value;

    posts.push({ title, content, videoUrl });
    this.reset();
    renderPosts();
});

function renderPosts() {
    const postList = document.getElementById('post-list');
    postList.innerHTML = '';
    posts.forEach((post, index) => {
        const card = document.createElement('div');
        card.className = 'card';
        card.innerHTML = `
            <div onclick="viewPost(${index})">
                <h3>${post.title}</h3>
                <p>${post.content.slice(0, 50)}...</p>
            </div>
        `;
        postList.appendChild(card);
    });
}

function viewPost(index) {
    if (activePostDetail) {
        activePostDetail.style.display = 'none'; // 기존 포스트 상세보기 숨김
    }

    const post = posts[index];
    const postDetail = document.getElementById('post-detail');
    postDetail.style.display = 'block';
    postDetail.innerHTML = `
        <iframe src="${post.videoUrl}?autoplay=1&mute=1" frameborder="0" allowfullscreen></iframe>
    `;
    postDetail.style.height = 'auto';

    currentRotateX = 0; // 초기화
    currentRotateY = 0; // 초기화
    let targetRotateX = 0;
    let targetRotateY = 0;

    // 이전 포스트의 이벤트 리스너를 제거
    if (postDetail.mouseMoveListener) {
        postDetail.removeEventListener('mousemove', postDetail.mouseMoveListener);
    }

    postDetail.mouseMoveListener = function (e) {
        const x = e.offsetX;
        const y = e.offsetY;
        targetRotateY = (-1 / 5) * x; // 목표 Y축 회전 값
        targetRotateX = (1 / 5) * y;   // 목표 X축 회전 값
    };

    postDetail.addEventListener('mousemove', postDetail.mouseMoveListener);

    function updateRotation() {
        const rotationSpeed = 0.1; // 회전 속도 조절
        currentRotateX += (targetRotateX - currentRotateX) * rotationSpeed;
        currentRotateY += (targetRotateY - currentRotateY) * rotationSpeed;

        postDetail.style.transform = `perspective(350px) rotateX(${currentRotateX}deg) rotateY(${currentRotateY}deg)`;
        requestAnimationFrame(updateRotation);
    }

    updateRotation(); // 회전 업데이트 시작

    postDetail.addEventListener('mouseout', function () {
        function resetRotation() {
            currentRotateX += (0 - currentRotateX) * 0.1;
            currentRotateY += (0 - currentRotateY) * 0.1;
            postDetail.style.transform = `perspective(350px) rotateX(${currentRotateX}deg) rotateY(${currentRotateY}deg)`;

            if (Math.abs(currentRotateX) > 0.01 || Math.abs(currentRotateY) > 0.01) {
                requestAnimationFrame(resetRotation);
            }
        }

        resetRotation(); // 회전 초기화 시작
    });

    activePostDetail = postDetail; // 현재 활성화된 포스트 상세보기 설정
}
