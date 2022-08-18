package dev.dneversky.pioneer.gateway.service;

import dev.dneversky.pioneer.gateway.api.grpc.SpecGrpc;
import dev.dneversky.pioneer.gateway.model.Spec;
import org.dneversky.gateway.SpecServiceOuterClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecService {

    private final SpecGrpc specGrpc;

    @Autowired
    public SpecService(SpecGrpc specGrpc) {
        this.specGrpc = specGrpc;
    }

    public Collection<Spec> getSpecsByIds(Collection<String> ids) {
        List<SpecServiceOuterClass.Spec> specs = specGrpc.getProtoSpecsByIds(ids);
        return specs.stream()
                .map(Spec::new)
                .collect(Collectors.toSet());
    }

    public Spec createSpec(Spec spec) {
        SpecServiceOuterClass.Spec newProtoSpec = specGrpc.createSpec(spec);
        return new Spec(newProtoSpec);
    }
}
