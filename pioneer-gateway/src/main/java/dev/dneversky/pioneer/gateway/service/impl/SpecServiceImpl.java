package dev.dneversky.pioneer.gateway.service.impl;

import dev.dneversky.pioneer.gateway.api.grpc.SpecGrpcImpl;
import dev.dneversky.pioneer.gateway.dto.SpecToCreateDto;
import dev.dneversky.pioneer.gateway.model.Spec;
import dev.dneversky.pioneer.gateway.service.SpecService;
import org.dneversky.gateway.SpecServiceOuterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecServiceImpl implements SpecService {

    private final SpecGrpcImpl specGrpcImpl;

    @Autowired
    public SpecServiceImpl(SpecGrpcImpl specGrpcImpl) {
        this.specGrpcImpl = specGrpcImpl;
    }

    @Override
    public List<Spec> getSpecs() {
        return specGrpcImpl.getSpecs().stream()
                .map(this::constructSpecWithProtoSpec)
                .collect(Collectors.toList());
    }

    @Override
    public List<Spec> getSpecsByIds(Collection<String> ids) {
        return specGrpcImpl.getSpecsByIds(ids)
                .stream()
                .map(this::constructSpecWithProtoSpec)
                .collect(Collectors.toList());
    }

    @Override
    public Spec createSpec(SpecToCreateDto specToCreateDto) {
        return constructSpecWithProtoSpec(specGrpcImpl.createSpec(specToCreateDto));
    }

    @Override
    public Spec updateSpec(Spec spec) {
        return constructSpecWithProtoSpec(specGrpcImpl.updateSpec(spec));
    }

    @Override
    public void deleteSpec(String specId) {
        specGrpcImpl.deleteSpec(specId);
    }

    private Spec constructSpecWithProtoSpec(SpecServiceOuterClass.Spec protoSpec) {
        return Spec.builder()
                .id(protoSpec.getId())
                .name(protoSpec.getName())
                .description(protoSpec.getDescription())
                .build();
    }
}
