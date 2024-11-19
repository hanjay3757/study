import React from 'react';  // React 라이브러리를 가져옴 (JSX 문법을 사용하기 위해 필요함)
import ReactDOM from 'react-dom/client';  // ReactDOM은 React 컴포넌트를 실제 DOM에 렌더링할 때 사용됨
import './index.css'; // CSS 파일을 불러옴 (전역 스타일을 적용)

// GuildTitle 컴포넌트를 불러옴 (.js 확장자 생략 가능, 대신 헷갈림 방지로 명시)
import App from './App.js';  // <-- 여기서는 명시적으로 .js 확장자를 추가함

// root DOM 요소를 가져와서 React의 가상 DOM을 여기에 렌더링할 준비를 함
const root = ReactDOM.createRoot(document.getElementById('root'));  // document.getElementById('root')로 HTML의 root 요소 찾기

// root 요소에 GuildTitle 컴포넌트를 렌더링함
root.render(
  <React.StrictMode>  {/* React.StrictMode는 개발 모드에서 잠재적인 문제를 감지하는 기능 제공 (런타임 성능 영향 없음) */}
    <App /> {/* cat 컴포넌트를 화면에 렌더링 */}
  </React.StrictMode>
);
