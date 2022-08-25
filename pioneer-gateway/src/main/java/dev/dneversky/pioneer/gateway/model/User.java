package dev.dneversky.pioneer.gateway.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class User {

    private long id;
    private String nickname;

    @Nullable
    private Team team;
    private List<Spec> specs = new ArrayList<>();

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
                ", team=" + team +
                ", specs=" + specs +
                '}';
    }
}
