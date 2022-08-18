package dev.dneversky.pioneer.specs.api;

import dev.dneversky.pioneer.specs.converter.SpecConverter;
import dev.dneversky.pioneer.specs.converter.SpecProtoConverter;
import dev.dneversky.pioneer.specs.entity.Spec;
import dev.dneversky.pioneer.specs.service.impl.DefaultSpecService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.dneversky.gateway.SpecServiceGrpc;
import org.dneversky.gateway.SpecServiceOuterClass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class SpecGrpcService extends SpecServiceGrpc.SpecServiceImplBase {

    private final DefaultSpecService specService;

    @Autowired
    public SpecGrpcService(DefaultSpecService specService) {
        this.specService = specService;
    }

    @Override
    public void getSpecsByIds(SpecServiceOuterClass.SpecsIds request, StreamObserver<SpecServiceOuterClass.Specs> responseObserver) {
        List<Spec> specs = specService.findSpecsByIds(request.getIdsList());
        SpecServiceOuterClass.Specs response = SpecServiceOuterClass.Specs.newBuilder()
                .addAllSpecs(specs.stream().map(SpecProtoConverter::convert).collect(Collectors.toList())).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createSpec(SpecServiceOuterClass.Spec request, StreamObserver<SpecServiceOuterClass.Spec> responseObserver) {
        Spec spec = specService.saveSpec(SpecConverter.convert(request));
        SpecServiceOuterClass.Spec response = SpecProtoConverter.convert(spec);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
