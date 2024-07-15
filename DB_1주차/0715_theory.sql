SELECT * FROM sqldb.buytbl;

start transaction;
delete from buytbl where num = 1;
delete from buytbl where num = 2;

select * from buytbl;

rollback; -- 원상복귀

select @@autocommit; -- 환경변수 읽기 1이면 on 0이면 off 명령어 실행마다 바로 commit

create database jdbc_ex;

create user 'jdbc_ex'@'%' identified by 'jdbc_ex';
grant all privileges on jdbc_ex.* to 'jbdc_ex'@'%';
flush privileges;