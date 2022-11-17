package org.binar.ChallengeChapter5BackEndJava.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.binar.ChallengeChapter5BackEndJava.model.ApplicationUsers;
import org.binar.ChallengeChapter5BackEndJava.model.CustomResponseJson;
import org.binar.ChallengeChapter5BackEndJava.model.dto.ApplicationUsersDto;
import org.binar.ChallengeChapter5BackEndJava.service.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "USER")
@RestController
@RequestMapping("/users")
public class ApplicationUsersController {

    @Autowired
    UserServicesImpl userServices;

    /**
     * Method controller untuk mengakomodasi kebutuhan menambah user baru
     * @param usersDto parameter data transfer onject untuk user
     * @return response entity hasil dari response endpoint API
     */
    @Operation(summary = "Endpoint untuk menambahkan user baru")
    @ApiResponse(
            responseCode = "201",
            description = "User baru berhasil ditambahkan",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationUsersDto.class)
            )
    )
    @PostMapping("/create_users")
    public ResponseEntity<ApplicationUsers> createUsers(@RequestBody ApplicationUsersDto usersDto) {
        final ApplicationUsers result = userServices.addUser(userServices.mapToEntity(usersDto));
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Method controller untuk mengakomodasi kebutuhan mengupdate data user
     * @param idUsers  paramater untuk id dari user
     * @param usersDto parameter untuk data transfer object dari user
     * @return response entity hasil dari response endpoint API
     */
    @Operation(summary = "Endpoint untuk mengupdate data user")
    @ApiResponse(
            responseCode = "200",
            description = "Data user berhasil diupdate",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationUsersDto.class)
            )
    )
    @PutMapping("/update_users/{id}")
    public ResponseEntity<ApplicationUsers> updateUsers(@PathVariable("id") Long idUsers, @RequestBody ApplicationUsersDto usersDto) {
        final ApplicationUsers result = userServices.updateUser(idUsers, userServices.mapToEntity(usersDto));
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Method controller untuk mengakomodasi kebutuhan untuk menghapus data user berdasarkan id user
     * @param idUsers parameter untuk id dari user
     * @return response entity hasil dari response endpoint API
     */
    @Operation(summary = "Endpoint untuk menghapus data user")
    @ApiResponse(
            responseCode = "200",
            description = "Data user berhasil dihapus",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CustomResponseJson.class)
            )
    )
    @DeleteMapping("/delete_users/{id}")
    public ResponseEntity<CustomResponseJson> deleteUsers(@PathVariable("id") Long idUsers) {
        try {
            userServices.deleteUser(idUsers);
            return new ResponseEntity<>(
                    new CustomResponseJson(
                            "User berhasil dihapus",
                            "200"
                    ),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new CustomResponseJson(
                            "Operasi menghapus user gagal",
                            "500"
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
