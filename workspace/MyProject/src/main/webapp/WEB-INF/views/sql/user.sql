DROP TABLE GALLERYBOARD;
DROP TABLE USERS;

CREATE TABLE USERS
(
    NO NUMBER NOT NULL UNIQUE,
    ID VARCHAR2(32) PRIMARY KEY,
    PW VARCHAR2(32) NOT NULL,
    NAME VARCHAR2(32),
    PHONE VARCHAR2(32) NOT NULL UNIQUE,
    EMAIL VARCHAR2(50) NOT NULL ,
    ADDRESS VARCHAR2(100),
    REGDATE DATE,
    STATUS VARCHAR2(10)
);

DROP SEQUENCE USERS_SEQ;
CREATE SEQUENCE USERS_SEQ START WITH 1 INCREMENT BY 1 NOCYCLE NOCACHE;


INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, 'user1', 1111, '사용자1', '010-0000-0000', 'ykt3401@naver.com', '경기도', SYSDATE, 'on');
INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, 'user2', 1111, '사용자2', '010-0000-0001', 'ykt3401@naver.com', '경기도', SYSDATE, 'on');
INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, 'user3', 1111, '사용자3', '010-0000-0003', 'ykt3401@naver.com', '경기도', SYSDATE, 'on');
INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, 'user4', 1111, '사용자4', '010-0000-0004', 'ykt3401@gmail.com', '경기도', SYSDATE, 'on');



CREATE TABLE GALLERYBOARD
(
    NO NUMBER,
    ID VARCHAR2(32),
    TITLE VARCHAR2(32),
    CONTENT VARCHAR2(2000),
    REGDATE DATE,
    POSTDATE DATE,
    IP VARCHAR2(32),
    HIT NUMBER,
    FILENAME VARCHAR2(64)
);

ALTER TABLE GALLERYBOARD ADD CONSTRAINT id_fk FOREIGN KEY(id) REFERENCES USERS(id);

DROP SEQUENCE GALLERYBOARD_SEQ;
CREATE SEQUENCE GALLERYBOARD_SEQ START WITH 1 INCREMENT BY 1 NOCYCLE NOCACHE;

INSERT INTO GALLERYBOARD VALUES(GALLERYBOARD_SEQ.NEXTVAL, 'user1', '제목1', '내용1', SYSDATE, SYSDATE, '127.0.0.1', 0, '');
INSERT INTO GALLERYBOARD VALUES(GALLERYBOARD_SEQ.NEXTVAL, 'user1', '제목2', '내용1', SYSDATE, SYSDATE, '127.0.0.1', 0, '');
INSERT INTO GALLERYBOARD VALUES(GALLERYBOARD_SEQ.NEXTVAL, 'user1', '제목3', '내용1', SYSDATE, SYSDATE, '127.0.0.1', 0, '');
INSERT INTO GALLERYBOARD VALUES(GALLERYBOARD_SEQ.NEXTVAL, 'user1', '제목4', '내용1', SYSDATE, SYSDATE, '127.0.0.1', 0, '');
INSERT INTO GALLERYBOARD VALUES(GALLERYBOARD_SEQ.NEXTVAL, 'user1', '제목5', '내용1', SYSDATE, SYSDATE, '127.0.0.1', 0, '');
INSERT INTO GALLERYBOARD VALUES(GALLERYBOARD_SEQ.NEXTVAL, 'user1', '제목6', '내용1', SYSDATE, SYSDATE, '127.0.0.1', 0, '');
INSERT INTO GALLERYBOARD VALUES(GALLERYBOARD_SEQ.NEXTVAL, 'user1', '제목7', '내용1', SYSDATE, SYSDATE, '127.0.0.1', 0, '');
INSERT INTO GALLERYBOARD VALUES(GALLERYBOARD_SEQ.NEXTVAL, 'user1', '제목8', '내용1', SYSDATE, SYSDATE, '127.0.0.1', 0, '');
INSERT INTO GALLERYBOARD VALUES(GALLERYBOARD_SEQ.NEXTVAL, 'user1', '제목9', '내용1', SYSDATE, SYSDATE, '127.0.0.1', 0, '');
INSERT INTO GALLERYBOARD VALUES(GALLERYBOARD_SEQ.NEXTVAL, 'user1', '제목10', '내용1', SYSDATE, SYSDATE, '127.0.0.1', 0, '');
INSERT INTO GALLERYBOARD VALUES(GALLERYBOARD_SEQ.NEXTVAL, 'user1', '제목11', '내용1', SYSDATE, SYSDATE, '127.0.0.1', 0, '');
INSERT INTO GALLERYBOARD VALUES(GALLERYBOARD_SEQ.NEXTVAL, 'user1', '제목12', '내용1', SYSDATE, SYSDATE, '127.0.0.1', 0, '');
INSERT INTO GALLERYBOARD VALUES(GALLERYBOARD_SEQ.NEXTVAL, 'user1', '제목13', '내용1', SYSDATE, SYSDATE, '127.0.0.1', 0, '');



DROP TABLE REPLY;
CREATE TABLE REPLY
(
    NO NUMBER,
    ID VARCHAR2(32),
    CONTENT VARCHAR2(32),
    IP VARCHAR2(32),
    REGDATE DATE
);

DROP SEQUENCE REPLY_SEQ;
CREATE SEQUENCE REPLY_SEQ START WITH 1 INCREMENT BY 1 NOCYCLE NOCACHE;

ALTER TABLE REPLY ADD CONSTRAINT replyid_fk FOREIGN KEY(id) REFERENCES USERS(id);

INSERT INTO REPLY VALUES(REPLY_SEQ.NEXTVAL, 'user1', '댓글1', '127.0.0.1', SYSDATE);
INSERT INTO REPLY VALUES(REPLY_SEQ.NEXTVAL, 'user1', '댓글2', '127.0.0.1', SYSDATE);
INSERT INTO REPLY VALUES(REPLY_SEQ.NEXTVAL, 'user1', '댓글3', '127.0.0.1', SYSDATE);
INSERT INTO REPLY VALUES(REPLY_SEQ.NEXTVAL, 'user1', '댓글4', '127.0.0.1', SYSDATE);
INSERT INTO REPLY VALUES(REPLY_SEQ.NEXTVAL, 'user1', '댓글5', '127.0.0.1', SYSDATE);

