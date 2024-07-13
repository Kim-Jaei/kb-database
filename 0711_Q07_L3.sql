-- 3p

use employees;

select e.emp_no, e.first_name, e.last_name, t.title 
from employees e
join titles t
on t.emp_no = e.emp_no
where t.to_date='9999-01-01';

-- 4p

select e.*, t.title, s.salary
from titles t
join employees e
on t.emp_no = e.emp_no
join salaries s
on s.emp_no = e.emp_no
where t.to_date='9999-01-01'
and s.to_date='9999-01-01';

select e.*, t.title, s.salary
from titles t
join employees e
on t.emp_no = e.emp_no
join salaries s
on s.emp_no = e.emp_no
where s.to_date='9999-01-01';


-- 5p
select e.emp_no, e.first_name, e.last_name, d.dept_name
from 