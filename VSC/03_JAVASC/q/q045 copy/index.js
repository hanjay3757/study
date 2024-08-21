function xxx(){
    var popup = document.getElementById("popup");
    popup.style.display = "none";
}


function closePopup() {

    //팝업창 ( <div id="popup"> ) 을 찾고
    var popupWindow = document.getElementById("popup");
    var popupTxt = document.getElementById("popup_txt");
    var popupBtnClose = document.getElementById("popup_btn_close");

    //찾은 팝업 창을 닫기(라고 쓰고 안보이게 처리라고 읽는다.)
    // popupWindow.style.display = "none";

    popupWindow.style.color = "blue";
    // popupTxt.style.color = 'rgba(0,0,0,0)';
    popupTxt.style.color = '#00000000';

    // popupWindow.style.height = "600px";
    popupWindow.style.height = "0px";

    // popupWindow.style.width = "600px";
    popupWindow.style.width = "0px";

    popupWindow.style.background = "gray";

    popupWindow.style.transitionProperty = "width, height, background";
    popupWindow.style.transitionDuration = "3s";

    popupTxt.style.transitionProperty = "color";
    popupTxt.style.transitionDuration = "3s";


}