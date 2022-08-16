package dev.dneversky.pioneer.specs.service;

import dev.dneversky.pioneer.specs.entity.Spec;

import java.util.List;

public interface SpecService {
    List<Spec> findSpecs();
    Spec findSpecById(String id);
    Spec saveSpec(Spec spec);
    Spec updateSpec(Spec spec);
    void deleteSpec(String id);
}
