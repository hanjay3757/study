var tagZ;

window.onload = function(){
    tagZ = document.getElementById("x");
    tagZ.addEventListener("click",z);
    // 이벤트 감시자
}

function x(){
    console.log("x버튼 누름")
}

function z(){
    console.log("z버튼 누름")
}