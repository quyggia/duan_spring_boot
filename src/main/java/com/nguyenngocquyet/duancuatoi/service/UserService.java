package com.nguyenngocquyet.duancuatoi.service;


import com.nguyenngocquyet.duancuatoi.dto.request.CreateUserRequest;
import com.nguyenngocquyet.duancuatoi.dto.request.UpdateUserRequest;
import com.nguyenngocquyet.duancuatoi.entity.User;
import com.nguyenngocquyet.duancuatoi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(CreateUserRequest request) {

        User user = new User();

        if(userRepository.existsUserByUsername(request.getUsername())) {
            throw new RuntimeException("User exited.");
        }

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());


        return userRepository.save(user);
    }

    public User updateUser(String id, UpdateUserRequest request)
    {
        User user = getUserById(id);

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public void deleteUser(String id)
    {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public User getUserById(String id)
    {
        return userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));
    }
}
