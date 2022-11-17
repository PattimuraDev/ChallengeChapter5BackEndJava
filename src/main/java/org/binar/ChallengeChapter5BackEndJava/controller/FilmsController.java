package org.binar.ChallengeChapter5BackEndJava.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.binar.ChallengeChapter5BackEndJava.model.CustomResponseJson;
import org.binar.ChallengeChapter5BackEndJava.model.Films;
import org.binar.ChallengeChapter5BackEndJava.model.dto.FilmsDto;
import org.binar.ChallengeChapter5BackEndJava.service.FilmsServicesImpl;
import org.binar.ChallengeChapter5BackEndJava.service.SchedulesServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "FILMS")
@RestController
@RequestMapping("/films")
public class FilmsController {

    @Autowired
    FilmsServicesImpl filmServices;

    @Autowired
    SchedulesServicesImpl scheduleServices;

    /**
     * method controller untuk kebutuhan menambah film baru
     * @param filmsDto parameter data transfer object dari sebuah film
     * @return response entity hasil dari response endpoint API
     */
    @Operation(summary = "Endpoint untuk menambahkan film baru")
    @ApiResponse(
            responseCode = "201",
            description = "Data film baru berhasil dimasukkan",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FilmsDto.class)
            )
    )
    @PostMapping("/create_films")
    public ResponseEntity<Films> createFilms(@RequestBody FilmsDto filmsDto) {
        try{
            Films film = filmServices.addFilms(filmServices.filmDtoMapToEntity(filmsDto));
            for (int i = 0; i < film.getSchedulesList().size(); i++) {
                scheduleServices.updateFilmCodeOfSchedules(film.getSchedulesList().get(i).getSchedulesId(), film.getFilmCode());
                film.getSchedulesList().get(i).setFilmsCode(film.getFilmCode());
            }
            return new ResponseEntity<>(film, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /**
     * Method controller untuk memenuhi kebutuhan update judul film berdasarkan judul film awal
     * @param namaFilmAwal parameter untuk judul film awal
     * @param namaFilmBaru parameter untuk judul film yang baru
     * @return response entity hasil dari response endpoint API
     */
    @Operation(summary = "Endpoint untuk mengupdate judul film")
    @ApiResponse(
            responseCode = "200",
            description = "Data film berhasil diupdate"
    )
    @PutMapping("/update_film")
    public ResponseEntity<CustomResponseJson> updateFilmName(
            @RequestParam("input_nama_film_lama") String namaFilmAwal,
            @RequestParam("input_nama_film_baru") String namaFilmBaru
    ) {
        try{
            filmServices.updateFilmByName(namaFilmAwal, namaFilmBaru);
            return new ResponseEntity<>(
                    new CustomResponseJson(
                            "Update judul film berhasil",
                            "200"
                    ),
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new CustomResponseJson(
                            "Operasi mengupdate judul film gagal",
                            "500"
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    /**
     * Method controller untuk mengatur kebutuhan melihat film yang sedang tayang
     * @return list dari semua film yang sedang tayang saat ini
     */
    @Operation(summary = "Endpoint untuk menampilkan film yang tayang pada saat ini")
    @ApiResponse(
            responseCode = "200",
            description = "Semua data film berhasil didapatkan",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Films.class))
            )
    )
    @GetMapping("/films_is_playing")
    public List<Films> getFilmIsPlaying() {
        return filmServices.getFilmIsPlaying();
    }


    /**
     * Method controller untuk mengakomodasi kebutuhan hapus film berdasarkan id film
     * @param idFilm parameter untuk id dari film
     * @return response entity hasil dari response endpoint API
     */
    @Operation(summary = "Endpoint untuk menghapus data film")
    @ApiResponse(
            responseCode = "200",
            description = "Film berhasil dihapus"
    )
    @DeleteMapping("/delete_film/{id}")
    public ResponseEntity<CustomResponseJson> deleteFilms(@PathVariable("id") Long idFilm) {
        try{
            scheduleServices.deleteSchedulesByFilmId(idFilm);
            filmServices.deleteFilmById(idFilm);
            return new ResponseEntity<>(
                    new CustomResponseJson(
                            "Film berhasil dihapus",
                            "200"
                    ),
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new CustomResponseJson(
                            "Operasi menghapus film gagal",
                            "500"
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
