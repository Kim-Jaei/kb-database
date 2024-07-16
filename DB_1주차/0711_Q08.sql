-- 2p
create database tabledb;
use tabledb;

drop table if exists buytbl, usertbl;

create table usertbl
(userid char(8) not null primary key,
name varchar(10) not null,
birthyear int not null,
addr char(2) not null,
mobile1 char(3) null,
mobile2 char(8) null,
height smallint null,
mdate date null);

-- 3p
drop table if exists buytbl;

create table buytbl
(num int auto_increment primary key not null,
userid char(8) not null,
prodname char(6) not null,
groupname char(4) null,
price int not null,
amount smallint not null,
foreign key(userid) references usertbl(userid));

-- 4p
insert into usertbl values('lsg', '이승기', 1987, '서울', '011', '11111111', 182, '2008-8-8');
insert into usertbl values('kbs', '김범수', 1979, '경남', '011', '11111112', 173, '2012-4-8');
insert into usertbl values('kkh', '김경호', 1971, '전남', '019', '11111113', 177, '2007-7-8');

insert into buytbl values(null, 'kbs', '운동화', null, 30, 1);
insert into buytbl values(null, 'kbs', '노트북', '전자', 1000, 1);
insert into buytbl values(null, 'jyp', '모니터', '전자', 200, 1); -- jyp가 usertbl에 없어서 오류

select * from usertbl;

-- 5p
drop table if exists usertbl;

create table usertbl
(userid char(8) not null,
name varchar(10) not null,
birtyear int not null,
constraint primary key (userid));

create table prodtbl
(prodcode char(3) not null,
prodid char(4) not null,
proddate datetime not null,
prodcur char(10) null,
constraint pk_prodtbl_prodcide_prodid primary key (prodcode, prodid));

-- 6p
create view v_user_buy
as select u.userid, u.name, b.prodname, u.addr, concat(u.mobile1, u.mobile2) as '연락처'
from usertbl u
join buytbl b
on u.userid = b.userid;

select * from v_user_buy where name='김범수';



