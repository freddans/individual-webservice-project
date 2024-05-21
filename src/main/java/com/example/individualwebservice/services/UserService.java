package com.example.individualwebservice.services;

import com.example.individualwebservice.Repositories.UserRepository;
import com.example.individualwebservice.entities.Address;
import com.example.individualwebservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private AddressService addressService;

    @Autowired
    public UserService(UserRepository userRepository, AddressService addressService) {
        this.userRepository = userRepository;
        this.addressService = addressService;
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

        User existingUser = userRepository.findUserByEmail(userInformation.getEmail());

        if (existingUser == null) {
            Address address = addressService.findAddressById(userInformation.getAddress().getId());

            if (userInformation.getFirstName() != null && userInformation.getLastName() != null && address != null && userInformation.getEmail() != null && userInformation.getPhone() != null && userInformation.getMemberType() != null) {

                User user = new User(userInformation.getFirstName(), userInformation.getLastName(), address, userInformation.getEmail(), userInformation.getPhone(), userInformation.getMemberType());

                userRepository.save(user);

                return user;

            } else {

                return null;
            }
        } else {

            return null;
        }
    }
}
