function C_health(name,hp,attack){
    this.name =name;
    this.hp=hp; 
    this.attack = attack;

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
var elf = new C_health();

orc.name = "오크전사";
orc.hp = 100;
orc.attack = 10;

elf.name = "엠피스";
elf.hp = 200;
elf.attack = 20;

orc.info();
elf.info();

// 			오크와 엘프 간 전투 처리 (서로 한번씩만 때려 피해를 입히는 처리)
//				상대의 공격력(attack) 수치 만큼 hp(체력) 에 피해 ( 마이너스 ) 처리	
// 			각 객체의 info 를 호출하여 현재 정보 출력					


function Monster(name,hp,attack){
	this.name = name;	// 이름
	this.hp = hp;	// 체력. 생명력
	this.attack = attack;	// 공격력

	this.info = function(){
		document.write("이름"+this.name+" HP:"+this.hp+" 공격력:"+this.attack);
	}
}



function Character(){
	this.name;	// 이름
	this.hp;	// 체력. 생명력
	this.attack;	// 공격력

	this.info = function(){
		document.write("이름"+this.name+" HP:"+this.hp+" 공격력:"+this.attack);
	}
}

var orc = new Monster("오크",100,10);

var elf = new Character();
elf.name = "성모";
elf.hp = 200;
elf.attack = 20;

orc.info();
br();br();
elf.info();

hr();
dw("전투시작");
hr();

//todo
elf.hp = elf.hp - orc.attack;
orc.hp = orc.hp - elf.attack;

orc.info();
br();br();
elf.info();


