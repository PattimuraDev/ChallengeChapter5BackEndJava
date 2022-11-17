create or replace procedure UPDATE_FILM_NAME(in input_nama_film_lama character varying, in input_nama_film_baru character varying)
as
$$
begin
update films set film_name = input_nama_film_baru where
films.film_name = input_nama_film_lama;
end;
$$
language plpgsql;

