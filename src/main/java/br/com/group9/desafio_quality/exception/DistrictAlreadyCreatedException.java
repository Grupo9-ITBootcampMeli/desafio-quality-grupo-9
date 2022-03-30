package br.com.group9.desafio_quality.exception;

public class DistrictAlreadyCreatedException extends RuntimeException{

    public DistrictAlreadyCreatedException(String mensagem) {
        super(mensagem);
    }
}
