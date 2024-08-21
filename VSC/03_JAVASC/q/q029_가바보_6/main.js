/**********************************************************************
가위 바위 보 게임 (v0.6.0)

[기존 기능]
- 가위바위보 기능
- 유저 가위바위보 입력 시 예외처리 추가
- 유저 가위바위보 입력을 입력창에서 받도록 수정
- 결과 출력 화면을 dw()가 아닌 출력화면용 textarea에 출력하도록 개선
(가위바위보 게임을 단판이 아닌 무한으로 가능해졌음)
- 컴퓨터와 유저의 가위 바위 보 를 이미지로도 표현

v0.6.0 업데이트 내용
- 처음 10골드를 주고 게임 이기면 +1 지면 -1 처리 및 표시 함

todo:
**********************************************************************/
// t1.addEventListener('click', btn_listener);

// 선언

// var gold = 0;

var userRpc = "";
var comRpc = "";

var rpcInputText;
var rpcResultScreen;

var resultString = "";

//이미지 놓일 div들
var divRpcImgUser;
var divRpcImgCom;
var divGold;

// ***** 중요 ******
window.onload = function () {

    rpcInputText = document.getElementById("rpc_input_text");       // 연결
    rpcResultScreen = document.getElementById("rpc_result_screen");       // 연결
    divRpcImgUser = document.getElementById("rpc_user");       // 연결
    divRpcImgCom = document.getElementById("rpc_com");       // 연결
    // divGold = document.getElementById("gold");       // 연결

    // rpcResultScreen.value = "고양이";

}

function rpcInputButtonClick() {

    // while (true) {    // while 무한루프 걸기는 이렇게 하면 됩니다
    //     // userRpc = prompt("가위,바위,보 중에 하나를 입력하세요:");
    //     resultString = "";  //결과 화면 초기화
    //     rpcResultScreen.value = resultString; 
    //     userRpc = rpcInputText.value;
    //     if (userRpc == "가위" || userRpc == "바위" || userRpc == "보") {
    //         break;  // while 무한루프를 빠져나감
    //     } else {
    //         alert("장난치지마세요");
    //         break;
    //     } 
    // }

    // comRpc = Math.floor(Math.random() * 3 + 1); // 컴퓨터 난수 발생 시키기 (1~3)
    // if (comRpc == 1) {
    //     comRpc = "가위";
    // }
    // if (comRpc == 2) {
    //     comRpc = "바위";
    // }
    // if (comRpc == 3) {
    //     comRpc = "보";
    // }

    // resultString = "유저:" + userRpc; // * 주의 * 다시 게임을 할 경우 이 명령줄로 인해 기존 누적된 내용이 초기화가 됨.
    // resultString = resultString + "\n";
    // resultString = resultString + "컴:" + comRpc;
    // resultString += "\n";
    resultString = "";  //결과 화면 초기화
    rpcResultScreen.value = resultString;   // 텍스트 에어리어 지움

    // userRpc = prompt("가위,바위,보 중에 하나를 입력하세요:");
    userRpc = rpcInputText.value;
    if (userRpc == "가위" || userRpc == "바위" || userRpc == "보") {  //정상 입력 검사. ok면 가위바위보 로직을 처리
        ////    가위바위보 로직을 처리  ////
        comRpc = Math.floor(Math.random() * 3 + 1); // 컴퓨터 난수 발생 시키기 (1~3)
        if (comRpc == 1) {
            comRpc = "가위";
        }
        if (comRpc == 2) {
            comRpc = "바위";
        }
        if (comRpc == 3) {
            comRpc = "보";
        }


        // dw("유저:"+userRpc);

        resultString = resultString + "유저:" + userRpc;

        // br();
        resultString = resultString + "\n";

        // dw("컴:"+comRpc);    
        resultString = resultString + "컴:" + comRpc;

        // br();
        resultString = resultString + "\n";

        var winDrawLose = "";
        switch (userRpc) {
            case "가위":
                switch (comRpc) {
                    case "가위":
                        winDrawLose = "DRAW";
                        break;
                    case "바위":
                        winDrawLose = "패배";
                        break;
                    case "보":
                        winDrawLose = "승리";
                        break;
                }
                break;
            case "바위":
                switch (comRpc) {
                    case "가위":
                        winDrawLose = "승리";
                        break;
                    case "바위":
                        winDrawLose = "DRAW";
                        break;
                    case "보":
                        winDrawLose = "패배";
                        break;
                }
                break;
            case "보":
                switch (comRpc) {
                    case "가위":
                        winDrawLose = "패배";
                        break;
                    case "바위":
                        winDrawLose = "승리";
                        break;
                    case "보":
                        winDrawLose = "DW";
                        break;
                }
                break;
        }

        // 이미지 추가
        switch (userRpc) {
            case "가위":
                divRpcImgUser.innerHTML = "<img src='sc.png'>"
                break;
            case "바위":
                divRpcImgUser.innerHTML = "<img src='ro.png'>"
                break;
            case "보":
                divRpcImgUser.innerHTML = "<img src='pa.png'>"
                break;
        }
        switch (comRpc) {
            case "가위":
                divRpcImgCom.innerHTML = "<img src='c_sc.png'>"
                break;
            case "바위":
                divRpcImgCom.innerHTML = "<img src='c_ro.png'>"
                break;
            case "보":
                divRpcImgCom.innerHTML = "<img src='c_pa.png'>"
                break;
        }

        var winDrawLose = "";
        switch (userRpc) {
            case "가위":
                switch (comRpc) {
                    case "가위":
                        winDrawLose = "DRAW";
                        break;
                    case "바위":
                        winDrawLose = "패배";
                        // gold = gold - 1;
                        break;
                    case "보":
                        winDrawLose = "승리";
                        // gold = gold + 1;
                        break;
                }
                break;
            case "바위":
                switch (comRpc) {
                    case "가위":
                        winDrawLose = "승리";
                        // gold = gold + 1;
                        break;
                    case "바위":
                        winDrawLose = "DRAW";
                        break;
                    case "보":
                        winDrawLose = "패배";
                        // gold = gold - 1;
                        break;
                }
                break;
            case "보":
                switch (comRpc) {
                    case "가위":
                        winDrawLose = "패배";
                        // gold = gold - 1;
                        break;
                    case "바위":
                        winDrawLose = "승리";
                        // gold = gold + 1;
                        break;
                    case "보":
                        winDrawLose = "DW";
                        break;
                }
                break;

            // dw(winDrawLose);    
            // resultString += "결과: " + winDrawLose;




            // 결과 출력

        }
        // divGold.innerHTML = gold;
        resultString = resultString + "결과: " + winDrawLose;
        rpcResultScreen.value = resultString;
        
    }
}