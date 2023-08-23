create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
	type_id int references type(id),
	expired_date date,
    price float,
	quantity int
);

insert into type(name) values('мясо');
insert into type(name) values('рыба');
insert into type(name) values('трава');
insert into type(name) values('всякая херня');
insert into type(name) values('сыр');
insert into type(name) values('молоко');

insert into product(name, type_id, expired_date, price, quantity)
values('мороженое детка в городе', 4, '2023-07-01', '100', '1000');
insert into product(name, type_id, expired_date, price, quantity)
values('молоко бабка в деревне', 6, '2023-09-01', '70', '5');
insert into product(name, type_id, expired_date, price, quantity)
values('Сыр плавленный', 5, '2023-09-01', '160', '2');
insert into product(name, type_id, expired_date, price, quantity)
values('Сыр моцарелла', 5, '2023-09-01', '235', '2');
insert into product(name, type_id, expired_date, price, quantity)
values('кокаин', 3, '2023-01-01', '7000', '22');
insert into product(name, type_id, expired_date, price, quantity)
values('телятина, запечённая в листьях коки', 1, '2023-01-01', '7000', '25');
insert into product(name, type_id, expired_date, price, quantity)
values('камбала', 2, '2023-09-01', '300', '35');

select * from product where type_id = 5;

select * from product where name like '%мороженое%';

select * from product where expired_date < NOW();

select name, price from product
where price = (select max(price) from product);

select type.name as "имя типа", count(p.type_id) as количество from type 
inner join product as p on type.id = p.type_id
group by type.name;

--select * from product where type_id > 4;
select * from product where type_id = 5 or type_id = 6;

select type.name as "имя типа", count(p.type_id) as количество,
sum(p.quantity) as остатки from type 
inner join product as p on type.id = p.type_id
group by type.name having sum(p.quantity) < 10;