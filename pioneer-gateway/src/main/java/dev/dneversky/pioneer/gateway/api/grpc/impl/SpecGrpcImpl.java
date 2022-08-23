package dev.dneversky.pioneer.gateway.api.grpc.impl;

import dev.dneversky.pioneer.gateway.model.Spec;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.dneversky.gateway.SpecServiceGrpc;
import org.dneversky.gateway.SpecServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class SpecGrpcImpl {

    @GrpcClient("spec-service")
    private SpecServiceGrpc.SpecServiceBlockingStub serviceBlockingStub;

    public List<SpecServiceOuterClass.Spec> getProtoSpecsByIds(Collection<String> specsIds) {
        SpecServiceOuterClass.Specs response = serviceBlockingStub.getSpecsByIds(
                SpecServiceOuterClass.SpecsIds.newBuilder().addAllIds(specsIds).build());
        return response.getSpecsList();
    }

    public SpecServiceOuterClass.Spec createSpec(Spec spec) {
        return serviceBlockingStub.createSpec(SpecServiceOuterClass.Spec.newBuilder()
                        .setName(spec.getName())
                        .setDescription(spec.getDescription()).build());
    }
}
