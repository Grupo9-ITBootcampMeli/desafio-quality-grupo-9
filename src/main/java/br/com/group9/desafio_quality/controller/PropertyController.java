package br.com.group9.desafio_quality.controller;

import br.com.group9.desafio_quality.service.PropertyService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyController {

    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }
}
