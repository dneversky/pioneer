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
    public void getUsersByIds(UserServiceOuterClass.UserIds request, StreamObserver<UserServiceOuterClass.Users> responseObserver) {
        List<User> users = userService.getUsersByIds(request.getIdsList());
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
    public void changeRoles(UserServiceOuterClass.UserRole request, StreamObserver<UserServiceOuterClass.User> responseObserver) {
        User user = userService.changeRoles(request.getUserId(), request.getRoleNameList().stream().map(String::valueOf).collect(Collectors.toSet()));
        UserServiceOuterClass.User response = ProtoUserConverter.convert(user);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void changePassword(UserServiceOuterClass.UserPassword request, StreamObserver<UserServiceOuterClass.User> responseObserver) {
        User user = userService.changePassword(request.getUserId(), request.getOldPassword(), request.getNewPassword());
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
    public void changeTeam(UserServiceOuterClass.UserTeamIds request, StreamObserver<UserServiceOuterClass.User> responseObserver) {
        User user = userService.changeTeam(request.getUserId(), request.getTeamId());
        UserServiceOuterClass.User response = ProtoUserConverter.convert(user);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void changeSpecs(UserServiceOuterClass.UserSpecsIds request, StreamObserver<UserServiceOuterClass.User> responseObserver) {
        User user = userService.changeSpecs(request.getUserId(), request.getSpecsIdsList().stream().map(String::valueOf).collect(Collectors.toSet()));
        UserServiceOuterClass.User response = ProtoUserConverter.convert(user);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
