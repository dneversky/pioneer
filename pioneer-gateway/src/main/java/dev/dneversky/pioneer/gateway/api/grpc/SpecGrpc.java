package dev.dneversky.pioneer.gateway.api.grpc;

import dev.dneversky.pioneer.gateway.model.SpecBody;
import dev.dneversky.pioneer.gateway.model.Spec;
import org.dneversky.gateway.SpecServiceOuterClass;

import java.util.Collection;

public interface SpecGrpc {
    Collection<SpecServiceOuterClass.Spec> getSpecs();
    Collection<SpecServiceOuterClass.Spec> getSpecsByIds(Collection<String> specsIds);
    SpecServiceOuterClass.Spec createSpec(SpecBody specBody);
    SpecServiceOuterClass.Spec updateSpec(Spec spec);
    void deleteSpec(String specId);
}
