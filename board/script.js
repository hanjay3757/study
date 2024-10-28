let posts = []; // 게시물 배열 초기화

// 게시물 등록 폼 제출 이벤트 리스너
document.getElementById('post-form').addEventListener('submit', function(event) {
    event.preventDefault(); // 기본 제출 동작 방지
    const title = document.getElementById('post-title').value; // 제목 값 가져오기
    const content = document.getElementById('post-content').value; // 내용 값 가져오기
    const videoUrl = document.getElementById('post-video').value; // 비디오 URL 값 가져오기

    posts.push({ title, content, videoUrl }); // 게시물 추가
    this.reset(); // 폼 초기화
    renderPosts(); // 게시물 렌더링
});

// 게시물 렌더링 함수
function renderPosts() {
    const postList = document.getElementById('post-list'); // 게시물 목록 요소
    postList.innerHTML = ''; // 목록 초기화
    posts.forEach((post, index) => {
        const card = document.createElement('div'); // 카드 요소 생성
        card.className = 'card'; // 클래스 이름 설정
        card.innerHTML = `
            <div onclick="viewPost(${index})"> <!-- 클릭 시 viewPost 함수 호출 -->
                <h3>${post.title}</h3> <!-- 제목 표시 -->
                <p>${post.content.slice(0, 50)}...</p> <!-- 내용 일부 표시 -->
            </div>
        `;
        postList.appendChild(card); // 카드 목록에 추가
    });
}

// 게시물 상세 보기 함수
function viewPost(index) {
    const post = posts[index]; // 선택한 게시물 가져오기
    const postDetail = document.getElementById('post-detail'); // 게시물 상세 요소
    const overlay = postDetail.querySelector('.overlay'); // 오버레이 요소
    postDetail.style.display = 'block'; // 상세 보기 표시
    postDetail.innerHTML = ` <!-- 상세 내용 설정 -->
        <h2>${post.title}</h2> <!-- 제목 -->
        <p>${post.content}</p> <!-- 내용 -->
        <iframe src="${post.videoUrl}?autoplay=1&mute=1" frameborder="0" allowfullscreen></iframe> <!-- 비디오 -->
    `;

    // 비디오 높이에 맞게 post-detail 높이 조정
    postDetail.style.height = 'calc(100vh - 250px)'; // container와 같은 높이로 설정

    // 오버레이 표시
    overlay.style.opacity = 1; // 오버레이 불투명도 설정
    postDetail.style.transform = 'none'; // 변형 초기화
}
