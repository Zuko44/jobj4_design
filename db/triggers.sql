create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function tax()
    returns trigger as
$$
    BEGIN
        NEW.price = NEW.price + NEW.price * 0.13;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    before insert
    on products
    for each row
    execute procedure tax();
	
insert into products(name, producer, price) values('bread', 'old witch', '100');
insert into products(name, producer, price) values('vine', 'old fag', '1000');
select * from products;

create or replace function big_tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.13
		where id = (select id from inserted);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger big_tax
    after insert on products
	referencing new table as inserted
    for each STATEMENT
    execute procedure big_tax();
	
insert into products(name, producer, price) values('beer', 'white bear', '500');
select * from products;

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function change_history()
    returns trigger as
$$
    BEGIN
        INSERT INTO history_of_price(name, price, date)
        VALUES(NEW.name, NEW.price, current_date);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger change_history
    after insert
    on products
    for each row
    execute procedure change_history();
	
insert into products(name, producer, price) values('banan', 'don banan', '89');
select * from history_of_price;
select * from products;