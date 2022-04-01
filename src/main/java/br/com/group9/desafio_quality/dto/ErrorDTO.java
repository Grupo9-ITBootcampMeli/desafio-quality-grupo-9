package br.com.group9.desafio_quality.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**Classe de DTO que define a entidade de erro, utilizada para formatar o retorno da BeanValidation (validação de campos) que falharem nas requisições.
 * @author Amanda Zara, André Veziane, Antônio Schappo, Guilherme Pereira, Joan Davi, Vinicius Clemente
 * @version 1.00
 * @since Release 01 da aplicação
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDTO {

    private String tipo;
    private String mensagem;
}


