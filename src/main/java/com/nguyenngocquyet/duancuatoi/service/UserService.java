package com.nguyenngocquyet.duancuatoi.service;


import com.nguyenngocquyet.duancuatoi.dto.request.CreateUserRequest;
import com.nguyenngocquyet.duancuatoi.dto.request.UpdateUserRequest;
import com.nguyenngocquyet.duancuatoi.dto.respon.UserResponse;
import com.nguyenngocquyet.duancuatoi.entity.User;
import com.nguyenngocquyet.duancuatoi.enums.Role;
import com.nguyenngocquyet.duancuatoi.exception.AppException;
import com.nguyenngocquyet.duancuatoi.exception.ErrorCode;
import com.nguyenngocquyet.duancuatoi.mapper.UserMapper;
import com.nguyenngocquyet.duancuatoi.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class UserService {

    final UserRepository userRepository;

    final UserMapper userMapper;

    final PasswordEncoder passwordEncoder;
    public User createUser(CreateUserRequest request) {
        if(userRepository.existsUserByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);


        return userRepository.save(user);
    }

    public UserResponse updateUser(String id, UpdateUserRequest request)
    {
        User user = userMapper.toUserResponse(getUserById(id));
        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String id)
    {

        getUserById(id);
        userRepository.deleteById(id);
    }

    public UserResponse getMyInfo()
    {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user = userRepository.findUserByUsername(username)
                .orElseThrow( () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers()
    {
        log.info("In method get users.");
        return userRepository.findAll()
                .stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .dob(user.getDob())
                        .roles(user.getRoles())
                        .build())
                .toList();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUserById(String id)
    {
        log.info("In method get users by id.");
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found")));
    }
}
