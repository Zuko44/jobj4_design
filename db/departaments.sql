create table departaments(
	id serial primary key,
	name varchar(255)
);

create table employees(
	id serial primary key,
	name varchar(255),
	departaments_id int references departaments(id)
);

insert into departaments(name) values('тролли');
insert into departaments(name) values('чвк');
insert into departaments(name) values('логистики и поставки питания');
insert into departaments(name) values('отмывания денег');

insert into employees(name, departaments_id) values('Вася', 1);
insert into employees(name, departaments_id) values('Петя', 2);
insert into employees(name, departaments_id) values('Лёша', 3);

select * from employees as e left join departaments as d on e.departaments_id = d.id;

select * from employees as e right join departaments as d on e.departaments_id = d.id;

select * from employees as e full join departaments as d on e.departaments_id = d.id;

select * from employees as e cross join departaments as d;

select e.name, d.name from
departaments as d left join employees as e on d.id = e.departaments_id
where e.name is null;

select * from employees as e left join departaments as d
on e.departaments_id = d.id;
select * from employees as e right join departaments as d
on e.departaments_id = d.id where e.name is not null;

create table teens(
	id serial primary key,
	name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) values('Лёша', 'Мужчина');
insert into teens(name, gender) values('Петя', 'Женщина');
insert into teens(name, gender) values('Женя', 'Транс');
insert into teens(name, gender) values('Стася', 'Андрогин');
insert into teens(name, gender) values('Буратино', 'Бигендер');
insert into teens(name, gender) values('Мальвина', 'Мужчина');
insert into teens(name, gender) values('Чебурашка', 'Женщина');
insert into teens(name, gender) values('Шапокляк', 'Транс');
insert into teens(name, gender) values('Гена', 'Андрогин');
insert into teens(name, gender) values('Миша', 'Бигендер');
insert into teens(name, gender) values('Гриша', 'Мужчина');
insert into teens(name, gender) values('Федя', 'Женщина');
insert into teens(name, gender) values('Антошка', 'Транс');
insert into teens(name, gender) values('Картошка', 'Андрогин');
insert into teens(name, gender) values('Киря', 'Бигендер');

select t1.id, t1.name, t1.gender, t2.id, t2.name, t2.gender from teens 
t1 cross join teens t2 where t1.gender != t2.gender and t2.id > t1.id;