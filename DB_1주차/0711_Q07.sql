-- 2p
use sqldb;

select * from buytbl
inner join usertbl
on buytbl.userid = usertbl.userid;

select * from buytbl
inner join usertbl
on buytbl.userid = usertbl.userid
where buytbl.userid = 'jyp';

-- 3p
select usertbl.userid, usertbl.name, buytbl.prodname, usertbl.addr, concat(usertbl.mobile1, usertbl.mobile2) 
from usertbl -- 얘 데이터는 모두 출력
LEFT join buytbl -- usertbl을 기준으로 삼아 데이터 합체
on usertbl.userid = buytbl.userid -- 이 조건에 부합하는 애들은 한 레코드에 같이 결합해서 사용 아니면 따로 한 줄 만들기
order by usertbl.userid; -- 오름차순 정렬

-- "ON은 조인할 때 두 테이블에서 조건에 맞는 행들을 하나의 레코드로 결합하는 역할을 합니다."

-- 4p
select name, concat(usertbl.mobile1, usertbl.mobile2) as 연락처
from usertbl where name not in (select name from usertbl where mobile1 is null);

select name, concat(usertbl.mobile1, usertbl.mobile2) as 연락처
from usertbl where userid in (select userid from usertbl where mobile1 is null);

select name, concat(usertbl.mobile1, usertbl.mobile2) as 연락처
from usertbl where name not in (select name from usertbl where mobile1 is null);

select name, addr, mobile1
from usertbl 
where mobile1 is not null
and name like '이%'
and addr in ('서울');