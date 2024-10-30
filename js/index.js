$(document).ready(function() {
    const videoElement = $('.video').get(0); // 비디오 요소 가져오기

    $('.thumbnail').on('mouseenter', function() {
        $(this).fadeOut(500); // 썸네일 서서히 사라짐
        $('.video').show().css('opacity', '0'); // 비디오 보여주기 및 초기 투명도 설정

        // 비디오 재생 시작
        if (videoElement.paused) {
            videoElement.play(); // 비디오가 멈춰있으면 재생
        }

        $('.video').animate({ opacity: 1 }, 500); // 비디오 서서히 나타남
    });

    // 비디오 위에 마우스가 있을 때 썸네일을 다시 보이지 않도록 설정
    $('.video').on('mouseenter', function() {
        $('.thumbnail').stop(true, true).fadeOut(); // 썸네일 숨기기
    });

    // 비디오에서 마우스가 나갔을 때
    $('.video').on('mouseleave', function() {
        const videoElement = $('.video').get(0); // 비디오 요소 가져오기
        videoElement.pause(); // 비디오 일시 정지
        $('.video').animate({ opacity: 0 }, 500, function() {
            $(this).hide(); // 비디오 숨기기
        });

        $('.thumbnail').fadeIn(500); // 썸네일 다시 나타남
    });

    // 비디오가 끝났을 때 썸네일을 다시 보이게 하기
    $(videoElement).on('ended', function() {
        $(this).hide(); // 비디오 숨기기
        $('.thumbnail').fadeIn(500); // 썸네일 다시 나타남
    });
});
