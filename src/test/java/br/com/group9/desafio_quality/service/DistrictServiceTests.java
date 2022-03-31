package br.com.group9.desafio_quality.service;

import br.com.group9.desafio_quality.dto.DistrictDTO;
import br.com.group9.desafio_quality.repository.DistrictRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class DistrictServiceTests {

    /**
     * Métodos do DistrictService:
     * - public DistrictDTO create(DistrictDTO district) throws RuntimeException
     *      Cenários:
     *          - Criar com sucesso quando é informado um DistrictDTO válido;
     *          - Não criar quando já existe um DistrictDTO com o mesmo getDistrictName();
     *          - Não criar quando é informado null no parâmetro.
     */

    private DistrictService districtService;
    private DistrictRepository districtRepository;

    @BeforeEach
    public void setupService() {
        districtRepository = Mockito.mock(DistrictRepository.class);
        districtService = new DistrictService(districtRepository);
    }

    @Test
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
    public void shouldNotCreateWhenNullParam() {
        // Pré-condição / Setup
        DistrictDTO testDTO = null;

        //Ação / Validação
        RuntimeException givenException = Assertions.assertThrows(NullPointerException.class, () -> {
            DistrictDTO result = districtService.create(testDTO);
        });
    }
}