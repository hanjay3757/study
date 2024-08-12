// '오크전사'라는 이름을 가진 몬스터 객체를 생성, 초기 HP는 100, 공격력은 10
var orc = new Monster("오크전사", 100, 10);

// '엠피스'라는 이름을 가진 캐릭터 객체를 생성, 초기 HP는 200, 공격력은 30
var elf = new Character("엠피스", 200, 30);

// 두 캐릭터의 초기 정보를 출력
displayCharactersInfo();

// 시각적 구분을 위한 수평 구분선 출력
hr();

// "전투 시작" 메시지 출력
dw("전투 시작");

// 또 다른 시각적 구분을 위한 수평 구분선 출력
hr();

// 전투 턴을 처리할 루프 초기화
var loop = true;
while(loop){
    // 한 턴의 전투를 처리하고 전투 상태에 따라 루프를 계속할지 결정
    loop = procBattleTurn();
}

// 한 턴의 전투를 처리하는 함수
function procBattleTurn(){
    // 두 캐릭터의 랜덤 데미지 값을 계산
    var monsterDamage = getRandomAttackValue(orc.attack);
    var playerDamage = getRandomAttackValue(elf.attack);
    
    // 각 캐릭터에 데미지를 적용
    orc.currentHp = orc.currentHp - playerDamage;
    // 플레이어(엠피스)가 몬스터(오크전사)에게 입힌 데미지 메시지 출력
    dw(elf.name + "가 " + orc.name + "에게 데미지를 " + playerDamage + " 입혔습니다.<br>");
    
    elf.currentHp = elf.currentHp - monsterDamage;
    // 몬스터(오크전사)가 플레이어(엠피스)에게 입힌 데미지 메시지 출력
    dw(orc.name + "가 " + elf.name + "에게 데미지를 " + monsterDamage + " 입혔습니다.<br>");
    
    // 캐릭터의 HP가 0 이하로 떨어졌는지 확인
    if(elf.currentHp <= 0 || orc.currentHp <= 0){
        // HP 조건이 충족되면 전투 종료
        endBattle();
        // 두 캐릭터의 최종 정보를 출력
        displayCharactersInfo();
        // 루프를 멈추기 위해 false 반환
        return false;
    } else {
        // 현재 두 캐릭터의 정보를 출력
        displayCharactersInfo();
        // 또 다른 턴을 계속하기 위해 true 반환
        return true;
    }
}

// 공격력에 따라 랜덤 데미지 값을 얻는 함수
function getRandomAttackValue(attack){
    attack = attack + 1;  // 최대 값을 포함하도록 공격력 조정
    // 0과 attack 사이의 랜덤 숫자를 생성
    var random = Math.floor(Math.random() * attack);
    return random;
}

// 전투 종료를 처리하는 함수
function endBattle(){
    // "전투 종료" 메시지 출력
    dw("전투 종료");
    // 시각적 구분을 위한 줄 바꿈
    br();
    // 엠피스 캐릭터의 경험치 업데이트
    // (이 부분은 orc 객체가 'exp' 속성을 가지고 있어야 함)
    elf.exp = elf.exp + orc.exp;
}

// 두 캐릭터의 현재 정보를 출력하는 함수
function displayCharactersInfo(){
    // 시각적 구분을 위한 수평 구분선 출력
    hr();
    // 엠피스 캐릭터의 정보 출력
    elf.info();
    // 오크전사 몬스터의 정보 출력
    orc.info();
    // 또 다른 시각적 구분을 위한 수평 구분선 출력
    hr();
}