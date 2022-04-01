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

/**Classe para o Service da Aplicação Springboot que lida com as requisições para os recursos associados a entidade
 * de propriedade, onde serão contidos, valores e métodos para o mesmo.
 * @author Amanda Zara, André Veziane, Antônio Schappo, Guilherme Pereira, Joan Davi, Vinicius Clemente
 * @version 1.00
 * @since Release 01 da aplicação
 */
@Service
public class PropertyService {
    private PropertyRepository propertyRepository;
    private DistrictRepository districtRepository;
    private DistrictService districtService;

    public PropertyService(PropertyRepository propertyRepository, DistrictService districtService) {
        this.propertyRepository = propertyRepository;
        this.districtService = districtService;
    }


    /**
     * Método para busca de PropertyDTO a partir do ID
     * @param id recebe o ID do PropertyDTO a ser pesquisado
     * @return retorna o PropertyDTO encontrado
     * @throws RuntimeException é lançado caso não seja encontrado PropertyDTO
     * com o ID informado
     */
    private PropertyDTO findProperty(Long id) throws RuntimeException {
        PropertyDTO foundProperty = propertyRepository.get(id);
        if (foundProperty == null)
            throw new PropertyNotFoundException("Não há nenhuma propriedade com o ID ".concat(id.toString()).concat("."));
        return foundProperty;
    }

    /**
     * Método para calcular o m² de uma lista de cômodos
     * @param roomList recebe a lista de cômodos à ser calculado
     * @return retorna em List<RoomDTO> a lista de cômodos com o m² calculado no atributo "roomM2"
     */
    private List<RoomDTO> calculateRoomM2(List<RoomDTO> roomList) {
        List<RoomDTO> returnedRooms = roomList.stream().map((r) -> {
            r.setRoomM2(r.getRoomLength() * r.getRoomWidth());
            return r;
        }).collect(Collectors.toList());
        return returnedRooms;
    }

    /**
     * Método para calcular o total de m² de uma propriedade
     * @param property recebe o PropertyDTO à ser calculado
     * @return retorna em Double o total de m²
     */
    private Double calculateTotalM2(PropertyDTO property) {
        Double totalM2 = 0.0;
        for (RoomDTO Room : property.getRooms()) {
            totalM2 += Room.getRoomM2();
        }
        return totalM2;
    }

    /**
     * Método para indicar o maior cômodo de uma propriedade
     * @param property recebe o PropertyDTO contendo os cômodos
     * @return retorna a RoomDTO com o maior m²
     */
    private RoomDTO getBiggestRoom(PropertyDTO property) {
        RoomDTO biggestRoom = new RoomDTO();
        biggestRoom.setRoomM2(Double.MIN_VALUE);
        for (RoomDTO room : property.getRooms()) {
            if (room.getRoomM2() > biggestRoom.getRoomM2())
                biggestRoom = room;
        }
        return biggestRoom;
    }

    /**
     * Método para registrar uma nova propriedade
     * @param propertyDTO recebe o PropertyDTO parcial à ser registrado
     * @return retorna o PropertyDTO completo registrado
     */
    public PropertyDTO registerProperty(PropertyDTO propertyDTO) {
        propertyDTO.setDistrict(districtService.findByName(propertyDTO.getDistrictName()));
        propertyDTO.setRooms(calculateRoomM2(propertyDTO.getRooms()));
        return propertyRepository.add(propertyDTO);
    }

    /**
     * Método para buscar o total de m² de uma propriedade a partir de seu ID.
     * @param id recebe o ID da propriedade à ser calculada
     * @return retorna em Double o total de m² da propriedade
     */
    public Double getTotalM2ByPropertyId(Long id) {
        PropertyDTO foundProperty = findProperty(id);
        return calculateTotalM2(foundProperty);
    }

    /**
     * Método para buscar a relação de m² por cômodo de uma propriedade a partir de seu ID.
     * @param id recebe o ID da propriedade à ser calculada
     * @return retorna em List<RoomDTO> uma lista dos cômodos contendo o m² de cada
     */
    public List<RoomDTO> getM2PerRoomByPropertyId(Long id) {
        PropertyDTO foundProperty = findProperty(id);
        return foundProperty.getRooms();
    }

    /**
     * Método para buscar o maior cômodo de uma propriedade a partir de seu ID.
     * @param id recebe o ID da propriedade à ser calculada
     * @return retorna em RoomDTO o maior cômodo da propriedade
     */
    public RoomDTO getBiggestRoomByPropertyId(Long id) {
        PropertyDTO foundProperty = findProperty(id);
        return getBiggestRoom(foundProperty);
    }

    /**
     * Método para buscar o valor total de uma propriedade a partir de seu ID.
     * @param id recebe o ID da propriedade à ser calculada
     * @return retorna em BigDecimal o valor total da propriedade
     */
    public BigDecimal getTotalValueByPropertyId(Long id) {
        PropertyDTO foundProperty = findProperty(id);
        Double totalM2 = calculateTotalM2(foundProperty);
        BigDecimal M2Value = foundProperty.getDistrict().getValueDistrictM2();
        return M2Value.multiply(BigDecimal.valueOf(totalM2));
    }
}
