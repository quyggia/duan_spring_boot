package com.nguyenngocquyet.duancuatoi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserRequest {
    @NotBlank(message = "FIRSTNAME_REQUIRED")
    String firstName;

    @NotBlank(message = "LASTNAME_REQUIRED")
    String lastName;

    @Past(message = "DOB_MUST_BE_IN_THE_PAST")
    LocalDate dob;
}
