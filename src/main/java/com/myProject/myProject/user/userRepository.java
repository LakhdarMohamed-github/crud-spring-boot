package com.myProject.myProject.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface userRepository extends CrudRepository<User, Integer> {
    //User findByEmail(String email);
    Integer countById(Integer count);
}
