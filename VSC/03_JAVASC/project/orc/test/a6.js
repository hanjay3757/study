function test(){
	turn = turn + 1;
	console.log("현재 턴:"+turn);

	input_turn.value = turn;
}

var turn = 0;

var cat_v2;
var cat_v1;
var t;	// 첫번째 스크린
var t2;	// 중앙
var t3;	// 하단

var str = "";	// 상단에 들어갈 텍스트
var str2 = "";	// 중앙에 들어갈 텍스트
var str3 = "";	// 하단에 들어갈 텍스트

// input_turn
var input_turn;

window.onload = function(){

	t = document.getElementById("a");
	t2 = document.getElementById("b");
	t3 = document.getElementById("c");
	input_turn = document.getElementById("input_turn");

	cat_v1 = new Monster("야옹",100,20,100,1000);

	cat_v2 = new Character();
	cat_v2.name = "냐옹";
	cat_v2.hp = 200;
	cat_v2.max_hp = 200;
	cat_v2.attack = 20;
	cat_v2.exp = 0;
	cat_v2.next_exp = 300;
	cat_v2.gold = 0;
	
	cat_v2.info();
	cat_v1.info();
	
	hr();
	tv("전투시작");
	hr();

	while(true){
		proc_battle();
	
		if(cat_v2.hp <= 0 || cat_v1.hp <= 0){
			break;
		}
	}

	tv("전투종료"); br();
	tv("불쌍한 "+cat_v1.name+", "+cat_v2.name+"에게 경험치 "+cat_v1.exp+" 을 주고 죽었습니다.");
	cat_v2.exp = cat_v2.exp + cat_v1.exp;
	br();
	tv(cat_v1.gold+" 골드를 얻었습니다.");
	cat_v2.gold = cat_v2.gold + cat_v1.gold;


	hr();hr();

	cat_v2.info();


}

var cat_v1_attack;
var cat_v2_attack;

function proc_battle(){
	cat_v1_attack = r(cat_v2.attack);
	cat_v2_attack = r(cat_v1.attack);
	
	hr();
	tv("🏹냥냥펀치 데미지:"+cat_v1_attack);
	hr();
	tv("🪓젤리펀치 데미지:"+cat_v2_attack);
	hr();
	
	cat_v2.hp = cat_v2.hp - cat_v2_attack;		// 1~10 랜덤 뎀
	cat_v1.hp = cat_v1.hp - cat_v1_attack;	
	
	cat_v2.info();
	cat_v1.info();
}