package dev.dneversky.pioneer.gateway.api.v1;

import dev.dneversky.pioneer.gateway.dto.SpecToCreateDto;
import dev.dneversky.pioneer.gateway.model.Spec;
import dev.dneversky.pioneer.gateway.service.impl.SpecServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/specs")
public class SpecController {

    private final SpecServiceImpl specService;

    @Autowired
    public SpecController(SpecServiceImpl specService) {
        this.specService = specService;
    }

    @GetMapping
    public ResponseEntity<List<Spec>> getSpecs() {
        return ResponseEntity.ok(specService.getSpecs());
    }

    @PostMapping
    public ResponseEntity<Spec> createSpec(@RequestBody SpecToCreateDto specToCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(specService.createSpec(specToCreateDto));
    }

    @PutMapping
    public ResponseEntity<Spec> updateSpec(@RequestBody Spec spec) {
        return ResponseEntity.ok(specService.updateSpec(spec));
    }

    @DeleteMapping("/{specId}")
    public ResponseEntity<?> deleteSpec(@PathVariable String specId) {
        specService.deleteSpec(specId);
        return ResponseEntity.noContent().build();
    }
}
