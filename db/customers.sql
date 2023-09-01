CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers(first_name, last_name, age, country) values
('Pety', 'Petev', 20, 'Russia'), ('Ignasio', 'Proebido', 30, 'Brasilia'),
('Sam', 'Gamgee', 40, 'Shire'), ('Frodo', 'Sumkin', 50, 'Buckland'), ('弥', '海砂', 20, 'Japan');

select * from customers where age = (select min(age) from customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders(amount, customer_id) values(1, 1), (2, 2), (3, 3);

select * from customers where customers.id not in (select orders.customer_id from orders);