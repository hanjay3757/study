function App() {
  var [dice, setDice] = useState(0);
  var [gold, setGold] = useState(0);  
  var [my, setMy] = useState([]);
  const [pj1, setPj1] = useState([]); // pjId 1에 대한 상태
  const [pj3, setPj3] = useState([]); // pjId 3에 대한 상태
  const [pjList, setPjList] = useState([]);

  // ... 기존 코드 ...

  function handleCardDrop(cardData, targetPjId) {
    if (!targetPjId) return; // 내 카드 영역으로는 드롭 불가
    
    if (targetPjId === 1) {
      if (pj1.length >= 5) {
        alert('참여 인원은 최대 5명까지만 추가할 수 있습니다.');
        return;
      }
      setPj1([...pj1, cardData]);
    } else if (targetPjId === 3) {
      if (pj3.length >= 5) {
        alert('참여 인원은 최대 5명까지만 추가할 수 있습니다.');
        return;
      }
      setPj3([...pj3, cardData]);
    }
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
          &nbsp;&nbsp;<button onClick={() => setPj3([])}>참여인원 비우기</button>
        </legend>
        <CardArea pjId={3} onDrop={handleCardDrop}>
          {pj3.map((character, index) => (
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
              {pjList[1].no} {pjList[1].name} <Stars amount={pjList[1].level} /> {pjList[1].gold}💰 {pjList[0].content}
            </>
          : '프로젝트 정보 없음'}
          &nbsp;&nbsp;<button onClick={() => setPj1([])}>참여인원 비우기</button>
        </legend>
        <CardArea pjId={1} onDrop={handleCardDrop}>
          {pj1.map((character, index) => (
            <Card 
              key={index} 
              job={character.job} 
              grade={character.grade}
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