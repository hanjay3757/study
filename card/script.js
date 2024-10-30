// YouTube Iframe API 스크립트를 동적으로 추가
const tag = document.createElement('script');
tag.src = "https://www.youtube.com/iframe_api"; // YouTube API 소스
const firstScriptTag = document.getElementsByTagName('script')[0]; // 기존 스크립트 태그
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag); // API 스크립트 삽입

let player; // 비디오 플레이어 변수를 정의

// YouTube API 준비 완료 시 호출되는 함수
function onYouTubeIframeAPIReady() {
    player = new YT.Player('youtube-player', { // YouTube 비디오 플레이어 생성
        events: {
            'onReady': function(event) { // 플레이어 준비 완료 시 이벤트
                const videoElement = document.getElementById('youtube-player'); // 비디오 요소 선택
                const thumbnailElement = document.querySelector('.thumbnail'); // 썸네일 요소 선택

                // 마우스를 올렸을 때 비디오 재생
                container.addEventListener('mouseenter', function() {
                    thumbnailElement.style.opacity = 0; // 썸네일 숨기기
                    videoElement.style.display = 'block'; // 비디오 표시
                    player.playVideo(); // 비디오 재생
                });

                // 마우스가 벗어났을 때 썸네일로 복원
                container.addEventListener('mouseleave', function() {
                    player.pauseVideo(); // 비디오 일시정지
                    videoElement.style.display = 'none'; // 비디오 숨기기
                    thumbnailElement.style.opacity = 1; // 썸네일 복원
                });

                // 썸네일 클릭 시 링크로 이동
                thumbnailElement.addEventListener('click', function() {
                    window.location.href = 'https://www.youtube.com/watch?v=8z7ZVoSmuB8'; // 이동할 URL
                });

                // 비디오가 끝나면 썸네일로 되돌리기
                player.addEventListener('onStateChange', function(event) {
                    if (event.data === YT.PlayerState.ENDED) { // 비디오가 끝났을 때
                        videoElement.style.display = 'none'; // 비디오 숨기기
                        thumbnailElement.style.opacity = 1; // 썸네일 복원
                    }
                });
            }
        }
    });
}

// 마우스 움직임에 따라 컨테이너 회전 효과 추가
var container = document.querySelector('.container'); // 컨테이너 요소 선택
var overlay = document.querySelector('.overlay'); // 오버레이 요소 선택
container.addEventListener('mousemove', function(e) { // 마우스 이동 이벤트
    var x = e.offsetX; // 마우스 X 좌표
    var y = e.offsetY; // 마우스 Y 좌표
    var rotateY = -1 / 5 * x + 20; // Y 축 회전 각도 계산
    var rotateX = 4 / 30 * y - 20; // X 축 회전 각도 계산

    // 오버레이 배경 위치와 필터 설정
    overlay.style = `background-position: ${x / 5 + y / 5}%; filter: opacity(${x / 200}) brightness(1.2)`;
    // 컨테이너의 3D 회전 효과 적용
    container.style = `transform: perspective(350px) rotateX(${rotateX}deg) rotateY(${rotateY}deg)`;
});

// 마우스가 컨테이너를 벗어날 때
container.addEventListener('mouseout', function() {
    overlay.style = 'filter: opacity(0)'; // 오버레이 숨기기
    container.style = 'transform: perspective(350px) rotateY(0deg) rotateX(0deg)'; // 회전 초기화
});
