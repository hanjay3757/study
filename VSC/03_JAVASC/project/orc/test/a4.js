Monster();
Character();

var cat_v1 = new Monster("미야옹", 100,20,100,1000);
var cat_v2 = new Character("야옹이", 100,20,100,1000);


cat_v1.info();
cat_v2.info();

hr();
dw("전투");
hr();


function proc_battle() {
    var cat_v1_attack = r(cat_v1.attack);
    var cat_v2_attack = r(cat_v2.attack);

    hr();
    dw("🐱‍👤미야옹의 데미지🐾:" + cat_v1_attack);
    hr();
    dw("≽^•⩊•^≼야옹이의 데미지🐱‍🐉:" + cat_v2_attack);
    hr();

    cat_v1.hp = cat_v1.hp - cat_v2_attack;
    cat_v2.hp = cat_v2.hp - cat_v1_attack;

    if (cat_v1.hp < 0) {
        cat_v1.hp = 0;
    }
    if (cat_v2.hp < 0) {
        cat_v2.hp = 0;
    }
    cat_v1.info();
    cat_v2.info();
}
while (true) {
    proc_battle();
    if (cat_v1.hp <= 0 || cat_v2.hp <= 0) {
        break;
    }
}

// var loop =true;
// while(loop){
//     loop=proc_battle;
// }


dw("전투종료"); br();
dw("불쌍한 "+cat_v2.name+", "+cat_v1.name+"에게 경험치 "+cat_v1.exp+" 을 주고 죽었습니다.");
cat_v1.exp = cat_v1.exp + cat_v2.exp;
br();
dw(cat_v2.gold+" 골드를 얻었습니다.");
cat_v1.gold = cat_v1.gold + cat_v2.gold;

