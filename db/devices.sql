create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('apple', '39990');
insert into devices(name, price) values('xiaomi', '10627');
insert into devices(name, price) values('huyiawei', '21990');
insert into devices(name, price) values('infinix', '2690');

insert into people(name) values('poor');
insert into people(name) values('rich');
insert into people(name) values('middle');
insert into people(name) values('very poor');

insert into devices_people(device_id, people_id) values(1, 2);
insert into devices_people(device_id, people_id) values(2, 1);
insert into devices_people(device_id, people_id) values(3, 3);
insert into devices_people(device_id, people_id) values(4, 4);

insert into devices_people(device_id, people_id) values(1, 2);
insert into devices_people(device_id, people_id) values(2, 3);
insert into devices_people(device_id, people_id) values(3, 4);
insert into devices_people(device_id, people_id) values(4, 4);

select avg(price) from devices;

select people.name as rating, avg(price)
from devices_people as dp
inner join devices as d on dp.device_id = d.id
inner join people on dp.people_id = people.id
group by rating;
/*select dp.people_id as rating, avg(price)
from devices_people as dp
inner join devices d on dp.device_id = d.id
group by rating;*/

select people.name as rating, avg(price)
from devices_people as dp
inner join devices as d on dp.device_id = d.id 
inner join people on dp.people_id = people.id
group by rating
having avg(price) > 5000;