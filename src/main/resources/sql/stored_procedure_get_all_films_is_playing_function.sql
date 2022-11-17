create or replace function GET_ALL_FILMS_IS_PLAYING()
returns table(film_code bigint, film_name character varying, is_playing boolean)
as
$$
begin
return query select * from films where films.is_playing=true;
end;
$$
language plpgsql VOLATILE;
