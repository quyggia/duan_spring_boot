package com.nguyenngocquyet.duancuatoi.controler;


import com.nguyenngocquyet.duancuatoi.dto.request.AuthenticationRequest;
import com.nguyenngocquyet.duancuatoi.dto.request.IntrospectRequest;
import com.nguyenngocquyet.duancuatoi.dto.respon.ApiResponse;
import com.nguyenngocquyet.duancuatoi.dto.respon.AuthenticationResponse;
import com.nguyenngocquyet.duancuatoi.dto.respon.IntrospectResponse;
import com.nguyenngocquyet.duancuatoi.service.AuthenticationService;
import com.nguyenngocquyet.duancuatoi.service.UserService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

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
        var authenticationResponse = authenticationService.authenticated(request);

        apiResponse.setResult(AuthenticationResponse.builder()
                        .token(authenticationResponse.getToken())
                        .authenticated(true)
                        .build());
        return apiResponse;
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);

        return ApiResponse.<IntrospectResponse>builder()
                .code(1000)
                .result(result)
                .build();
    }

}
