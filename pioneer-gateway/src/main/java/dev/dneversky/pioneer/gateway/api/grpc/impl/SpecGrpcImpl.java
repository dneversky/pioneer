package dev.dneversky.pioneer.gateway.api.grpc.impl;

import dev.dneversky.pioneer.gateway.api.grpc.SpecGrpc;
import dev.dneversky.pioneer.gateway.model.SpecBody;
import dev.dneversky.pioneer.gateway.model.Spec;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.dneversky.gateway.SpecServiceGrpc;
import org.dneversky.gateway.SpecServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SpecGrpcImpl implements SpecGrpc {

    @GrpcClient("spec-service")
    private SpecServiceGrpc.SpecServiceBlockingStub serviceBlockingStub;

    @Override
    public SpecServiceOuterClass.Spec createSpec(SpecBody specBody) {
        return serviceBlockingStub.createSpec(SpecServiceOuterClass.NewSpec.newBuilder()
                        .setName(specBody.getName())
                        .setDescription(specBody.getDescription()).build());
    }

    @Override
    public Collection<SpecServiceOuterClass.Spec> getSpecs() {
        SpecServiceOuterClass.Specs response = serviceBlockingStub
                .getSpecs(SpecServiceOuterClass.EmptySpec.newBuilder().build());
        return response.getSpecsList();
    }

    @Override
    public Collection<SpecServiceOuterClass.Spec> getSpecsByIds(Collection<String> specsIds) {
        SpecServiceOuterClass.Specs response = serviceBlockingStub.getSpecsByIds(
                SpecServiceOuterClass.SpecsIds.newBuilder().addAllIds(specsIds).build());
        return response.getSpecsList();
    }

    @Override
    public SpecServiceOuterClass.Spec updateSpec(Spec spec) {
        return serviceBlockingStub.updateSpec(SpecServiceOuterClass.Spec.newBuilder()
                .setId(spec.getId())
                .setName(spec.getName())
                .setDescription(spec.getDescription()).build());
    }

    @Override
    public void deleteSpec(String specId) {
        serviceBlockingStub.deleteSpec(SpecServiceOuterClass.SpecId.newBuilder().setId(specId).build());
    }
}
