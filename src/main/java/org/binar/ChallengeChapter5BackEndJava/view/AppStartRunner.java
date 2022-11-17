package org.binar.ChallengeChapter5BackEndJava.view;

import org.binar.ChallengeChapter5BackEndJava.model.*;
import org.binar.ChallengeChapter5BackEndJava.service.FilmsServicesImpl;
import org.binar.ChallengeChapter5BackEndJava.service.SchedulesServicesImpl;
import org.binar.ChallengeChapter5BackEndJava.service.SeatsServicesImpl;
import org.binar.ChallengeChapter5BackEndJava.service.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Profile("!test")
@Component
public class AppStartRunner implements CommandLineRunner {
    @Autowired
    FilmsServicesImpl filmsService;

    @Autowired
    SeatsServicesImpl seatsService;

    @Autowired
    UserServicesImpl userService;

    @Autowired
    SchedulesServicesImpl scheduleServices;

    /**
     * running console
     *
     * @param args arguments
     */
    @Override
    public void run(String... args) {
        //initializeDb(); // only run once
        consoleRunner();
    }

    /**
     * Fungsi untuk menjalankan console
     */
    private void consoleRunner() {
        boolean terminalRunning = true;
        int pilihan;
        Scanner input = new Scanner(System.in);

        while (terminalRunning) {
            mainMenu();
            System.out.print("Pilihan: ");
            pilihan = input.nextInt();
            switch (pilihan) {
                case 1:
                    List<Films> listFilmIsPlaying = filmsService.getFilmIsPlaying();
                    for (Films films : listFilmIsPlaying) {
                        System.out.println(films.getFilmName());
                    }
                    System.out.println();
                    break;
                case 2:
                    input.nextLine();
                    System.out.print("Masukkan judul film: ");
                    String inputJudul = input.nextLine();
                    List<Schedules> listSchedules = scheduleServices.schedulesOfFilmsByName(inputJudul);
                    for (Schedules schedules : listSchedules) {
                        System.out.println("Jadwal tayang: " +
                                schedules.getDate() +
                                " " +
                                schedules.getStartTime() +
                                "-" +
                                schedules.getEndTime() +
                                "\nStudio: " +
                                schedules.getStudioName() +
                                "\nHarga tiket: " +
                                schedules.getTicketPrice() +
                                "\n"
                        );
                    }
                    break;
                case 3:
                    List<Seats> seatsAvailable = seatsService.getAllSeatsAvailable();
                    List<Schedules> listSchedule = new ArrayList<>();
                    List<Films> listFilm = new ArrayList<>();
                    for (int i = 0; i < seatsAvailable.size(); i++) {
                        listSchedule.add(i, scheduleServices.findScheduleById(seatsAvailable.get(i).getScheduleID()));
                    }

                    for (int i = 0; i < listSchedule.size(); i++) {
                        listFilm.add(i, filmsService.getFilmById(listSchedule.get(i).getFilmsCode()));
                    }

                    for (int i = 0; i < seatsAvailable.size(); i++) {
                        System.out.println("Nomor seat: " +
                                seatsAvailable.get(i).getSeatNumberCompositeKey().getNomorBarisKursi() +
                                seatsAvailable.get(i).getSeatNumberCompositeKey().getNomorKolomKursi() +
                                "\nStatus: " +
                                seatsAvailable.get(i).getStatus() +
                                "\nStudio: " +
                                seatsAvailable.get(i).getStudioName() +
                                "\nJudul film: " +
                                listFilm.get(i).getFilmName() +
                                "\nJadwal tayang: " +
                                listSchedule.get(i).getDate() +
                                " " +
                                listSchedule.get(i).getStartTime() +
                                "-" +
                                listSchedule.get(i).getEndTime() +
                                "\n"
                        );
                    }
                    System.out.println();
                    break;
                case 4:
                    input.nextLine();
                    System.out.print("Masukkan nomor baris kursi: ");
                    String inputNomorBarisKursi = input.nextLine();
                    System.out.print("Masukkan nomor kolom kursi: ");
                    String inputNomorKolomKursi = input.nextLine();
                    seatsService.updateSeatsStatus(
                            "not available",
                            inputNomorBarisKursi,
                            inputNomorKolomKursi
                    );
                    break;
                case 0:
                    terminalRunning = false;
                    break;
                default:
                    break;
            }
        }
        System.exit(0);
    }

    /**
     * Fungsi untuk menampilkan main menu
     */
    private void mainMenu() {
        System.out.println("=====\n" +
                "Main Menu\n" +
                "=====\n" +
                "1. Film ditayangkan saat ini\n" +
                "2. Jadwal film, studio, harga tiket\n" +
                "3. Seats tersedia\n" +
                "4. Reservase tiket\n" +
                "0. Akhiri aplikasi\n" +
                "=====\n"
        );
    }

    /**
     * Fungsi untuk menginisialisasi database di awal program dijalankan (dijalankan sekali saja)
     */
    private void initializeDb() {
        // initialize user table
        ApplicationUsers applicationUsers = new ApplicationUsers(null, "Patra", "patra@email.com", "12345");
        userService.addUser(applicationUsers);

        //initialize film and schedules table
        Films films = new Films();
        films.setFilmName("Ini judul film 1");
        films.setFilmCode(null);
        films.setIsPlaying(true);
        filmsService.addFilms(films);

        List<Schedules> schedulesListOfFilms = new ArrayList<>();
        schedulesListOfFilms.add(new Schedules(null, films.getFilmCode(), "11 Juli", "19.00", "21.00", 20000L, "Studio B"));
        schedulesListOfFilms.add(new Schedules(null, films.getFilmCode(), "13 Juli", "15.00", "17.00", 15000L, "Studio D"));
        films.setSchedulesList(schedulesListOfFilms);
        filmsService.addFilms(films);

        Films films2 = new Films();
        films2.setFilmName("Ini judul film 2");
        films2.setFilmCode(null);
        films2.setIsPlaying(false);
        filmsService.addFilms(films2);

        List<Schedules> schedulesListOfFilms2 = new ArrayList<>();
        schedulesListOfFilms2.add(new Schedules(null, films2.getFilmCode(), "12 Juli", "14.00", "17.00", 35000L, "Studio E"));
        schedulesListOfFilms2.add(new Schedules(null, films2.getFilmCode(), "17 Juli", "16.00", "19.00", 25000L, "Studio C"));
        films2.setSchedulesList(schedulesListOfFilms2);
        filmsService.addFilms(films2);

        Films films3 = new Films();
        films3.setFilmName("Ini judul film 3");
        films3.setFilmCode(null);
        films3.setIsPlaying(true);
        filmsService.addFilms(films3);

        List<Schedules> schedulesListOfFilms3 = new ArrayList<>();
        schedulesListOfFilms3.add(new Schedules(null, films3.getFilmCode(), "20 Juli", "20.00", "23.00", 35000L, "Studio A"));
        schedulesListOfFilms3.add(new Schedules(null, films3.getFilmCode(), "21 Juli", "09.00", "12.00", 25000L, "Studio C"));
        films3.setSchedulesList(schedulesListOfFilms3);
        filmsService.addFilms(films3);

        //initialize seats table
        Seats seats1 = new Seats(new SeatNumberCompositeKey("1", "A"), "Studio A", "available", 1L);
        Seats seats2 = new Seats(new SeatNumberCompositeKey("1", "B"), "Studio A", "available", 1L);
        Seats seats3 = new Seats(new SeatNumberCompositeKey("2", "A"), "Studio B", "available", 2L);
        Seats seats4 = new Seats(new SeatNumberCompositeKey("2", "B"), "Studio B", "available", 2L);
        Seats seats5 = new Seats(new SeatNumberCompositeKey("3", "A"), "Studio C", "available", 2L);
        Seats seats6 = new Seats(new SeatNumberCompositeKey("3", "B"), "Studio C", "available", 3L);
        Seats seats7 = new Seats(new SeatNumberCompositeKey("4", "A"), "Studio D", "available", 3L);
        Seats seats8 = new Seats(new SeatNumberCompositeKey("4", "B"), "Studio D", "available", 3L);
        Seats seats9 = new Seats(new SeatNumberCompositeKey("5", "A"), "Studio E", "available", 3L);
        Seats seats10 = new Seats(new SeatNumberCompositeKey("5", "B"), "Studio E", "available", 2L);

        seatsService.addSeats(seats1);
        seatsService.addSeats(seats2);
        seatsService.addSeats(seats3);
        seatsService.addSeats(seats4);
        seatsService.addSeats(seats5);
        seatsService.addSeats(seats6);
        seatsService.addSeats(seats7);
        seatsService.addSeats(seats8);
        seatsService.addSeats(seats9);
        seatsService.addSeats(seats10);
    }
}
