document.addEventListener('DOMContentLoaded', () => {
    const postList = document.getElementById('post-list');

    // 게시물 추가 함수 (예시)
    function addPost(title, content) {
        const postItem = document.createElement('div');
        postItem.className = 'post-item';
        postItem.innerHTML = `
            <h4 class="post-title">${title}</h4>
            <p class="post-content">${content}</p>
            <button class="edit-button">수정</button>
            <button class="delete-button">삭제</button>
        `;
        postList.appendChild(postItem);

        // 삭제 버튼 이벤트 리스너
        postItem.querySelector('.delete-button').addEventListener('click', () => {
            postList.removeChild(postItem);
        });

        // 수정 버튼 이벤트 리스너
        postItem.querySelector('.edit-button').addEventListener('click', () => {
            const newTitle = prompt('새 제목을 입력하세요:', title);
            const newContent = prompt('새 내용을 입력하세요:', content);
            if (newTitle) postItem.querySelector('.post-title').innerText = newTitle;
            if (newContent) postItem.querySelector('.post-content').innerText = newContent;
        });
    }

    // 예시로 몇 개의 게시물 추가
    addPost('첫 번째 게시물', '첫 번째 게시물 내용입니다.');
    addPost('두 번째 게시물', '두 번째 게시물 내용입니다.');
});
