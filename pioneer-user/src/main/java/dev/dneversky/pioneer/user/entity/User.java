package dev.dneversky.pioneer.user.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Document(collection = "users")
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @NotNull(message = "Nickname must not be null.")
    @Size(min = 2, max = 16, message = "Nickname must be between 2 and 16 characters.")
    private String nickname;

    private UserDetails details;
    private Set<String> specs = new HashSet<>();

    @DocumentReference(db = "teams", collection = "team")
    private String teamId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(details, user.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, details);
    }
}
