package dev.dneversky.pioneer.gateway.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.dneversky.gateway.SpecServiceOuterClass;

import java.util.Objects;

@Data
@NoArgsConstructor
public class Spec {

    private String id;
    private String name;
    private String description;

    public Spec(SpecServiceOuterClass.Spec spec) {
        this.id = spec.getId();
        this.name = spec.getName();
        this.description = spec.getDescription();
    }

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
