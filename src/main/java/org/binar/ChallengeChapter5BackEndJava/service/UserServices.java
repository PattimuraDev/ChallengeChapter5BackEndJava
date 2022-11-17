package org.binar.ChallengeChapter5BackEndJava.service;

import org.binar.ChallengeChapter5BackEndJava.model.ApplicationUsers;
import org.binar.ChallengeChapter5BackEndJava.model.dto.ApplicationUsersDto;

public interface UserServices {
    ApplicationUsers addUser(ApplicationUsers users);
    ApplicationUsers updateUser(Long idUser, ApplicationUsers users);
    void deleteUser(Long idUser);
    ApplicationUsers mapToEntity(ApplicationUsersDto applicationUsersDto);
}
