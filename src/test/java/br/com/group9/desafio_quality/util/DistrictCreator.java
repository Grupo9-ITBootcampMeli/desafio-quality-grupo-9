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

    public static DistrictDTO createInvalidDistrictWithInitialCharacterLowercase() {
        return new DistrictDTO("district", valueDistrictM2);
    }

    public static DistrictDTO createInvalidDistrictWithMoreCharactersThanMaximal() {
        return new DistrictDTO("Lorem Ipsum is simply dummy text of the printing", valueDistrictM2);
    }

    public static DistrictDTO createInvalidDistrictWithEmptyName() {
        return new DistrictDTO("", valueDistrictM2);
    }

    public static DistrictDTO createInvalidDistrictWithNullName() {
        return new DistrictDTO(null, valueDistrictM2);
    }

    public static DistrictDTO createInvalidDistrictWithValueLessThanMinimal() {
        return new DistrictDTO(districtName, BigDecimal.valueOf(0.0));
    }

    public static DistrictDTO createInvalidDistrictWithValueMoreThanMaximal() {
        return new DistrictDTO(districtName, BigDecimal.valueOf(13.1));
    }

    public static DistrictDTO createInvalidDistrictWithNullValue() {
        return new DistrictDTO(districtName, null);
    }

    public static DistrictDTO createInvalidDistrictWithNullProperties() {
        return new DistrictDTO(null, null);
    }
}
