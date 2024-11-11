use my_cat;
drop table tbl_video;
CREATE TABLE tbl_video (
    vno BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    content TEXT,
    writer VARCHAR(50) NOT NULL,
    regDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updateDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    viewCount INT DEFAULT 0,
    videoUrl VARCHAR(255) NOT NULL
);


CREATE TABLE videos (
    vno BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    writer VARCHAR(50) NOT NULL,
    regdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 테스트 데이터 삽입
INSERT INTO videos (title, writer) VALUES 
('테스트 비디오 1', '작성자1'),
('테스트 비디오 2', '작성자2');

-- 댓글 테이블 
CREATE TABLE tbl_video_reply (
    rno BIGINT PRIMARY KEY AUTO_INCREMENT,
    vno BIGINT,
    reply TEXT NOT NULL,
    replyer VARCHAR(50) NOT NULL,
    replyDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updateDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (vno) REFERENCES tbl_video(vno)
);
ALTER TABLE videos ADD COLUMN reg_date DATETIME DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE videos 
ADD COLUMN is_deleted TINYINT(1) DEFAULT 0 COMMENT '삭제 여부 (0: 활성, 1: 삭제됨)';
alter table videos drop column  deletes;
ALTER TABLE videos ADD COLUMN url VARCHAR(255) AFTER writer;
CREATE TABLE tbl_member (
    userid VARCHAR(50) PRIMARY KEY,
    userpw VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    regdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
   
);
ALTER TABLE videos 
ADD COLUMN deleted_at TIMESTAMP NULL DEFAULT NULL COMMENT '삭제된 일시';
select * from videos;

-- boolean 방식
UPDATE videos SET is_deleted = 1 WHERE id = ?;

-- timestamp 방식
UPDATE videos SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?;
#지워진거 확인
SELECT * FROM videos WHERE is_deleted = 0;
#복구
-- 특정 비디오 하나 복구
UPDATE videos SET is_deleted = 0 WHERE vno = 9;

-- 여러 비디오 한번에 복구
UPDATE videos 
SET is_deleted = 0 
WHERE vno IN (1, 2, 3);

-- 삭제된 모든 비디오 복구
UPDATE videos 
SET is_deleted = 0 
WHERE is_deleted = 1;