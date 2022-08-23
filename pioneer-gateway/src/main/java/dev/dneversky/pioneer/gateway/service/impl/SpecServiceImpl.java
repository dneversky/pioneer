package dev.dneversky.pioneer.gateway.service.impl;

import dev.dneversky.pioneer.gateway.api.grpc.impl.SpecGrpcImpl;
import dev.dneversky.pioneer.gateway.model.Spec;
import org.dneversky.gateway.SpecServiceOuterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecServiceImpl {

    private final SpecGrpcImpl specGrpcImpl;

    @Autowired
    public SpecServiceImpl(SpecGrpcImpl specGrpcImpl) {
        this.specGrpcImpl = specGrpcImpl;
    }

    public List<Spec> getSpecsByIds(Collection<String> ids) {
        List<SpecServiceOuterClass.Spec> specs = specGrpcImpl.getProtoSpecsByIds(ids);
        return specs.stream().map(Spec::new).collect(Collectors.toList());
    }

    public Spec createSpec(Spec spec) {
        SpecServiceOuterClass.Spec newProtoSpec = specGrpcImpl.createSpec(spec);
        return new Spec(newProtoSpec);
    }
}
