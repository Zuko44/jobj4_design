/*create view show_all_cars_and_bodies
    as select cars.id, cars.name as car_name, car_bodies.name as body_name,
car_engines.name as engine_name,
car_transmissions.name as transmission_name from cars
left join car_bodies on cars.body_id = car_bodies.id
left join car_engines on cars.engine_id = car_engines.id
left join car_transmissions on cars.transmission_id = car_transmissions.id;*/
select * from show_all_cars_and_bodies;