create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values(
	'Acipenser medirostris, fish', '18250', '1854-01-01');
insert into fauna(name, avg_age, discovery_date) values(
	'Acipenser gueldenstaedtii', '18250', '1833-01-01');
insert into fauna(name, avg_age, discovery_date) values(
	'Acipenser baerii, fish', '18250', '1869-01-01');
	
insert into fauna(name, avg_age, discovery_date) values(
	'Hippopótamus amphíbius', '20000', '1758-01-01');
insert into fauna(name, avg_age, discovery_date) values(
	'Felis catus', '7300', '1758-01-01');	
	
insert into fauna(name, avg_age, discovery_date) values(
	'Canis lupus', '2000', null);	
	
insert into fauna(name, avg_age, discovery_date) values(
	'Strix hadorami', '1825', '2015-01-01');
==========================================================
select * from fauna where name like '%fish%';

select * from fauna where avg_age between '10000' and '21000';

select * from fauna where discovery_date isnull;

select * from fauna where discovery_date < '1950-01-01';