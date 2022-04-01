package br.com.group9.desafio_quality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**Classe de DTO que define a entidade de bairro, armazenada no DistrictRepository e utilizada como dependência da entidade PropertyDTO (propriedade).
 * @author Amanda Zara, André Veziane, Antônio Schappo, Guilherme Pereira, Joan Davi, Vinicius Clemente
 * @version 1.00
 * @since Release 01 da aplicação
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDTO {
    @NotBlank(message = "O bairro não pode estar vazio.")
    @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres ")
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "O nome do bairro deve começar com letra maiúscula")
    private String districtName;

    @NotNull(message = "O valor do metro não pode estar vazio.")
    @DecimalMin(message = "O valor do m² deve ser maior que 0",value = "0.0", inclusive = false)
    @DecimalMax(message = "O valor do m² deve ser menor ou igual a 13",value = "13")
    private BigDecimal valueDistrictM2;
}
