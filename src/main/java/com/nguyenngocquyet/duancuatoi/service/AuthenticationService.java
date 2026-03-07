package com.nguyenngocquyet.duancuatoi.service;

import com.nguyenngocquyet.duancuatoi.dto.request.AuthenticationRequest;

import com.nguyenngocquyet.duancuatoi.exception.AppException;
import com.nguyenngocquyet.duancuatoi.exception.ErrorCode;
import com.nguyenngocquyet.duancuatoi.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    public boolean authenticated(AuthenticationRequest request)
    {

        var user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }
}
