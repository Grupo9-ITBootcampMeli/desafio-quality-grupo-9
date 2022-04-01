package br.com.group9.desafio_quality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

/**Classe de DTO que define a entidade de propriedade, armazenada no PropertyRepository.
 * @author Amanda Zara, André Veziane, Antônio Schappo, Guilherme Pereira, Joan Davi, Vinicius Clemente
 * @version 1.00
 * @since Release 01 da aplicação
 */
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

    @NotEmpty(message = "Por favor informar o nome do bairro.")
    private String districtName;


    private DistrictDTO district;


    @NotEmpty(message = "Favor informar um ou mais cômodos e suas características.")
    private List<@Valid RoomDTO> rooms;
}
