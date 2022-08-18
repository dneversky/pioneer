package dev.dneversky.pioneer.team.api;

import dev.dneversky.pioneer.team.converter.ProtoTeamConverter;
import dev.dneversky.pioneer.team.converter.TeamConverter;
import dev.dneversky.pioneer.team.entity.Team;
import dev.dneversky.pioneer.team.service.impl.DefaultTeamService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.dneversky.gateway.TeamServiceGrpc;
import org.dneversky.gateway.TeamServiceOuterClass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class TeamGrpcService extends TeamServiceGrpc.TeamServiceImplBase {

    private final DefaultTeamService teamService;

    @Autowired
    public TeamGrpcService(DefaultTeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public void getTeams(org.dneversky.gateway.TeamServiceOuterClass.EmptyTeam request, StreamObserver<org.dneversky.gateway.TeamServiceOuterClass.Teams> responseObserver) {
        List<Team> teams = teamService.findTeams();
        TeamServiceOuterClass.Teams response = TeamServiceOuterClass.Teams.newBuilder()
                .addAllTeams(teams.stream().map(ProtoTeamConverter::convert).collect(Collectors.toList())).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createTeam(TeamServiceOuterClass.NewTeam request, StreamObserver<TeamServiceOuterClass.Team> responseObserver) {
        Team team = teamService.saveTeam(TeamConverter.convert(request));
        TeamServiceOuterClass.Team response = ProtoTeamConverter.convert(team);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
