package dev.dneversky.pioneer.gateway.api.grpc;

import dev.dneversky.pioneer.gateway.dto.SpecToCreateDto;
import dev.dneversky.pioneer.gateway.model.Spec;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.dneversky.gateway.SpecServiceGrpc;
import org.dneversky.gateway.SpecServiceOuterClass;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SpecGrpcImpl {

    @GrpcClient("spec-service")
    private SpecServiceGrpc.SpecServiceBlockingStub serviceBlockingStub;

    public SpecServiceOuterClass.Spec createSpec(SpecToCreateDto specToCreateDto) {
        return serviceBlockingStub.createSpec(SpecServiceOuterClass.NewSpec.newBuilder()
                        .setName(specToCreateDto.getName())
                        .setDescription(specToCreateDto.getDescription()).build());
    }

    public Collection<SpecServiceOuterClass.Spec> getSpecs() {
        SpecServiceOuterClass.Specs response = serviceBlockingStub
                .getSpecs(SpecServiceOuterClass.EmptySpec.newBuilder().build());
        return response.getSpecsList();
    }

    public Collection<SpecServiceOuterClass.Spec> getSpecsByIds(Collection<String> specsIds) {
        SpecServiceOuterClass.Specs response = serviceBlockingStub.getSpecsByIds(
                SpecServiceOuterClass.SpecsIds.newBuilder().addAllIds(specsIds).build());
        return response.getSpecsList();
    }

    public SpecServiceOuterClass.Spec updateSpec(Spec spec) {
        return serviceBlockingStub.updateSpec(SpecServiceOuterClass.Spec.newBuilder()
                .setId(spec.getId())
                .setName(spec.getName())
                .setDescription(spec.getDescription()).build());
    }

    public void deleteSpec(String specId) {
        serviceBlockingStub.deleteSpec(SpecServiceOuterClass.SpecId.newBuilder().setId(specId).build());
    }
}
