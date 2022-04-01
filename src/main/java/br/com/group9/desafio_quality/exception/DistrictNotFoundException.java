package br.com.group9.desafio_quality.exception;

/**Classe de Exceção que é lançada quando há a busca de um bairro que não existe.
 * @author Amanda Zara, André Veziane, Antônio Schappo, Guilherme Pereira, Joan Davi, Vinicius Clemente
 * @version 1.00
 * @since Release 01 da aplicação
 */
public class DistrictNotFoundException extends RuntimeException{

    public DistrictNotFoundException(String mensagem) {
        super(mensagem);
    }
}
