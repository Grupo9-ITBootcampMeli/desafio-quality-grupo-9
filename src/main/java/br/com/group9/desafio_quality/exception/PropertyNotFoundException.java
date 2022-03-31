package br.com.group9.desafio_quality.exception;

public class PropertyNotFoundException extends RuntimeException{

    public PropertyNotFoundException(String mensagem) {
        super(mensagem);
    }
}
