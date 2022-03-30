package br.com.group9.desafio_quality.service;

import br.com.group9.desafio_quality.dto.DistrictDTO;
import br.com.group9.desafio_quality.dto.PropertyDTO;
import br.com.group9.desafio_quality.dto.RoomDTO;
import br.com.group9.desafio_quality.repository.DistrictRepository;
import br.com.group9.desafio_quality.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    private PropertyRepository propertyRepository;
    private DistrictRepository districtRepository;

    public PropertyService(PropertyRepository propertyRepository, DistrictRepository districtRepository) {
        this.propertyRepository = propertyRepository;
        this.districtRepository = districtRepository;
    }

    private DistrictDTO findDistrict(Long districtId) {
        return null;
        // TODO: 30/03/22 Implementar lógica com DistrictRepository e exceptionHandler
    }

    private List<RoomDTO> calculateRoomM2(List<RoomDTO> roomList) {
        List<RoomDTO> returnedRooms = roomList.stream().map((r) -> {
            r.setRoomM2(r.getRoomLength() * r.getRoomWidth());
            return r;
        }).collect(Collectors.toList());
        return returnedRooms;
    }


    public PropertyDTO registerProperty(PropertyDTO propertyDTO) {
        propertyDTO.setDistrict(findDistrict(propertyDTO.getDistrictId())) ;
        propertyDTO.setRooms(calculateRoomM2(propertyDTO.getRooms()));
        PropertyDTO registeredProp = propertyRepository.add(propertyDTO);
        return registeredProp;
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

    public Double getTotalValueByPropertyId(Long id) {
        throw new UnsupportedOperationException("Falta implementar.");
    }
}
