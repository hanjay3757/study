var score = 0;
var plusScore = 0;

function add(){
    score = score +1+plusScore;
    update()
}

function update(){
    document.querySelector("#countNum").value=score;
    document.querySelector("mowPlusScore").innerHTML="현재 추가 클리수는 +"+plusScore+"입니다.";
}

function store(){
    if(score >- 20){
        score = score -20;
        plusScore =plusScore +1;
        update()
    }else{
        alert("저리가라");
    }

}

function save (){
    localStorage.setItem("score",score);
    localStorage.setItem("plusScore",plusScore);
}

function load(){
    score = localStorage.getItem("score");
    score=parselnt(score);
    plusScore = localStorage.getItem("plusScore");
    plusScore = parseInt(plusScore);
    update()
}