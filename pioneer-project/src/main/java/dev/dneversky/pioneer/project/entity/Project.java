package dev.dneversky.pioneer.project.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.util.*;

@Data
@Document
public class Project {

    @Id
    private String id;

    @Size(min = 1, max = 8, message = "Members must be between 1 and 8.")
    private Collection<Long> membersId = new HashSet<>();

    @Size(min = 1, max = 8, message = "Specs must be between 1 and 16.")
    private Collection<String> specsId = new HashSet<>();

    @Size(min = 64, max = 4096, message = "Description's length needs to be in range of 64 and 4096.")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
