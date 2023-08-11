create table position(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    position_id int references position(id)
);

insert into position(name) values ('programmer');
insert into employees(name, position_id) VALUES ('Ivan', 1);

select * from employees;

select * from position where id in (1);