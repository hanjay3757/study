import React, { useState, useEffect } from 'react';
//라면 타이머
function RamenTimer({ duration, onComplete }) {
  const [timeLeft, setTimeLeft] = useState(duration); // 선택된 라면 시간

  useEffect(() => {
    if (timeLeft > 0) {
      const timerId = setTimeout(() => {
        setTimeLeft(timeLeft - 1);
      }, 1000);

      return () => clearTimeout(timerId);
    } else {
      onComplete(); // 타이머 종료 시 호출
    }
  }, [timeLeft, onComplete]);

  return (
    <div style={{ fontSize: '1em', color: 'blue' }}>
      남은 시간: {timeLeft}초
    </div>
  );
}

function Burner({ burnerNumber }) {
  const [selectedRamen, setSelectedRamen] = useState('');
  const [timerDuration, setTimerDuration] = useState(0);
  const [isTimerRunning, setIsTimerRunning] = useState(false);
  const [isBurnerOn, setIsBurnerOn] = useState(false);
  const [resultMessage, setResultMessage] = useState('');

  const ramenOptions = {
    너구리: 20,
    신라면: 10,
    짜파게티: 30
  };

  const handleRamenSelect = (e) => {
    const ramenType = e.target.value;
    setSelectedRamen(ramenType);
    setTimerDuration(ramenOptions[ramenType]); // 선택한 라면의 조리 시간 설정
  };

  const handleStartTimer = () => {
    if (timerDuration > 0) {
      setIsBurnerOn(true);
      setIsTimerRunning(true);
      setResultMessage('');
    }
  };

  const handleTimerComplete = () => {
    setIsBurnerOn(false);
    setIsTimerRunning(false);
    setResultMessage(`${selectedRamen} 라면이 완성되었습니다!`);
  };

  return (
    <div style={{ border: '1px solid gray', padding: '20px', margin: '10px' }}>
      <h2>버너 {burnerNumber}</h2>
      <p>버너 상태: {isBurnerOn ? "ON" : "OFF"}</p>
      <select onChange={handleRamenSelect} value={selectedRamen}>
        <option value="">라면 종류 선택</option>
        <option value="너구리">너구리</option>
        <option value="신라면">신라면</option>
        <option value="짜파게티">짜파게티</option>
      </select>
      <button onClick={handleStartTimer} disabled={isTimerRunning || !selectedRamen}>
        {isTimerRunning ? "조리 중..." : "버너 ON"}
      </button>
      
      {isTimerRunning && (
        <RamenTimer duration={timerDuration} onComplete={handleTimerComplete} />
      )}

      {resultMessage && <p>{resultMessage}</p>}
    </div>
  );
}

function App() {
  return (
    <div style={{ textAlign: 'center' }}>
      <h1>라면 버너 타이머</h1>
      <div style={{ display: 'flex', justifyContent: 'center' }}>
        <Burner burnerNumber={1} />
        <Burner burnerNumber={2} />
        <Burner burnerNumber={3} />
      </div>
    </div>
  );
}

export default App;
