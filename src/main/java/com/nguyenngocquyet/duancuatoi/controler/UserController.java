package com.nguyenngocquyet.duancuatoi.controler;

import com.nguyenngocquyet.duancuatoi.dto.request.CreateUserRequest;
import com.nguyenngocquyet.duancuatoi.dto.request.UpdateUserRequest;
import com.nguyenngocquyet.duancuatoi.dto.respon.ApiResponse;
import com.nguyenngocquyet.duancuatoi.dto.respon.UserResponse;
import com.nguyenngocquyet.duancuatoi.entity.User;
import com.nguyenngocquyet.duancuatoi.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {
    final UserService userService;

    @PostMapping
    public ApiResponse<User> createUser(@RequestBody @Valid CreateUserRequest request) {

        ApiResponse<User> apiResponse = new ApiResponse<>();
        User  user = userService.createUser(request);

        apiResponse.setMessage("success");
        apiResponse.setResult(user);
        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
       ApiResponse<List<UserResponse>> apiResponse = new ApiResponse<>();
       List<UserResponse> allUsers  =userService.getAllUsers();

       apiResponse.setMessage("success");
       apiResponse.setResult(allUsers);

       return apiResponse;
    }

    @PostMapping("/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        UserResponse userResponse = userService.getUserById(userId);
        apiResponse.setMessage("Get user by ID successfully.");
        apiResponse.setResult(userResponse);
        return apiResponse;
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(@PathVariable("userId") String userId, @RequestBody @Valid UpdateUserRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        UserResponse userResponse = userService.updateUser(userId, request);

        apiResponse.setMessage("Update user successfully.");
        apiResponse.setResult(userResponse);

        return apiResponse;
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        ApiResponse<String> apiResponse = new ApiResponse<>();

        apiResponse.setMessage("Deleted done");
        apiResponse.setResult("Deleted done");
        return apiResponse;
    }
}
