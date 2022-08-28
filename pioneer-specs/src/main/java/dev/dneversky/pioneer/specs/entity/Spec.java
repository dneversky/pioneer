package dev.dneversky.pioneer.specs.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@Document
@NoArgsConstructor
public class Spec {

    @Id
    private String id;

    @NotNull(message = "Name must not be null.")
    @Size(min = 2, max = 16, message = "Name must be between 2 and 16 characters.")
    private String name;

    @Size(min = 2, max = 16, message = "Description must be between 2 and 256 characters.")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spec spec = (Spec) o;
        return Objects.equals(id, spec.id) && Objects.equals(name, spec.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Spec{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
