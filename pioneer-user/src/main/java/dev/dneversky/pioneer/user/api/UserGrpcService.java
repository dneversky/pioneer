package dev.dneversky.pioneer.user.api;

import dev.dneversky.pioneer.user.converter.ProtoUserConverter;
import dev.dneversky.pioneer.user.converter.UserConverter;
import dev.dneversky.pioneer.user.entity.User;
import dev.dneversky.pioneer.user.service.impl.DefaultUserService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.dneversky.gateway.UserServiceGrpc;
import org.dneversky.gateway.UserServiceOuterClass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    private final DefaultUserService userService;

    @Autowired
    public UserGrpcService(DefaultUserService userService) {
        this.userService = userService;
    }

    @Override
    public void getUsers(UserServiceOuterClass.EmptyUser request, StreamObserver<UserServiceOuterClass.Users> responseObserver) {
        List<User> users = userService.getUsers();
        UserServiceOuterClass.Users response = UserServiceOuterClass.Users.newBuilder()
                .addAllUsers(users.stream().map(ProtoUserConverter::convert).collect(Collectors.toList())).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUserById(UserServiceOuterClass.UserId request, StreamObserver<UserServiceOuterClass.User> responseObserver) {
        User user = userService.getUserById(request.getId());
        UserServiceOuterClass.User response = ProtoUserConverter.convert(user);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createUser(UserServiceOuterClass.NewUser request, StreamObserver<UserServiceOuterClass.User> responseObserver) {
        User user = userService.saveUser(UserConverter.convert(request));
        UserServiceOuterClass.User response = ProtoUserConverter.convert(user);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateUser(UserServiceOuterClass.User request, StreamObserver<UserServiceOuterClass.User> responseObserver) {
        User user = userService.updateUser(UserConverter.convert(request));
        UserServiceOuterClass.User response = ProtoUserConverter.convert(user);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void patchRole(UserServiceOuterClass.UserRole request, StreamObserver<UserServiceOuterClass.User> responseObserver) {
        User user = userService.patchRole(request.getUserId(), request.getRoleNameList().stream().map(String::valueOf).collect(Collectors.toSet()));
        UserServiceOuterClass.User response = ProtoUserConverter.convert(user);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void patchPassword(UserServiceOuterClass.UserPassword request, StreamObserver<UserServiceOuterClass.User> responseObserver) {
        User user = userService.patchPassword(request.getUserId(), request.getOldPassword(), request.getNewPassword());
        UserServiceOuterClass.User response = ProtoUserConverter.convert(user);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteUser(UserServiceOuterClass.UserId request, StreamObserver<UserServiceOuterClass.User> responseObserver) {
        userService.deleteUser(request.getId());
        responseObserver.onCompleted();
    }

    @Override
    public void addTeam(UserServiceOuterClass.UserTeamIds request, StreamObserver<UserServiceOuterClass.User> responseObserver) {
        User user = userService.setTeam(request.getUserId(), request.getTeamId());
        UserServiceOuterClass.User response = ProtoUserConverter.convert(user);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void addSpecs(UserServiceOuterClass.UserSpecsIds request, StreamObserver<UserServiceOuterClass.User> responseObserver) {
        User user = userService.addSpecs(request.getUserId(), request.getSpecsIdsList().stream().map(String::valueOf).collect(Collectors.toSet()));
        UserServiceOuterClass.User response = ProtoUserConverter.convert(user);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void removeTeam(UserServiceOuterClass.UserTeamIds request, StreamObserver<UserServiceOuterClass.User> responseObserver) {
        User user = userService.removeTeam(request.getUserId(), request.getTeamId());
        UserServiceOuterClass.User response = ProtoUserConverter.convert(user);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void removeSpecs(UserServiceOuterClass.UserSpecsIds request, StreamObserver<UserServiceOuterClass.User> responseObserver) {
        User user = userService.removeSpecs(request.getUserId(), request.getSpecsIdsList().stream().map(String::valueOf).collect(Collectors.toSet()));
        UserServiceOuterClass.User response = ProtoUserConverter.convert(user);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
