package br.com.group9.desafio_quality.repository;

import br.com.group9.desafio_quality.dto.DistrictDTO;
import br.com.group9.desafio_quality.repository.interfaces.IDistrictRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DistrictRepository implements IDistrictRepository<DistrictDTO> {
    Map<String, BigDecimal> districtMap = new HashMap<String, BigDecimal>();

    /**
     * @param district recebe districtDTO que possui os campos districtName e valueDistrictM2.
     * @return retorna o cadastro do bairro.
     */
    public DistrictDTO create (DistrictDTO district){
        districtMap.put(district.getDistrictName(), district.getValueDistrictM2());
        return district;
    }

    /**
     * @param district recebe uma String que possui o nome do bairro para realizar a busca.
     * @return realiza o resultado da busca.
     */
    public DistrictDTO findByName(String district){
        return new DistrictDTO(district, districtMap.get(district));
    }
}
