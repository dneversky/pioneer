package dev.dneversky.pioneer.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @NotBlank(message = "Id must not be blank.")
    private long id;

    @NotNull(message = "Nickname must not be null.")
    @Size(min = 2, max = 16, message = "Nickname must be between 2 and 16 characters.")
    private String nickname;

    @Nullable
    private String teamId;

    @Size(max = 12, message = "Nickname must be between 2 and 16 characters.")
    private List<String> specsId = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", team=" + teamId +
                ", specs=" + specsId +
                '}';
    }
}
