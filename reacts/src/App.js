import axios from 'axios';			// axios를 임포트하여 API 요청에 사용
import React, { useState, useRef } from 'react';  // React hooks 사용
import './App.css';  // CSS 파일을 임포트하여 스타일 적용

// 직업(job)과 등급(grade) 설정
var jobs = ["전사", "마법사", "궁수", "도적", "사제"];
var grade = ["SSR", "SR", "S", "R", "H", "N"];

// 카드 등급에 따른 확률 계산 함수
function getLuck() {
  var r = Math.floor(Math.random() * 100) + 1;  // 1~100 사이의 랜덤 숫자 생성
  var t = 5;	// 기본값: N Normal

  if (r <= 50) {	// 확률에 따라 등급을 결정
    t = 4; // H High
  }
  if (r <= 20) {	// 확률에 따라 등급을 결정
    t = 3; // R Rare
  }
  if (r <= 10) {	// 확률에 따라 등급을 결정
    t = 2; // S Super
  }
  if (r <= 4) {	// 확률에 따라 등급을 결정
    t = 1; // SR SuperRare
  }
  if (r === 1) {	// 1% 확률로 SSR
    t = 0; // SSR SuperSuperRare
  }

  return t;  // 등급 반환
}

// 주사위처럼 숫자 범위 내에서 랜덤 값을 반환하는 함수
function dice(s, e) {
  return Math.floor(Math.random() * (e - s + 1)) + s;
}

// 카드 컴포넌트 (각 카드의 UI와 효과를 관리)
function Card({ job, grade, isFlipped, onFlip }) {
  const [rotation, setRotation] = useState({ x: 0, y: 0 });  // 카드 회전 상태
  const cardRef = useRef(null);  // 카드 참조

  // 마우스 이동 시 카드가 3D로 회전하는 효과를 처리하는 함수
  const handleMouseMove = (e) => {
    if (!cardRef.current) return;  // isFlipped 체크 제거

    const card = cardRef.current;
    const rect = card.getBoundingClientRect();  // 카드의 위치와 크기 정보를 가져옴
    const x = e.clientX - rect.left;
    const y = e.clientY - rect.top;

    const centerX = rect.width / 2;
    const centerY = rect.height / 2;

    // 마우스 위치에 따라 카드의 회전 각도를 계산
    const rotateX = -(y - centerY) / 10;
    const rotateY = (x - centerX) / 10;

    setRotation({ x: rotateX, y: rotateY });
  };

  // 마우스가 카드에서 벗어나면 회전 효과를 리셋하는 함수
  const handleMouseLeave = () => {
    setRotation({ x: 0, y: 0 });
  };

  return (
    <div 
      ref={cardRef}
      className={`card ${job} ${grade} ${isFlipped ? 'flipped' : ''}`}
      style={{
        transform: `perspective(1000px) rotateX(${rotation.x}deg) rotateY(${rotation.y}deg) ${isFlipped ? 'rotateY(180deg)' : ''}`,
        transition: 'transform 0.6s'  // 3D 회전 효과
      }}
      onClick={onFlip}  // 카드 클릭 시 뒤집기
      onMouseMove={handleMouseMove}  // 마우스 이동 시 회전
      onMouseLeave={handleMouseLeave}  // 마우스가 카드에서 벗어나면 리셋
      draggable
    >
      <div className="card-front">
        <div className="card-content">
          <div className="card-title">{job}</div>
          <div className="card-grade">{grade}</div>
        </div>
      </div>
      <div className="card-back">
        <div className="card-back-design"></div>
      </div>
    </div>
  );
}

// 카드들을 묶어서 보여주는 영역
function CardArea({ children }) {
  return (
    <div id='card_area'>
      {children}
    </div>
  );
}

// 전투 컴포넌트
function Battle({ playerParty, enemyParty }) {
  const [battleLog, setBattleLog] = useState([]);  // 전투 로그
  const [currentTurn, setCurrentTurn] = useState(1);  // 현재 턴
  const [isBattling, setIsBattling] = useState(false);  // 전투 진행 여부

  // 카드의 스탯 계산 함수 수정 - 더 균형잡힌 스탯
  const getCardStats = (card) => {
    const baseStats = {
      SSR: { hp: 100, atk: 20, def: 15 },
      SR: { hp: 85, atk: 17, def: 13 },
      S: { hp: 70, atk: 15, def: 11 },
      R: { hp: 60, atk: 13, def: 9 },
      H: { hp: 50, atk: 11, def: 7 },
      N: { hp: 40, atk: 9, def: 5 }
    };

    const jobMultipliers = {
      전사: { hp: 1.2, atk: 0.9, def: 1.3 },
      마법사: { hp: 0.8, atk: 1.4, def: 0.7 },
      궁수: { hp: 0.9, atk: 1.2, def: 0.8 },
      도적: { hp: 0.7, atk: 1.3, def: 0.6 },
      사제: { hp: 1.1, atk: 0.7, def: 1.0 }
    };

    const base = baseStats[card.grade];
    const multiplier = jobMultipliers[card.job];

    return {
      hp: Math.floor(base.hp * multiplier.hp),
      atk: Math.floor(base.atk * multiplier.atk),
      def: Math.floor(base.def * multiplier.def),
      maxHp: Math.floor(base.hp * multiplier.hp)
    };
  };

  // 데미지 계산 함수 추가 - 크리티컬과 회피 시스템 추가
  const calculateDamage = (attacker, defender) => {
    // 크리티컬 확률 (15%)
    const isCritical = Math.random() < 0.15;
    // 회피 확률 (10%)
    const isEvaded = Math.random() < 0.10;

    if (isEvaded) {
      return { damage: 0, isCritical: false, isEvaded: true };
    }

    let damage = Math.max(1, attacker.atk - defender.def);
    if (isCritical) {
      damage = Math.floor(damage * 1.5);
    }

    return { damage, isCritical, isEvaded: false };
  };

  // 로그 메시지 생성 함수 수정
  const createLogMessage = (attacker, target, damageInfo, isPlayerAttack) => {
    if (damageInfo.isEvaded) {
      return {
        text: `${target.job}(${target.grade})가 ${attacker.job}(${attacker.grade})의 공격을 회피했습니다!`,
        type: isPlayerAttack ? 'evaded-by-enemy' : 'evaded-by-player'
      };
    }

    let message = `${attacker.job}(${attacker.grade})가 ${target.job}(${target.grade})에게 ${damageInfo.damage} 데미지를 입혔습니다!`;
    if (damageInfo.isCritical) {
      message = `크리티컬! ${message}`;
    }
    message += ` (${target.hp}/${target.maxHp})`;

    return {
      text: message,
      type: isPlayerAttack ? 
        (damageInfo.isCritical ? 'critical-dealt' : 'damage-dealt') : 
        (damageInfo.isCritical ? 'critical-taken' : 'damage-taken')
    };
  };

  // 전투 시작 함수 수정
  const startBattle = async () => {
    setIsBattling(true);
    setBattleLog([{ text: '전투 시작!', type: 'battle-start' }]);

    const playerCards = playerParty.map(card => ({
      ...card,
      ...getCardStats(card),
      isPlayer: true
    }));

    const enemyCards = enemyParty.map(card => ({
      ...card,
      ...getCardStats(card),
      isPlayer: false
    }));

    let turn = 1;
    const maxTurns = 30; // 최대 턴 수 제한

    while (playerCards.some(card => card.hp > 0) && 
           enemyCards.some(card => card.hp > 0) && 
           turn <= maxTurns) {
      await new Promise(resolve => setTimeout(resolve, 1000));

      const isPlayerTurn = turn % 2 === 1;
      const attacker = isPlayerTurn ? playerCards : enemyCards;
      const defender = isPlayerTurn ? enemyCards : playerCards;

      setBattleLog(prev => [...prev, { 
        text: `${isPlayerTurn ? '플레이어' : '적'} 턴 시작!`, 
        type: 'turn-start' 
      }]);

      for (let card of attacker.filter(c => c.hp > 0)) {
        const target = defender.find(c => c.hp > 0);
        if (target) {
          const damageInfo = calculateDamage(card, target);
          target.hp = Math.max(0, target.hp - damageInfo.damage);

          setBattleLog(prev => [...prev, 
            createLogMessage(card, target, damageInfo, isPlayerTurn)
          ]);
        }
      }

      setCurrentTurn(turn);
      turn++;
    }

    // 승패 판정
    const playerWon = playerCards.some(card => card.hp > 0);
    setBattleLog(prev => [...prev, { 
      text: playerWon ? '플레이어의 승리!' : '적의 승리!',
      type: playerWon ? 'victory' : 'defeat'
    }]);
    setIsBattling(false);
  };

  return (
    <div className="battle-container">
      <div className="battle-field">
        <div className="enemy-area">
          {enemyParty.map((card, index) => (
            <Card key={`enemy-${index}`} {...card} />
          ))}
        </div>
        <div className="player-area">
          {playerParty.map((card, index) => (
            <Card key={`player-${index}`} {...card} />
          ))}
        </div>
      </div>
      
      <div className="battle-controls">
        <button 
          onClick={startBattle} 
          disabled={isBattling}
          className="battle-button"
        >
          전투 시작
        </button>
        <div className="turn-counter">턴: {currentTurn}</div>
      </div>

      <div className="battle-log">
        {battleLog.map((log, index) => (
          <div key={index} className={`log-entry ${log.type}`}>
            {log.text}
          </div>
        ))}
      </div>
    </div>
  );
}

function App() {
  const [my, setMy] = useState([]);  // 보유 카드
  const [party, setParty] = useState([  // 기본 파티 설정
    { job: '전사', grade: 'SSR', isFlipped: false },
    { job: '마법사', grade: 'SR', isFlipped: false },
    { job: '궁수', grade: 'S', isFlipped: false },
    { job: '전사', grade: 'R', isFlipped: false },
    { job: '궁수', grade: 'H', isFlipped: false }
  ]);
  
  // 적 파티 생성 - 랜덤하게 생성
  const [enemyParty] = useState(() => {
    const enemies = [];
    for (let i = 0; i < 5; i++) {
      const j = jobs[dice(0, 4)];
      const g = grade[getLuck()];
      enemies.push({ job: j, grade: g, isFlipped: false });
    }
    return enemies;
  });
  
  const [isGachaAnimating, setIsGachaAnimating] = useState(false);  // 가챠 애니메이션 상태

  // 가챠 연출과 카드 추가 함수를 수정
  async function gacha() {
    setIsGachaAnimating(true);
    
    try {
      // 로컬 가챠 (기존 방식)
      const j = jobs[dice(0, 4)];
      const g = grade[getLuck()];
      
      const newCard = {
        job: j,
        grade: g,
        isFlipped: true
      };
      
      setMy(prev => [...prev, newCard]);
      
      // 카드 뒤집기 애니메이션
      setTimeout(() => {
        setMy(prev => prev.map((card, idx) => 
          idx === prev.length - 1 ? {...card, isFlipped: false} : card
        ));
      }, 500);
      
    } catch (error) {
      console.error('가챠 실패:', error);
      alert('카드 뽑기에 실패했습니다. 다시 시도해주세요.');
    } finally {
      setIsGachaAnimating(false);
    }
  }

  // API를 통한 가챠 함수 추가
  async function gachaApi() {
    setIsGachaAnimating(true);
    
    try {
      const response = await axios.get('http://localhost:8080/spring/api/gacha');
      
      const newCard = {
        ...response.data,
        isFlipped: true
      };
      
      setMy(prev => [...prev, newCard]);
      
      // 카드 뒤집기 애니메이션
      setTimeout(() => {
        setMy(prev => prev.map((card, idx) => 
          idx === prev.length - 1 ? {...card, isFlipped: false} : card
        ));
      }, 500);
      
    } catch (error) {
      console.error('API 가챠 실패:', error);
      alert('API를 통한 카드 뽑기에 실패했습니다. 다시 시도해주세요.');
    } finally {
      setIsGachaAnimating(false);
    }
  }

  // 카드 클릭 이벤트 핸들러 추가
  function handleCardClick(index) {
    console.log(`보유카드 ��호: ${index}`);
    alert(`보유카드 번호: ${index}`);
  }

  return (
    <div className="game-container">
      <div className="battle-area">
        <h2>파티 1</h2>
        <CardArea>
          {party.map((character, index) => (
            <Card 
              key={`party-${index}`}
              job={character.job} 
              grade={character.grade}
              isFlipped={character.isFlipped}
              onFlip={() => {
                setParty(prev => prev.map((card, idx) => 
                  idx === index ? {...card, isFlipped: !card.isFlipped} : card
                ));
              }}
            />
          ))}
        </CardArea>
      </div>

      <div className="gacha-area">
        <h2>가챠</h2>
        <button 
          onClick={gacha} 
          disabled={isGachaAnimating}
          className={`gacha-button ${isGachaAnimating ? 'animating' : ''}`}
        >
          로컬 가챠
        </button>
        <button 
          onClick={gachaApi} 
          disabled={isGachaAnimating}
          className={`gacha-button ${isGachaAnimating ? 'animating' : ''}`}
        >
          API 가챠
        </button>
      </div>

      <div className="inventory">
        <h2>보유 카드</h2>
        <CardArea>
          {my.map((character, index) => (
            <Card 
              key={`my-${index}`}
              job={character.job} 
              grade={character.grade}
              isFlipped={character.isFlipped}
              onFlip={() => handleCardClick(index)}
            />
          ))}
        </CardArea>
      </div>

      <div className="battle-section">
        <h2>전투</h2>
        <Battle playerParty={party} enemyParty={enemyParty} />
      </div>
    </div>
  );
}

export default App;
