
SELECT * FROM productTBL;
SELECT member_name, member_address FROM membertbl;
SELECT * FROM membertbl WHERE member_name = '지운이';

SHOW DATABASES;
SHOW TABLES;

USE employees;

SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES;
select * from information_schema.PROCESSLIST;
SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE table_type = 'BASE TABLE';

SHOW TABLES FROM employees;
SHOW COLUMNS FROM employees;

select * from titles;
select first_name from employees;
select first_name, last_name, gender from employees;

select * from employees;
select first_name AS 이름,  gender as 성별, hire_date as '회사 입사일' from employees;

CREATE DATABASE sqldb;