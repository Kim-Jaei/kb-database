-- 현재 서버에 존재하는 데이터베이스 확인
show databases;

-- 현재 데이터베이스를 employees로 설정하기
use employees;

-- employees 데이터베이스의 테이블 목록 보기
show tables;

-- employees 테이블의 열 목록 출력하기
show COLUMNS from employees.employees;

-- titles 테이블의 데이터 출력하기
select * from titles;

-- employees 테이블에서 first_name 컬럼만 출력하기
select first_name from employees;

-- employees 테이블에서 first_name, last_name, gender 컬럼 출력하기
select first_name, last_name, gender from employees;

-- employees 테이블에서 이름, 성별, 회사 입사일이 나오도록 쿼리 작성
select first_name as 이름, gender as 성별, hire_date as '회사 입사일' from employees;

-- 배포된 sqldb.sql 파일을 이용하여 데이터베이스를 구축
-- File - Open SQL Script - sqldb.sql 열기

-- usertbl 테이블에서 이름이 '김경호'인 행을 출력
use sqldb;
select * from usertbl where name = '김경호';

-- usertbl 테이블에서 생년이 1970 이상이고 키가 182 이상인 데이터 출력
select * from usertbl where birthYear >= 1970 and height >= 182;

-- usertbl 테이블에서 키가 180~183 범위인 데이터 출력
select * from usertbl where height between 180 and 183;

-- usertbl 테이블에서 주소가 '경남' 또는 '전남' 또는 '경북'인 데이터 출력
select * from usertbl where addr in ('경남','전남','경북');

-- usertbl 테이블에서 이름이 '김'으로 시작하는 데이터 출력
select * from usertbl where name like '김%';

-- usertbl 테이블에서 김경호보다 큰 사람들의 이름과 키 출력
select name, height from usertbl where height > (select height from usertbl where name = '김경호');

-- usertbl을 mdate의 오름차순으로 정렬하여 출력
select * from usertbl order by mdate;

-- usertbl을 mdate의 내림차순으로 정렬하여 출력
select * from usertbl order by mdate desc;

-- usertbl을 height의 내림차순으로 정렬, 동률인 경우 name의 내림차순으로 정렬하여 출력
select * from usertbl order by height desc, name desc;

-- usertbl의 주소지를 중복없이 오름차순으로 출력
select distinct addr from usertbl order by addr;

desc usertbl;

-- Q06 마지막 페이지
use world;
select * from city where countrycode = 'kor' order by population desc;

select countrycode, population from city order by countrycode, population desc;

select count(*) from city where countrycode = 'kor';

select name from city where countrycode in ('kor', 'chn', 'jpn');

select name from city where countrycode='kor' and population > 1000000;

select name from city where countrycode='kor' order by population desc limit 10;

select name from city where countrycode='kor' and population between 1000000 and 5000000;