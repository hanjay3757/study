import axios from 'axios';			// axios를 임포트하여 API 요청에 사용
import React, { useState, useRef, useEffect } from 'react';  // React hooks 사용
import './App.css';  // CSS 파일을 임포트하여 스타일 적용

// =========================================
// 상수 및 유틸리티 함수
// =========================================
var jobs = ["전사", "마법사", "궁수", "도적", "사제"];
var grade = ["SSR", "SR", "S", "R", "H", "N"];

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

function dice(s, e) {
  return Math.floor(Math.random() * (e - s + 1)) + s;
}

// =========================================
// 컴포넌트 정의
// =========================================

// Card 컴포넌트: 개별 카드의 표시와 상호작용을 담당
function Card({ job, grade, isFlipped, onFlip, draggable, onDragStart, onDragOver, onDrop, index, isActive, isAttacker, isTarget, onClick }) {
  const [rotation, setRotation] = useState({ x: 0, y: 0 });
  const cardRef = useRef(null);

  const handleMouseMove = (e) => {
    if (!cardRef.current) return;

    const card = cardRef.current;
    const rect = card.getBoundingClientRect();
    const x = e.clientX - rect.left;
    const y = e.clientY - rect.top;

    const centerX = rect.width / 2;
    const centerY = rect.height / 2;

    // 회전 각도 증가
    const rotateX = -((y - centerY) / 10) * 1.5;
    const rotateY = ((x - centerX) / 10) * 1.5;

    setRotation({ x: rotateX, y: rotateY });
  };

  const handleMouseLeave = () => {
    // 부드러운 원위치 효과
    setRotation({ x: 0, y: 0 });
  };

  return (
    <div 
      ref={cardRef}
      className={`card ${job} ${grade} ${isFlipped ? 'flipped' : ''} ${isActive ? 'active' : ''} ${isAttacker ? 'attacker' : ''} ${isTarget ? 'target' : ''}`}
      style={{
        transform: `
          perspective(1000px) 
          rotateX(${rotation.x}deg) 
          rotateY(${rotation.y}deg) 
          translateZ(20px)
          ${isFlipped ? 'rotateY(180deg)' : ''}
        `,
        transition: 'transform 0.3s ease'
      }}
      onClick={onClick || onFlip}
      onMouseMove={handleMouseMove}
      onMouseLeave={handleMouseLeave}
      draggable={draggable}
      onDragStart={(e) => onDragStart && onDragStart(e, index)}
      onDragOver={(e) => onDragOver && onDragOver(e)}
      onDrop={(e) => onDrop && onDrop(e, index)}
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

// CardArea 컴포넌트: 카드들의 컨테이너 
function CardArea({ children, type, handleDrop }) {
  const [isDragOver, setIsDragOver] = useState(false);

  const handleDragOver = (e) => {
    e.preventDefault();
    setIsDragOver(true);
  };

  const handleDragLeave = () => {
    setIsDragOver(false);
  };

  const onAreaDrop = (e) => {
    e.preventDefault();
    setIsDragOver(false);
    handleDrop(e, type);
  };

  return (
    <div 
      className={`card-area ${type} ${isDragOver ? 'drag-over' : ''}`}
      onDragOver={handleDragOver}
      onDragLeave={handleDragLeave}
      onDrop={onAreaDrop}
    >
      {children}
    </div>
  );
}

// Battle 컴포넌트: 전투 시스템 구현
function Battle({ playerParty, enemyParty, onBattleEnd }) {
  const [battleLog, setBattleLog] = useState([]);  // 전투 로그
  const [currentTurn, setCurrentTurn] = useState(1);  // 현재 턴
  const [isBattling, setIsBattling] = useState(false);  // 전투 진행 여부
  const logRef = useRef(null);
  const [activeAttacker, setActiveAttacker] = useState(null);
  const [activeTarget, setActiveTarget] = useState(null);

  // 로그가 업데이트될 때마다 스크롤을 맨 아래로 이동시키는 함수
  const scrollToBottom = () => {
    if (logRef.current) {
      logRef.current.scrollTop = logRef.current.scrollHeight;
    }
  };

  // battleLog가 변경될 때마다 스크롤 실행
  useEffect(() => {
    scrollToBottom();
  }, [battleLog]);

  // 카드의 스탯 계산 함수 수정 - 적군에게 보너스 부여
  const getCardStats = (card, isEnemy = false) => {
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

    // 적군에게 10~15% 스탯 보너스
    const enemyBonus = isEnemy ? 1.1 + (Math.random() * 0.05) : 1.0;

    return {
      hp: Math.floor(base.hp * multiplier.hp * enemyBonus),
      atk: Math.floor(base.atk * multiplier.atk * enemyBonus),
      def: Math.floor(base.def * multiplier.def * enemyBonus),
      maxHp: Math.floor(base.hp * multiplier.hp * enemyBonus)
    };
  };

  // calculateDamage 함수 수정 - 적군에게 유리한 확률 조정
  const calculateDamage = (attacker, defender) => {
    const isEnemyAttacking = !attacker.isPlayer;

    // 명중률 조정 (적군 90%, 아군 85%)
    const baseHitChance = isEnemyAttacking ? 0.90 : 0.85;
    const hitChance = Math.random() < baseHitChance;
    if (!hitChance) {
      return { damage: 0, isCritical: false, isEvaded: false, isMissed: true };
    }

    // 회피율 조정 (적군 회피율 약간 증가)
    const jobEvadeBonus = {
      도적: 0.05,
      궁수: 0.03
    };
    const evadeBonus = jobEvadeBonus[defender.job] || 0;
    const baseEvadeChance = 0.10;
    const evadeChance = baseEvadeChance + evadeBonus + (defender.isPlayer ? 0 : 0.02);
    const isEvaded = Math.random() < evadeChance;
    if (isEvaded) {
      return { damage: 0, isCritical: false, isEvaded: true, isMissed: false };
    }

    // 크리티컬 확률 조정 (적군 크리티컬 확률 증가)
    const jobCritBonus = {
      궁수: 0.05,
      도적: 0.03
    };
    const critBonus = jobCritBonus[attacker.job] || 0;
    const baseCritChance = 0.15 + (isEnemyAttacking ? 0.05 : 0);
    const isCritical = Math.random() < (baseCritChance + critBonus);

    // 기본 데미지 계산
    let damage = Math.max(1, attacker.atk - defender.def);
    const variation = 0.2;
    const randomFactor = 1 + (Math.random() * variation * 2 - variation);
    damage = Math.floor(damage * randomFactor);

    // 크리티컬 데미지 (적군은 더 높은 크리티컬 데미지)
    if (isCritical) {
      const baseCritMultiplier = isEnemyAttacking ? 1.7 : 1.5;
      const critMultiplier = baseCritMultiplier + (Math.random() * 0.5);
      damage = Math.floor(damage * critMultiplier);
    }

    // 직업별 특수 효과 (적군 버전은 더 강화)
    if (attacker.job === '') {
      const warriorChance = isEnemyAttacking ? 0.15 : 0.10;
      if (Math.random() < warriorChance) {
        const bonusMultiplier = isEnemyAttacking ? 1.4 : 1.3;
        damage = Math.floor(damage * bonusMultiplier);
      }
    } else if (attacker.job === '마법사') {
      const baseDefIgnore = isEnemyAttacking ? 0.3 : 0.2;
      const defIgnore = baseDefIgnore + (Math.random() * 0.3);
      damage += Math.floor(defender.def * defIgnore);
    }

    return { damage, isCritical, isEvaded: false, isMissed: false };
  };

  // createLogMessage 함수 수정
  const createLogMessage = (attacker, target, damageInfo, isPlayerAttack) => {
    const attackerTeam = isPlayerAttack ? '아군' : '적군';
    const baseMessage = `[${attackerTeam}] ${attacker.job}(${attacker.grade}) ➔ ${target.job}(${target.grade}): `;

    if (damageInfo.isMissed) {
      return {
        text: baseMessage + "빗나감!",
        type: 'missed'
      };
    }

    if (damageInfo.isEvaded) {
      return {
        text: baseMessage + "회피!",
        type: isPlayerAttack ? 'evaded-by-enemy' : 'evaded-by-player'
      };
    }

    let message = baseMessage;
    if (damageInfo.isCritical) {
      message += '치명타! ';
    }
    message += `${damageInfo.damage}데미지`;
    message += ` (HP: ${target.hp}/${target.maxHp})`;

    return {
      text: message,
      type: isPlayerAttack ? 
        (damageInfo.isCritical ? 'critical-dealt' : 'damage-dealt') : 
        (damageInfo.isCritical ? 'critical-taken' : 'damage-taken')
    };
  };

  // startBattle 함수 수정 - 최대 턴 수 관련 부분
  const startBattle = async () => {
    setIsBattling(true);
    setBattleLog([{ text: '전투 시작!', type: 'battle-start' }]);

    const playerCards = playerParty.map(card => ({
      ...card,
      ...getCardStats(card, false),
      isPlayer: true
    }));

    const enemyCards = enemyParty.map(card => ({
      ...card,
      ...getCardStats(card, true), // 적군 스탯 보너스 적용
      isPlayer: false
    }));

    const MAX_TURNS = 20;
    let turn = 1;

    const updateBattleLog = (newEntry) => {
      setBattleLog(prev => [...prev, newEntry]);
    };

    while (playerCards.some(card => card.hp > 0) && 
           enemyCards.some(card => card.hp > 0) && 
           turn <= MAX_TURNS) {
      await new Promise(resolve => setTimeout(resolve, 1000));

      updateBattleLog({ 
        text: `${turn}턴 시작!`, 
        type: 'turn-start' 
      });

      // 모든 생존 캐릭터를 하나의 배열로 합치고 섞기
      const allLivingCards = [
        ...playerCards.filter(card => card.hp > 0).map(card => ({ ...card, isPlayerCard: true })),
        ...enemyCards.filter(card => card.hp > 0).map(card => ({ ...card, isPlayerCard: false }))
      ];
      
      // Fisher-Yates 셔플 알고리즘
      for (let i = allLivingCards.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [allLivingCards[i], allLivingCards[j]] = [allLivingCards[j], allLivingCards[i]];
      }

      // 섞인 순서대로 공격 실행
      for (const attacker of allLivingCards) {
        // 공격 대상 선택 (아군이면 적군에서, 적군이면 아군에서 랜덤 선택)
        const possibleTargets = attacker.isPlayerCard ? 
          enemyCards.filter(card => card.hp > 0) :
          playerCards.filter(card => card.hp > 0);

        if (possibleTargets.length > 0) {
          const target = possibleTargets[Math.floor(Math.random() * possibleTargets.length)];
          
          setActiveAttacker({ 
            card: attacker, 
            isPlayer: attacker.isPlayerCard 
          });
          setActiveTarget({ 
            card: target, 
            isPlayer: !attacker.isPlayerCard 
          });

          const damageInfo = calculateDamage(attacker, target);
          target.hp = Math.max(0, target.hp - damageInfo.damage);

          updateBattleLog(createLogMessage(attacker, target, damageInfo, attacker.isPlayerCard));

          if (target.hp <= 0) {
            updateBattleLog({
              text: `[${attacker.isPlayerCard ? '적군' : '아군'}] ${target.job}(${target.grade})가 쓰러졌습니다!`,
              type: 'card-death'
            });
          }

          await new Promise(resolve => setTimeout(resolve, 800));
          setActiveAttacker(null);
          setActiveTarget(null);
          await new Promise(resolve => setTimeout(resolve, 200));
        }
      }

      if (turn === MAX_TURNS) {
        updateBattleLog({ 
          text: `${MAX_TURNS}턴이 경과했습니다. 시간 초과로 패배!`, 
          type: 'time-over' 
        });
        break;
      }

      setCurrentTurn(turn);
      turn++;
    }

    const battleResult = {
      playerWon: turn <= MAX_TURNS && playerCards.some(card => card.hp > 0),
      deadCards: playerCards
        .map((card, index) => ({ ...card, originalIndex: index }))
        .filter(card => card.hp <= 0),
      timeOver: turn > MAX_TURNS
    };

    updateBattleLog({ 
      text: battleResult.timeOver ? '시간 초과로 패배!' : 
            battleResult.playerWon ? '플레이어의 승리!' : '패배!',
      type: battleResult.timeOver ? 'time-over' : 
            battleResult.playerWon ? 'victory' : 'defeat'
    });

    setIsBattling(false);
    onBattleEnd && onBattleEnd(battleResult);
  };

  return (
    <div className="battle-container">
      <div className="battle-field">
        <div className="enemy-area">
          {enemyParty.map((card, index) => (
            <Card 
              key={`enemy-${index}`} 
              {...card}
              isActive={activeAttacker?.card === card || activeTarget?.card === card}
              isAttacker={activeAttacker?.card === card}
              isTarget={activeTarget?.card === card}
            />
          ))}
        </div>
        <div className="player-area">
          {playerParty.map((card, index) => (
            <Card 
              key={`player-${index}`} 
              {...card}
              isActive={activeAttacker?.card === card || activeTarget?.card === card}
              isAttacker={activeAttacker?.card === card}
              isTarget={activeTarget?.card === card}
            />
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

      <div className="battle-log" ref={logRef}>
        {battleLog.map((log, index) => (
          <div key={index} className={`log-entry ${log.type}`}>
            {log.text}
          </div>
        ))}
      </div>
    </div>
  );
}

// =========================================
// 메인 App 컴포넌트
// =========================================
function App() {
  // =========================================
  // 상태 관리
  // =========================================
  const [my, setMy] = useState([]);
  const [party, setParty] = useState([
    { job: '전사', grade: 'SSR', isFlipped: false },
    { job: '마법사', grade: 'SR', isFlipped: false },
    { job: '궁수', grade: 'S', isFlipped: false },
    { job: '전사', grade: 'R', isFlipped: false },
    { job: '궁수', grade: 'H', isFlipped: false }
  ]);
  
  // 적 파티 상태 추가 - 랜덤하게 생성
  const [enemyParty] = useState(() => {
    return Array(5).fill(null).map(() => ({
      job: jobs[dice(0, 4)],
      grade: grade[getLuck()],
      isFlipped: false
    }));
  });
  // getMyCardsApi 함수 수정
  function getMyCardsApi() {
    axios.get('http://localhost:8080/spring/card/getMyCards')			
      .then(response => {		
        // 서버로부터 받은 데이터를 카 형식에 맞게 변환
        const cards = response.data.map(cardData => ({
          job: cardData.job || jobs[dice(0, 4)], // 서버 데이터에 job이 없으면 랜덤 생성
          grade: cardData.grade || grade[getLuck()], // 서버 데이터에 grade가 없으면 랜덤 생성
          isFlipped: false,
          id: cardData.id // 서버에서 제공하는 고유 ID가 있다면 저장
        }));
        
        // 상태 업데이트
        setMy(prevCards => [...prevCards, ...cards]);
      })		
      .catch(error => {		
        console.error('카드 정보 가져오기 실패:', error);
        alert('카드 정보를 가져오는데 실패했습니다.');
      });		
  }

  // useEffect를 사용하여 컴포넌트 마운트 시 카드 정보 가져오기
  useEffect(() => {
    getMyCardsApi();
  }, []); // 빈 배열을 전달하여 컴포넌트 마운트 시 한 번만 실행

  const [isGachaAnimating, setIsGachaAnimating] = useState(false);

  // =========================================
  // 드래그 앤 드롭 핸들러
  // =========================================
  const handleDragStart = (e, sourceType, index) => {
    e.dataTransfer.setData('text/plain', JSON.stringify({ type: sourceType, index }));
  };

  const handleDrop = (e, targetType, targetIndex = null) => {
    e.preventDefault();
    e.stopPropagation();
    
    try {
      const { type: sourceType, index: sourceIndex } = JSON.parse(e.dataTransfer.getData('text/plain'));
      
      // 파티에서 보유 카드함으로 드래그할 때
      if (sourceType === 'party' && targetType === 'my') {
        const movedCard = party[sourceIndex];
        // 파티에서 제거
        setParty(prev => prev.filter((_, idx) => idx !== sourceIndex));
        // 보유 카드함에 추가
        setMy(prev => [...prev, movedCard]);
        return;
      }

      const sourceArray = sourceType === 'party' ? party : my;
      const targetArray = targetType === 'party' ? party : my;
      const setSourceArray = sourceType === 'party' ? setParty : setMy;
      const setTargetArray = targetType === 'party' ? setParty : setMy;

      // 파티 최대 인원 체크
      if (targetType === 'party' && targetArray.length >= 5 && sourceType !== 'party') {
        alert('파티 인원이 최대입니다.');
        return;
      }

      // 드래그한 카드
      const draggedCard = { ...sourceArray[sourceIndex] };

      if (targetIndex !== null) {
        // 카드 간 교체
        if (sourceType === targetType) {
          // 같은 영역 내 이동
          const newArray = [...sourceArray];
          [newArray[sourceIndex], newArray[targetIndex]] = [newArray[targetIndex], newArray[sourceIndex]];
          setSourceArray(newArray);
          
          // 파티 영역 내 이동일 경우 DB 업데이트
          if (targetType === 'party') {
            sendPartyToServer(newArray);
          }
        } else {
          // 다른 영역 간 이동
          const newSourceArray = [...sourceArray];
          const newTargetArray = [...targetArray];
          
          if (targetType === 'party') {
            // 보유 카드에서 파티로 이동
            if (newTargetArray[targetIndex]) {
              newSourceArray.push(newTargetArray[targetIndex]);
            }
            newTargetArray[targetIndex] = draggedCard;
            newSourceArray.splice(sourceIndex, 1);
            
            setSourceArray(newSourceArray);
            setTargetArray(newTargetArray);
            
            // 파티 정보 DB 업데이트
            sendPartyToServer(newTargetArray);
          } else {
            // 파티에서 보유 카드로 이동
            newSourceArray[sourceIndex] = newTargetArray[targetIndex];
            newTargetArray[targetIndex] = draggedCard;
            
            setSourceArray(newSourceArray);
            setTargetArray(newTargetArray);
          }
        }
      } else {
        // 영역에 드롭
        if (targetType === 'party' && targetArray.length < 5) {
          const newSourceArray = [...sourceArray];
          newSourceArray.splice(sourceIndex, 1);
          const newTargetArray = [...targetArray, draggedCard];
          
          setSourceArray(newSourceArray);
          setTargetArray(newTargetArray);
          
          // 파티 정보 DB 업데이트
          sendPartyToServer(newTargetArray);
        } else if (targetType === 'my') {
          const newSourceArray = [...sourceArray];
          newSourceArray.splice(sourceIndex, 1);
          setSourceArray(newSourceArray);
          setTargetArray(prev => [...prev, draggedCard]);
        }
      }
    } catch (error) {
      console.error('드래그 앤 드롭 처리 오류:', error);
    }
  };

  // 파티 정보를 서버로 전송하는 함수
  const sendPartyToServer = (partyArray) => {
    // 현재 파티에 있는 모든 카드들의 정보를 전송
    partyArray.forEach((card, index) => {
      const cardData = {
        job: card.job,        // 각 카드의 실제 직업
        grade: card.grade,    // 각 카드의 실제 등급
        partyNumber: 1,       // 파티 번호
        position: index + 1   // 파티 내 위치 (1부터 시작)
      };

      console.log(`전송되는 카드 정보:`, cardData); // 디버깅용 로그

      axios.post('http://localhost:8080/spring/card/partyAdd', cardData)
        .then(response => {
          console.log(`카드 ${index + 1} 추가 성공:`, response.data);
        })
        .catch(error => {
          console.error(`카드 ${index + 1} 추가 실패:`, error);
          alert('파티원 추가에 실패했습니다.');
        });
    });
  };

  // =========================================
  // 가챠 시스템
  // =========================================
  const handleGacha = async (useApi = false) => {
    setIsGachaAnimating(true);
    
    try {
      let newCard;
      if (useApi) {
        const response = await axios.get('http://localhost:8080/spring/api/gacha');
        newCard = response.data;
      } else {
        newCard = {
          job: jobs[dice(0, 4)],
          grade: grade[getLuck()]
        };
      }
      
      newCard.isFlipped = true;
      setMy(prev => [...prev, newCard]);
      
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
  };

  // =========================================
  // 카드 렌더링 헬퍼 함수
  // =========================================

  // 카드 렌더링 함수
  const renderCards = (cards, type) => {
    return cards.map((card, index) => (
      <Card 
        key={`${type}-${index}`}
        {...card}
        onFlip={() => {
          const setFunction = type === 'party' ? setParty : setMy;
          setFunction(prev => prev.map((c, idx) => 
            idx === index ? {...c, isFlipped: !c.isFlipped} : c
          ));
        }}
        draggable={true}
        onDragStart={(e) => handleDragStart(e, type, index)}
        onDragOver={(e) => e.preventDefault()}
        onDrop={(e) => handleDrop(e, type, index)}
        index={index}
      />
    ));
  };

  // 등급별 정렬 순서 정의
  const gradeOrder = {
    'SSR': 0,
    'SR': 1,
    'S': 2,
    'R': 3,
    'H': 4,
    'N': 5
  };

  // 카드 그룹화 및 정렬 함수
  const groupCards = (cards) => {
    // 1. 카드 그룹화
    const grouped = cards.reduce((acc, card) => {
      const key = `${card.job}-${card.grade}`;
      if (!acc[key]) {
        acc[key] = { ...card, count: 1 };
      } else {
        acc[key].count++;
      }
      return acc;
    }, {});

    // 2. 그룹화된 카드를 배열로 변환하고 정렬
    return Object.entries(grouped)
      .map(([key, card]) => card)
      .sort((a, b) => {
        // 먼저 등급으로 정렬
        const gradeCompare = gradeOrder[a.grade] - gradeOrder[b.grade];
        
        // 등급이 같으면 직업명으로 정렬
        if (gradeCompare === 0) {
          return a.job.localeCompare(b.job);
        }
        
        return gradeCompare;
      });
  };

  // cat 함수도 동일한 형식으로 수정
  const cat = (index, job, grade) => {
    console.log(`보유카드 번호: ${index}`);
    alert(`보유카드 번호: ${index} 직업:${job} 등급:${grade}`);
    
    // 파티 최대 인원 체크
    if (party.length >= 5) {
      alert('파티 인원이 최대입니다.');
      return;
    }

    const cardData = {
      job: job,              // 선택된 카드의 실제 직업
      grade: grade,          // 선택된 카드의 실제 등급
      partyNumber: 1,        // 파티 번호
      position: party.length + 1  // 현재 파티 크기 + 1 위치에 추가
    };

    // DB에 파티원 추가 요청
    axios.post('http://localhost:8080/spring/card/partyAdd', cardData)
      .then(response => {
        console.log('파티원 추가 성공:', response.data);
        // DB 저장 성공 후 로컬 상태 업데이트
        setParty(prev => [...prev, { job: job, grade: grade, isFlipped: false }]);
      })
      .catch(error => {
        console.error('파티원 추가 실패:', error);
        alert('파티원 추가에 실패했습니다.');
      });
  };

  // renderGroupedCards 함수 수정
  const renderGroupedCards = () => {
    const sortedGroupedCards = groupCards(my);
    return sortedGroupedCards.map((card, index) => {
      const originalIndex = my.findIndex(c => c.job === card.job && c.grade === card.grade);
      
      return (
        <div key={`grouped-${card.job}-${card.grade}`} className="grouped-card">
          <Card 
            job={card.job} 
            grade={card.grade}
            isFlipped={card.isFlipped}
            onFlip={() => {
              setMy(prev => prev.map((c, idx) => 
                idx === originalIndex ? {...c, isFlipped: !c.isFlipped} : c
              ));
            }}
            draggable={true}
            onDragStart={(e) => handleDragStart(e, 'my', originalIndex)}
            onDragOver={(e) => e.preventDefault()}
            onDrop={(e) => handleDrop(e, 'my', originalIndex)}
            index={index}
            onClick={() => cat(originalIndex, card.job, card.grade)}
          />
          {card.count > 1 && (
            <div className="card-count">
              x{card.count}
            </div>
          )}
        </div>
      );
    });
  };

  // 전투 결과 처리 함수 수정
  const handleBattleEnd = (battleResult) => {
    if (battleResult.deadCards.length > 0) {
      // 파티의 원래 상태를 유지하면서 죽은 카드들의 HP만 리셋
      setParty(prev => prev.map(card => {
        const deadCard = battleResult.deadCards.find((dead, index) => 
          dead.job === card.job && dead.grade === card.grade
        );
        
        if (deadCard) {
          // 죽은 카드를 리셋하여 파티에 유지
          return {
            ...card,
            isFlipped: false
          };
        }
        return card;
      }));
    }
  };

  // =========================================
  // 렌더링
  // =========================================
  return (
    <div className="game-container">
      <div className="gacha-area">
        <h2>가챠</h2>
        <button 
          onClick={() => handleGacha(false)} 
          disabled={isGachaAnimating}
          className={`gacha-button ${isGachaAnimating ? 'animating' : ''}`}
        >
          로컬 가챠
        </button>
        <button 
          onClick={() => handleGacha(true)} 
          disabled={isGachaAnimating}
          className={`gacha-button ${isGachaAnimating ? 'animating' : ''}`}
        >
          API 가챠
        </button>
      </div>

      <div className="battle-area">
        <h2>파티 1</h2>
        <CardArea 
          type="party" 
          handleDrop={handleDrop}
        >
          {renderCards(party, 'party')}
        </CardArea>
      </div>

      <div className="inventory">
        <h2>보유 카드</h2>
        <CardArea 
          type="my"
          handleDrop={handleDrop}  // handleDrop 함수 전달
        >
          {renderGroupedCards()}
        </CardArea>
      </div>

      <div className="battle-section">
        <h2>전투</h2>
        <Battle 
          playerParty={party} 
          enemyParty={enemyParty} 
          onBattleEnd={handleBattleEnd}  // 전투 결과 처리 함수 전달
        />
      </div>
    </div>
  );
}

export default App;
