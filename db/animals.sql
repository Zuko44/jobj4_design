create table animals(
	id serial primary key,
	species_name varchar(255),
	area text	
);
insert into animals(species_name, area) values('Grizli', 'USA');
select * from animals;
update animals set species_name = 'Grizzli';
select * from animals;
delete from animals;
select * from animals;
insert into animals(species_name, area) values('Tiger', 'Far East');
select * from animals;