import React, { useState } from 'react';
import axios from 'axios';

function ItemRegister() {
  const [formData, setFormData] = useState({
    itemName: '',
    price: '',
    stock: '',
    description: '',
    deleted: 0
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    try {
      const response = await axios.post('http://localhost:8080/mvc/stuff/item/register', {
        itemName: formData.itemName,
        price: parseInt(formData.price),
        stock: parseInt(formData.stock),
        description: formData.description,
        deleted: 0
      }, {
        withCredentials: true,
        headers: {
          'Content-Type': 'application/json'
        }
      });

      if (response.data.success) {
        alert('물건이 성공적으로 등록되었습니다.');
        setFormData({
          itemName: '',
          price: '',
          stock: '',
          description: '',
          deleted: 0
        });
      } else {
        throw new Error(response.data.message || '물건 등록에 실패했습니다.');
      }
    } catch (error) {
      console.error('에러:', error);
      alert(error.message || '물건 등록 중 오류가 발생했습니다.');
    }
  };

  return (
    <div className="register-container">
      <h2>물건 등록</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="itemName">물건 이름:</label>
          <input
            type="text"
            id="itemName"
            name="itemName"
            value={formData.itemName}
            onChange={handleChange}
            required
            minLength="2"
          />
        </div>
        
        <div className="form-group">
          <label htmlFor="price">가격:</label>
          <input
            type="number"
            id="price"
            name="price"
            value={formData.price}
            onChange={handleChange}
            min="100"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="stock">재고:</label>
          <input
            type="number"
            id="stock"
            name="stock"
            value={formData.stock}
            onChange={handleChange}
            min="0"
            required
          />
        </div>

        <button type="submit">등록하기</button>
      </form>
    </div>
  );
}

export default ItemRegister;