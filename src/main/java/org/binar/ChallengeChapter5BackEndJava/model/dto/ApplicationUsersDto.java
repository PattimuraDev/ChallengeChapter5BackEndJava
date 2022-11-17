package org.binar.ChallengeChapter5BackEndJava.model.dto;

import lombok.Data;

@Data
public class ApplicationUsersDto {
    private Long id;
    private String username;
    private String emailAddress;
    private String password;
}
