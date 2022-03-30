package br.com.group9.desafio_quality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class ErrorDTO {

    private String tipo;
    private String mensagem;
}


