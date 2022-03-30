package br.com.group9.desafio_quality.controller;

import br.com.group9.desafio_quality.dto.DistrictDTO;
import br.com.group9.desafio_quality.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @PostMapping("/district")
    public ResponseEntity<DistrictDTO> create(@Valid @RequestBody DistrictDTO district) {
            return new ResponseEntity<>(districtService.create(district), HttpStatus.CREATED);
    }
}
