package br.com.group9.desafio_quality.controller;

import br.com.group9.desafio_quality.dto.PropertyDTO;
import br.com.group9.desafio_quality.dto.RoomDTO;
import br.com.group9.desafio_quality.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**Classe para o Controller da Aplicação Springboot que lida com as requisições para os recursos associados a entidade
 * de PropertyDTO (propriedades), onde serão contidos, valores e métodos para a mesma.
 * @author Amanda Zara, André Veziane, Antônio Schappo, Guilherme Pereira, Joan Davi, Vinicius Clemente
 * @version 1.00
 * @since Release 01 da aplicação
 */
@RestController
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /**
     * Método POST para criar uma nova propriedade na aplicação
     * @param property recebe uma PropertyDTO válida no body da requisição
     * @return retorna a entidade de resposta com o status-code "201 - Created"
     * e a PropertyDTO criada com ID criado e m² dos cômodos calculados
     */
    @PostMapping("/property")
    public ResponseEntity<PropertyDTO> insertProperty(@RequestBody @Valid PropertyDTO property) {
        PropertyDTO registered = propertyService.registerProperty(property);
        return new ResponseEntity<>(registered, HttpStatus.CREATED);
    }

    /**
     * Método GET para buscar o total de m² de uma propriedade
     * @param id recebe o ID da propriedade à ser realizada a requisição
     * @return retorna a entidade de resposta com o status-code "200 - OK"
     * e o resultado em Double
     */
    @GetMapping("/property/{id}/totalM2")
    public ResponseEntity<Double> getPropertyM2(@PathVariable Long id) {
        Double propertyM2 = propertyService.getTotalM2ByPropertyId(id);
        return new ResponseEntity<>(propertyM2, HttpStatus.OK);
    }

    /**
     * Método GET para buscar o maior cômodo de uma propriedade
     * @param id recebe o ID da propriedade à ser realizada a requisição
     * @return retorna a entidade de resposta com o status-code "200 - OK"
     * e o resultado em RoomDTO
     */
    @GetMapping("/property/{id}/biggestRoom")
    public ResponseEntity<RoomDTO> getBiggestRoom(@PathVariable Long id) {
        RoomDTO biggestRoom = propertyService.getBiggestRoomByPropertyId(id);
        return new ResponseEntity<>(biggestRoom, HttpStatus.OK);
    }

    /**
     * Método GET para buscar todos os cômodos de uma propriedade
     * @param id recebe o ID da propriedade à ser realizada a requisição
     * @return retorna a entidade de resposta com o status-code "200 - OK"
     * e o resultado em List<RoomDTO>
     */
    @GetMapping("/property/{id}/rooms")
    public ResponseEntity<List<RoomDTO>> getRooms(@PathVariable Long id) {
        List<RoomDTO> rooms = propertyService.getM2PerRoomByPropertyId(id);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    /**
     * Método GET para buscar o valor total de uma propriedade
     * @param id recebe o ID da propriedade à ser realizada a requisição
     * @return retorna a entidade de resposta com o status-code "200 - OK"
     * e o resultado em BigDecimal
     */
    @GetMapping("/property/{id}/totalValue")
    public ResponseEntity<BigDecimal> getTotalValue(@PathVariable Long id) {
        BigDecimal totalValue = propertyService.getTotalValueByPropertyId(id);
        return new ResponseEntity<>(totalValue, HttpStatus.OK);
    }
}
