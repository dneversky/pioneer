package dev.dneversky.pioneer.team.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.HashSet;

@Data
@Document
@NoArgsConstructor
public class Team {

    @Id
    private String id;

    private Collection<Long> members = new HashSet<>();
    private Collection<String> specs = new HashSet<>();

    // chat, users, tags
}
