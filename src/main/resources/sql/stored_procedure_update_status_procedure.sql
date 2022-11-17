create or replace procedure UPDATE_SEATS(in input_nomor_baris_kursi character varying, in input_nomor_kolom_kursi character varying, in input_new_status character varying)
as
$$
begin
update seats set status = input_new_status where seats.nomor_baris_kursi = input_nomor_baris_kursi and seats.nomor_kolom_kursi = input_nomor_kolom_kursi;
end;
$$
language plpgsql;

