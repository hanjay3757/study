import React, { useState, useEffect } from 'react';

function RemovedStaff() {
  const [removedStaff, setRemovedStaff] = useState([]);

  useEffect(() => {
    fetchRemovedStaff();
  }, []);

  const fetchRemovedStaff = async () => {
    try {
      const response = await fetch('/api/staff/removed');
      const data = await response.json();
      setRemovedStaff(data);
    } catch (error) {
      console.error('삭제된 직원 목록을 가져오는데 실패했습니다:', error);
    }
  };

  const handleRestore = async (bno) => {
    try {
      const response = await fetch(`/api/staff/restore/${bno}`, {
        method: 'POST'
      });

      if (response.ok) {
        alert('직원이 복구되었습니다.');
        fetchRemovedStaff(); // 목록 새로고침
      } else {
        alert('직원 복구에 실패했습니다.');
      }
    } catch (error) {
      console.error('직원 복구 중 오류가 발생했습니다:', error);
    }
  };

  return (
    <div className="removed-staff-container">
      <h2>삭제된 직원 목록</h2>
      <div className="staff-grid">
        {removedStaff.map((staff) => (
          <div key={staff.bno} className="staff-card">
            <h3>{staff.name}</h3>
            <p>직원번호: {staff.bno}</p>
            <p>이메일: {staff.email}</p>
            <p>삭제일: {new Date(staff.deleteDate).toLocaleDateString()}</p>
            <button onClick={() => handleRestore(staff.bno)}>복구</button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default RemovedStaff;