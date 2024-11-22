import './App.css';
import React, { useState } from 'react';

function App() {
  const [text, setText] = useState('');

  const handleInput = (e) => {
    setText(e.target.value); // 입력 값 상태 업데이트
  };

  return (
    <>
      <label>
        입력:
        <input 
          type="text" 
          value={text} 
          //텍스트 값으로 바꿔라
          onInput={handleInput} 
          
          placeholder="텍스트를 입력하세요" 
        />
      </label>
      <p>실시간 입력: {text}</p>
    </>
  );
}

export default App;
