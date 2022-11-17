package org.binar.ChallengeChapter5BackEndJava.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.binar.ChallengeChapter5BackEndJava.model.ApplicationUsers;
import org.binar.ChallengeChapter5BackEndJava.model.dto.ApplicationUsersDto;
import org.binar.ChallengeChapter5BackEndJava.repository.ApplicationUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserServicesImpl implements UserServices{
    @Autowired
    ApplicationUsersRepository applicationUsersRepository;

    /**
     * Method yang digunakan untuk menambah user
     * @param users parameter objek user yang ingin ditambahkan
     * @return user yang telah ditambahkan
     */
    @Override
    public ApplicationUsers addUser(ApplicationUsers users) {
        return applicationUsersRepository.save(users);
    }


    /**
     * Method yang digunakan untuk mengupdate data user
     * @param idUser parameter untuk idUser
     * @param users  parameter untuk objek user
     * @return data user yang sudah diupdate
     */
    @Override
    public ApplicationUsers updateUser(Long idUser, ApplicationUsers users) {
        Optional<ApplicationUsers> usersOptional = applicationUsersRepository.findById(idUser);
        final ApplicationUsers result = usersOptional.get();
        result.setEmailAddress(users.getEmailAddress());
        result.setPassword(users.getPassword());
        result.setUsername(users.getUsername());
        return applicationUsersRepository.save(result);
    }


    /**
     * Method yang digunakan untuk menghapus delete user
     * @param idUser parameter untuk id dari user
     */
    @Override
    public void deleteUser(Long idUser) {
        applicationUsersRepository.deleteById(idUser);
    }

    //mapper
    ObjectMapper mapper = new ObjectMapper();

    /**
     * Method yang digunakan untuk memetakan objek ApplicationUsersDto menjadi ApplicationUsers
     * @param applicationUsersDto parameter untuk objek ApplicationUsersDto
     * @return hasil pemetaan menjadi objek ApplicationUsersDto
     */
    @Override
    public ApplicationUsers mapToEntity(ApplicationUsersDto applicationUsersDto) {
        return mapper.convertValue(applicationUsersDto, ApplicationUsers.class);
    }
}
