# 2p
use sqldb;
select * from usertbl;
show index from usertbl;

SHOW TABLE STATUS LIKE 'usertbl'; -- 인덱스 크기, 데이터 크기

# 3p
create index idx_usertbl_addr on usertbl(addr);
show index from usertbl;

# 4p
ANALYZE TABLE usertbl; -- 테이블 최신화
show table status like 'usertbl';

# 5p
drop index idx_uertbl_name on usertbl;
create unique index idx_uertbl_name on usertbl(name);
show index from usertbl;

# 6p
create index idx_usertbl_name_birthyear on usertbl (name, birthyear);
show index from usertbl; -- 조합해서 만들었기 때문에 두 열이 하나의 인덱스

# 7p
drop index idx_usertbl_name_birthyear on usertbl;
drop index idx_usertbl_addr on usertbl;
alter table usertbl drop primary key;

select table_name, constraint_name
from information_schema.referential_constraints
where constraint_schema = 'sqldb';

alter table buytbl drop foreign key buytbl_ibfk_1;
alter table usertbl drop primary key;
