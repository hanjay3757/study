/*
LottoV1.1.0

- 유저가 구매한 번호를 공모양으로 표시
- 맞춘 번호의 경우 공모양에 추가 표시

*/

var tagResult;
var winNumbers = new Array();

var t;
var resultText = "";
var p = [1,2,3,4,5,6];      // 내번호들 
var r = [0,0,0,0,0,0];  // 컴 번호들(초기값 다 0으로 일단)

var a1;
var a2;
var a3;
var a4;
var a5;
var a6;
window.onload=function(){
    t=document.getElementById("t");
    a1=document.getElementById("a1");
    a2=document.getElementById("a2");
    a3=document.getElementById("a3");
    a4=document.getElementById("a4");
    a5=document.getElementById("a5");
    a6=document.getElementById("a6");
    tagResult=document.getElementById("result");
}

function insertPlayerLotto(){
    p[0]=a1.value;
    p[1]=a2.value;
    p[2]=a3.value;
    p[3]=a4.value;
    p[4]=a5.value;
    p[5]=a6.value;
}

function checkLottoNumberDuplicates(){
    var isDuplicated = false;
    for(var i=0;i<5;i++){
        for(var j=i+1;j<=5;j++){
            if(p[i]==p[j]){
                isDuplicated = true;
            }
        }
    }
    return isDuplicated;
}

function runLotto(){
    resultText = ""; // 초기화
    winNumbers = [];    // 초기화

    insertPlayerLotto();

    var isPlayerLottoDuplicated = checkLottoNumberDuplicates();
    log(isPlayerLottoDuplicated);
    if(isPlayerLottoDuplicated){
        //게임 취소
        t.value = "장난x";
        return ;   
    }

    resultTextAdd("유저: \n")
    for(var i=0;i<=5;i=i+1){
        resultTextAdd(p[i]+"\n");
    }
    resultTextAdd("당첨번호: \n")

    ////////////////////////////////////////////////////////
    // 랜덤 번호들
    ////////////////////////////////////////////////////////
    ////    번호 1
    r[0] = Math.floor(Math.random() * 45) + 1; // 1 ~ n 까지 범위내에서 랜덤하게 숫자 하나 뽑아주는 애.
    resultTextAdd(r[0]);
    resultTextAdd("\n");
    
    ////    번호 2
    ////    앞선 번호들과 비교하여 중복된 번호가 나온 경우 다시 번호를 뽑게 하기(그렇게 해서 나온 번호도 또 검사를 계속 해야함)
    while(true){
        r[1] = Math.floor(Math.random() * 45) + 1; // 1 ~ n 까지 범위내에서 랜덤하게 숫자 하나 뽑아주는 애.
        if(r[0] != r[1]){   // r[1]가 r[0]과 같지 않으면 빠져나감
            resultTextAdd(r[1]);
            resultTextAdd("\n");
            break;
        }
    }
    
    ////    번호 3
    // r[2]  가 r[0] 이나 r[1]와 같으면 다시뽑게하기(무한히)
    while(true){
        r[2] = Math.floor(Math.random() * 45) + 1; // 1 ~ n 까지 범위내에서 랜덤하게 숫자 하나 뽑아주는 애.
        if(r[2] != r[0] && r[2] != r[1] ){   // r[2]  가 r[0] 이나 r[1]와 같으면 다시뽑게하기(무한히)
            resultTextAdd(r[2]);
            resultTextAdd("\n");
            break;
        }
    }
    
    ////    번호 4
    while(true){
        r[3] = Math.floor(Math.random() * 45) + 1; // 1 ~ n 까지 범위내에서 랜덤하게 숫자 하나 뽑아주는 애.
        if(r[3] != r[0] && r[3] != r[1] && r[3] != r[2]){
            resultTextAdd(r[3]);
            resultTextAdd("\n");
            break;
        }
    }
    
    ////    번호 5
    while(true){
        r[4] = Math.floor(Math.random() * 45) + 1; // 1 ~ n 까지 범위내에서 랜덤하게 숫자 하나 뽑아주는 애.
        if(r[4] != r[0] && r[4] != r[1] && r[4] != r[2] && r[4] != r[3]){
            resultTextAdd(r[4]);
            resultTextAdd("\n");
            break;
        }
    }
    
    ////    번호 6
    while(true){
        r[5] = Math.floor(Math.random() * 45) + 1; // 1 ~ n 까지 범위내에서 랜덤하게 숫자 하나 뽑아주는 애.
        if(r[5] != r[0] && r[5] != r[1] && r[5] != r[2] && r[5] != r[3] && r[5] != r[4]){
            resultTextAdd(r[5]);
            resultTextAdd("\n");
            break;
        }
    }
    ////    보너스번호
    var b = 0;
    while(true){
        b = Math.floor(Math.random() * 45) + 1; // 1 ~ n 까지 범위내에서 랜덤하게 숫자 하나 뽑아주는 애.
        if(b != r[0] && b != r[1] && b != r[2] && b != r[3] && b != r[4] && b != r[5]){
            resultTextAdd("보너스번호:"+b);
            resultTextAdd("\n");
            break;
        }
    }
    
    ////////////////////////////////////////////////////////
    // 컴퓨터의 6개의 숫자랑 내꺼랑 비교해서 몇개가 일치하는 지 세어 출력하기
    // ex. 3개 일치	
    // p1 ~ p6
    ////////////////////////////////////////////////////////
    
    var win = 0;    //내가 맞춘 수
    
    //컴퓨터의 6개의 숫자랑 내꺼랑 비교해서 몇개가 일치하는 지 세는 작업을 반복문으로 처리
    for(var i=0;i<=5;i=i+1){
        for(var j=0;j<=5;j=j+1){
            if(p[i]==r[j]){
                win = win + 1;
                // 맞춘 번호도 기록해두기
                winNumbers.push(r[j]);
            }
        }
    }
    
    ////////////////////////////////////////////////////////
    // 몇개 맞췄는지 출력
    ////////////////////////////////////////////////////////
    resultTextAdd("맞은 번호 갯수:"+win+"\n");
    
    ////////////////////////////////////////////////////////
    // 몇개 맞췄는지에 따라 등수 출력하기
    ////////////////////////////////////////////////////////
    var str = "";
    switch(win){
        case 0:
        case 1:
        case 2:
            str = "꽝!!! 다음기회에";
            break;
        case 3:
            str = "5등에 당첨되셨습니다.";
            break;
        case 4:
            str = "4등에 당첨되셨습니다.";
            break;
        case 5:
            str = "3등에 당첨되셨습니다.";
            //2등 처리
            for(var i=0;i<6;i=i+1){
                if(b==p[i]){    // 보너스 번호가 3등 당첨 유저의 나머지 한번호와 일치하는경우
                    // 2등 처리
                    str = "2등에 당첨되셨습니다.";
                }
            }
            break;
        case 6:
            str = "1등에 당첨되셨습니다.";
            break;        
    }
    
    t.value = resultText;

    // - 유저가 구매한 번호를 공모양으로 표시
    // - 맞춘 번호의 경우 공모양에 추가 표시
    displayBall();
}


function displayBall(){
    var resultTags = "";
    for(var i=0;i<6;i=i+1){
        if(winNumbers.includes(parseInt(p[i]))){
            resultTags = resultTags + "<div class='ball win'>" + p[i] + "</div>"
        } else {
            resultTags = resultTags + "<div class='ball'>" + p[i] + "</div>"
        }
    }
    tagResult.innerHTML = resultTags;
    // a
    
}