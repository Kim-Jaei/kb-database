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
from employees e
join dept_emp on e.emp_no = dept_emp.emp_no
join departments d on dept_emp.dept_no = d.dept_no
where dept_emp.to_date like'9999%'
order by e.emp_no;

-- 6p
select d.dept_no, d.dept_name, count(*)
from departments d
join dept_emp de
on d.dept_no = de.dept_no
join employees e
on de.emp_no = e.emp_no
where de.to_date like '9999%'
group by d.dept_no
order by d.dept_no;