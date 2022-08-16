package dev.dneversky.pioneer.team.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Data
@Document
@NoArgsConstructor
public class Team {

    @Id
    private String id;

    private Set<Long> members = new HashSet<>();
    private Set<String> specs = new HashSet<>();

    // chat, users, tags
}
