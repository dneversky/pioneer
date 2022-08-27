package dev.dneversky.pioneer.gateway.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class SpecToCreateDto {

    private String name;
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
