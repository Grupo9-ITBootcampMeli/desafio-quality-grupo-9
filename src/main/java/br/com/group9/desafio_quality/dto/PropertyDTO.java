package br.com.group9.desafio_quality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDTO {

    private static Long counter = 1L;
    public static Long generateId(){
        return counter++;
    }

    private Long id;

    private String propName;
    private Long districtId;


    private DistrictDTO district;

    private List<RoomDTO> rooms;
}
