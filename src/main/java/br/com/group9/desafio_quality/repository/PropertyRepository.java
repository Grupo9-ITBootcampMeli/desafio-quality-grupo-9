package br.com.group9.desafio_quality.repository;

import br.com.group9.desafio_quality.dto.PropertyDTO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class PropertyRepository {
    private HashMap<Long, PropertyDTO> properties;

    public PropertyRepository() {
        properties = new HashMap<>();
    }

    public PropertyDTO add(PropertyDTO property) {
        throw new UnsupportedOperationException("Falta implementar.");
    }

    public PropertyDTO get(Long id) {
        throw new UnsupportedOperationException("Falta implementar.");
    }
}
