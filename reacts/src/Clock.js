import React, { useState, useEffect } from 'react';

function Clock() {
  const [time, setTime] = useState(new Date());

  useEffect(() => {
    const timer = setInterval(() => {
      setTime(new Date());
    }, 1000);

    // 컴포넌트 언마운트 시 타이머 정리 아 라면타임
    return () => clearInterval(timer);
  }, []);

  const formatDate = (date) => {
    const options = { year: 'numeric', month: 'long', day: 'numeric', weekday: 'long' };
    return date.toLocaleDateString('ko-KR', options); // 한국어 형식으로 날짜와 요일 표시
  };

  return (
    <div style={styles.container}>
      <h1 style={styles.time}>
        {formatDate(time)} - {time.toLocaleTimeString('ko-KR')}
      </h1>
    </div>
  );
}

const styles = {
  container: {
    display: 'flex',
    justifyContent: 'left',
    alignItems: 'center',
    height: '30px',
    backgroundColor: '#282c34',
    color: 'pink',
    fontFamily: 'Arial, sans-serif',
    paddingLeft: '10px',
  },
  time: {
    fontSize: '14px',
  },
};

export default Clock;
