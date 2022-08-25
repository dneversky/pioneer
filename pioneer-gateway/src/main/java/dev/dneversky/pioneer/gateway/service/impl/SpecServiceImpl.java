package dev.dneversky.pioneer.gateway.service.impl;

import dev.dneversky.pioneer.gateway.api.grpc.impl.SpecGrpcImpl;
import dev.dneversky.pioneer.gateway.model.SpecBody;
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
        return specGrpcImpl.getSpecs().stream().map(this::constructSpecWithProtoSpec).collect(Collectors.toList());
    }

    @Override
    public List<Spec> getSpecsByIds(Collection<String> ids) {
        return specGrpcImpl.getSpecsByIds(ids).stream().map(this::constructSpecWithProtoSpec).collect(Collectors.toList());
    }

    @Override
    public Spec createSpec(SpecBody specBody) {
        return constructSpecWithProtoSpec(specGrpcImpl.createSpec(specBody));
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
        Spec spec = new Spec();
        spec.setId(protoSpec.getId());
        spec.setName(protoSpec.getName());
        spec.setDescription(protoSpec.getDescription());
        return spec;
    }
}
