var userRpc;
var comRpc;

var rpcInputText;
var rpcResultScreen;
var resultString = "";

window.onload = function () {
    rpcInputText = document.getElementById("rpc_input_text");
    rpcResultScreen = document.getElementById("rpc_result_screen");
}

function rpcInputButtonClick() {
    resultString = "";
    rpcResultScreen.value = resultString;

    userRpc = rpcInputText.value;
    if (userRpc == "가위" || userRpc == "바위" || userRpc == "보") {
        comRpc = Math.floor(Math.random() * 3 + 1);
        if (comRpc == 1) {
            comRpc = "가위";
        }
        if (comRpc == 2) {
            comRpc = "바위";
        }
        if (comRpc == 3) {
            comRpc = "보";
        }

        resultString = resultString + "유저:" + userRpc;
        resultString = resultString + "\n";
        resultString = resultString + "컴:" + comRpc;
        resultString = resultString + "\n";
        var winDrawLose = "";

        switch (userRpc) {
            case "가위":
                switch (comRpc) {
                    case "가위":
                        winDrawLose = "Draw";
                        break;
                    case "바위":
                        winDrawLose = "Lose";
                        break;
                    case "보":
                        winDrawLose = "Win";
                        break;
                }
                break;

            case "바위":
                switch (comRpc) {
                    case "가위":
                        winDrawLose = "Win";
                        break;
                    case "바위":
                        winDrawLose = "Draw";
                        break;
                    case "보":
                        winDrawLose = "Lose";
                        break;
                }
                break;
            case "보":
                switch (comRpc) {
                    case "가위":
                        winDrawLose = "Lose";
                        break;
                    case "바위":
                        winDrawLose = "Win";
                        break;
                    case "보":
                        winDrawLose = "Draw";
                        break;
                }
                break;
        }
        resultString = resultString + "결과: " + winDrawLose;
        rpcResultScreen.value = resultString;
    } else {
        alert("장난 ㄴㄴ");
    }
}
