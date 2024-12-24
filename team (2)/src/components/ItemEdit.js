import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useSearchParams } from 'react-router-dom';

function ItemEdit() {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const itemId = searchParams.get('itemId');
  
  const [item, setItem] = useState({
    item_id: '',
    item_name: '',
    item_price: '',
    item_stock: '',
    item_description: ''
  });

  useEffect(() => {
    const loadItem = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/mvc/stuff/item/list`, {
          withCredentials: true
        });
        console.log('서버에서 받은 전체 아이템:', response.data);
        
        const currentItem = response.data.find(item => item.item_id === parseInt(itemId));
        console.log('현재 수정할 아이템:', currentItem);
        
        if (currentItem) {
          setItem({
            item_id: currentItem.item_id || '',
            item_name: currentItem.item_name || '',
            item_price: currentItem.item_price || '',
            item_stock: currentItem.item_stock || '',
            item_description: currentItem.item_description || ''
          });
        } else {
          throw new Error('아이템을 찾을 수 없습니다.');
        }
      } catch (error) {
        console.error('상품 정보 로딩 실패:', error);
        alert('상품 정보를 불러오는데 실패했습니다.');
        navigate('/stuff/item/list');
      }
    };

    if (itemId) {
      loadItem();
    }
  }, [itemId, navigate]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const loginResponse = await axios.get('http://localhost:8080/mvc/staff/check-login', {
        withCredentials: true
      });
      
      console.log('로그인 정보:', loginResponse.data);

      const params = new URLSearchParams();
      params.append('item_id', itemId);
      params.append('item_name', item.item_name || '');
      params.append('item_price', item.item_price || '');
      params.append('item_stock', item.item_stock || '');
      params.append('item_description', item.item_description || '');
      params.append('admin_no', loginResponse.data.admin_no);

      console.log('수정할 데이터:', Object.fromEntries(params));

      const response = await axios.post(
        'http://localhost:8080/mvc/stuff/item/edit',
        params,
        {
          withCredentials: true,
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          }
        }
      );

      console.log('서버 응답:', response.data);

      if (response.data === 'redirect:/stuff/item/list' || response.status === 200) {
        alert('상품이 수정되었습니다.');
        
        navigate('/stuff/item/list', { 
          replace: true,
          state: { refresh: true }
        });
      } else {
        throw new Error('수정에 실패했습니다.');
      }
    } catch (error) {
      console.error('상품 수정 실패:', error);
      if (error.response) {
        console.error('서버 에러 응답:', error.response.data);
      }
      if (error.response?.status === 403) {
        alert('관리자 권한이 필요합니다.');
        navigate('/stuff/item/list');
      } else {
        alert('상품 수정 중 오류가 발생했습니다.');
      }
    }
  };

  return (
    <div className="edit-form-container">
      <h2>상품 수정</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>상품명</label>
          <input
            type="text"
            name="item_name"
            value={item.item_name || ''}
            onChange={(e) => setItem({...item, item_name: e.target.value})}
            required
          />
        </div>
        <div className="form-group">
          <label>가격</label>
          <input
            type="number"
            name="item_price"
            value={item.item_price || ''}
            onChange={(e) => setItem({...item, item_price: e.target.value})}
            required
            min="0"
          />
        </div>
        <div className="form-group">
          <label>재고</label>
          <input
            type="number"
            name="item_stock"
            value={item.item_stock || ''}
            onChange={(e) => setItem({...item, item_stock: e.target.value})}
            required
            min="0"
          />
        </div>
        <div className="form-group">
          <label>설명</label>
          <textarea
            name="item_description"
            value={item.item_description || ''}
            onChange={(e) => setItem({...item, item_description: e.target.value})}
          />
        </div>
        <div className="button-group">
          <button type="submit">수정</button>
          <button type="button" onClick={() => navigate('/stuff/item/list')}>
            취소
          </button>
        </div>
      </form>
    </div>
  );
}

export default ItemEdit; 