package dev.dneversky.pioneer.user.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Data
@Document(collection = "userDetails")
@NoArgsConstructor
public class UserDetails {

    @Id
    @NotNull(message = "Username must not be null.")
    @Size(min = 6, max = 32, message = "Username must be between 6 and 32 characters.")
    @Email(message = "Username must be email.")
    private String username;

    @NotNull(message = "Password must not be null.")
    @Size(min = 4, max = 16, message = "Password must be between 4 and 16 characters.")
    private String password;
    private Set<Role> roles;
    private boolean enabled;

    @DBRef
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails that = (UserDetails) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", enabled=" + enabled +
                '}';
    }
}
