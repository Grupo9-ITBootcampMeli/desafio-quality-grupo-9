package br.com.group9.desafio_quality.util;

import br.com.group9.desafio_quality.dto.DistrictDTO;
import br.com.group9.desafio_quality.dto.ErrorDTO;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class DistrictErrorCreator {
    static String type = "campo inválido";

    public static ErrorDTO createErrorWithEmptyNameMessage() {
        return new ErrorDTO(type, "O bairro não pode estar vazio.");
    }

    public static ErrorDTO createErrorWithExceedingNameLength() {
        return new ErrorDTO(type, "O comprimento do bairro não pode exceder 45 caracteres ");
    }

    public static ErrorDTO createErrorWithNameStartingInLowercaseCharacter() {
        return new ErrorDTO(type, "O nome do bairro deve começar com letra maiúscula");
    }

    public static ErrorDTO createErrorWithNullValue() {
        return new ErrorDTO(type, "O valor do metro não pode estar vazio.");
    }

    public static ErrorDTO createErrorWithLessThanMinimalValue() {
        return new ErrorDTO(type, "O valor do m² deve ser maior que 0");
    }

    public static ErrorDTO createErrorWithMoreThanMaximalValue() {
        return new ErrorDTO(type, "O valor do m² deve ser menor ou igual a 13");
    }
}
