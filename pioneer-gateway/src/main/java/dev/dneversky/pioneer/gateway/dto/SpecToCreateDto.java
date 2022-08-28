package dev.dneversky.pioneer.gateway.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@NoArgsConstructor
public class SpecToCreateDto {

    @NotNull(message = "Name must not be null.")
    @Size(min = 2, max = 16, message = "Name must be between 2 and 16 characters.")
    private String name;

    @Size(min = 2, max = 16, message = "Description must be between 2 and 256 characters.")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecToCreateDto specToCreateDto = (SpecToCreateDto) o;
        return Objects.equals(name, specToCreateDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
