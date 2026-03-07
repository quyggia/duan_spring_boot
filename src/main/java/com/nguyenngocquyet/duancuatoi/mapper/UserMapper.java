package com.nguyenngocquyet.duancuatoi.mapper;


import com.nguyenngocquyet.duancuatoi.dto.request.CreateUserRequest;
import com.nguyenngocquyet.duancuatoi.dto.request.UpdateUserRequest;
import com.nguyenngocquyet.duancuatoi.dto.respon.UserResponse;
import com.nguyenngocquyet.duancuatoi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(CreateUserRequest userRequest);
    UserResponse toUserResponse(User user);
    User toUserResponse(UserResponse response);
    void updateUser(@MappingTarget User user, UpdateUserRequest request);
}
