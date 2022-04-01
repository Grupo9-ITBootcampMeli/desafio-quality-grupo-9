package br.com.group9.desafio_quality.util;

import br.com.group9.desafio_quality.dto.PropertyDTO;

public class PropertyCreator {
    static Long validId = 1L;
    static String propName = "Property Test Name";
    static String districtName = DistrictCreator.districtName;

    public static PropertyDTO createPropertyToBeSaved() {
        return new PropertyDTO(null, propName, districtName, null, RoomCreator.createRoomListToBeSaved());
    }

    public static PropertyDTO createValidProperty() {
        return new PropertyDTO(validId, propName, districtName, DistrictCreator.createValidDistrict(), RoomCreator.createValidRoomList());
    }
}
