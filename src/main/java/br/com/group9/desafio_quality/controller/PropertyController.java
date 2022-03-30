package br.com.group9.desafio_quality.controller;

import br.com.group9.desafio_quality.dto.PropertyDTO;
import br.com.group9.desafio_quality.dto.RoomDTO;
import br.com.group9.desafio_quality.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public PropertyDTO insertProperty(@RequestBody @Valid PropertyDTO property) {
        PropertyDTO registered = propertyService.registerProperty(property);
        return registered;
    }

    @GetMapping("/property/{id}/totalM2")
    public Double getPropertyM2(@PathVariable Long id) {
        Double propertyM2 = propertyService.getTotalM2ByPropertyId(id);
        return propertyM2;
    }

    @GetMapping("/property/{id}/biggestRoom")
    public RoomDTO getBiggestRoom(@PathVariable Long id) {
        RoomDTO biggestRoom = propertyService.getBiggestRoomByPropertyId(id);
        return biggestRoom;
    }

    @GetMapping("/property/{id}/rooms")
    public List<RoomDTO> getRooms(@PathVariable Long id) {
        return propertyService.getM2PerRoomByPropertyId(id);
    }

    @GetMapping("/property/{id}/totalValue")
    public Double getTotalValue(@PathVariable Long id) {
        return propertyService.getTotalValueByPropertyId(id);
    }
}
