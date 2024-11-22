import axios from 'axios';

axios.defaults.withCredentials = true;
axios.defaults.headers.common['Accept'] = 'application/json';
axios.defaults.headers.common['Content-Type'] = 'application/json';

const BASE_URL = 'http://localhost:8080/card';

export const API = {
  getWealth: () => axios.get(`${BASE_URL}/pay/getWealth`),
  
  getPjMembers: (pjId) => axios.get(`${BASE_URL}/card/getPjMember?no=${pjId}`),
  
  getMyCards: () => axios.get(`${BASE_URL}/card/getMyCards`),
  
  getPjList: () => axios.get(`${BASE_URL}/card/pj/getPjList`),
  
  gacha: () => axios.get(`${BASE_URL}/api/gacha`),
  
  clearPjMember: (pjId) => axios.get(`${BASE_URL}/card/clearPjMember?PjId=${pjId}`),
  
  buyDice: () => axios.get(`${BASE_URL}/pay/buyDice`),
  
  createPj: () => axios.get(`${BASE_URL}/card/pj/create`),
  
  addPjMember: (data) => axios.post(`${BASE_URL}/card/pjMemberAdd`, data),
  
  getBuyGoldUrl: () => `${BASE_URL}/pay/buy`
}; 