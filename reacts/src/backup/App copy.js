
import React, { useState } from 'react';
import './App.css';

var jobs = ["전사","마법사","궁수","도적","사제"];
// 등급별 확률 설정 가챠 시스템
const gradeChances = {
  SSR: 3,    // 3%
  SR: 12,    // 12%
  S: 20,     // 20%
  R: 25,     // 25%
  H: 20,     // 20%
  N: 20      // 20%
};

function dice(s,e){
  return Math.floor(Math.random()*(e-s+1))+s;
}

// 확률 기반 등급 선택 함수
function getRandomGrade() {
  const random = Math.random() * 100;
  let sum = 0;

  for (const grade in gradeChances) {
    sum += gradeChances[grade];
    if (random <= sum) {
      return grade;
    }
  }
  return 'N';
}
// 카드들이 마우스의 움직임 따라 움직임
function Card({ job, grade, count, isInParty, dragStart, dragEnd }) {
  const handleMouseMove = (e) => {
    if (!dragStart) return; // 드래그 불가능한 카드는 회전 효과 제외
    const card = e.currentTarget;
    const rect = card.getBoundingClientRect();
    const x = e.clientX - rect.left;
    const y = e.clientY - rect.top;
    const rotateY = -1/5 * x + 20;
    const rotateX = 4/30 * y - 20;
    
    card.style.transform = `perspective(350px) rotateX(${rotateX}deg) rotateY(${rotateY}deg)`;
  };

  const handleMouseLeave = (e) => {
    if (!dragStart) return;
    e.currentTarget.style.transform = 'perspective(350px) rotateY(0deg) rotateX(0deg)';
  };
// 마우스드롭으로 카드 위치 옮기기
  return (
    <div 
      className={`card ${job} ${grade} ${isInParty ? 'in-party' : ''}`}
      onMouseMove={handleMouseMove}
      onMouseLeave={handleMouseLeave}
      draggable={!!dragStart}
      onDragStart={dragStart}
      onDragEnd={dragEnd}
    >
      <span>{job} - {grade}</span>
      {count > 1 && <div className="card-count">×{count}</div>}
      {isInParty && <div className="party-badge">파티</div>}
    </div>
  );
}

function CardArea({ children, onDragOver, onDrop, areaType }) {
  return (
    <div 
      id='card_area'
      className={`drop-area ${areaType}`}
      onDragOver={(e) => {
        e.preventDefault();
        e.currentTarget.classList.add('drag-over');
      }}
      onDragLeave={(e) => {
        e.currentTarget.classList.remove('drag-over');
      }}
      onDrop={(e) => {
        e.currentTarget.classList.remove('drag-over');
        onDrop && onDrop(e);
      }}
    >
      {children}
    </div>
  );
}

function App() {
  var [my, setMy] = useState([]);
  const [party, setParty] = useState([]);
  const [draggedCard, setDraggedCard] = useState(null);
  
  // 적 파티 생성 (기존 코드 유지) 파티와 겹치지 않게끔 랜덤적이 생성되고 이를 고정
  const [enemyParty] = useState(() => {
    const enemies = [];
    for(let i = 0; i < 5; i++) {
      const j = jobs[dice(0, 4)];
      const g = getRandomGrade();
      enemies.push({ job: j, grade: g });
    }
    return enemies;
  });

  // 카드 그룹화 함수 중첩으로 표시할려면 필요
  const groupCards = (cards) => {
    return cards.reduce((acc, card) => {
      const key = `${card.job}-${card.grade}`;
      if (!acc[key]) {
        acc[key] = { ...card, count: 1 };
      } else {
        acc[key].count++;
      }
      return acc;
    }, {});
  };

  // 드래그 시작 영역을 지정해서 드로그된 카드들 위치를 고정
  const handleDragStart = (card, sourceArea) => (e) => {
    const dragInfo = {
      card: { ...card },
      sourceArea: sourceArea
    };
    setDraggedCard(dragInfo);
    e.currentTarget.classList.add('dragging');
  };

  // 드래그 종료
  const handleDragEnd = (e) => {
    e.currentTarget.classList.remove('dragging');
  };

  // 파티 영역에 드롭
  const handlePartyDrop = (e) => {
    e.preventDefault();
    if (!draggedCard || party.length >= 5) return;

    if (draggedCard.sourceArea === 'my') {
      // 보유에서 파티로 이동
      const newCard = { ...draggedCard.card };
      delete newCard.count; // count 속성 제거
      setParty([...party, newCard]);
      
      // 보유 카드 수 감소
      const updatedMy = [...my];
      const cardIndex = updatedMy.findIndex(card => 
        card.job === draggedCard.card.job && card.grade === draggedCard.card.grade
      );
      if (cardIndex !== -1) {
        updatedMy.splice(cardIndex, 1);
        setMy(updatedMy);
      }
    }
    setDraggedCard(null);
  };

  // 보유 영역에 드롭
  const handleMyDrop = (e) => {
    e.preventDefault();
    if (!draggedCard) return;

    if (draggedCard.sourceArea === 'party') {
      // 파티에서 보유로 이동
      const newCard = { ...draggedCard.card };
      delete newCard.count; // count 속성 제거
      setMy([...my, newCard]);
      
      // 파티에서 카드 제거
      const updatedParty = party.filter((card, index) => 
        !(card.job === draggedCard.card.job && card.grade === draggedCard.card.grade)
      );
      setParty(updatedParty);
    }
    setDraggedCard(null);
  };

  // 1회 뽑기
  function gacha(){
    var j = jobs[dice(0,4)];
    var g = getRandomGrade();
    console.log(`뽑은 카드: ${j} - ${g}`);
    setMy([...my, { job: j, grade: g }]);
  }

  // 10연차 뽑기 추가
  function gacha10() {
    const newCards = [];
    let hasSSR = false;

    for (let i = 0; i < 10; i++) {
      const j = jobs[dice(0, 4)];
      let g;

      if (i === 9 && !hasSSR) {
        // 10번째 뽑기에서 SSR이 없었다면 SR 이상 확정
        g = Math.random() < 0.2 ? 'SSR' : 'SR';
      } else {
        g = getRandomGrade();
        if (g === 'SSR') hasSSR = true;
      }

      newCards.push({ job: j, grade: g });
    }
    
    console.log('10연차 결과:', newCards);
    setMy([...my, ...newCards]);
  }

  return (
    <>
      <h2>파티 ({party.length}/5)</h2>
      <CardArea onDrop={handlePartyDrop} areaType="party">
        {party.map((character, index) => (
          <Card 
            key={index} 
            job={character.job} 
            grade={character.grade}
            isInParty={true}
            dragStart={handleDragStart(character, 'party')}
            dragEnd={handleDragEnd}
          />
        ))}
      </CardArea>
      <h2>보유</h2>
      <button onClick={gacha}>카드 1회 뽑기</button>
      <button onClick={gacha10}>카드 10연차 뽑기</button>
      <CardArea onDrop={handleMyDrop} areaType="my">
        {Object.values(groupCards(my)).map((character, index) => (
          <Card 
            key={index} 
            job={character.job} 
            grade={character.grade}
            count={character.count}
            isInParty={false}
            dragStart={handleDragStart(character, 'my')}
            dragEnd={handleDragEnd}
          />
        ))}
      </CardArea>
      <h2>적</h2>
      <CardArea>
        {Object.values(groupCards(enemyParty)).map((character, index) => (
          <Card 
            key={index} 
            job={character.job} 
            grade={character.grade}
            count={character.count}
          />
        ))}
      </CardArea>
    </>
  );
}

export default App;
