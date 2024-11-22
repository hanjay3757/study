import './App.css';
import React, { useState } from 'react';

function App() {
  const [i, setCat] = useState(0);
  var cats = ['야옹','어흥'];

  return (
    <>
      <button onClick={() => setCat(i == 0 ? 1 : 0)}>
        {cats[i]}
        {/* 고양이 함수를 미리 입력해서 돌게끔 설정 */}
      </button>
    </>
  );
}

export default App;
