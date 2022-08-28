package dev.dneversky.pioneer.gateway.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@NoArgsConstructor
public class UserToCreateDto {

    @NotNull(message = "Nickname must not be null.")
    @Size(min = 2, max = 16, message = "Nickname must be between 2 and 16 characters.")
    private String nickname;

    @NotNull(message = "Username must not be null.")
    @Size(min = 6, max = 32, message = "Username must be between 6 and 32 characters.")
    @Email(message = "Username must be email.")
    private String username;

    @NotNull(message = "Password must not be null.")
    @Size(min = 4, max = 16, message = "Password must be between 4 and 16 characters.")
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
