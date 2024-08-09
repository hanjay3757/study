// - 전투 무한 루프 처리 : 전투 시 플레이어나 적의 현재 체력이 0 이하로 될 때까지(죽을때 까지) 전투 반복 처리

var cat_v2 = new Monster("드래곤",100,10);

var cat_v1 = new Character();
cat_v1.name = "성모";
cat_v1.hp = 200;
cat_v1.max_hp = 200;
cat_v1.attack = 20;

cat_v1.info();
cat_v2.info();

hr();
dw("전투시작");
hr();


function proc_battle(){
	var elf_attack = r(cat_v1.attack);
	var dragon_attack = r(cat_v2.attack);
	
	hr();
	dw("플레이어 데미지:"+elf_attack);
	hr();
	dw("몬스터 데미지:"+dragon_attack);
	hr();
	
	cat_v1.hp = cat_v1.hp - dragon_attack;		// 1~10 랜덤 뎀
	cat_v2.hp = cat_v2.hp - elf_attack;	
	
	cat_v1.info();
	cat_v2.info();
}



while(true){
	proc_battle();

	if(cat_v1.hp <= 0 || cat_v2.hp <= 0){
		break;
	}
}