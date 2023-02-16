CREATE Table USERS (
ID VARCHAR2(8) PRIMARY KEY,
PASSWORD VARCHAR2(8),
NAME VARCHAR2(20),
ROLE VARCHAR2(20)
);

drop table Users;

INSERT INTO USERS VALUES ('admin', '1234', '관리자', 'Admin');
INSERT INTO USERS VALUES ('user1', '1234', '홍길동', 'User');

select * from Users;


Create Table Board (
 SEQ number(5) primary key,
 TITLE varchar2(200),
 WRITER varchar2(20),
 CONTENT varchar2(2000),
 REGDATE date default sysdate,
 CNT number(5) default 0 
)

insert into Board values (1, '가입인사', '관리자', '잘 부탁드립니다.' , default , default);

select * from board;

commit;