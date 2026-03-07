package com.nguyenngocquyet.duancuatoi.service;


import com.nguyenngocquyet.duancuatoi.dto.request.CreateUserRequest;
import com.nguyenngocquyet.duancuatoi.dto.request.UpdateUserRequest;
import com.nguyenngocquyet.duancuatoi.dto.respon.UserResponse;
import com.nguyenngocquyet.duancuatoi.entity.User;
import com.nguyenngocquyet.duancuatoi.exception.AppException;
import com.nguyenngocquyet.duancuatoi.exception.ErrorCode;
import com.nguyenngocquyet.duancuatoi.mapper.UserMapper;
import com.nguyenngocquyet.duancuatoi.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService {

    final UserRepository userRepository;

    final UserMapper userMapper;
    public User createUser(CreateUserRequest request) {
        if(userRepository.existsUserByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);

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
        userRepository.deleteById(id);
    }

    public List<UserResponse> getAllUsers()
    {
        return userRepository.findAll()
                .stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .dob(user.getDob())
                        .build())
                .toList();
    }

    public UserResponse getUserById(String id)
    {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found")));
    }
}
