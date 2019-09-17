
package me.potato.demopreschool2tier;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class SampleService {

    private final SampleRepository samples;

    public SampleService(SampleRepository samples) {
        this.samples = samples;
    }

    public Optional<Sample> get(Long id) {
        return samples.findById(id);
    }

    public Collection<Sample> getAll() {
        return samples.findAll();
    }

    public Optional<Sample> update(Sample newSample) {
        Sample save = samples.save(newSample);

        if (save != null)
            return Optional.of(save);

        return Optional.empty();
    }

    @Transactional
    public Boolean delete(Long id) {
        samples.deleteById(id);
        return samples.findById(id).isEmpty();
    }
}
