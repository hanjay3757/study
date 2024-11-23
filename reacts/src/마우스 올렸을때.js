import './App.css';
import React, { useState } from 'react';

function App() {
  const [message, setMessage] = useState('');

  const handleFocus = () => {
    setMessage('이름을 입력하세요'); // 포커스 시 안내 메시지 설정
  };

  const handleBlur = () => {
    setMessage(''); // 포커스가 빠져나가면 안내 메시지 지우기
  };

  return (
    <>
      <label>
        이름:
        <input 
          type="text" 
          onFocus={handleFocus} 
          onBlur={handleBlur} 
          placeholder="이름을 입력하세요" 
        />
      </label>
      <p>{message}</p>
    </>
  );
}

export default App;
