package com.nguyenngocquyet.duancuatoi.controler;

import com.nguyenngocquyet.duancuatoi.dto.request.CreateUserRequest;
import com.nguyenngocquyet.duancuatoi.dto.request.UpdateUserRequest;
import com.nguyenngocquyet.duancuatoi.dto.respon.ApiRespon;
import com.nguyenngocquyet.duancuatoi.entity.User;
import com.nguyenngocquyet.duancuatoi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControler {

    @Autowired
    private UserService userService;

    @PostMapping
    public ApiRespon<User> createUser(@RequestBody @Valid CreateUserRequest request) {

        ApiRespon<User> apiRespon = new ApiRespon<>();
        User  user = userService.createUser(request);

        apiRespon.setMessage("success");
        apiRespon.setResult(user);
        return apiRespon;
    }

    @GetMapping
    public List<User> getUsers() {
       return userService.getAllUsers();
    }

    @PostMapping("/{userId}")
    public User getUser(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable("userId") String userId, @RequestBody UpdateUserRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return "Deleted done";
    }
}
