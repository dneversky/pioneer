package dev.dneversky.pioneer.user.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private UserDetails details;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_spec", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "spec_id")
    private Set<String> specs = new HashSet<>();

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
