function x(){

	dateInstance = new Date();
	divNow.innerHTML = dateInstance;

	sec = sec - 1;
	
	if(sec <= 0){
		divA.innerHTML = "라면 불 꺼!";
		// 쌍테그 안에 빈공간 <div id="now">요기</div>
	} else {
		divA.innerHTML = sec;
	}
}


var dateInstance;

// var sec = 240 + 30;
var sec = 10;
var divA;
var divNow;
window.onload = function(){
	// 윈도우 onload domcument 개체 브라우저 창 document 문서/ onload = 로딩완료했을 함수를 담는 애 onload 변수((body 로딩되고 난뒤 실행 함수)익명함수 => 
		// 브라우저에 출력 끝내놓고(로딩끝내고) 여기 봐주세요 ) 
	divA=document.getElementById("a");
	divNow=document.getElementById("now");
	//getElementById(단수) - document.getElementsByName(복수형 배열로)

	////처음 한번 출력 해주기
	//현 시간
	dateInstance = new Date();
	divNow.innerHTML = dateInstance;
	//타이머 설정 초
	divA.innerHTML = sec;

	//이후 타이머로 출력 해주기(1초 후부터 됨)
	setInterval(x, 1000);
	console.log("4")
}

//실행시점 x 라는 함수 계속 많은 시간걸림