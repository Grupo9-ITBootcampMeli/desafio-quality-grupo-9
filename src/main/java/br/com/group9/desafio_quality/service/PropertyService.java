package br.com.group9.desafio_quality.service;

import br.com.group9.desafio_quality.dto.PropertyDTO;
import br.com.group9.desafio_quality.dto.RoomDTO;
import br.com.group9.desafio_quality.repository.DistrictRepository;
import br.com.group9.desafio_quality.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    private PropertyRepository propertyRepository;
    private DistrictRepository districtRepository;

    public PropertyService(PropertyRepository propertyRepository, DistrictRepository districtRepository) {
        this.propertyRepository = propertyRepository;
        this.districtRepository = districtRepository;
    }

    private boolean validateDistrict(Long districtId) {
        throw new UnsupportedOperationException("Falta implementar.");
    }

    public Double calculateTotalM2(PropertyDTO propertyDTO) {
        throw new UnsupportedOperationException("Falta implementar.");
    }

    public Double calculateTotalValue(PropertyDTO propertyDTO) {
        throw new UnsupportedOperationException("Falta implementar.");
    }

    public PropertyDTO registerProperty(PropertyDTO propertyDTO) {
        throw new UnsupportedOperationException("Falta implementar.");
    }

    public Double getTotalM2ByPropertyId(Long id) {
        throw new UnsupportedOperationException("Falta implementar.");
    }

    public List<RoomDTO> getM2PerRoomByPropertyId(Long id) {
        throw new UnsupportedOperationException("Falta implementar.");
    }

    public RoomDTO getBiggerRoomByPropertyId(Long id) {
        throw new UnsupportedOperationException("Falta implementar.");
    }

    public Double getValueByPropertyId(Long id) {
        throw new UnsupportedOperationException("Falta implementar.");
    }
}
