package org.binar.ChallengeChapter5BackEndJava.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomResponseJson {
    private String message;
    private String statusCode;
}
