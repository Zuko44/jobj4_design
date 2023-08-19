create table accounts(
    id serial primary key,
    acc_type varchar(255)
);

create table people(
    id serial primary key,
    name varchar(255),
    account_id int references accounts(id) unique
);

insert into accounts(acc_type) values('games');
insert into accounts(acc_type) values('movie');
insert into accounts(acc_type) values('porno');

insert into people(name, account_id) values ('Ivan', 1);
insert into people(name, account_id) values ('Boris', 2);
insert into people(name, account_id) values ('Petr', 3);
insert into people(name) values ('Vasya');
insert into people(name) values ('Anya');

select pp.name, acc.acc_type
from people as pp join accounts as acc on pp.account_id = acc.id;

select pp.name as Имя, acc.acc_type as Тип
from people as pp join accounts as acc on pp.account_id = acc.id;

select pp.name as "Имя владельца", acc.acc_type Тип
from people pp join accounts acc on pp.account_id = acc.id;