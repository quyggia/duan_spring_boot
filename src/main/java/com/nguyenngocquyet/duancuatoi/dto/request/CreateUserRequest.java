package com.nguyenngocquyet.duancuatoi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {
    @NotBlank(message = "USERNAME_REQUIRED")
    @Size(min = 3, max = 50, message = "USERNAME_INVALID")
    String username;

    @NotBlank(message = "PASSWORD_REQUIRED")
    @Size(min = 6, message = "PASSWORD_TOO_SHORT")
    String password;

    @NotBlank(message = "FIRSTNAME_REQUIRED")
    String firstName;

    @NotBlank(message = "LASTNAME_REQUIRED")
    String lastName;

    @Past(message = "DOB_MUST_BE_IN_THE_PAST")
    LocalDate dob;


}
