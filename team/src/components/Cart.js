import React, { useState } from 'react';

function Cart() {
  const [cartItems, setCartItems] = useState([
    // 예시 데이터
    { id: 1, name: '상품1', price: 10000, quantity: 1 },
    { id: 2, name: '상품2', price: 20000, quantity: 2 }
  ]);

  const removeItem = (id) => {
    setCartItems(cartItems.filter(item => item.id !== id));
  };

  const updateQuantity = (id, newQuantity) => {
    setCartItems(cartItems.map(item => 
      item.id === id ? {...item, quantity: newQuantity} : item
    ));
  };

  const calculateTotal = () => {
    return cartItems.reduce((total, item) => total + (item.price * item.quantity), 0);
  };

  return (
    <div className="cart-container">
      <h2>장바구니</h2>
      {cartItems.length === 0 ? (
        <p>장바구니가 비어있습니다.</p>
      ) : (
        <>
          <div className="cart-items">
            {cartItems.map(item => (
              <div key={item.id} className="cart-item">
                <h3>{item.name}</h3>
                <p>가격: {item.price.toLocaleString()}원</p>
                <input 
                  type="number" 
                  min="1" 
                  value={item.quantity}
                  onChange={(e) => updateQuantity(item.id, parseInt(e.target.value))}
                />
                <button onClick={() => removeItem(item.id)}>삭제</button>
              </div>
            ))}
          </div>
          <div className="cart-total">
            <h3>총 금액: {calculateTotal().toLocaleString()}원</h3>
            <button onClick={() => alert('주문 처리 예정')}>주문하기</button>
          </div>
        </>
      )}
    </div>
  );
}

export default Cart;