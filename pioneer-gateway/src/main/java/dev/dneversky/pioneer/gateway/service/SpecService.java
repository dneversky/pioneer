package dev.dneversky.pioneer.gateway.service;

import dev.dneversky.pioneer.gateway.model.SpecBody;
import dev.dneversky.pioneer.gateway.model.Spec;

import java.util.Collection;

public interface SpecService {
    Collection<Spec> getSpecs();
    Collection<Spec> getSpecsByIds(Collection<String> specsIds);
    Spec createSpec(SpecBody specBody);
    Spec updateSpec(Spec spec);
    void deleteSpec(String specId);
}
