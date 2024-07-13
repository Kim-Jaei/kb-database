SELECT * FROM employees.employees;

select * from employees
where first_name = 'georgi';

use world;
select * from city where countrycode = 'kor' order by population desc; -- city 테이블에서 내림차순으로 countrycode가 kor인 애들 전체 컬럼 출력

-- 우리나라에서 제주의 인구수보다 작은 도시를 찾아 인구수를 내림차순으로 도시명, 인구수를 출력하세요
select Name, Population from city 
where countrycode = 'kor' and
population < (select population from city where id = '2358');


select * from city
where countrycode = 'kor' and
name like 'c%';


use employees;

create table users2
(select first_name as name, gender from employees where gender='W');

DROP DATABASE IF EXISTS users2;

use world;
create table kor_city
(select id, name, district, population from city where countrycode = 'kor');

select * from kor_city;

-- city 테이블을 사용해서 국가별 총 인구수 출력
select * from city;
select countrycode, sum(population) as '인구수'
from city
where countrycode = 'kor' -- where 절은 grouping 전에
group by countrycode
order by sum(population) desc; 

-- 인구수가 1억 이상인 나라만 출력
select countrycode, sum(population) as '인구수'
from city
group by countrycode
having sum(population) > 10000000 -- 집계 함수에 대한 조건 검사
order by sum(population) desc;


