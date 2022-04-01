package br.com.group9.desafio_quality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/**Classe de DTO que define a entidade de quarto, utilizada como dependência da entidade PropertyDTO (propriedade).
 * @author Amanda Zara, André Veziane, Antônio Schappo, Guilherme Pereira, Joan Davi, Vinicius Clemente
 * @version 1.00
 * @since Release 01 da aplicação
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {

    @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "Nome deve estar em letra maiúscula.")
    @Size(max = 30, message = "Favor escolher um nome menor que 30 caracteres.")
    @NotEmpty(message = "Favor informar o nome do cômodo.")
    private String roomName;

    @NotNull(message = "Favor informar a largura do cômodo.")
    @Max(value = 25, message = "Favor informar a metragem correta.")
    private Double roomWidth;

    @NotNull(message = "Favor informar o comprimento do cômodo.")
    @Max(value = 33, message = "Favor informar a metragem correta.")
    private Double roomLength;

    private Double roomM2;
}
