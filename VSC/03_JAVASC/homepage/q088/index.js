var clock;

window.onload = function(){
    clock=document.getElementById("clock");
    setInterval(x, 1000);  // 타이머 시작
}

// ㅇㅇㅇㅇ년 ㅇㅇ월 ㅇㅇ일 ㅇㅇ시 ㅇㅇ분 ㅇㅇ초 ㅇㅇㅇ 밀리초

function x(){
    var now = new Date();
    var result = 
        now.getFullYear() + "년 " 
        + now.getMonth() + "월 "
        + now.getDate() + "일 "
        + now.getHours() + "시 "
        + now.getMinutes() + "분 "
        + now.getSeconds() + "초 ";
    clock.innerHTML = result;
}

// document.write("현재 시간은 :" + now.toLocaleTimeString()); br();
// document.write("현재 시간은 :" + now.toLocaleString()); br();
// document.write("getDate :" + now.getDate() );   br();
// document.write("getDay :" + now.getDay() );   br(); // 요일. 0-일, 1-월, 2-화, 3-수, 4-목, 5-금, 6-토
// document.write("getFullYear :" + now.getFullYear() );   br();
// document.write("getHours :" + now.getHours() );   br();
// document.write("getMilliseconds :" + now.getMilliseconds() );   br();
// document.write("getMinutes :" + now.getMinutes() );   br();
// document.write("getMonth :" + now.getMonth() );   br(); // 달 데이터는 0부터시작이라 +1 해야 현재달이 나옴.
// document.write("getSeconds :" + now.getSeconds() );   br();
// document.write("getTime :" + now.getTime() );   br();
// document.write("getTimezoneOffset :" + now.getTimezoneOffset() );   br();
// document.write("getUTCDate :" + now.getUTCDate() );   br();
// document.write("getUTCDay :" + now.getUTCDay() );   br();
// document.write("getUTCFullYear :" + now.getUTCFullYear() );   br();
// document.write("getUTCHours :" + now.getUTCHours() );   br();
// document.write("getUTCMilliseconds :" + now.getUTCMilliseconds() );   br();
// document.write("getUTCMinutes :" + now.getUTCMinutes() );   br();
// document.write("getUTCMonth :" + now.getUTCMonth() );   br();
// document.write("getUTCSeconds :" + now.getUTCSeconds() );   br();
// document.write("getVarDate :" + now.getVarDate() );   br();

