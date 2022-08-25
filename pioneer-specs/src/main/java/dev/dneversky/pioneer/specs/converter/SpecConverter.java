package dev.dneversky.pioneer.specs.converter;

import dev.dneversky.pioneer.specs.entity.Spec;
import org.dneversky.gateway.SpecServiceOuterClass;

public class SpecConverter {

    public static Spec convert(SpecServiceOuterClass.Spec protoSpec) {
        Spec spec = new Spec();
        spec.setId(protoSpec.getId());
        spec.setName(protoSpec.getName());
        spec.setDescription(protoSpec.getDescription());
        return spec;
    }

    public static Spec convert(SpecServiceOuterClass.NewSpec protoSpec) {
        Spec spec = new Spec();
        spec.setName(protoSpec.getName());
        spec.setDescription(protoSpec.getDescription());
        return spec;
    }
}
