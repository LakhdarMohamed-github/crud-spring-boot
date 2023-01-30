package com.myProject.myProject;

import com.myProject.myProject.user.User;
import com.myProject.myProject.user.userRepository;
import lombok.ToString;
import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@ToString
public class UserRepositoryTest {
    @Autowired
    private userRepository repo;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("moha@gmail.com");
        user.setPassword("1234");
        user.setFirstName("moha");
        user.setLastName("med");

        User savedUser = repo.save(user);

        //assertThat(savedUser, is(notNullValue()));
        //assertThat(savedUser.getId(), is(greaterThan(0)));
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

    }

    @Test
    public void testListAll() {
        Iterable<User> users = repo.findAll();
        //assertThat(users, is(hasSize(greaterThan(0))));
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void testUpdate() {

       /* User user = repo.findByEmail("moha@gmail.com");

        if(user != null){
            user.setEmail("med@gmail.com");
        }*/

        Integer userId = 1;

        Optional<User> optionalUser = repo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setPassword("azerty");
            repo.save(user);

            User updateUser = repo.findById(userId).get();
            Assertions.assertThat(updateUser.getPassword()).isEqualTo("azerty");
        } else {
            System.out.println("Nooop");
        }

    }

    @Test
    public void testGet() {
        Integer userId = 1;
        Optional<User> optionalId = repo.findById(userId);
        /*if (optionalId.isPresent()) {
            User user = optionalId.get();
        }*/
        Assertions.assertThat(optionalId.isPresent());
        System.out.println(optionalId.get());
    }

    @Test
    public void testDelete(){
        Integer userId = 1;
        repo.deleteById(userId);

        Optional<User> id = repo.findById(userId);
        Assertions.assertThat(id).isNotPresent();

    }


}














