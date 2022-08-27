package dev.dneversky.pioneer.gateway.service;

import dev.dneversky.pioneer.gateway.dto.SpecToCreateDto;
import dev.dneversky.pioneer.gateway.model.Spec;

import java.util.Collection;

public interface SpecService {
    Collection<Spec> getSpecs();
    Collection<Spec> getSpecsByIds(Collection<String> specsIds);
    Spec createSpec(SpecToCreateDto specToCreateDto);
    Spec updateSpec(Spec spec);
    void deleteSpec(String specId);
}
