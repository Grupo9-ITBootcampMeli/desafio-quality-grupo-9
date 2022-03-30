package br.com.group9.desafio_quality.repository;

import br.com.group9.desafio_quality.dto.PropertyDTO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

// TODO: 30/03/22 Create JavaDoc comments
@Repository
public class PropertyRepository {
    private HashMap<Long, PropertyDTO> properties;

    public PropertyRepository() {
        properties = new HashMap<>();
    }

    public PropertyDTO add(PropertyDTO property) {
        property.setId(PropertyDTO.generateId());
        properties.put(property.getId(), property);
        return property;
    }

    public PropertyDTO get(Long id) {
        PropertyDTO foundProperty =  properties.get(id);
        // TODO: 30/03/22 Create custom exception for not founding property by ID.
        if(foundProperty == null) throw new RuntimeException();
        return foundProperty;
    }
}
