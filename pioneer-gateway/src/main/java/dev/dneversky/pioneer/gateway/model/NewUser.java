package dev.dneversky.pioneer.gateway.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewUser {
    private String nickname;
    private String username;
    private String password;
}
