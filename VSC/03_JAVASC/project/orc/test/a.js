function C_health(){
    this.name;
    this.hp;
    this.attack;

    this.info = function(){
        hr();
        dw("["+this.name+"]["+this.hp+"][atk:"+this.attack+"]");
        hr();
    }
}

function M_health(){
    this.name;
    this.hp;
    this.attack;
    this.info = function(){
        hr();
        dw("["+this.name+"]["+this.hp+"][atk:"+this.attack+"]");
        hr();
    }
}

// var orc =new M_health("a",hp,attack);
var orc = new M_health();
var cat_v1 = new C_health();

orc.name = "오크전사";
orc.hp = 100;
orc.attack = 10;

cat_v1.name = "엠피스";
cat_v1.hp = 200;
cat_v1.attack = 20;

orc.info();
cat_v1.info();

