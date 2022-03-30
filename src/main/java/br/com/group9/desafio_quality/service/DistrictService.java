package br.com.group9.desafio_quality.service;

import br.com.group9.desafio_quality.dto.DistrictDTO;
import br.com.group9.desafio_quality.exception.DistrictAlreadyCreatedException;
import br.com.group9.desafio_quality.repository.DistrictRepository;
import org.springframework.stereotype.Service;

@Service
public class DistrictService {

    private DistrictRepository repository;

    public DistrictService(DistrictRepository repository) {
        this.repository = repository;
    }

    public DistrictDTO create(DistrictDTO district) throws RuntimeException {
        DistrictDTO checkDistrict = repository.findByName(district.getDistrictName());

        if (checkDistrict.getValueDistrictM2() != null) {
            throw new DistrictAlreadyCreatedException("District already exists");
        }
        return repository.create(district);

    }

}
