insert into roles(name) values('programmer');
insert into roles(name) values('damn_manager');

insert into rules(name) values('program');
insert into rules(name) values('whip the programmers');

insert into roles_rules(roles_id, rules_id) values(9, 9);
insert into roles_rules(roles_id, rules_id) values(10, 10);

insert into users(name, role_id) values('Vanya the slave', 9);
insert into users(name, role_id) values('Vasya the sadist', 10);

insert into categories(name) values('model applications');
insert into categories(name) values('individual applications');

insert into states(name) values('requires additional processing');
insert into states(name) values('requires manager approval');

insert into items(name, date_of_items, users_id, categories_id, states_id) values(
	'application for advanced training', '2023-08-16', 6, 13, 14);
	insert into items(name, date_of_items, users_id, categories_id, states_id) values(
	'new whip for particularly brutal beating of programmers', '2023-08-16', 7, 14, 14);
	
insert into comments(date_of_comments, comment, items_id) values(
	'2023-08-16', 'send Vanya in three letters', 9);
insert into comments(date_of_comments, comment, items_id) values(
	'2023-08-16', 'praise Vasya and assign a bonus', 10);
	
insert into attachs(name, items_id) values(pg_read_file('C:\Program Files\PostgreSQL\Retina.txt')::bytea, 9);