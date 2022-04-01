package br.com.group9.desafio_quality.util;

import br.com.group9.desafio_quality.dto.DistrictDTO;

import java.math.BigDecimal;

public class DistrictCreator {
    static String districtName = "District Test Name";
    static BigDecimal valueDistrictM2 = BigDecimal.valueOf(10.0);

    public static DistrictDTO createDistrictToBeSaved() {
        return new DistrictDTO(districtName, valueDistrictM2);
    }

    public static DistrictDTO createValidDistrict() {
        return new DistrictDTO(districtName, valueDistrictM2);
    }
}
