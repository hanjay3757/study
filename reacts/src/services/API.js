import axios from 'axios';

const BASE_URL = 'http://localhost:8080/card';

axios.defaults.withCredentials = true;
axios.defaults.headers.common['Accept'] = 'application/json';
axios.defaults.headers.common['Content-Type'] = 'application/json';

export const API = {
  // 재화 관련
  getWealth: () => axios.get(`${BASE_URL}/pay/getWealth`),
  buyDice: () => axios.get(`${BASE_URL}/pay/buyDice`),
  
  // 카드 관련
  getMyCards: () => axios.get(`${BASE_URL}/card/getMyCards`),
  getPjMember: (no) => axios.get(`${BASE_URL}/card/getPjMember?no=${no}`),
  getPjList: () => axios.get(`${BASE_URL}/card/pj/getPjList`),
  pjMemberAdd: (data) => axios.post(`${BASE_URL}/card/pjMemberAdd`, data),
  clearPjMember: () => axios.get(`${BASE_URL}/card/clearPjMember`),
  gacha: () => axios.get(`${BASE_URL}/api/gacha`),
};

export default API; 