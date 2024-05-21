package com.example.individualwebservice.services;

import com.example.individualwebservice.Repositories.UserRepository;
import com.example.individualwebservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            return user;
        } else {

            return null;
        }
    }

    public User createNewUser(User userInformation) {
        if (userInformation.getFirstName() != null && userInformation.getLastName() != null && userInformation.getAddress() != null && userInformation.getEmail() != null && userInformation.getPhone() != null && userInformation.getMemberType() != null) {

            User user = new User(userInformation.getFirstName(), userInformation.getLastName(), userInformation.getAddress(), userInformation.getEmail(), userInformation.getPhone(), userInformation.getMemberType());

            userRepository.save(user);

            return user;
        } else {

            return null;
        }
    }
}
