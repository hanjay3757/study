// Cat 클래스에	crying()	추가				
// 	crying()	는	고양이 소리를 출력( document.write ) 하는 함수로 작성			
// 기존 두 객체의	crying()	각각 호출(실행)				


function Cat(){	
	/* 속성 또는 프로퍼티들 */
	this.name = "";  // 이름
	this.age = 0;    // 나이
	this.weight = 0; // 몸무게
	this.family = "";    // 종류 (ex. 코리안숏헤어, 페르시아고양이)
	this.color = "";     // 털색


	/* 멤버 함수들 선언 */	
	this.crying = function() {	

		document.write("야옹");

	}

	// this.eat = function(food){
	// 	document.write(this.name + "가 " + food + "를 먹습니다.");
	// }
}



// 1. Cat 클래스 객체를 생성하기.
// new Cat();

// 2. 변수 kitty 를 선언하고 여기에 위의(1번문제의) Cat 클래스 객체 생성문을 넣기(대입하기).
var kitty = new Cat();



// 3.이 클래스형 변수 kitty 가 가진 name 변수에 "야옹이" 를 넣기( "야옹이" 라고 이름 지어주기)
kitty.name = "야옹이";
kitty.age = 7;
kitty.weight = 100;
kitty.family = "페르시아";
kitty.color = "삼색이";


// 4.위 고양이 이름을 출력하기
document.write(kitty.name);  
br();
document.write(kitty.age);
 br();
document.write(kitty.weight);  br();
document.write(kitty.family);  br();
document.write(kitty.color);  br();


br();br();

// 추가로 Cat 클래스로 부터 yaongi 객체를 하나 더 만들기(kitty 객체는 두시고). 					
var yaongi = new Cat();
// 속성 값들도 임의로 전부 주시고	
yaongi.name = "보리";
yaongi.age = 3;
yaongi.weight = 10;
yaongi.family = "시고르자브종";
yaongi.color = "흰색";

// 4.위 고양이 이름을 출력하기
document.write(yaongi.name);  br();
document.write(yaongi.age); br();
document.write(yaongi.weight);  br();
document.write(yaongi.family);  br();
document.write(yaongi.color);  br();



br();
br();

// 키티랑 야옹이랑 서열정리		나이를 비교하여	ex.	형님 고양이: 야옹이		식으로 출력 처리하기
if(kitty.age > yaongi.age){
    document.write("키티가 형님");
}else if(kitty.age < yaongi.age){
    document.write("보리가 형님");
}else{
    document.write("둘은 친구");
}

br();br();

// 				동생 고양이: 키티		
// 				둘은 친구임	(같으면)	


yaongi.crying();

kitty.crying();

br();br();

// kitty.eat("츄르");




/**********************************************************************
rpg (v0.1.0)
***********************************************************************

[v0.1.0 업데이트 내용]
- 몬스터 클래스 생성
- 플레이어 클래스 생성
- 몬스터 객체 생성(orc)
- 플레이어 객체 생성(elf)


**********************************************************************/
function Monster(){ 
    //속성, 프로퍼티    
    this.name;
    this.hp;
    this.attack;

    this.info = function(){
        hr();
        dw("["+this.name+"]["+this.hp+"][atk:"+this.attack+"]");
        hr();
    }
}
// function Monster(name,hp,attack){ 
//     //속성, 프로퍼티    
//     this.name = name;  // 이름
//     this.hp = hp;    // 체력
//     this.attack = attack; // 공격력

//     this.info = function(){
//         hr();
//         dw("["+name+"]["+hp+"][atk:"+attack+"]");
//         hr();
//     }
// }
function Character(){ 
    //속성, 프로퍼티    
    this.name;
    this.hp;
    this.attack;

    this.info = function(){
        hr();
        dw("["+this.name+"]["+this.hp+"][atk:"+this.attack+"]");
        hr();
    }
}
// function Character(name,hp,attack){ 
//     //속성, 프로퍼티    
//     this.name = name;  // 이름
//     this.hp = hp;    // 체력
//     this.attack = attack; // 공격력

//     this.info = function(){
//         hr();
//         dw("["+name+"]["+hp+"][atk:"+attack+"]");
//         hr();
//     }
// }

// var orc = new Monster("오크전사",100,10);
var orc = new Monster();
var elf = new Character();

orc.name = "오크전사";
orc.hp = 100;
orc.attack = 10;

elf.name = "엠피스";
elf.hp = 200;
elf.attack = 20;


orc.info();
elf.info();