package com.nguyenngocquyet.duancuatoi.configuration;


import com.nguyenngocquyet.duancuatoi.entity.User;
import com.nguyenngocquyet.duancuatoi.enums.Role;
import com.nguyenngocquyet.duancuatoi.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class ApplicationInitConfig {
    final PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository)
    {
        return args -> {
            if(userRepository.findUserByUsername("admin").isEmpty())
            {
                HashSet<String> roles = new HashSet<>();
                roles.add(Role.ADMIN.name());
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .build();
                userRepository.save(user);
                log.info("Your admin account has been initialized. Please update its information.");
            }

        };
    }
}
