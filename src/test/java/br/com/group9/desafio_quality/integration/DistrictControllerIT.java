package br.com.group9.desafio_quality.integration;

import br.com.group9.desafio_quality.dto.DistrictDTO;
import br.com.group9.desafio_quality.dto.ErrorDTO;
import br.com.group9.desafio_quality.util.DistrictCreator;
import br.com.group9.desafio_quality.util.DistrictErrorCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Testes de Integração do DistrictController")
public class DistrictControllerIT {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Teste de POST /district com dados válidos - esperado CREATED")
    public void shouldCreateNewDistrict() throws Exception {
        // Preparando massa de teste
        DistrictDTO districtDTO = DistrictCreator.createDistrictToBeSaved();
        String payload = new ObjectMapper().writeValueAsString(districtDTO);

        // Ação / simulação de requisição à rota
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/district")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
        ).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        // Desserialização do JSON para DTO
        String jsonReturned = result.getResponse().getContentAsString();
        DistrictDTO resultDTO = new ObjectMapper().readValue(jsonReturned, DistrictDTO.class);

        Assertions.assertEquals(districtDTO.getDistrictName(), resultDTO.getDistrictName());
        Assertions.assertEquals(districtDTO.getValueDistrictM2(), resultDTO.getValueDistrictM2());
        Assertions.assertEquals(districtDTO, resultDTO);
    }

    @Test
    @DisplayName("Teste de POST /district com letra inicial minúscula - esperado BAD REQUEST")
    public void shouldNotCreateWhenInitialCharIsNotUppercase() throws Exception {
        // Preparando massa de teste
        DistrictDTO districtDTO = DistrictCreator.createInvalidDistrictWithInitialCharacterLowercase();
        ErrorDTO expectedError = DistrictErrorCreator.createErrorWithNameStartingInLowercaseCharacter();
        String payload = new ObjectMapper().writeValueAsString(districtDTO);

        // Ação / simulação de requisição à rota
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/district")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

        // Desserialização do JSON para DTO
        String jsonReturned = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        List<ErrorDTO> errorList = new ObjectMapper().readValue(jsonReturned, new TypeReference<List<ErrorDTO>>(){});

        Assertions.assertEquals(1, errorList.size());
        Assertions.assertEquals(expectedError, errorList.get(0));
    }

    @Test
    @DisplayName("Teste de POST /district com nome vazio - esperado BAD REQUEST")
    public void shouldNotCreateWhenNameIsEmpty() throws Exception {
        // Preparando massa de teste
        DistrictDTO districtDTO = DistrictCreator.createInvalidDistrictWithEmptyName();
        ErrorDTO expectedError = DistrictErrorCreator.createErrorWithEmptyNameMessage();
        String payload = new ObjectMapper().writeValueAsString(districtDTO);

        // Ação / simulação de requisição à rota
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/district")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

        // Desserialização da String JSON para DTO
        String jsonReturned = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        List<ErrorDTO> errorList = new ObjectMapper().readValue(jsonReturned, new TypeReference<List<ErrorDTO>>(){});

        Assertions.assertTrue(errorList.size() > 1);
        Assertions.assertTrue(errorList.contains(expectedError));
    }

    @Test
    @DisplayName("Teste de POST /district com nome vazio - esperado BAD REQUEST")
    public void shouldNotCreateWhenNameIsNull() throws Exception {
        // Preparando massa de teste
        DistrictDTO districtDTO = DistrictCreator.createInvalidDistrictWithNullName();
        ErrorDTO expectedError = DistrictErrorCreator.createErrorWithEmptyNameMessage();
        String payload = new ObjectMapper().writeValueAsString(districtDTO);

        // Ação / simulação de requisição à rota
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/district")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

        // Desserialização do JSON para DTO
        String jsonReturned = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
        List<ErrorDTO> errorList = new ObjectMapper().readValue(jsonReturned, new TypeReference<List<ErrorDTO>>(){});

        Assertions.assertEquals(1, errorList.size());
        Assertions.assertEquals(expectedError, errorList.get(0));
    }
}
