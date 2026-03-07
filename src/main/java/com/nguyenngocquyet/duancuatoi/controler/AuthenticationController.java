package com.nguyenngocquyet.duancuatoi.controler;


import com.nguyenngocquyet.duancuatoi.dto.request.AuthenticationRequest;
import com.nguyenngocquyet.duancuatoi.dto.respon.ApiResponse;
import com.nguyenngocquyet.duancuatoi.dto.respon.AuthenticationResponse;
import com.nguyenngocquyet.duancuatoi.service.AuthenticationService;
import com.nguyenngocquyet.duancuatoi.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request)
    {
        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(AuthenticationResponse.builder()
                        .authenticated(authenticationService.authenticated(request))
                        .build());
        return apiResponse;
    }
}
