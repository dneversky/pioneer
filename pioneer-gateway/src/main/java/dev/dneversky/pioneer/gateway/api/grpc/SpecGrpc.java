package dev.dneversky.pioneer.gateway.api.grpc;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.dneversky.gateway.SpecServiceGrpc;
import org.dneversky.gateway.SpecServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class SpecGrpc {

    @GrpcClient("cloud-grpc-server")
    private SpecServiceGrpc.SpecServiceBlockingStub serviceBlockingStub;

    public List<SpecServiceOuterClass.Spec> getProtoSpecsByIds(Collection<String> specsIds) {
        SpecServiceOuterClass.Specs response = serviceBlockingStub.getSpecsByIds(
                SpecServiceOuterClass.SpecsIds.newBuilder().addAllIds(specsIds).build());
        return response.getSpecsList();
    }
}
