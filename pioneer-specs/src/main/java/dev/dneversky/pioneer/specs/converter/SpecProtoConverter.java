package dev.dneversky.pioneer.specs.converter;

import dev.dneversky.pioneer.specs.entity.Spec;
import org.dneversky.gateway.SpecServiceOuterClass;

public class SpecProtoConverter {

    public static SpecServiceOuterClass.Spec convert(Spec spec) {
        return SpecServiceOuterClass.Spec.newBuilder()
                .setId(spec.getId())
                .setName(spec.getName())
                .setDescription(spec.getDescription())
                .build();
    }
}
