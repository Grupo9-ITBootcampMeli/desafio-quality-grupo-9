package br.com.group9.desafio_quality.controller;

import br.com.group9.desafio_quality.dto.PropertyDTO;
import br.com.group9.desafio_quality.dto.RoomDTO;
import br.com.group9.desafio_quality.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("/property")
    public ResponseEntity<PropertyDTO> insertProperty(@RequestBody @Valid PropertyDTO property) {
        PropertyDTO registered = propertyService.registerProperty(property);
        return new ResponseEntity<>(registered, HttpStatus.CREATED);
    }

    @GetMapping("/property/{id}/totalM2")
    public ResponseEntity<Double> getPropertyM2(@PathVariable Long id) {
        Double propertyM2 = propertyService.getTotalM2ByPropertyId(id);
        return new ResponseEntity<>(propertyM2, HttpStatus.OK);
    }

    @GetMapping("/property/{id}/biggestRoom")
    public ResponseEntity<RoomDTO> getBiggestRoom(@PathVariable Long id) {
        RoomDTO biggestRoom = propertyService.getBiggestRoomByPropertyId(id);
        return new ResponseEntity<>(biggestRoom, HttpStatus.OK);
    }

    @GetMapping("/property/{id}/rooms")
    public ResponseEntity<List<RoomDTO>> getRooms(@PathVariable Long id) {
        List<RoomDTO> rooms = propertyService.getM2PerRoomByPropertyId(id);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/property/{id}/totalValue")
    public ResponseEntity<Double> getTotalValue(@PathVariable Long id) {
        Double totalValue = propertyService.getTotalValueByPropertyId(id);
        return new ResponseEntity<>(totalValue, HttpStatus.OK);
    }
}
