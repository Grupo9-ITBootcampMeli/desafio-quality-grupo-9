package br.com.group9.desafio_quality.exception;

/**Classe de Exceção que é lançada quando há a busca de uma propriedade que não existe.
 * @author Amanda Zara, André Veziane, Antônio Schappo, Guilherme Pereira, Joan Davi, Vinicius Clemente
 * @version 1.00
 * @since Release 01 da aplicação
 */
public class PropertyNotFoundException extends RuntimeException{

    public PropertyNotFoundException(String mensagem) {
        super(mensagem);
    }
}
