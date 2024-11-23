import axios from 'axios';
import React, { useState, useEffect, useCallback, useRef } from 'react';
import './App.css';
import Clock from './Clock.js';
import Stars from './Stars.js';

axios.defaults.withCredentials = true;
axios.defaults.headers.common['Accept'] = 'application/json';
axios.defaults.headers.common['Content-Type'] = 'application/json';

function Card({ job, grade, xxx, draggable, onDragStart }) {
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
      className={`card ${job} ${grade}`} 
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
      {job} - {grade}
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
    onDrop && onDrop(cardData, pjId);
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
  var [dice,setDice] = useState(0);
  var [gold,setGold] = useState(0);  
  var [my,setMy] = useState([]);
  const [pj,setPj] = useState([]);
  const [pjList, setPjList] = useState([]);

  var getMyWealth = useCallback(() => {
    axios.get('http://localhost:8080/card/pay/getWealth', {
      withCredentials: true,
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      }
    })			
    .then(response => {		
      console.log(response.data);  
      setGold(response.data.gold);
      setDice(response.data.dice);
    })		
    .catch(error => {		
      console.error('에러:', error);	
    });		
  }, []);

  var getMyCardsApi = useCallback(() => {
    axios.get('http://localhost:8080/card/card/getMyCards')			
    .then(response => {		
      console.log(response.data);
      setMy(response.data);
    })		
    .catch(error => {		
      console.error('에러:', error);
    });		
  }, []);

  var getPjApi = useCallback(() => {
    axios.get('http://localhost:8080/card/card/getPjMember?no=1')			
    .then(response => {		
      console.log(response.data);
      setPj(response.data);
    })		
    .catch(error => {		
      console.error('에러:', error);
    });		
  }, []);

  function getPjListApi() {
    axios.get('http://localhost:8080/card/pj/getPjList')			
      .then(response => setPjList(response.data))		
      .catch(error => console.error('에러:', error));
  }

  useEffect(() => {	
    console.log('컴포넌트가 생성됨(마운트됨)');
    getMyWealth();
    getMyCardsApi();
    getPjApi();
    getPjListApi();
    return () => {
      console.log('컴포넌트가 언마운트됨');
    };
  }, [getMyWealth, getMyCardsApi, getPjApi]);

  function clearPj(){
    setPj([]);
    clearPjApi();
  }

  function cat(index,job,grade,no){
    if (pj.length >= 5) {
      alert('참여 인원은 최대 5명까지만 추가할 수 있습니다.');
      return; // 추가 중단
    }    
    console.log(`보유카드 번호: ${index} 고유no: ${no}`);
    // var d = {job: job,grade: grade};
    var d = {id:'cat',no: no};
    // setPj([...pj,d]);
    pjMemberAdd(d);
  }

  function pjMemberAdd(d){
    axios.post('http://localhost:8080/card/card/pjMemberAdd',d)			
    .then(response => {		
      console.log(response.data);  // 서버로부터 받은 데이터 출력	
      getPjApi();
      getMyCardsApi();
    })		
    .catch(error => {		
      console.error('Error fetching data:', error);  // 에러 처리	
    });		
  }

  function gachaApi(){
    axios.get('http://localhost:8080/card/api/gacha')			
    .then(response => {		
      console.log(response.data);  // 서버로부터 받은 데이터 출력	
          // 기존의 `my` 배열을 복사하고, 새 객체를 추가한 새로운 배열로 업데이트
      setMy([...my, response.data]);
      getMyWealth();
    })		
    .catch(error => {		
      console.error('Error fetching data:', error);  // 에러 처리	
    });		
  }
  function clearPjApi(){
    axios.get('http://localhost:8080/card/card/clearPjMember')			
    .then(response => {		
      console.log(response.data);  // 서버로부터 받은 데이터 출력	
      getMyCardsApi();
    })		
    .catch(error => {		
      console.error('Error fetching data:', error);  // 에러 처리	
    });		
  }

  function buyGold() {
    // 새 창에서 결제 페이지 열기
    const popup = window.open('http://localhost:8080/card/pay/buy', '_blank', 'width=800,height=600');
    
    // 팝업 창이 닫힐 때 데이터 갱신
    const checkClosed = setInterval(() => {
      if (popup.closed) {
        clearInterval(checkClosed);
        // 약간의 지연 후 데이터 갱신 (서버 처리 시간 고려)
        setTimeout(() => {
          getMyWealth();
        }, 1000);
      }
    }, 500);
  }

  function buyDice(){
    axios.get('http://localhost:8080/card/pay/buyDice', {
        withCredentials: true,
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }
    })			
    .then(response => {		
        console.log(response.data);
        getMyWealth();  // 성공 후 잔액 갱신
    })		
    .catch(error => {		
        console.error('Error:', error);
        alert('주사위 구매에 실패했습니다.');	
    });		
  }  

  function handleCardDrop(cardData, targetPjId) {
    if (!targetPjId) return; // 내 카드 영역으로는 드롭 불가
    
    if (pj.length >= 5) {
      alert('참여 인원은 최대 5명까지만 추가할 수 있습니다.');
      return;
    }

    const d = { id: 'cat', no: cardData.no };
    pjMemberAdd(d);
  }

  return (
    <>
      <Clock />
      <fieldset>
        <legend>
          {pjList && pjList.length > 0 && pjList[0] ? 
            <>
              {pjList[0].no} {pjList[0].name} <Stars amount={pjList[0].level} /> {pjList[0].gold}💰 {pjList[0].content}
            </>
          : '프로젝트 정보 없음'}
          &nbsp;&nbsp;<button onClick={clearPj}>참여인원 비우기</button>
        </legend>
        <CardArea pjId={3} onDrop={handleCardDrop}>
          {pj.map((character, index) => (
            <Card 
              key={index} 
              job={character.job} 
              grade={character.grade}
              draggable={false}
            />
          ))}
        </CardArea>
      </fieldset>
      <fieldset>
        <legend>
          {pjList && pjList.length > 1 && pjList[1] ? 
            <>
              {pjList[1].no} {pjList[1].name} <Stars amount={pjList[1].level} /> {pjList[1].gold}💰 {pjList[1].content}
            </>
          : '프로젝트 정보 없음'}
          &nbsp;&nbsp;<button onClick={clearPj}>참여인원 비우기</button>
        </legend>
        <CardArea pjId={2}>
          {pj.map((character, index) => (
            <Card key={index} job={character.job} grade={character.grade} />
          ))}
        </CardArea>
      </fieldset>
      <fieldset>
        <legend>
          {pjList && pjList.length > 2 && pjList[2] ? 
            <>
              {pjList[2].no} {pjList[2].name} <Stars amount={pjList[2].level} /> {pjList[2].gold}💰 {pjList[2].content}
            </>
          : '프로젝트 정보 없음'}
          &nbsp;&nbsp;<button onClick={clearPj}>참여인원 비우기</button>
        </legend>
        <CardArea pjId={1}>
          {pj.map((character, index) => (
            <Card key={index} job={character.job} grade={character.grade} />
          ))}
        </CardArea>
      </fieldset>
      <CardArea>
        {my.map((character, index) => (
          <Card 
            key={index} 
            job={character.job} 
            grade={character.grade}
            xxx={() => cat(index, character.no)}
            draggable={true}
            onDragStart={(e) => {
              e.dataTransfer.setData('card', JSON.stringify({
                index: index,
                no: character.no,
                job: character.job,
                grade: character.grade
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