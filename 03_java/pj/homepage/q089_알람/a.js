function x(){

	dateInstance = new Date();
	divNow.innerHTML = dateInstance;

	sec = sec - 1;
	
	if(sec <= 0){
		divA.innerHTML = "라면 불 꺼!";
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
	divA=document.getElementById("a");
	divNow=document.getElementById("now");

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

//실행시점 x 라는 함수 계속 