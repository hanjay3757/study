const reportWebVitals = onPerfEntry => {
  if (onPerfEntry && onPerfEntry instanceof Function) {
    import('web-vitals').then(({ getCLS, getFID, getFCP, getLCP, getTTFB }) => {
      getCLS(onPerfEntry);
      getFID(onPerfEntry);
      getFCP(onPerfEntry);
      getLCP(onPerfEntry);
      getTTFB(onPerfEntry);
    });
  }
};
//Web Vitals는 Google에서 제공하는 표준화된 지표들로, 웹사이트의 사용자 경험을 측정하는 데 중요한 역할을 합니다. 여기서 측정하는 주요 지표는 다음과 같습니다
 
export default reportWebVitals;
