package br.com.group9.desafio_quality.exception;

public class DistrictNotFoundException extends RuntimeException{

    public DistrictNotFoundException(String mensagem) {
        super(mensagem);
    }
}
