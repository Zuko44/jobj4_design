create table roles(
	id serial primary key,
	name varchar(255)
);

create table rules(
	id serial primary key,
	name varchar(255)
);

create table roles_rules(
	id serial primary key,
	roles_id int references roles(id),
	rules_id int references rules(id)
);

create table users(
	id serial primary key,
	name varchar(255),
	role_id int references roles(id)
);

create table categories(
	id serial primary key,
	name varchar(255)
);

create table states(
	id serial primary key,
	name varchar(255)
);

create table items(
	id serial primary key,
	name varchar(255),
	date_of_items date,
	users_id int references users(id),
	categories_id int references categories(id),
	states_id int references states(id)
);

create table attachs(
	id serial primary key,
	name bytea,
	items_id int references items(id)
);

create table comments(
	id serial primary key,
	date_of_comments date,
	comment varchar(255),
	items_id int references items(id)
);