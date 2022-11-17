package org.binar.ChallengeChapter5BackEndJava.repository;
import org.binar.ChallengeChapter5BackEndJava.model.Films;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FilmsRepository extends JpaRepository<Films, Long> {
    @Query(value = "select * from GET_ALL_FILMS_IS_PLAYING()", nativeQuery = true)
    List<Films> repoGetFilmIsPlaying();

    @Query(value = "call UPDATE_FILM_NAME(:input_nama_film_lama, :input_nama_film_baru)", nativeQuery = true)
    @Modifying
    void repoUpdateFilmByName(
            @Param("input_nama_film_lama") String judulFilmAsli,
            @Param("input_nama_film_baru") String judulFilmBaru
    );


}
