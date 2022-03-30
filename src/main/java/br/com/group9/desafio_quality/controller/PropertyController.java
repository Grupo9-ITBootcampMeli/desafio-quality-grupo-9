package br.com.group9.desafio_quality.controller;

import br.com.group9.desafio_quality.dto.PropertyDTO;
import br.com.group9.desafio_quality.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("/insert-property")
    public PropertyDTO insertProperty(@RequestBody @Valid PropertyDTO property) {
        PropertyDTO registered = propertyService.registerProperty(property);
        return registered;
    }
}
