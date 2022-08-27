package dev.dneversky.pioneer.gateway.dto;

import lombok.Data;

@Data
public class PasswordToChangeDto {
    private String oldPassword;
    private String newPassword;
}
