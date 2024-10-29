let posts = [];
let currentRotateX = 0; // 현재 X축 회전 값
let currentRotateY = 0; // 현재 Y축 회전 값

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
    const post = posts[index];
    const postDetail = document.getElementById('post-detail');
    postDetail.style.display = 'block';
    postDetail.innerHTML = `
        <iframe src="${post.videoUrl}?autoplay=1&mute=1" frameborder="0" allowfullscreen></iframe>
    `;
    postDetail.style.height = 'auto';

    let targetRotateX = 0;
    let targetRotateY = 0;

    postDetail.addEventListener('mousemove', function (e) {
        const x = e.offsetX;
        const y = e.offsetY;
        targetRotateY = (-1 / 5) * x; // 목표 Y축 회전 값
        targetRotateX = (1 / 5) * y;   // 목표 X축 회전 값
    });

    function updateRotation() {
        const rotationSpeed = 0.1; // 회전 속도 조절

        // 부드러운 회전을 위해 보간
        currentRotateX += (targetRotateX - currentRotateX) * rotationSpeed;
        currentRotateY += (targetRotateY - currentRotateY) * rotationSpeed;

        postDetail.style.transform = `perspective(350px) rotateX(${currentRotateX}deg) rotateY(${currentRotateY}deg)`;

        requestAnimationFrame(updateRotation); // 다음 프레임 요청
    }

    updateRotation(); // 회전 업데이트 시작

    postDetail.addEventListener('mouseout', function () {
        // 마우스가 나갔을 때 원래 위치로 돌아가도록
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
}

      