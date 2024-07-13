-- 3p
use employees;

SELECT 
    d.dept_name, 
    e.emp_no AS manager_id, 
    e.first_name AS manager_first_name, 
    e.last_name AS manager_last_name
FROM dept_manager dm
JOIN employees e ON d.emp_no = e.emp_no
JOIN departments d ON dm.dept_no = d.dept_no
where to_date like '9999-%';





SELECT departments
TABLE_NAME AS '테이블명',
COLUMN_NAME AS '열명'
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = DATABASE();

select * from information_schema.COLUMNS;