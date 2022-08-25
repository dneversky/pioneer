package dev.dneversky.pioneer.specs.service;

import dev.dneversky.pioneer.specs.entity.Spec;

import java.util.Collection;
import java.util.List;

public interface SpecService {
    List<Spec> findSpecsByIds(Collection<String> ids);
    List<Spec> findSpecs();
    Spec saveSpec(Spec spec);
    Spec updateSpec(Spec spec);
    void deleteSpec(String id);
}
