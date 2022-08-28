package dev.dneversky.pioneer.gateway.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class TeamToCreateDto {

    @NotNull(message = "Host's id must not be null.")
    private long memberId;

    @Size(min = 1, max = 8, message = "Specs must be between 1 and 16.")
    private Set<String> specsIds = new HashSet<>();
}
