package dev.dneversky.pioneer.specs.service.impl;

import dev.dneversky.pioneer.specs.entity.Spec;
import dev.dneversky.pioneer.specs.exception.SpecWithIdNotFoundException;
import dev.dneversky.pioneer.specs.repository.SpecRepository;
import dev.dneversky.pioneer.specs.service.SpecService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DefaultSpecService implements SpecService {

    private final SpecRepository specRepository;

    @Autowired
    public DefaultSpecService(SpecRepository specRepository) {
        this.specRepository = specRepository;
    }

    @Override
    public List<Spec> findSpecsByIds(Collection<String> ids) {
        List<Spec> specs = new ArrayList<>();
        for(String id : ids) {
            Optional<Spec> findSpec = specRepository.findById(id);
            if(findSpec.isEmpty()) {
                log.error("Couldn't find spec with id {} from findSpecsByIds() method in {}.", id, this.getClass().getName());
                continue;
            }
            specs.add(findSpec.get());
        }
        return specs;
    }

    @Override
    public List<Spec> findSpecs() {
        return specRepository.findAll();
    }

    @Override
    public Spec findSpecById(String id) {
        return specRepository.findById(id).orElseThrow(() -> new SpecWithIdNotFoundException(id));
    }

    @Override
    public Spec saveSpec(Spec spec) {
        return specRepository.save(spec);
    }

    @Override
    public Spec updateSpec(Spec spec) {
        specRepository.findById(spec.getId()).orElseThrow(() -> new SpecWithIdNotFoundException(spec.getId()));
        return specRepository.save(spec);
    }

    @Override
    public void deleteSpec(String id) {
        Spec findSpec = specRepository.findById(id).orElseThrow(() -> new SpecWithIdNotFoundException(id));
        specRepository.delete(findSpec);
    }
}
