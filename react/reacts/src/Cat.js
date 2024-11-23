function Cat() {  // React 컴포넌트 Cat 를 정의하는 함수형 컴포넌트
    return (  // JSX 문법으로 HTML과 유사하게 작성된 React 컴포넌트의 UI 구조를 반환
      // JSX의 최상위 요소로 <div> 태그 사용 (JSX 바깥이므로 일반 주석 사용 가능)
      <div> {/* 여기서부터 JSX 문법 시작. JSX에서는 HTML 태그와 유사하게 작성하지만, 실제로는 JavaScript 코드 */}
        <p>고양이</p> {/* 주석 */}
        <p>야옹이</p>
        <p>키티</p>
      </div>
    );
  }
  export default Cat; // 이 컴포넌트 Cat 을 다른 파일에서 사용할 수 있도록 내보내기(export) 설정
  