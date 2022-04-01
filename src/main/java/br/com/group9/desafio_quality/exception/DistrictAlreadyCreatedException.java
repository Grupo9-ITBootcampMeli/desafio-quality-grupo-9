package br.com.group9.desafio_quality.exception;

/**Classe de Exceção que é lançada quando há a tentativa de criação de um bairro já existente.
 * @author Amanda Zara, André Veziane, Antônio Schappo, Guilherme Pereira, Joan Davi, Vinicius Clemente
 * @version 1.00
 * @since Release 01 da aplicação
 */
public class DistrictAlreadyCreatedException extends RuntimeException{

    public DistrictAlreadyCreatedException(String mensagem) {
        super(mensagem);
    }
}
