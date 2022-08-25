package dev.dneversky.pioneer.gateway.api.v1;

import dev.dneversky.pioneer.gateway.model.*;
import dev.dneversky.pioneer.gateway.service.impl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/teams")
public class TeamController {

    private final TeamServiceImpl teamService;

    @Autowired
    public TeamController(TeamServiceImpl teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<List<Team>> getTeams() {
        return ResponseEntity.ok(teamService.getTeams());
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable String teamId) {
        return ResponseEntity.ok(teamService.getTeamById(teamId));
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody TeamBody teamBody) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamService.createTeam(teamBody));
    }

    @PutMapping
    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
        return ResponseEntity.ok(teamService.updateTeam(team));
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable String teamId) {
        teamService.deleteTeam(teamId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{teamId}/specs")
    public ResponseEntity<Team> changeSpecs(@PathVariable String teamId, @RequestParam List<String> specsIds) {
        return ResponseEntity.ok(teamService.changeSpecs(specsIds));
    }

    @PatchMapping("/{teamId}/members")
    public ResponseEntity<Team> changeMembers(@PathVariable String teamId, @RequestParam List<Long> membersIds) {
        return ResponseEntity.ok(teamService.changeMembers(membersIds));
    }
}
