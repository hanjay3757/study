import axios from 'axios';
import React, { useState, useEffect, useCallback, useRef } from 'react';
import './App.css';
import Clock from './Clock.js';

function Card({ job, grade, xxx}) {
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

function CardArea({ children, pjId }) {
  function pjClick(pjId){
    if(pjId > 0){
      alert(pjId);
    }
  }

  return (
    <div id='card_area' onClick={()=>pjClick(pjId)}>
      {children}
    </div>
  );
}

function App() {
  var [dice,setDice] = useState(0);
  var [gold,setGold] = useState(0);  
  function clearPj(){
    setPj([]);
    clearPjApi();
  }

  useEffect(() => {	
    console.log('컴포넌트가 생성됨(마운트됨)');
    getMyWealth();
    getMyCardsApi();
    getPjApi();
    return () => {
      console.log('컴포넌트가 언마운트됨');
    };
  }, []); // []로 비우기	 

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

  var getMyCardsApi = useCallback(() => {
    axios.get('http://localhost:8080/card/card/getMyCards')			
    .then(response => {		
      console.log(response.data);  // 서버로부터 받은 데이터 출력	
      setMy(response.data);
    })		
    .catch(error => {		
      console.error('에러:', error);  // 에러 처리	
    });		
  }, []);

  var getPjApi = useCallback(() => {
    axios.get('http://localhost:8080/card/card/getPjMember?no=1')			
    .then(response => {		
      console.log(response.data);  // 서버로부터 받은 데이터 출력	
      setPj(response.data);
    })		
    .catch(error => {		
      console.error('에러:', error);  // 에러 처리	
    });		
  }, []);

  var getMyWealth = useCallback(() => {
    axios.get('http://localhost:8080/card/shop/getWealth')			
    .then(response => {		
      console.log(response.data);  // 서버로부터 받은 데이터 출력	
      setGold(response.data.gold);
      setDice(response.data.dice);
    })		
    .catch(error => {		
      console.error('에러:', error);  // 에러 처리	
    });		
  }, []);

  function buyGold(){
    axios.get('http://localhost:8080/card/shop/buyGold')			
    .then(response => {		
      console.log(response.data);  // 서버로부터 받은 데이터 출력	
      getMyWealth(); 
    })		
    .catch(error => {		
      console.error('Error fetching data:', error);  // 에러 처리	
    });		
  }

  function buyDice(){
    axios.get('http://localhost:8080/card/shop/buyDice')			
    .then(response => {		
      console.log(response.data);  // 서버로부터 받은 데이터 출력	
      getMyWealth();
    })		
    .catch(error => {		
      console.error('Error fetching data:', error);  // 에러 처리	
    });		
  }  

  var [my,setMy] = useState([]);
  const [pj,setPj] = useState([]);

  return (
    <>
      <Clock />
      <fieldset>
        <legend>pj 1</legend>
        <button onClick={clearPj}>참여인원 비우기</button>
        <CardArea pjId={1}>
          {pj.map((character, index) => (
            <Card key={index} job={character.job} grade={character.grade} />
          ))}
        </CardArea>
      </fieldset>
      <CardArea>
        {my.map((character, index) => (
          <Card key={index} job={character.job} grade={character.grade} 
            xxx={()=>cat(index,character.job,character.grade,character.no)}
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