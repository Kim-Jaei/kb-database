use employees;

select * from dept_emp; -- 직원이 어느 부서에 언제부터 언제까지 배정됐나

select * from dept_emp where to_date = '9999-01-01'; -- 재직자들 출력
select count(*) from dept_emp where to_date = '9999/01/01'; -- 재직자 수 출력

select * from buytbl b inner join usertbl u -- 순서 바꿔도 결과 동일
on b.userid  = u.userid -- 구매 이력이 없는 데이터는 출력 X
order by b.userid; 
-- 자바에서 테이블 하나 당 클래스 하나 지정
-- class BuyTbl num userId .. 등등 필드
-- class UserTbl userId name .. 등등 필드 List<BuyTbl> list ** 참고

use sakila;
select a.*, f.title from film f
inner join film_actor fa
on f.film_id = fa.film_id
inner join actor a
on fa.actor_id = a.actor_id
order by f.title;

