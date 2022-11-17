package org.binar.ChallengeChapter5BackEndJava.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.binar.ChallengeChapter5BackEndJava.model.CustomResponseJson;
import org.binar.ChallengeChapter5BackEndJava.model.Schedules;
import org.binar.ChallengeChapter5BackEndJava.model.dto.SchedulesDto;
import org.binar.ChallengeChapter5BackEndJava.service.SchedulesServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "SCHEDULES")
@RestController
@RequestMapping("/schedules")
public class SchedulesController {

    @Autowired
    SchedulesServicesImpl scheduleServices;

    /**
     * Method controller untuk mengakomodasi kebutuhan menambahkan schedule baru
     * @param schedulesDto parameter data transfer object untuk schedule
     * @return response entity hasil dari response endpoint API
     */
    @Operation(summary = "Endpoint untuk menambahkan schedule baru")
    @ApiResponse(
            responseCode = "201",
            description = "Schedule baru berhasil ditambahkan",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SchedulesDto.class)
            )
    )
    @PostMapping("/create_schedule")
    public ResponseEntity<Schedules> createSchedule(@RequestBody SchedulesDto schedulesDto){
        final Schedules result = scheduleServices.addSchedule(scheduleServices.scheduleDtoMapToEntity(schedulesDto));
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Method controller untuk mengakomodasi kebutuhan menghapus schedule
     * @param idSchedule parameter untuk id dari schedule yang ingin dihapus
     * @return response entity hasil dari response endpoint API
     */
    @Operation(summary = "Endpoint untuk menghapus schedule berdasarkan id")
    @ApiResponse(
            responseCode = "200",
            description = "Schedule berhasil dihapus",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CustomResponseJson.class)
            )
    )
    @DeleteMapping("/delete_schedule/{id}")
    public ResponseEntity<CustomResponseJson> deleteSchedule(@PathVariable("id") Long idSchedule){
        try{
            scheduleServices.deleteSchedule(idSchedule);
            return new ResponseEntity<>(
                    new CustomResponseJson(
                    "Schedule berhasil dihapus",
                            "200"
                    ),
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new CustomResponseJson(
                            "Operasi menghapus schedule gagal",
                            "500"
                    ),
                    HttpStatus.OK
            );
        }
    }
}
