package br.com.group9.desafio_quality.service;

import br.com.group9.desafio_quality.dto.DistrictDTO;
import br.com.group9.desafio_quality.dto.PropertyDTO;
import br.com.group9.desafio_quality.dto.RoomDTO;
import br.com.group9.desafio_quality.repository.DistrictRepository;
import br.com.group9.desafio_quality.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;
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
    private Double calculateTotalM2(PropertyDTO property) {
        Double totalM2 = 0.0;
        for (RoomDTO Room : property.getRooms()) {
            totalM2 += Room.getRoomM2();
        }
        return totalM2;
    }

    private RoomDTO getBiggestRoom(PropertyDTO property) {
        RoomDTO biggestRoom = new RoomDTO();
        biggestRoom.setRoomM2(Double.MIN_VALUE);
        for (RoomDTO room : property.getRooms()) {
            if(room.getRoomM2() > biggestRoom.getRoomM2())
                biggestRoom = room;
        }
        return biggestRoom;
    }

    public PropertyDTO registerProperty(PropertyDTO propertyDTO) {
        propertyDTO.setDistrict(findDistrict(propertyDTO.getDistrictId())) ;
        propertyDTO.setRooms(calculateRoomM2(propertyDTO.getRooms()));
        PropertyDTO registeredProp = propertyRepository.add(propertyDTO);
        return registeredProp;
    }

    public Double getTotalM2ByPropertyId(Long id) {
       PropertyDTO foundProperty = propertyRepository.get(id);
       return calculateTotalM2(foundProperty);
    }

    public List<RoomDTO> getM2PerRoomByPropertyId(Long id) {
        PropertyDTO foundProperty = propertyRepository.get(id);
        return foundProperty.getRooms();
    }

    public RoomDTO getBiggestRoomByPropertyId(Long id) {
        PropertyDTO foundProperty = propertyRepository.get(id);
        return getBiggestRoom(foundProperty);
    }

    public Double getTotalValueByPropertyId(Long id) {
        PropertyDTO foundProperty = propertyRepository.get(id);
        Double totalM2 = calculateTotalM2(foundProperty);
        Double M2Value = 10.0; // foundProperty.getDistrict().getValueDistrictM2();
        // TODO: 30/03/22 Implementar com o valor real do DistrictDTO
        return totalM2 * M2Value;
    }
}
