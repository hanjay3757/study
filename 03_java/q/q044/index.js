//js
var btn;
var imgCat;

var isPopupOpened = false;
// 처음에 popup x 


window.onload = function () {
    btn = document.getElementById("btn");
    imgCat = document.getElementById("cat");
    btn.addEventListener("click", popup);
}

function popup() {
    if (isPopupOpened == false) {
        imgCat.style.display = "inline";
        isPopupOpened = true;
        // pop 이 없으면 보이게 해라
    }
    else {
        imgCat.style.display = "none";
        isPopupOpened = false;
    }
}



