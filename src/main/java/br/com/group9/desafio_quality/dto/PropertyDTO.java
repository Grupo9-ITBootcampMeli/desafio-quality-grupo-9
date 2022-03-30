package br.com.group9.desafio_quality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
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

    @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "Nome deve estar em letra maiúscula.")
    @Size(max = 30, message = "Favor escolher um nome menor que 30 caracteres.")
    @NotEmpty(message = "Favor informar o nome da propriedade.")
    private String propName;

    @NotNull(message = "Por favor informar o id do bairro.")
    private Long districtId;


    private DistrictDTO district;


    @NotEmpty(message = "Favor informar um ou mais cômodos e suas características.")
    private List<@Valid RoomDTO> rooms;
}
