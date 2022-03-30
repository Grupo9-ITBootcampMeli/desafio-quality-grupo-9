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

    public DistrictDTO create (DistrictDTO district){
        districtMap.put(district.getDistrictName(), district.getValueDistrictM2());
        return district;
    }

    public DistrictDTO findByName(String district){
        DistrictDTO foundDistrict = new DistrictDTO(district, districtMap.get(district));
        return foundDistrict;
    }
}
