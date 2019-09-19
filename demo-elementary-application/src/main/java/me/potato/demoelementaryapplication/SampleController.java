package me.potato.demoelementaryapplication;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/samples")
public class SampleController {
    private final SampleService sampleService;


    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        Collection<Sample> all = sampleService.getAll();
        if (all == null || all.size() <= 0)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        Optional<Sample> sample = sampleService.get(id);

        return sample
                .map(ResponseEntity.status(HttpStatus.OK)::body)
                .orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Sample newSample) {

        if (newSample == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        Optional<Sample> saved = sampleService.update(newSample);

        return saved
                .map(ResponseEntity.status(HttpStatus.OK)::body)
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Sample newSample) {
        if (id == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        if (newSample == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


        newSample.setId(id);
        Optional<Sample> update = sampleService.update(newSample);

        return update
                .map(ResponseEntity.status(HttpStatus.OK)::body)
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (id == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Boolean delete = sampleService.delete(id);
        if (delete)
            return ResponseEntity.status(HttpStatus.OK).build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
