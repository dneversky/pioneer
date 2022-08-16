package dev.dneversky.pioneer.specs.service.impl;

import dev.dneversky.pioneer.specs.entity.Spec;
import dev.dneversky.pioneer.specs.exception.SpecWithIdNotFoundException;
import dev.dneversky.pioneer.specs.repository.SpecRepository;
import dev.dneversky.pioneer.specs.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultSpecService implements SpecService {

    private final SpecRepository specRepository;

    @Autowired
    public DefaultSpecService(SpecRepository specRepository) {
        this.specRepository = specRepository;
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
        Optional<Spec> findSpec = specRepository.findById(spec.getId());
        if(findSpec.isEmpty()) {
            throw new SpecWithIdNotFoundException(spec.getId());
        }
        return specRepository.save(spec);
    }

    @Override
    public void deleteSpec(String id) {
        Optional<Spec> findSpec = specRepository.findById(id);
        if(findSpec.isEmpty()) {
            throw new SpecWithIdNotFoundException(id);
        }
        specRepository.delete(findSpec.get());
    }
}
