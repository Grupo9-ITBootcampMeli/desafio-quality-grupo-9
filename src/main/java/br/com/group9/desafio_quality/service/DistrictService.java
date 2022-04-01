package br.com.group9.desafio_quality.service;

import br.com.group9.desafio_quality.dto.DistrictDTO;
import br.com.group9.desafio_quality.exception.DistrictAlreadyCreatedException;
import br.com.group9.desafio_quality.exception.DistrictNotFoundException;
import br.com.group9.desafio_quality.repository.DistrictRepository;
import org.springframework.stereotype.Service;

/**Classe para o Service da Aplicação Springboot que lida com as requisições para os recursos associados a entidade
 * de bairro, onde serão contidos, valores e métodos para o mesmo.
 * @author Amanda Zara, André Veziane, Antônio Schappo, Guilherme Pereira, Joan Davi, Vinicius Clemente
 * @version 1.00
 * @since Release 01 da aplicação
 */
@Service
public class DistrictService {

    private DistrictRepository repository;

    public DistrictService(DistrictRepository repository) {
        this.repository = repository;
    }

    /**
     * @param district recebe districtDTO que possui os campos districtName e valueDistrictM2.
     * @return Realiza o cadastro do bairro passando os parâmetros contidos em district.
     * @throws RuntimeException é lançado caso o retorno da função "findByName" seja null,
     *                          indicando que o bairro já está cadastrado na base de dados.
     */
    public DistrictDTO create(DistrictDTO district) throws RuntimeException {
        DistrictDTO getDistrict = repository.findByName(district.getDistrictName());
        if (getDistrict.getValueDistrictM2() != null) {
            throw new DistrictAlreadyCreatedException("O bairro já consta na base de dados");
        }
        return repository.create(district);
    }

    public DistrictDTO findByName(String districtName) throws RuntimeException {
        DistrictDTO checkDistrict = repository.findByName(districtName);
        if (checkDistrict.getValueDistrictM2() == null) {
            throw new DistrictNotFoundException("O bairro não consta na base de dados");
        }
        return checkDistrict;
    }
}
