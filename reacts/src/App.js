import axios from 'axios';
import React, { useState, useEffect, useCallback, useRef } from 'react';
import './App.css';
import Clock from './Clock.js';
import Stars from './Stars.js';

axios.defaults.withCredentials = true;
axios.defaults.headers.common['Accept'] = 'application/json';
axios.defaults.headers.common['Content-Type'] = 'application/json';

function Card({ no, job, grade, xxx, draggable, onDragStart }) {
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

    const rotateX = -((y - centerY) / 10) * 1.5;
    const rotateY = ((x - centerX) / 10) * 1.5;

    setRotation({ x: rotateX, y: rotateY });
  };

  const handleMouseLeave = () => {
    setRotation({ x: 0, y: 0 });
  };

  return (
    <div
      ref={cardRef}
      className={`card ${job} ${grade} ${no}`}
      onClick={xxx}
      onMouseMove={handleMouseMove}
      onMouseLeave={handleMouseLeave}
      draggable={draggable}
      onDragStart={onDragStart}
      style={{
        transform: `
          perspective(1000px)
          rotateX(${rotation.x}deg)
          rotateY(${rotation.y}deg)
          translateZ(20px)
        `,
        transition: 'transform 0.3s ease'
      }}
    >
      {job} - {grade} - {no}
    </div>
  );
}

function CardArea({ children, pjId, onDrop }) {
  function handleDragOver(e) {
    e.preventDefault();
  }

  function handleDrop(e) {
    e.preventDefault();
    const cardData = JSON.parse(e.dataTransfer.getData('card'));
    onDrop && onDrop({ ...cardData, pjId }, pjId);
    var d = { id: 'cat', no: cardData.no, deployment: pjId };
    axios.post('http://localhost:8080/card/card/pjMemberAdd', d)
      .then(() => {
        // 화면 업데이트를 위해 상태를 변경
        onDrop({ ...cardData, pjId });
      })
      .catch(error => {
        console.error('Error:', error);
      });
  }

  return (
    <div
      id='card_area'
      onDragOver={handleDragOver}
      onDrop={handleDrop}
    >
      {children}
    </div>
  );
}

function App() {
  const [dice, setDice] = useState(0);
  const [gold, setGold] = useState(0);
  const [my, setMy] = useState([]);
  const [pjList, setPjList] = useState([]);
  const [pjMembers, setPjMembers] = useState({});

  const getMyWealth = useCallback(() => {
    axios.get('http://localhost:8080/card/pay/getWealth')
      .then(response => {
        setGold(response.data.gold);
        setDice(response.data.dice);
      })
      .catch(error => {
        console.error('에러:', error);
      });
  }, []);

  const getPjMembersApi = useCallback((pjId) => {
    axios.get(`http://localhost:8080/card/card/getPjMember?no=${pjId}`)
      .then(response => {
        setPjMembers(prev => ({ ...prev, [pjId]: response.data }));
      })
      .catch(error => {
        console.error('에러:', error);
      });
  }, []);

  const getMyCardsApi = useCallback(() => {
    axios.get('http://localhost:8080/card/card/getMyCards')
      .then(response => {
        setMy(response.data);
      })
      .catch(error => {
        console.error('에러:', error);
      });
  }, []);

  const getPjListApi = useCallback(() => {
    axios.get('http://localhost:8080/card/card/pj/getPjList')
      .then(response => setPjList(response.data))
      .catch(error => console.error('에러:', error));
  }, []);

  useEffect(() => {
    getMyWealth();
    getMyCardsApi();
    getPjListApi();
  }, [getMyWealth, getMyCardsApi, getPjListApi]);

  useEffect(() => {
    pjList.forEach(pj => {
      getPjMembersApi(pj.no);
    });
  }, [pjList, getPjMembersApi]);

  function gachaApi() {
    axios.get('http://localhost:8080/card/api/gacha')
      .then(response => {
        setMy(prev => [...prev, response.data]);
        getMyWealth();
      })
      .catch(error => {
        console.error('Error:', error);
      });
  }

  function clearPjApi(a) {
    axios.get('http://localhost:8080/card/card/clearPjMember?PjId=' + a)
      .then(() => {
        getMyCardsApi();
        getPjMembersApi(a);
      })
      .catch(error => {
        console.error('Error:', error);
      });
  }

  function buyGold() {
    const popup = window.open('http://localhost:8080/card/pay/buy', '_blank', 'width=800,height=600');

    const checkClosed = setInterval(() => {
      if (popup.closed) {
        clearInterval(checkClosed);
        setTimeout(getMyWealth, 1000);
      }
    }, 500);
  }

  function buyDice() {
    axios.get('http://localhost:8080/card/pay/buyDice')
      .then(() => {
        getMyWealth();
      })
      .catch(() => {
        alert('주사위 구매에 실패했습니다.');
      });
  }

  function handleDrop(cardData, targetPjId) {
    if (!targetPjId) return;

    const updatedCardData = { ...cardData, deployment: targetPjId };

    if (pjMembers[targetPjId]?.length < 5) {
      setPjMembers(prev => ({
        ...prev,
        [targetPjId]: [...(prev[targetPjId] || []), updatedCardData]
      }));
      setMy(prev => prev.filter((_, index) => index !== cardData.index));
      getPjMembersApi(targetPjId);
      getMyCardsApi();
    } else {
      alert('참여 인원은 최대 5명까지만 추가할 수 있습니다.');
    }
  }

  function createPj() {
    axios.post('http://localhost:8080/card/card/pj/create')
      .then(() => {
        getPjListApi();
      })
      .catch(error => {
        console.error('Error:', error);
      });
  }

  return (
    <>
      <Clock />
      {pjList.map((pj, index) => (
        <fieldset key={index}>
          <legend>
            {pj.no} {pj.name} <Stars amount={pj.level} /> {pj.gold}💰 {pj.content}
            &nbsp;&nbsp;<button onClick={() => { setPjMembers(prev => ({ ...prev, [pj.no]: [] })); clearPjApi(pj.no); }}>참여인원 비우기</button>
          </legend>
          <CardArea pjId={pj.no} onDrop={(cardData) => handleDrop(cardData, pj.no)}>
            {pjMembers[pj.no]?.map((character, index) => (
              <Card
                key={index}
                job={character.job}
                no={character.no}
                grade={character.grade}
                deployment={character.deployment}
                draggable={false}
              />
            ))}
          </CardArea>
        </fieldset>
      ))}
      <CardArea>
        {my.map((character, index) => (
          <Card
            key={index}
            job={character.job}
            no={character.no}
            grade={character.grade}
            deployment={character.deployment}
            draggable={true}
            onDragStart={(e) => {
              e.dataTransfer.setData('card', JSON.stringify({
                index: index,
                no: character.no,
                job: character.job,
                grade: character.grade,
                deployment: character.deployment
              }));
            }}
          />
        ))}
      </CardArea>
      <fieldset>
        <legend>내 카드</legend>
        <button onClick={gachaApi}>카드 1뽑 by api</button>
      </fieldset>
      <fieldset>
        <legend>상점</legend>
        <p>{dice}🎲</p>
        <p>{gold}💰</p>
        <button onClick={buyDice}>주사위상자 구매</button>
        <button onClick={buyGold}>골드 충전(만원)</button>
      </fieldset>
      <button onClick={createPj}>프로젝트 생성</button>
    </>
  );
}

export default App;