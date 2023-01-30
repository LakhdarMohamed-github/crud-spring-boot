package com.myProject.myProject.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {
    @Autowired
    private userRepository repo;

    public List<User> listAll(){
        return (List<User>)  repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> res = repo.findById(id);
        if(res.isPresent()) {
            return res.get();
        }
        throw new UserNotFoundException("Could not find any user id: "+id);

    }

    public void deleteUser(Integer id) throws UserNotFoundException {
        Integer count = repo.countById(id);
        if(count == null && count == 0){
            throw new UserNotFoundException("Could not find any user id: "+id);
        }
        repo.deleteById(id);
    }
}








