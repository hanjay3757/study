import axios from 'axios';
import React, { useState, useEffect } from 'react';
import './App.css';
import { Routes, Route, useNavigate } from 'react-router-dom';
import StaffEdit from './components/StaffEdit';
import ItemList from './components/ItemList';
import ItemRegister from './components/ItemRegister';
import StaffRegister from './components/Register';
import DeletedItems from './components/DeletedItems';
import Cart from './components/Cart';
import RemovedStaff from './components/RemovedStaff';
import ItemEdit from './components/ItemEdit';
//ㄴ 위의 내용들은 불러오기 기능
// axios 기본 설정
axios.defaults.withCredentials = true;

// StaffTable을 별도의 컴포넌트로 분리
const StaffTable = ({ staffList, onDelete, onEdit }) => {
  // stafftable의 각 기능들을 미리 넣어야 정상 작동
  return (
    <>
      <h2 className="section-title">직원 목록</h2>
      {/* 위치 지정 */}
      <table className="staff-table">
        <thead>
          <tr>
            <th>직원번호</th>
            <th>아이디</th>
            <th>이름</th>
            <th>관리자 여부</th>
            <th>관리</th>
          </tr>
        </thead>
        {/* 테이블안에 들어갈 이름 지정 */}
        <tbody>
          {staffList.map(staff => (
            <tr key={staff.member_no + '-' + staff.member_id}> 
            {/* 표시 회원번호 + 회원 아이디 */}
              <td>{staff.member_no}</td>
              <td>{staff.member_id}</td>
              <td>{staff.member_nick}</td>
              <td>{staff.admins === 1 ? '관리자' : '일반 직원'}</td>
              {/* 관리자는 해당코드의 1인지 아닌지에 따라 결정 일반직원일 경우 0 */}
              <td>
                <button onClick={() => onDelete(staff.member_no)}>삭제</button>
                <button onClick={() => onEdit(staff.member_no)}>수정</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
};

// App 컴포넌트
function App() {
  // 상태 변수들
  const [isAdmin, setIsAdmin] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [staffList, setStaffList] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    checkLoginStatus();
    loadStaffList();
  }, []);

  // 로그인 상태 확인
  function checkLoginStatus() {
    axios.get('http://localhost:8080/mvc/staff/check-login')
      .then(response => {
        setIsLoggedIn(response.data.isLoggedIn);
        setIsAdmin(response.data.isAdmin);
      })
      .catch(error => {
        console.error('로그인 상태 확인 실패:', error);
        setIsLoggedIn(false);
        setIsAdmin(false);
      });
  }

  // 로그인 처리
  function handleLogin(e, formData) {
    e.preventDefault();
    const params = new URLSearchParams();
    params.append('staffId', formData.username);
    params.append('password', formData.password);

    axios.post('http://localhost:8080/mvc/staff/login', params, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    })
      .then(response => {
        if (response.data.success) {
          setIsLoggedIn(true);
          setIsAdmin(response.data.isAdmin);
          navigate('/stuff/item/list');
        } else {
          alert(response.data.message || '로그인에 실패했습니다.');
        }
      })
      .catch(error => {
        console.error('로그인 요청 실패:', error);
        alert('로그인에 실패했습니다. 서버에 문제가 있습니다.');
      });
  }

  // 로그아웃 처리
  function handleLogout() {
    axios.post('http://localhost:8080/mvc/staff/logout')
      .then(response => {
        if (response.data.success) {
          setIsLoggedIn(false);
          setIsAdmin(false);
          navigate('/'); // 로그아웃 후 메인 페이지로 이동
        }
      })
      .catch(error => {
        console.error('로그아웃 실패:', error);
      });
  }

  // 직원 목록 불러오기
  const loadStaffList = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/mvc/staff/list?_t=${Date.now()}`);
      const filteredList = response.data.filter(staff => !staff.member_delete);
      const sortedStaffList = [...filteredList].sort((a, b) => a.member_no - b.member_no);
      setStaffList(sortedStaffList);
    } catch (error) {
      console.error('직원 목록 조회 실패:', error);
    }
  };

  // 직원 삭제
  function confirmDelete(member_no) {
    if (window.confirm('이 직원을 삭제하시겠습니까?')) {
      const params = new URLSearchParams();
      params.append('member_no', member_no);

      axios.post('http://localhost:8080/mvc/staff/remove', params, {
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        withCredentials: true
      })
        .then(response => {
          if (response.data === 'redirect:/staff/list' || response.status === 200) {
            alert('직원이 삭제되었습니다.');
            loadStaffList();
          } else {
            throw new Error('삭제에 실패했습니다.');
          }
        })
        .catch(error => {
          console.error('직원 삭제 실패:', error);
          alert('직원 삭제에 실패했습니다.');
        });
    }
  }

  // 직원 수정
  function editStaff(member_no) {
    navigate(`/staff/edit?member_no=${member_no}`);
  }

  // TopMenu 컴포넌트 - 모든 사용자용 공통 메뉴
  const TopMenu = () => {
    // 로컬 상태로 관리
    const [localLoginForm, setLocalLoginForm] = useState({
      username: '',
      password: ''
    });

    const handleLocalSubmit = (e) => {
      e.preventDefault();
      handleLogin(e, localLoginForm);  // 부모의 handleLogin 함수 호출
    };

    return (
      <div className="top-menu">
        <div className="menu-buttons">
          <button onClick={() => navigate('/')}>메인 페이지</button>
          {isLoggedIn && (
            <button onClick={() => navigate('/stuff/cart')}>🛒 장바구니</button>
          )}
        </div>
        <div className="auth-buttons">
          {!isLoggedIn ? (
            <form onSubmit={handleLocalSubmit} className="login-form">
              <input
                type="text"
                placeholder="아이디"
                value={localLoginForm.username}
                onChange={(e) => setLocalLoginForm({
                  ...localLoginForm,
                  username: e.target.value
                })}
                required
              />
              <input
                type="password"
                placeholder="비밀번호"
                value={localLoginForm.password}
                onChange={(e) => setLocalLoginForm({
                  ...localLoginForm,
                  password: e.target.value
                })}
                required
              />
              <button type="submit">로그인</button>
            </form>
          ) : (
            <button onClick={handleLogout}>로그아웃</button>
          )}
        </div>
      </div>
    );
  };

  // AdminMenu 컴포넌트 - 관리자 전용 메뉴
  const AdminMenu = () => (
    isAdmin && (
      <div className="admin-menu">
        <button onClick={() => navigate('/stuff/item/register')}>물건 등록</button>
        <button onClick={() => navigate('/staff/register')}>사원 등록</button>
        <button onClick={() => navigate('/stuff/item/deleted')}>삭제된 건 목록</button>
        <button onClick={() => navigate('/staff/removelist')}>삭제된 직원 목록</button>
        <button onClick={() => navigate('/staff/list')}>직원 목록</button>
      </div>
    )
  );

  return (
    <div className="App">
      <TopMenu />
      <AdminMenu />
      
      {/* 라우트 설정 수정 */}
      <Routes>
        {/* 메인 페이지에 ItemList 표시 */}
        <Route path="/" element={<ItemList isLoggedIn={isLoggedIn} isAdmin={isAdmin} />} />
        <Route path="/staff/edit" element={<StaffEdit onUpdate={loadStaffList} />} />
        <Route path="/stuff/item/list" element={<ItemList isLoggedIn={isLoggedIn} isAdmin={isAdmin} />} />
        <Route path="/staff/register" element={<StaffRegister />} />
        <Route path="/stuff/item/register" element={<ItemRegister />} />
        <Route path="/stuff/item/edit" element={<ItemEdit />} />
        <Route path="/stuff/item/deleted" element={<DeletedItems />} />
        <Route path="/stuff/cart" element={<Cart />} />
        <Route path="/staff/removelist" element={<RemovedStaff />} />
        <Route path="/staff/list" element={
          <StaffTable 
            staffList={staffList}
            onDelete={confirmDelete}
            onEdit={editStaff}
          />
        } />
      </Routes>

      {/* 관리자일 경우에만 StaffTable 표시하고 목록도 새로고침 */}
      {isAdmin && (
        <StaffTable 
          staffList={staffList}
          onDelete={confirmDelete}
          onEdit={editStaff}
          key={JSON.stringify(staffList)}
        />
      )}
    </div>
  );
}

export default App;
