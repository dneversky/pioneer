package dev.dneversky.pioneer.gateway.api.v1;

import dev.dneversky.pioneer.gateway.dto.TeamToCreateDto;
import dev.dneversky.pioneer.gateway.model.Team;
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
    public ResponseEntity<Team> createTeam(@RequestBody TeamToCreateDto teamToCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamService.createTeam(teamToCreateDto));
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable String teamId) {
        teamService.deleteTeam(teamId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{teamId}/specs/add")
    public ResponseEntity<Team> addSpecs(@PathVariable String teamId, @RequestParam List<String> specsIds) {
        return ResponseEntity.ok(teamService.addSpecs(teamId, specsIds));
    }

    @PatchMapping("/{teamId}/members/add")
    public ResponseEntity<Team> addMembers(@PathVariable String teamId, @RequestParam List<Long> membersIds) {
        return ResponseEntity.ok(teamService.addMembers(teamId, membersIds));
    }

    @PatchMapping("/{teamId}/specs/remove")
    public ResponseEntity<Team> removeSpecs(@PathVariable String teamId, @RequestParam List<String> specsIds) {
        return ResponseEntity.ok(teamService.removeSpecs(teamId, specsIds));
    }

    @PatchMapping("/{teamId}/members/remove")
    public ResponseEntity<Team> removeMembers(@PathVariable String teamId, @RequestParam List<Long> membersIds) {
        return ResponseEntity.ok(teamService.removeMembers(teamId, membersIds));
    }
}
