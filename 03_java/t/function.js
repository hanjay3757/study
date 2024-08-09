function hr(){
    document.write("<hr>");
}
function br(){
    document.write("<br>");
}

function r(max){
    var x = Math.floor(Math.random()*max)+1
    // document.write(x);
    return x;
    // return 
}
var swordRandom;
swordRandom = r(5);
document.write(swordRandom);
hr();
var longSwordRandom = r(1000);
document.write(longSwordRandom);0.