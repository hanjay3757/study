
const mysql = require('mysql2');

// 데이터베이스 연결 설정
const connection = mysql.createConnection({
    host: 'localhost',
    user: 'user',
    password: 'password',
    database: 'mydb'
});

// 데이터베이스 연결
connection.connect((err) => {
    if (err) {
        console.error('데이터베이스 연결 실패:', err.stack);
        return;
    }
    console.log('데이터베이스에 연결되었습니다:', connection.threadId);
});

// 예제 쿼리 실행
connection.query('SELECT * FROM your_table', (err, results) => {
    if (err) {
        console.error('쿼리 실행 실패:', err);
        return;
    }
    console.log('쿼리 결과:', results);
});

// 연결 종료
connection.end();
