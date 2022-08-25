package dev.dneversky.pioneer.gateway.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UpdateUserDto {
    private long id;
    private String nickname;
    private String teamId;
    private List<String> specsId = new ArrayList<>();
}
