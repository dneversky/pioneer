package dev.dneversky.pioneer.gateway.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PasswordToChangeDto {

    @NotNull(message = "Old password must not be null.")
    @Size(min = 4, max = 16, message = "Old password must be between 4 and 16 characters.")
    private String oldPassword;

    @NotNull(message = "New password must not be null.")
    @Size(min = 4, max = 16, message = "New password must be between 4 and 16 characters.")
    private String newPassword;
}
