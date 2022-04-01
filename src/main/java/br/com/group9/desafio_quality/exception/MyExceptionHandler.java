package br.com.group9.desafio_quality.exception;

import br.com.group9.desafio_quality.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**Classe para o Handler da Aplicação Springboot que lida com as exceções lançadas a partir de uma requisição e fornece
 * um retorna customizado ao usuário.
 * @author Amanda Zara, André Veziane, Antônio Schappo, Guilherme Pereira, Joan Davi, Vinicius Clemente
 * @version 1.00
 * @since Release 01 da aplicação
 */
@RestControllerAdvice
public class MyExceptionHandler {

    /**
     * Método que trata exceções do tipo MethodArgumentNotValidException (BeanValidation) e retorna o erro de validação
     * @param e recebe a exceção lançada durante a execução da Aplicação
     * @return retorna a entidade de resposta com o status-code "400 - Bad Request"
     * e o ErrorDTO contendo os detalhes de erro de validação
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<List<ErrorDTO>> handleValidationExceptions(MethodArgumentNotValidException e) {
        List<ObjectError> erros = e.getBindingResult().getAllErrors();
        List<ErrorDTO> results = new ArrayList<ErrorDTO>();
        erros.forEach(x -> {
            ErrorDTO error = new ErrorDTO("campo inválido", x.getDefaultMessage());
            results.add(error);
        });
        return new ResponseEntity<>(results, HttpStatus.BAD_REQUEST);
    }

    /**
     * Método que trata exceções do tipo DistrictAlreadyCreatedException e retorna a mensagem de erro
     * @param e recebe a exceção lançada durante a execução da Aplicação
     * @return retorna a entidade de resposta com o status-code "400 - Bad Request"
     * e a mensagem de erro definida
     */
    @ExceptionHandler(DistrictAlreadyCreatedException.class)
    protected ResponseEntity<Object> handleDistrictAlreadyCreatedException(DistrictAlreadyCreatedException e) {
        String bodyOfResponse = e.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }

    /**
     * Método que trata exceções do tipo DistrictNotFoundException e retorna a mensagem de erro
     * @param e recebe a exceção lançada durante a execução da Aplicação
     * @return retorna a entidade de resposta com o status-code "400 - Bad Request"
     * e a mensagem de erro definida
     */
    @ExceptionHandler(DistrictNotFoundException.class)
    protected ResponseEntity<Object> handleDistrictNotFoundException(DistrictNotFoundException e) {
        String bodyOfResponse = e.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }

    /**
     * Método que trata exceções do tipo PropertyNotFoundException e retorna a mensagem de erro
     * @param e recebe a exceção lançada durante a execução da Aplicação
     * @return retorna a entidade de resposta com o status-code "400 - Bad Request"
     * e a mensagem de erro definida
     */
    @ExceptionHandler(PropertyNotFoundException.class)
    protected ResponseEntity<Object> handlePropertyNotFoundException(PropertyNotFoundException e) {
        String bodyOfResponse = e.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }
}
