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
        List<Team> teams = teamService.getTeams();
        TeamServiceOuterClass.Teams response = TeamServiceOuterClass.Teams.newBuilder()
                .addAllTeams(teams.stream().map(ProtoTeamConverter::convert).collect(Collectors.toList())).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getTeamById(TeamServiceOuterClass.TeamId request, StreamObserver<TeamServiceOuterClass.Team> responseObserver) {
        Team team = teamService.getTeamById(request.getId());
        TeamServiceOuterClass.Team response = ProtoTeamConverter.convert(team);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createTeam(TeamServiceOuterClass.TeamBody request, StreamObserver<TeamServiceOuterClass.Team> responseObserver) {
        Team team = teamService.saveTeam(TeamConverter.convert(request));
        TeamServiceOuterClass.Team response = ProtoTeamConverter.convert(team);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateTeam(TeamServiceOuterClass.Team request, StreamObserver<TeamServiceOuterClass.Team> responseObserver) {
        Team team = teamService.updateTeam(TeamConverter.convert(request));
        TeamServiceOuterClass.Team response = ProtoTeamConverter.convert(team);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteTeam(TeamServiceOuterClass.TeamId request, StreamObserver<TeamServiceOuterClass.EmptyTeam> responseObserver) {
        teamService.deleteTeam(request.getId());
        TeamServiceOuterClass.EmptyTeam response = TeamServiceOuterClass.EmptyTeam.newBuilder().build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void changeSpecs(TeamServiceOuterClass.TeamSpecs request, StreamObserver<TeamServiceOuterClass.Team> responseObserver) {
        Team team = teamService.changeSpecs(request.getTeamId(), request.getSpecsIdsList());
        TeamServiceOuterClass.Team response = ProtoTeamConverter.convert(team);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void changeMembers(TeamServiceOuterClass.TeamMembers request, StreamObserver<TeamServiceOuterClass.Team> responseObserver) {
        Team team = teamService.changeMembers(request.getTeamId(), request.getMembersIdsList());
        TeamServiceOuterClass.Team response = ProtoTeamConverter.convert(team);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
