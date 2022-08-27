package dev.dneversky.pioneer.gateway.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class UserToCreateDto {

    private String nickname;
    private String username;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserToCreateDto userToCreateDto = (UserToCreateDto) o;
        return Objects.equals(username, userToCreateDto.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
