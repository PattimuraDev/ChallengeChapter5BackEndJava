create or replace procedure DELETE_SCHEDULE_BY_FILM_CODE(in input_kode_film bigint)
as
$$
begin
delete from schedules where films_code = input_kode_film;
end;
$$
language plpgsql;