package br.com.group9.desafio_quality.service;

import br.com.group9.desafio_quality.dto.DistrictDTO;
import br.com.group9.desafio_quality.repository.DistrictRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.math.BigDecimal;

@DisplayName("Testes do District Service")
public class DistrictServiceTests {

    /**
     * Métodos do DistrictService:
     * - public DistrictDTO create(DistrictDTO district) throws RuntimeException
     * Cenários:
     * - Criar com sucesso quando é informado um DistrictDTO válido;
     * - Não criar quando já existe um DistrictDTO com o mesmo getDistrictName();
     * - Não criar quando é informado null no parâmetro.
     */

    private DistrictService districtService;
    private DistrictRepository districtRepository;

    @BeforeEach
    public void setupService() {
        districtRepository = Mockito.mock(DistrictRepository.class);
        districtService = new DistrictService(districtRepository);
    }

    @Test
    @DisplayName("Teste do método create passando bairro, esperado sucesso")
    public void shouldCreateNewDistrictDTOWhenValidParam() {
        // Pré-condição / Setup
        DistrictDTO testDTO = new DistrictDTO("Test", BigDecimal.valueOf(10.0));

        // Retorno do repository quando o DistrictDTO não é achado
        Mockito.when(districtRepository.findByName(testDTO.getDistrictName()))
                .thenReturn(new DistrictDTO(testDTO.getDistrictName(), null));
        Mockito.when(districtRepository.create(testDTO))
                .thenReturn(testDTO);

        //Ação / Validação
        Assertions.assertDoesNotThrow(() -> {
            DistrictDTO result = districtService.create(testDTO);
            Assertions.assertEquals(testDTO, result);
        });

    }

    @Test
    @DisplayName("Teste do método create passando um bairro existente, esperado exception")
    public void shouldNotCreateWhenDistrictNameAlreadyExists() {
        // Pré-condição / Setup
        DistrictDTO testDTO = new DistrictDTO("Test", BigDecimal.valueOf(10.0));

        // Retorno do repository quando o DistrictDTO é achado
        Mockito.when(districtRepository.findByName(testDTO.getDistrictName()))
                .thenReturn(testDTO);

        //Ação / Validação
        RuntimeException givenException = Assertions.assertThrows(RuntimeException.class, () -> {
            DistrictDTO result = districtService.create(testDTO);
        });
        Assertions.assertEquals("O bairro já consta na base de dados", givenException.getMessage());
    }

    @Test
    @DisplayName("Teste do método create passando null, esperado exception")
    public void shouldNotCreateWhenNullParam() {
        // Pré-condição / Setup
        DistrictDTO testDTO = null;

        //Ação / Validação
        RuntimeException givenException = Assertions.assertThrows(NullPointerException.class, () -> {
            DistrictDTO result = districtService.create(testDTO);
        });
    }

    @Test
    @DisplayName("Teste do método findName com bairro válido, esperado sucesso")
    public void shouldFindDistrict() {
        // Setup
        DistrictDTO testDistric = new DistrictDTO("Centro", BigDecimal.valueOf(11.0));
        Mockito.when(districtRepository.findByName(testDistric.getDistrictName())).thenReturn(testDistric);

        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(testDistric, districtService.findByName(testDistric.getDistrictName()));
        });
    }

    @Test
    @DisplayName("Teste do método findName com bairro inválido, esperado exception")
    public void shouldNotFindDistrictAndThrowException() {
        DistrictDTO testDistrict = new DistrictDTO("Centro", BigDecimal.valueOf(11.0));
        Mockito.when(districtRepository.findByName(testDistrict.getDistrictName())).thenReturn(new DistrictDTO("Centro", null));

        RuntimeException returnException = Assertions.assertThrows(RuntimeException.class, () -> {
            districtService.findByName(testDistrict.getDistrictName());
        });
        Assertions.assertEquals("O bairro não consta na base de dados",returnException.getMessage());
    }
}
