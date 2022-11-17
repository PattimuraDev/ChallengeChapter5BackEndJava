create or replace procedure UPDATE_FILM_CODE_ON_SCHEDULE(in kode_film bigint, in input_id_schedule bigint)
as
$$
begin
update schedules set films_code=kode_film where schedules_id=input_id_schedule;
end;
$$
language plpgsql;