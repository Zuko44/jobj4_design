create or replace procedure delete_data(u_id integer)
language 'plpgsql'
as $$
    BEGIN
		delete from products where id = u_id and count = 0;
    END;
$$;

call delete_data(13);
select * from products;

create or replace function deleted_data(u_id integer)
returns void
language 'plpgsql'
as
$$
    begin
        delete from products where id = u_id and count > 0;
    end;
$$;

select deleted_data(14);
select * from products;