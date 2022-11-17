create or replace function all_seats_available() 
returns table(nomor_baris_kursi character varying, nomor_kolom_kursi character varying, status character varying, studio_name character varying, schedule_id bigint)
as
$$
begin
return query
select seats.nomor_baris_kursi, seats.nomor_kolom_kursi, seats.status, seats.studio_name, seats.schedule_id
from seats where seats.status = 'available';
end;
$$
language plpgsql;



