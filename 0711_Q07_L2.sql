-- 2p
use sqldb;
create table stdtbl
(stdname varchar(10) not null primary key,
addr char(4) not null);

create table clubdb
(clubname varchar(10) not null primary key,
roomno char(4) not null);

create table stdclubtbl
(num int auto_increment not null primary key,
stdname varchar(10) not null,
clubname varchar(10) not null,
foreign key(stdname) references stdtbl(stdname),
foreign key(clubname) references clubdb(clubname));

-- 3p
insert into stdtbl values ('김범수', '경남'), ('성시경', '서울'), ('조승연', '서울'), ('강영현', '서울'), ('김재이', '인천');
insert into clubdb values ('축구', '101호'), ('수영', '102호'), ('바둑', '103호'), ('봉사', '104호');
insert into stdclubtbl values (null, '김범수', '바둑'), (null, '김범수', '수영'), (null, '조승연', '축구'), (null, '강영현', '축구'), (null, '강영현', '봉사'), (null, '김재이', '봉사');

-- 4p
select s.stdname, s.addr, c.clubname, c.roomno
from stdtbl s
inner join stdclubtbl sc
on s.stdname = sc.stdname
inner join clubdb c
on sc.clubname = c.clubname
order by s.stdname;

-- 5~6p
USE sqldb;
DROP TABLE emptbl;
CREATE TABLE empTbl(emp CHAR(3), manager CHAR(3), empTel VARCHAR(8));

INSERT INTO empTbl VALUES('나사장', NULL, '0000');
INSERT INTO empTbl VALUES('김재무', '나사장', '2222');
INSERT INTO empTbl VALUES('김부장', '김재무', '2222-1');
INSERT INTO empTbl VALUES('이부장', '김재무', '2222-2');
INSERT INTO empTbl VALUES('우대리', '이부장', '2222-2-1');
INSERT INTO empTbl VALUES('지사원', '이부장', '2222-2-2');
INSERT INTO empTbl VALUES('이영업', '나사장', '1111');
INSERT INTO empTbl VALUES('한과장', '이영업', '1111-1');
INSERT INTO empTbl VALUES('최정보', '나사장', '3333');
INSERT INTO empTbl VALUES('윤차장', '최정보', '3333-1');
INSERT INTO empTbl VALUES('이주임', '윤차장', '3333-1-1');

-- select a.emp as '부하직원', b.emp as 직속상관, b.emptel as 직속상관연락처
select *
from emptbl b
inner join emptbl a
on b.manager = a.emp; -- on: 어떤 조건으로 결합할 건지
-- where a.emp = '우대리';

-- 필터링 역할: ON 절은 조인할 때 두 테이블에서 조건에 맞는 행들만 선택하여 결합합니다. 즉, 조건에 맞지 않는 행들은 제외되므로 필터링 기능을 합니다.
-- 결합 조건 지정: ON 절은 두 테이블의 어떤 열을 기준으로 조인할지를 지정합니다. 이를 통해 두 테이블 간의 관계를 정의합니다. (순서로 이해했삼)