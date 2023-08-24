insert into car_bodies(name) values('ВАЗ-2101');
insert into car_bodies(name) values('ЗАЗ-968А');
insert into car_bodies(name) values('ВАЗ-2102');
insert into car_bodies(name) values('ВАЗ-2104');
insert into car_bodies(name) values('ГАЗ-22');

insert into car_engines(name) values('Карбюраторный');
insert into car_engines(name) values('Инжекторный');
insert into car_engines(name) values('Газодизельный');
insert into car_engines(name) values('Дизельный');
insert into car_engines(name) values('с искровым зажиганием');

insert into car_transmissions(name) values('Механическая');
insert into car_transmissions(name) values('Автоматическая');
insert into car_transmissions(name) values('Роботизированная');
insert into car_transmissions(name) values('Вариативная');
insert into car_transmissions(name) values('фиговая');

insert into cars(name, body_id, engine_id, transmission_id) 
values('Тарантайка', 1, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) 
values('Драндулет', 1, 2, 2);
insert into cars(name, body_id, engine_id, transmission_id) 
values('Коробчонка', 3, 4, 3);
insert into cars(name, body_id, engine_id, transmission_id) 
values('Корыто', 4, 4, 5);
insert into cars(name, body_id, engine_id, transmission_id) 
values('Развалюха', 5, 5, 5);
insert into cars(name, body_id, engine_id, transmission_id) 
values('Порш', null, null, null);