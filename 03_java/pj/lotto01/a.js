// var p1 = r(45);
// var p2 = r(45);
// var p3 = r(45);
// var p4 = r(45);
// var p5 = r(45);
// var p6 = r(45);
var p = [r(45), r(45), r(45), r(45), r(45), r(45)];
var x = [0, 0, 0, 0, 0, 0];
x[0] = Math.floor(Math.random() * 45) + 1;
dw(x[0]);
br();

while (true) {
    x[1] = Math.floor(Math.random() * 45) + 1;
    if (x[1] != x[0]) {
        dw(x[1]);
        br();
        break;
    }
}

while (true) {
    x[2] = Math.floor(Math.random() * 45) + 1;
    if (x[2] != x[1] && x[2] != x[0]) {
        dw(x[2]);
        br();
        break;
    }
}

while (true) {
    x[3] = Math.floor(Math.random() * 45) + 1;
    if (x[3] != x[2] && x[3] != x[1] && x[3] != x[0]) {
        dw(x[3]);
        br();
        break;
    }
}

while (true) {
    x[4] = Math.floor(Math.random() * 45) + 1;
    if (x[4] != x[3] && x[4] != x[2] && x[4] != x[1] && x[4] != x[0]) {
        dw(x[4]);
        br();
        break;
    }
}

while (true) {
    x[5] = Math.floor(Math.random() * 45) + 1;
    if (x[5] != x[4] && x[5] != x[3] && x[5] != x[2] && x[5] != x[1] && x[5] != x[0]) {
        dw(x[5]);
        br();
        break;
    }
}


// // p1 ~ p6


//내가 맞춘 수

// if(p1 == r1){
//     win = win + 1;
// }
// if(p1 == r2){
//     win = win + 1;
// }
// if(p1 == r3){
//     win = win + 1;
// }
// if(p1 == r4){
//     win = win + 1;
// }
// if(p1 == r5){
//     win = win + 1;
// }
// if(p1 == r6){
//     win = win + 1;
// }

// if(p2 == r1){
//     win = win + 1;
// }
// if(p2 == r2){
//     win = win + 1;
// }
// if(p2 == r3){
//     win = win + 1;
// }
// if(p2 == r4){
//     win = win + 1;
// }
// if(p2 == r5){
//     win = win + 1;
// }
// if(p2 == r6){
//     win = win + 1;
// }

// //3

// if(p3 == r1){
//     win = win + 1;
// }
// if(p3 == r2){
//     win = win + 1;
// }
// if(p3 == r3){
//     win = win + 1;
// }
// if(p3 == r4){
//     win = win + 1;
// }
// if(p3 == r5){
//     win = win + 1;
// }
// if(p3 == r6){
//     win = win + 1;
// }

// //4

// if(p4 == r1){
//     win = win + 1;
// }
// if(p4 == r2){
//     win = win + 1;
// }
// if(p4 == r3){
//     win = win + 1;
// }
// if(p4 == r4){
//     win = win + 1;
// }
// if(p4 == r5){
//     win = win + 1;
// }
// if(p4 == r6){
//     win = win + 1;
// }

// //5

// if(p5 == r1){
//     win = win + 1;
// }
// if(p5 == r2){
//     win = win + 1;
// }
// if(p5 == r3){
//     win = win + 1;
// }
// if(p5 == r4){
//     win = win + 1;
// }
// if(p5 == r5){
//     win = win + 1;
// }
// if(p5 == r6){
//     win = win + 1;
// }

// //6

// if(p6 == r1){
//     win = win + 1;
// }
// if(p6 == r2){
//     win = win + 1;
// }
// if(p6 == r3){
//     win = win + 1;
// }
// if(p6 == r4){
//     win = win + 1;
// }
// if(p6 == r5){
//     win = win + 1;
// }
// if(p6 == r6){
//     win = win + 1;
// }
// br();
// /var str = "";
var b = 0;
while (true) {
    b = Math.floor(Math.random() * 45) + 1; // 1 ~ n 까지 범위내에서 랜덤하게 숫자 하나 뽑아주는 애.
    if (b != r[0] && b != r[1] && b != r[2] && b != r[3] && b != r[4] && b != r[5]) {
        document.write("보너스번호:" + b);
        document.write("<br>");
        break;
    }
}
var win = 0;

for (var i = 0; i <= 5; i++) {
    for (var j = 0; j <= 5; j++) {
        if (p[i] == x[j]) {
            win = win + 1;
            break;
        }
    }
}
document.write("맞은 번호 갯수:" + win + "<br>");
// document.write(i);
var str = "";

switch (win) {
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
        for (var i = 0; i < 6; i = i + 1) {
            if (b == p[i]) {
                str = "2등에 당첨되셨습니다.";
            }
        }
        break;
        break;
    case 6:
        str = "1등에 당첨되셨습니다.";
        break;
}
document.write(str);