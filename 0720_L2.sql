-- 2p
select * from buytbl;
use sqldb;
select userid as '사용자 아이디', amount as '총 구매 개수' from buytbl;
select userid as '사용자 아이디', price as '총 구매액' from buytbl;

-- 3p
select avg(amount) as '평균 구매 개수' from buytbl;
select userid, avg(amount) as '평균 구매 개수' from buytbl group by userid;

-- 4p
select name, height from usertbl 
where height = (select max(height) from usertbl) or
height = (select min(height) from usertbl);

-- 5p
select * from usertbl;
select count(mobile1) as '휴대폰이 있는 사용자' from usertbl; -- null값 제외하고 카운트

-- 6p
select userid, sum(price*amount) from buytbl group by userid;
select userid, sum(price*amount) from buytbl 
group by userid -- where절은 그룹화되기 전 개별 레코드에 대해 조건을 적용한다
having sum(price*amount) > 1000; -- 그룹화된 결과를 필터링하기 때문에 where에서 사용 x

-- 7p
use world;
select * from city;
select * from country;
select sum(population) from city where countrycode = 'kor';
select min(population) as 최솟값 from city where countrycode = 'kor';
select avg(population) from city where countrycode = 'kor';
select max(population) as 최대값 from city where countrycode = 'kor';
select name, length(name) as '칼럼의 글자수' from city;
select name, concat(UPPER(SUBSTRING(name, 1, 3)),  LOWER(substring(name, 4))) as name2 from country;
select round(lifeexpectancy, 1) from country;








