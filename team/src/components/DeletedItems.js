import React, { useState, useEffect } from 'react';

function DeletedItems() {
  const [deletedItems, setDeletedItems] = useState([]);

  useEffect(() => {
    // 서버에서 삭제된 항목을 가져오는 API 호출 예시
    fetch('/api/deleted-items')
      .then(response => response.json())
      .then(data => setDeletedItems(data))
      .catch(error => console.error('Error fetching deleted items:', error));
  }, []);

  return (
    <div>
      <h2>삭제된 물건 목록</h2>
      <ul>
        {deletedItems.map(item => (
          <li key={item.id}>{item.name}</li>
        ))}
      </ul>
    </div>
  );
}

export default DeletedItems;