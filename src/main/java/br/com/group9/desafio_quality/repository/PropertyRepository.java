package br.com.group9.desafio_quality.repository;

import br.com.group9.desafio_quality.dto.PropertyDTO;
import br.com.group9.desafio_quality.exception.PropertyNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**Classe que implementa a abstração na forma de Repositório de compras responsável por gerenciar as leituras e
 * inserções de PropertyDTO (propriedades).
 * @author Amanda Zara, André Veziane, Antônio Schappo, Guilherme Pereira, Joan Davi, Vinicius Clemente
 * @version 1.00
 * @since Release 01 da aplicação
 */
@Repository
public class PropertyRepository {
    private HashMap<Long, PropertyDTO> properties;

    public PropertyRepository() {
        properties = new HashMap<>();
    }

    /**
     * Método para adicionar uma nova propriedade no repositório
     * @param property recebe a PropertyDTO sem ID
     * @return retorna a PropertyDTO com novo ID
     */
    public PropertyDTO add(PropertyDTO property) {
        property.setId(PropertyDTO.generateId());
        properties.put(property.getId(), property);
        return property;
    }

    /**
     * Método para buscar uma propriedade a partir de seu ID
     * @param id recebe o ID da propriedade
     * @return retorna a PropertyDTO encontrada ou null caso não exista propriedade com o ID
     */
    public PropertyDTO get(Long id) {
        return properties.get(id);
    }
}
