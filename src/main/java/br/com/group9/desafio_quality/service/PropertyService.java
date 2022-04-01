package br.com.group9.desafio_quality.service;

import br.com.group9.desafio_quality.dto.DistrictDTO;
import br.com.group9.desafio_quality.dto.PropertyDTO;
import br.com.group9.desafio_quality.dto.RoomDTO;
import br.com.group9.desafio_quality.exception.DistrictNotFoundException;
import br.com.group9.desafio_quality.exception.PropertyNotFoundException;
import br.com.group9.desafio_quality.repository.DistrictRepository;
import br.com.group9.desafio_quality.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {
    private PropertyRepository propertyRepository;
    private DistrictRepository districtRepository;
    private DistrictService districtService;

    public PropertyService(PropertyRepository propertyRepository, DistrictService districtService) {
        this.propertyRepository = propertyRepository;
        this.districtService = districtService;
    }


    private PropertyDTO findProperty(Long id) throws RuntimeException {
        PropertyDTO foundProperty = propertyRepository.get(id);
        if (foundProperty == null)
            throw new PropertyNotFoundException("Não há nenhuma propriedade com o ID ".concat(id.toString()).concat("."));
        return foundProperty;
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
            if (room.getRoomM2() > biggestRoom.getRoomM2())
                biggestRoom = room;
        }
        return biggestRoom;
    }

    public PropertyDTO registerProperty(PropertyDTO propertyDTO) {
        propertyDTO.setDistrict(districtService.findByName(propertyDTO.getDistrictName()));
        propertyDTO.setRooms(calculateRoomM2(propertyDTO.getRooms()));
        return propertyRepository.add(propertyDTO);
    }

    public Double getTotalM2ByPropertyId(Long id) {
        PropertyDTO foundProperty = findProperty(id);
        return calculateTotalM2(foundProperty);
    }

    public List<RoomDTO> getM2PerRoomByPropertyId(Long id) {
        PropertyDTO foundProperty = findProperty(id);
        return foundProperty.getRooms();
    }

    public RoomDTO getBiggestRoomByPropertyId(Long id) {
        PropertyDTO foundProperty = findProperty(id);
        return getBiggestRoom(foundProperty);
    }

    public BigDecimal getTotalValueByPropertyId(Long id) {
        PropertyDTO foundProperty = findProperty(id);
        Double totalM2 = calculateTotalM2(foundProperty);
        BigDecimal M2Value = foundProperty.getDistrict().getValueDistrictM2();
        return M2Value.multiply(BigDecimal.valueOf(totalM2));
    }
}
