use sqldb;

select * from buytbl;

select userid, sum(amount)
from buytbl
group by userid;


-- 한 줄로 구매상품 나열
select userid, group_concat(prodname separator ',')
from buytbl
group by userid;