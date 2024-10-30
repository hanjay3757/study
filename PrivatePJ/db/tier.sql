-- 데이터베이스 선택
USE my_cat;

-- 테이블 삭제
DROP TABLE IF EXISTS b등급;
DROP TABLE IF EXISTS a등급;
DROP TABLE IF EXISTS member;

-- a등급 테이블 생성
CREATE TABLE a등급 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    score INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- b등급 테이블 생성
CREATE TABLE b등급 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    level INT NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    
);
DESCRIBE items;
CREATE TABLE items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tier VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL
);

-- member 테이블 생성
CREATE TABLE member (
    s_id CHAR(50) PRIMARY KEY,
    s_pw CHAR(50) NOT NULL
);
SELECT * FROM MEMBER;
-- 데이터 삽입
INSERT INTO a등급 (id, name, score, created_at) VALUES (0, '1', 2, NOW());
INSERT INTO a등급 (id, name, score, created_at) VALUES (0, '2', 3, NOW());
INSERT INTO a등급 (id, name, score, created_at) VALUES (0, '3', 4, NOW());

INSERT INTO b등급 (id, name, level, updated_at) VALUES (0, '1', 4, NOW());
INSERT INTO b등급 (id, name, level, updated_at) VALUES (0, '2', 2, NOW());
INSERT INTO b등급 (id, name, level, updated_at) VALUES (0, '2', 5, NOW());
DELETE FROM a등급
WHERE id NOT IN (
    SELECT * FROM (
        SELECT MIN(id)
        FROM b등급
        GROUP BY name, level
    ) AS temp
);
select*from a등급;
SELECT 'a등급' AS table_name, id, name, score AS score_or_level, created_at AS timestamp
FROM a등급
UNION
SELECT 'b등급' AS table_name, id, name, level AS score_or_level, updated_at AS timestamp
FROM b등급;

-- 서브쿼리를 통해 결합한 테이블을 member 테이블과 JOIN
SELECT sub.table_name, sub.id, sub.name, sub.score_or_level, sub.timestamp, m.s_id
FROM (
    SELECT 'a등급' AS table_name, id, name, score AS score_or_level, created_at AS timestamp
    FROM a등급
    UNION ALL
    SELECT 'b등급' AS table_name, id, name, level AS score_or_level, updated_at AS timestamp
    FROM b등급
) AS sub
LEFT JOIN member AS m ON sub.name = m.s_id;
