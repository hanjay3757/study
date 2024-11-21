import axios from 'axios';
import React, { useState, useEffect, useCallback, useRef } from 'react';
import './App.css';
import Clock from './Clock.js';
import Stars from './Stars.js';

axios.defaults.withCredentials = true;
axios.defaults.headers.common['Accept'] = 'application/json';
axios.defaults.headers.common['Content-Type'] = 'application/json';

function Card({  no,job, grade, xxx, draggable, onDragStart }) {
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
      className={`card ${job} ${grade} ${no} `} 
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
    var d= {id:'cat',no:cardData.no,deployment:pjId};
    axios.post('http://localhost:8080/card/card/pjMemberAdd', d)			
    .then(() => {		
   
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
  const [pj1, setPj1] = useState([]);
  const [pj3, setPj3] = useState([]);
  const [pjList, setPjList] = useState([]);

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

  const getMyCardsApi = useCallback(() => {
    axios.get('http://localhost:8080/card/card/getMyCards')			
    .then(response => {		
      setMy(response.data);
    })		
    .catch(error => {		
      console.error('에러:', error);
    });		
  }, []);

  const getPjApi = useCallback(() => {
    axios.get('http://localhost:8080/card/card/getPjMember?no=1')			
    .then(response => {		
      setPj1(response.data);
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
    getPjApi();
    getPjListApi();
  }, [getMyWealth, getMyCardsApi, getPjApi, getPjListApi]);

 /*  function pjMemberAdd(d){
    axios.post('http://localhost:8080/card/card/pjMemberAdd', d)			
    .then(() => {		
      getPjApi();
      getMyCardsApi();
    })		
    .catch(error => {		
      console.error('Error:', error);
    });		
  } */

  function gachaApi(){
    axios.get('http://localhost:8080/card/api/gacha')			
    .then(response => {		
      setMy(prev => [...prev, response.data]);
      getMyWealth();
    })		
    .catch(error => {		
      console.error('Error:', error);
    });		
  }

  function clearPjApi(){
    axios.get('http://localhost:8080/card/card/clearPjMember')			
    .then(() => {		
      getMyCardsApi();
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

  function buyDice(){
    axios.get('http://localhost:8080/card/pay/buyDice')			
    .then(() => {		
        getMyWealth();
    })		
    .catch(() => {		
        alert('주사위 구매에 실패했습니다.');	
    });		
  }  
  function handleCard(cardData, targetPjId) {
    if (!targetPjId) return;
    
    const updatedCardData = { ...cardData, deployment: targetPjId };

    if (targetPjId === 1 && pj1.length < 5) {
      setPj1(prev => [...prev, updatedCardData]);
      setMy(prev => prev.filter((_, index) => index !== cardData.index));
      getPjApi();
      getMyCardsApi();
    } else if (targetPjId === 3 && pj3.length < 5) {
      setPj3(prev => [...prev, updatedCardData]);
      setMy(prev => prev.filter((_, index) => index !== cardData.index));
      getPjApi(); 
      getMyCardsApi();
    } else {
      alert('참여 인원은 최대 5명까지만 추가할 수 있습니다.');
    }
  }
//정보 표시임
  return (
    <>
      <Clock />
      <fieldset>
        <legend>
          {pjList?.[0] ? 
            <>
              {pjList[0].no} {pjList[0].name} <Stars amount={pjList[0].level} /> {pjList[0].gold}💰 {pjList[0].content}
            </>
          : '프로젝트 정보 없음'}
          &nbsp;&nbsp;<button onClick={() => { setPj3([]); clearPjApi(); }}>참여인원 비우기1</button>
        </legend>
        <CardArea pjId={3} onDrop={(cardData) => handleCard(cardData, 3)}>
          {pj3.map((character, index) => (
            <Card 
              key={index} 
              job={character.job} 
              no= {character.no}
              grade={character.grade}
              deployment={character.deployment}
              draggable={false}
            />
          ))}
        </CardArea>
      </fieldset>
      <fieldset>
        <legend>
          {pjList?.[1] ? 
            <>
              {pjList[1].no} {pjList[1].name} <Stars amount={pjList[1].level} /> {pjList[1].gold}💰 {pjList[1].content}
            </>
          : '프로젝트 정보 없음'}
          &nbsp;&nbsp;<button onClick={() => { setPj1([]); clearPjApi(); }}>참여인원 비우기2</button>
        </legend>
        <CardArea pjId={1} onDrop={(cardData) => handleCard(cardData, 1)}>
          {pj1.map((character, index) => (
            <Card 
            key={index} 
            job={character.job} 
            no= {character.no}
            grade={character.grade}
            deployment={character.deployment}
            draggable={false}
            />
          ))}
        </CardArea>
      </fieldset>
      <CardArea>
        {my.map((character, index) => (
          <Card 
            key={index} 
            job={character.job} 
            no = {character.no}
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
    </>
  );
}

export default App;