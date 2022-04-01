package br.com.group9.desafio_quality.controller;

import br.com.group9.desafio_quality.dto.DistrictDTO;
import br.com.group9.desafio_quality.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**Classe para o Controller da Aplicação Springboot que lida com as requisições para os recursos associados a entidade
 * de DistrictDTO (bairros), onde serão contidos, valores e métodos para a mesma.
 * @author Amanda Zara, André Veziane, Antônio Schappo, Guilherme Pereira, Joan Davi, Vinicius Clemente
 * @version 1.00
 * @since Release 01 da aplicação
 */
@RestController
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    /**
     * Método POST para criar um novo bairro na aplicação
     * @param district recebe um DistrictDTO válido no body da requisição
     * @return retorna a entidade de resposta com o status-code "201 - Created"
     * e o DistrictDTO criado
     */
    @PostMapping("/district")
    public ResponseEntity<DistrictDTO> create(@Valid @RequestBody DistrictDTO district) {
            return new ResponseEntity<>(districtService.create(district), HttpStatus.CREATED);
    }
}
