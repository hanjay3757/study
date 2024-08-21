var userRpc = prompt("가위 바위 보 중에 하나를 입력하세요:");
var comRpcNumber = Math.floor(Math.random() * 3 + 1);
var comRpcStr = "";
if (comRpcNumber == 1) {
    comRpcStr = "가위";
}
if (comRpcNumber == 2) {
    comRpcStr = "바위";
}
if (comRpcNumber == 3) {
    comRpcStr = "보";
}

dw("유저:" + userRpc);
br();
dw("컴:" + comRpcStr);
br();

var winDrawLose = "";
switch (userRpc) {
    case "가위":
        switch (comRpcStr) {
            case "가위":
                winDrawLose = "Draw";
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
        switch (comRpcStr) {
            case "가위":
                winDrawLose = "승리";
                break;
            case "바위":
                winDrawLose = "Draw";
                break;
            case "보":
                winDrawLose = "패배";
                break;
        }
        break;
    case "보":
        switch (comRpcStr) {
            case "가위":
                winDrawLose = "승리";
                break;
            case "바위":
                winDrawLose = "Draw";
                break;
            case "보":
                winDrawLose = "패배";
                break;
        }
        break;
}

dw(winDrawLose);