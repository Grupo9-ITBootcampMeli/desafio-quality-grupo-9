package br.com.group9.desafio_quality.service;

import br.com.group9.desafio_quality.dto.DistrictDTO;
import br.com.group9.desafio_quality.dto.PropertyDTO;
import br.com.group9.desafio_quality.dto.RoomDTO;
import br.com.group9.desafio_quality.repository.PropertyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DisplayName("Testes do Property Service")
public class PropertyServiceTests {
    /**
     * Métodos do PropertyService:
     * - public Double getTotalM2ByPropertyId(Long id)
     * Cenários:
     * - Calcular a área total do imóvel.
     * - Soltar uma exceção caso o imóvel não exista.
     */
    private PropertyService propertyService;
    private PropertyRepository propertyRepository;
    private DistrictService districtService;

    @BeforeEach
    public void setupService() {
        propertyRepository = Mockito.mock(PropertyRepository.class);
        districtService = Mockito.mock(DistrictService.class);
        propertyService = new PropertyService(propertyRepository, districtService);
    }


    @Test
    @DisplayName("Teste do método getTotalM2ByPropertyId, esperado sucesso")
    public void shouldCalculatePropertyArea() {
        //Setup
        final Double areaToBeChecked = 13.0;

        DistrictDTO districtDTO = new DistrictDTO("Eldorado", BigDecimal.valueOf(10.0));

        ArrayList<RoomDTO> listOfRooms = new ArrayList<RoomDTO>();
        listOfRooms.add(new RoomDTO("Sala1", 2.0, 2.0, 4.0));
        listOfRooms.add(new RoomDTO("Sala2", 3.0, 3.0, 9.0));

        PropertyDTO propertyDTO = new PropertyDTO(1L, "Propriedade de Teste", "Teste bairro", districtDTO, listOfRooms);
        Mockito.when(propertyRepository.get(propertyDTO.getId())).thenReturn(propertyDTO);

        //Ação
        Assertions.assertDoesNotThrow(() -> {
            Double propertyArea = propertyService.getTotalM2ByPropertyId(propertyDTO.getId());
            Assertions.assertEquals(areaToBeChecked, propertyArea);
        });

    }

    @Test
    @DisplayName("Teste do método getTotalM2ByPropertyId, esperado exception")
    public void shouldThrowExceptionIfPropertyNotExists() {
        Mockito.when(propertyRepository.get(ArgumentMatchers.anyLong())).thenReturn(null);
        Long notRegisteredId = 1L;

        RuntimeException runtimeException1 = Assertions.assertThrows(RuntimeException.class, () -> {
            propertyService.getTotalM2ByPropertyId(notRegisteredId);
        });
        RuntimeException runtimeException2 = Assertions.assertThrows(RuntimeException.class, () -> {
            propertyService.getM2PerRoomByPropertyId(notRegisteredId);
        });
        RuntimeException runtimeException3 = Assertions.assertThrows(RuntimeException.class, () -> {
            propertyService.getBiggestRoomByPropertyId(notRegisteredId);
        });
        RuntimeException runtimeException4 = Assertions.assertThrows(RuntimeException.class, () -> {
            propertyService.getTotalValueByPropertyId(notRegisteredId);
        });
        String expectedMessage = "Não há nenhuma propriedade com o ID ".concat(notRegisteredId.toString()).concat(".");
        Assertions.assertEquals(expectedMessage, runtimeException1.getMessage());
        Assertions.assertEquals(expectedMessage, runtimeException2.getMessage());
        Assertions.assertEquals(expectedMessage, runtimeException3.getMessage());
        Assertions.assertEquals(expectedMessage, runtimeException4.getMessage());
    }

    @Test
    @DisplayName("Teste do método registerProperty se bairro é válido, esperado sucesso ")
    public void shouldCheckIfDistrictIsValidOnProperty() {
        //Setup
        DistrictDTO districtDTO = new DistrictDTO("Eldorado", BigDecimal.valueOf(10.0));
        Mockito.when(districtService.findByName("Eldorado")).thenReturn(districtDTO);

        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setDistrictName(districtDTO.getDistrictName());
        propertyDTO.setPropName("Propriedade teste");
        propertyDTO.setRooms(Arrays.asList(new RoomDTO("Sala", 3.0, 2.0, 6.0)));
        Mockito.when(propertyRepository.add(propertyDTO)).thenReturn(propertyDTO);

        //Ação
        Assertions.assertDoesNotThrow(() -> {
            PropertyDTO propertyDistrictToBeChecked = propertyService.registerProperty(propertyDTO);
            Mockito.verify(districtService).findByName(propertyDTO.getDistrictName());
            Assertions.assertEquals(districtDTO.getDistrictName(), propertyDistrictToBeChecked.getDistrict().getDistrictName());
        });
    }

    @Test
    @DisplayName("Teste do método registerProperty se os tamanhos dos quartos estão sendo calculados, esperado sucesso ")
    public void shouldCalculateRoomsM2OfProperty() {
        //Setup
        DistrictDTO districtDTO = new DistrictDTO("Eldorado", BigDecimal.valueOf(10.0));
        Mockito.when(districtService.findByName("Eldorado")).thenReturn(districtDTO);

        Double roomAreaToBeChecked = 6.0;

        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setDistrictName(districtDTO.getDistrictName());
        propertyDTO.setPropName("Propriedade teste");
        RoomDTO testRoom = new RoomDTO();

        testRoom.setRoomWidth(3.0);
        testRoom.setRoomLength(2.0);

        propertyDTO.setRooms(Arrays.asList(testRoom));
        Mockito.when(propertyRepository.add(propertyDTO)).thenReturn(propertyDTO);

        //Ação
        Assertions.assertDoesNotThrow(() -> {
            PropertyDTO propertyDistrictToBeChecked = propertyService.registerProperty(propertyDTO);
            Assertions.assertEquals(roomAreaToBeChecked, propertyDistrictToBeChecked.getRooms().get(0).getRoomM2());
        });
    }

    @Test
    @DisplayName("Teste do método getBiggestRoomByPropertyId se retorna o maior quarto em m², esperado sucesso ")
    public void shouldReturnPropertyBiggestRoom() {
        DistrictDTO districtDTO = new DistrictDTO("Eldorado", BigDecimal.valueOf(10.0));

        ArrayList<RoomDTO> listOfRooms = new ArrayList<>();
        listOfRooms.add(new RoomDTO("Sala1", 2.0, 2.0, 4.0));
        listOfRooms.add(new RoomDTO("Sala2", 3.0, 3.0, 9.0));

        PropertyDTO propertyDTO = new PropertyDTO(1L, "Propriedade de Teste", "Teste bairro", districtDTO, listOfRooms);
        Mockito.when(propertyRepository.get(propertyDTO.getId())).thenReturn(propertyDTO);

        //Ação
        Assertions.assertDoesNotThrow(() -> {
            RoomDTO biggestRoomToCheck = propertyService.getBiggestRoomByPropertyId(propertyDTO.getId());
            Assertions.assertEquals(listOfRooms.get(1).getRoomM2(), biggestRoomToCheck.getRoomM2());
        });
    }

    @Test
    @DisplayName("Teste do método getM2PerRoomByPropertyId se retorna o tamanho dos quartos em m², esperado sucesso ")
    public void shouldReturnM2PerRoomByPropertyId() {
        //Setup
        DistrictDTO districtDTO = new DistrictDTO("Eldorado", BigDecimal.valueOf(10.0));
        ArrayList<RoomDTO> listOfRooms = new ArrayList<>();
        listOfRooms.add(new RoomDTO("Sala1", 2.0, 2.0, 4.0));
        listOfRooms.add(new RoomDTO("Sala2", 3.0, 3.0, 9.0));

        PropertyDTO propertyDTO = new PropertyDTO(1L, "Propriedade de Teste", "Bairro teste", districtDTO, listOfRooms);
        Mockito.when(propertyRepository.get(propertyDTO.getId())).thenReturn(propertyDTO);

        //Ação
        Assertions.assertDoesNotThrow(() -> {
            List<RoomDTO> m2PerRoom = propertyService.getM2PerRoomByPropertyId(propertyDTO.getId());
            Assertions.assertEquals(listOfRooms.get(0).getRoomM2(), m2PerRoom.get(0).getRoomM2());
            Assertions.assertEquals(listOfRooms.get(1).getRoomM2(), m2PerRoom.get(1).getRoomM2());
        });

    }

    @Test
    @DisplayName("Teste do método getTotalValueByPropertyId se retorna o tamanho total da propriedade m², esperado sucesso ")
    public void shouldReturnTotalValuePerPropertyId() {
        //Setup
        DistrictDTO districtDTO = new DistrictDTO("Eldorado", BigDecimal.valueOf(10.0));
        ArrayList<RoomDTO> listOfRooms = new ArrayList<>();
        listOfRooms.add(new RoomDTO("Sala1", 2.0, 2.0, 4.0));
        listOfRooms.add(new RoomDTO("Sala2", 3.0, 3.0, 9.0));
        BigDecimal valueToBeChecked = BigDecimal.valueOf(130.00);

        PropertyDTO propertyDTO = new PropertyDTO(1L, "Propriedade de Teste", "Bairro teste", districtDTO, listOfRooms);
        Mockito.when(propertyRepository.get(propertyDTO.getId())).thenReturn(propertyDTO);

//      Ação
        Assertions.assertDoesNotThrow(() -> {
            BigDecimal m2Value = propertyService.getTotalValueByPropertyId(1L);
            Assertions.assertEquals(valueToBeChecked.compareTo(m2Value), 0);
        });
    }

}
