import React, { useState } from 'react';  // React와 useState 훅을 임포트합니다.
import './App.css';  // 스타일 파일을 임포트합니다.

function App() {
  // position 상태는 드래그할 사각형의 위치를 저장합니다.
  const [position, setPosition] = useState({ x: 0, y: 0 });

  // isDragging 상태는 마우스가 드래그 중인지 여부를 저장합니다.
  const [isDragging, setIsDragging] = useState(false);

  // 마우스 다운 이벤트 처리 함수
  const handleMouseDown = (e) => {
    setIsDragging(true);  // 마우스를 클릭하면 드래그 상태를 true로 설정
  };

  // 마우스 이동 이벤트 처리 함수
  const handleMouseMove = (e) => {
    if (isDragging) {  // 드래그 중일 때만 실행
      // 마우스가 이동할 때마다 x, y 좌표를 업데이트
      setPosition({
        x: e.clientX,  // 마우스의 x 좌표
        y: e.clientY   // 마우스의 y 좌표
      });
    }
  };

  // 마우스 업 이벤트 처리 함수
  const handleMouseUp = () => {
    setIsDragging(false);  // 마우스 버튼을 떼면 드래그 상태를 false로 설정
  };

  return (
    <div 
      onMouseMove={handleMouseMove}  // 마우스 이동 시 handleMouseMove 함수 호출
      onMouseUp={handleMouseUp}      // 마우스를 떼면 handleMouseUp 함수 호출
      style={{
        height: '100vh',  // 1. 화면 전체 높이를 차지하도록 설정. '100vh'는 뷰포트(화면)의 높이의 100%에 해당.
        position: 'relative',  // 2. 상대적인 위치 지정. 기본 위치를 기준으로 자식 요소들을 상대적으로 위치시킬 수 있도록 함.relative는 상대적인 위치를 의미합니다.
        //부모 요소에 relative를 설정하면, 자식 요소를 top, left, right, bottom 등의 속성을 이용해 부모의 원래 위치를 기준으로 이동시킬 수 있습니다.
        overflow: 'hidden'  // 3. 자식 요소가 부모 div의 크기를 넘어가면 숨기도록 설정. 
      }}
      
    >
      <div 
        onMouseDown={handleMouseDown}  // 마우스 다운 시 handleMouseDown 함수 호출
        style={{
          position: 'absolute',  // 절대 위치 지정
          top: position.y,       // y 좌표
          left: position.x,      // x 좌표
          //위치값 전송
          width: '100px',        // 사각형의 너비
          height: '100px',       // 사각형의 높이
          backgroundColor: 'skyblue',  // 배경색은 하늘색
          cursor: 'pointer',     // 마우스 커서를 포인터로 변경 (클릭할 수 있음을 나타냄)
        }}
      >
        드래그하세요  {/* 화면에 "드래그하세요"라는 텍스트 표시 */}
      </div>
    </div>
  );
}

export default App;  // App 컴포넌트를 내보냅니다.
