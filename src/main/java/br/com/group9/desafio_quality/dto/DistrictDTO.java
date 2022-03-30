package br.com.group9.desafio_quality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDTO {
    @NotEmpty(message = "O bairro não pode estar vazio.")
    @NotNull(message = "O bairro não pode estar vazio.")
    @NotBlank(message = "O bairro não pode estar vazio.")
    @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres ")
    @Pattern(regexp = "^[A-Z][a-záàâãéèêíïóôõöúçñ]+[a-zA-Z]*$", message = "O nome do bairro deve começar com letra maiúscula")
    private String districtName;

    @NotNull(message = "O valor do metro não pode estar vazio.")
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "13")
    private BigDecimal valueDistrictM2;
}
